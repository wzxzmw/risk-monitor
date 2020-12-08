package com.beebank.monitor.service.serviceimp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.beebank.monitor.entity.*;
import com.beebank.monitor.monitorenum.InterfaceCodeEnum;
import com.beebank.monitor.monitorenum.ResultCodeEnum;
import com.beebank.monitor.service.QueryService;
import com.beebank.monitor.util.HttpClientUtil;
import com.beebank.monitor.util.JsonAndXmlUtils;
import com.beebank.monitor.util.ValidateDataUtil;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service("queryServiceImp")
public class QueryServiceImp implements QueryService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Logger monitorLog = LoggerFactory.getLogger("monitorLog");

    @Autowired

    private InterfaceRecordServiceImpl interfaceRecordService;

    @Autowired
    private OnOffServiceImpl onOffService;

    @Autowired
    private ChannelInfoInit channelInfoInit;

    @Autowired
    private CompanyPeopleInfoServiceImpl companyPeopleInfoService;

    @Autowired
    private BcsCusvd4ServiceImpl bcsCusvd4Service;
    @Autowired
    private BcsCumiServiceImpl bcsCumiService;
    @Autowired
    private BcsCtaxServiceImpl bcsCtaxService;
    @Autowired
    private BcsCbnfServiceImpl bcsCbnfService;

    //多次使用的提升为静态变量，方便以后修改
    // 校验流水号字段
    private static final String TRXREFKey = "TRXREF";
    // 申请流水号字段
    private static final String OWNREFKey = "OWNREF";
    @Value("${blackList.url}")
    private String url;
    @Value("${blackList.timeOut}")
    private int timeOut;
    @Value("${blackList.port}")
    private int port;
    @Value("${blackList.charset}")
    private String charset;

    @Value("${company.person.own}")
    private String own;



    //总开关名称
    private static final String DEFAULT_ALL = "ALL";
    private static String URL;


    /**
     * 暂时按一个来做
     * @param requestJson
     * @return
     */
    @Override
    public JSONObject queryBlackJson(JSONObject requestJson) {
        String requestJsonStr = requestJson.toJSONString();
        logger.info("收到请求报文：{} , 当前时间： {}",requestJsonStr,System.currentTimeMillis());
        JSONObject returnJson ;
        JSONObject dataJson = new JSONObject();
        long start = System.currentTimeMillis();
        String statusKey = "STATUS";
        try {
            logger.info("|OWN:{}|",requestJson.getString("OWN"));
            //校验参数是否非法,非法则直接返回
            String validateResult= ValidateDataUtil.validateCheckRisk(requestJson);
            if(validateResult != null) {
                returnJson = new JSONObject();
                returnJson.put("CODE",ResultCodeEnum.VALITDATE_ERROR.getKey());
                returnJson.put("MESSAGE",validateResult);
                returnJson.put("DATA",dataJson);
//                return returnJson;
            }else {
                //校验开关，如果已关闭，则直接返回
                returnJson = validateOnOff(requestJson.getString("OWN") );
                if(returnJson != null) {
                    returnJson.put("DATA",dataJson);
//                    return returnJson;
                }else {
                    //校验全部通过，则继续组装数据并调用后端服务
                    returnJson = this.callBlack(requestJson);
                }
            }




        } catch (Exception e) {
            returnJson = new JSONObject();
            returnJson.put("CODE",ResultCodeEnum.SYSTEM_ERROR.getKey());
            returnJson.put("MESSAGE",ResultCodeEnum.SYSTEM_ERROR.getValue());
            returnJson.put("DATA",dataJson);
            logger.error("发生异常！",e);
        }


        String status = "";
        dataJson = returnJson.getJSONObject("DATA");
        if(dataJson != null && dataJson.containsKey(statusKey)) {
            status = dataJson.getString(statusKey);
            dataJson.remove(statusKey);
        }

        long times = (System.currentTimeMillis()-start);
        // 启用线程池，保存记录
        interfaceRecordService.saveRecord(requestJson.getString("OWNREF"),
                requestJson.getString("OWN"),
                requestJson.getString("BRANCHID"),
                requestJson.toJSONString(),
                returnJson.toJSONString(),
                status,
                dataJson.getString("RLEVEL"),
                (int)times);

        monitorLog.info("{}|+|{}|+|{}|+|{}|+| 返回报文为：{}",
                requestJson.getString("OWN"),
                returnJson.getString("CODE"),
                requestJson.getString("OWNREF"),
                times,
                returnJson

        );
        return returnJson;
    }

    @Override
    public String queryBlackSoap(String soapXml) {
        logger.info("收到soap格式报文： {}",soapXml);
        JSONObject returnJson;
        try {
            String soapString = JsonAndXmlUtils.xml2json(soapXml);
            JSONObject requestBody = JSONObject.parseObject(soapString).
                    getJSONObject("soapenv:Envelope").
                    getJSONObject("soapenv:Body").
                    getJSONObject("cqr:MCR1002"). //接口编号，配合金服固定格式
                    getJSONObject("RequestBody");

            // 如果是 JSONObject 而不是 JSONArray,则封装成 JSONArray,以便于统一格式
            if(requestBody.getJSONObject("MSGS").get("MSG") instanceof JSONObject) {
                JSONObject msgJson = requestBody.getJSONObject("MSGS");
                JSONArray array = new JSONArray();
                array.add(requestBody.getJSONObject("MSGS").getJSONObject("MSG"));
                msgJson.put("MSG",array);
                requestBody.put("MSGS",msgJson);
            }
            // 本质还是调用json格式的报文进行组装和调用
            returnJson = this.queryBlackJson(requestBody);
        }catch (Exception e) {
            returnJson = new JSONObject();
            returnJson.put("CODE", ResultCodeEnum.SYSTEM_ERROR.getKey());
            returnJson.put("MESSAGE", ResultCodeEnum.SYSTEM_ERROR.getValue());
            //错误则直接放空的json串
            returnJson.put("DATA", new JSONObject());
            logger.error("soap格式转换或获取RequestBody发生异常！", e);

        }
        // 组装返回的soap报文
        String returnSoap = SoapTemplateInit.returnSoap;
        returnSoap = returnSoap.replace("CODE_re",returnJson.getString("CODE"))
            .replace("MESSAGE_re",returnJson.getString("MESSAGE"))
            .replace("OWNREF_re",returnJson.getJSONObject("DATA").containsKey("OWNREF") ? returnJson.getJSONObject("DATA").getString("OWNREF") : "")
            .replace("RLEVEL_re",returnJson.getJSONObject("DATA").containsKey("RLEVEL") ? returnJson.getJSONObject("DATA").getString("RLEVEL") : "");
        return returnSoap;
    }

    public JSONObject callBlack(JSONObject requestJson) {
        logger.info("开始组装数据， 申请流水号：{} ",requestJson.getString(OWNREFKey));
        SoapXML soapXml = this.organizeJSONToXml(requestJson);
        logger.info("组装数据完成， 申请流水号： {}",requestJson.getString(OWNREFKey));
        URL = URL == null ? url+":"+port+"/"+InterfaceCodeEnum.DATA_QUERY.getESBcode() : URL;
        return this.run(URL, soapXml, timeOut,InterfaceCodeEnum.DATA_QUERY.getESBcode(),charset);
    }

    /**
     * 构建请求报文
     * @param fatherJson
     * @param
     * @return
     */
    private SoapXML organizeJSONToXml(JSONObject fatherJson) {
        //Person实体的编号
        int personNum = 1;
        String OWN = fatherJson.getString("OWN");
//        ChannelPropertiesEnum enumByChannelName = ChannelPropertiesEnum.getEnumByOWN(OWN);
        ChannelInfo channelInfoByOwn = channelInfoInit.getChannelInfoByOwn(OWN);
        SoapXML soapXML = new SoapXML();

        //组装header中的信息
        JSONObject headerJson = new JSONObject();

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = sdf.format(date);
        headerJson.put("ReqDt",format.substring(0,8));
        headerJson.put("ReqTm",format.substring(8));

        JSONObject bodyJson = new JSONObject();
        bodyJson.put("ICODE", InterfaceCodeEnum.DATA_QUERY.getBlackCode());
        bodyJson.put("OWN",OWN);
        bodyJson.put("ORGID",channelInfoByOwn.getOrgid());
        bodyJson.put("USERID",channelInfoByOwn.getUserid());
        bodyJson.put("BRANCHID",fatherJson.getString("BRANCHID"));
        bodyJson.put(OWNREFKey,fatherJson.getString(OWNREFKey));

        if(fatherJson.containsKey(TRXREFKey) && !StringUtils.isEmpty(fatherJson.getString(TRXREFKey))) {
            bodyJson.put(TRXREFKey,fatherJson.getString(TRXREFKey));
        }else {
            bodyJson.put(TRXREFKey,fatherJson.getString(OWNREFKey));
        }

        bodyJson.put("OWNTASKDESC",fatherJson.getString("OWNTASKDESC"));
        bodyJson.put("BESREF",fatherJson.getString("BESREF"));
        bodyJson.put("AUTHFLAG",fatherJson.getString("AUTHFLAG"));
        bodyJson.put("AUTHORG",fatherJson.getString("AUTHORG"));
        bodyJson.put("SMSFLAG","");
        bodyJson.put("EMAILFLAG","");
//        bodyJson.put("MSGS",fatherJson.getJSONArray("MSGS"));
        JSONObject msgsJson = fatherJson.getJSONObject("MSGS");
        JSONArray msgArray = msgsJson.getJSONArray("MSG");
        //设置调用方对应黑名单系统固定值
        for (int i = 0; i < msgArray.size(); i++) {
            JSONObject currentJson = msgArray.getJSONObject(i);
            currentJson.put("TARGET",channelInfoByOwn.getTarget());
            currentJson.put("CONFIG",channelInfoByOwn.getConfig());
            if(currentJson.getString("TYPE").equalsIgnoreCase("Person")) {
                personNum++;
            }
        }
        //如果是企业网银的请求，从数据库查询收益人信息、财务负责人信息、法人信息等并组装进数组
        if(OWN.equals(own)) {
            String custNo = fatherJson.getString("CUSTNO");
            //核心商户号为空则不校验
            if(!StringUtils.isEmpty(custNo)) {
                List<CompanyPeopleInfo> companyPeopleInfos = companyPeopleInfoService.listByObj(new CompanyPeopleInfo(custNo));
                if(companyPeopleInfos != null && companyPeopleInfos.size() > 0) {
                    msgArray.addAll(JsonAndXmlUtils.peopleInfosToMSGArray(personNum, companyPeopleInfos,channelInfoByOwn.getTarget(),channelInfoByOwn.getConfig()));
                }else {
                    logger.info("总表未查询到企业网银相关人员信息，采用取子表查询的方案，本次查询流水号为： {}",fatherJson.getString("OWNREF"));
                    msgArray.addAll(this.queryPersonPlanB(fatherJson.getString("CUSTNO"),personNum,channelInfoByOwn.getTarget(),channelInfoByOwn.getConfig()));

                }
            }

        }

        msgsJson.put("MSG",msgArray);
        bodyJson.put("MSGS",msgsJson);
        soapXML.setRequestHeader(headerJson);
        soapXML.setRequestBody(bodyJson);
        return soapXML;
    }

    /**
     * 发送请求并处理返回报文
     * @param url
     * @param soapXML
     * @param timeOut
     * @param interfaceCode
     * @param charset
     * @return 返回处理结果
     */
    public JSONObject run(String url, SoapXML soapXML, int timeOut, String interfaceCode,String charset) {
        JSONObject returnJson = new JSONObject();
        JSONObject dataJson = new JSONObject();
        String OWNREF = soapXML.getRequestBody().getString(OWNREFKey);

        String code;
        String message;
        String isRlevel ;
        String errorMsg = "";
        String status ;
        dataJson.put(OWNREFKey,OWNREF);
        logger.info("开始转换数据为soap协议,样式申请流水号： {}",OWNREF);
        String requestMessage = JsonAndXmlUtils.soapBeanToXmlString(soapXML);

        logger.info("转换后的请求报文为：{} ",requestMessage);
        try {
            String returnMessage = HttpClientUtil.sendPostRequest(url, requestMessage, timeOut,charset);
//            String returnMessage = XMLTepmlate.RETURN_XML_STR;

            if (returnMessage == null) {
                errorMsg = " 没有获取到返回信息！";
                code = ResultCodeEnum.NOT_ASSERT.getKey();
                message = ResultCodeEnum.NOT_ASSERT.getValue()+errorMsg;

            }else {
                //解析xml格式返回数据
                String responseJson = JsonAndXmlUtils.xml2json(returnMessage);
//                String responseJson = XMLTepmlate.RETURN_JSON_STR;

                //判断与ESB交互是否成功 Fault-Detail-TxnStat的值为：SUCCESS 则代表调用成功
                JSONObject faultJson = JSON.parseObject(responseJson).getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("ns:" + interfaceCode).getJSONObject("Fault");
                if(faultJson != null && faultJson.getJSONObject("Detail").getString("TxnStat").equalsIgnoreCase("SUCCESS")) {
                    //获取响应体内容
                    JSONObject bodyJson = JSON.parseObject(responseJson).getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("ns:" + interfaceCode).getJSONObject("ResponseBody");
                    status = bodyJson.getString("STATUS");
                    isRlevel = bodyJson.getString("RLEVEL");
                    //检索状态为成功是，继续进行判断
                    if("1".equals(status) && "H".equalsIgnoreCase(isRlevel)) {
                        //如果命中，则继续判断相似度的值
                        String rpercent = bodyJson.getString("RPERCENT");
                        logger.info("申请流水号： {}, 命中黑名单，相似度： {}, 黑名单id为： {}", OWNREF,rpercent,bodyJson.getString("RBLID"));
                    }
                    code = ResultCodeEnum.SUCCESS.getKey();
                    message = ResultCodeEnum.SUCCESS.getValue();
                    dataJson.put("RLEVEL",isRlevel);
                    dataJson.put("STATUS",status);
                }else {
                    //ESB通信失败，则采用无法判断
                    code = ResultCodeEnum.NOT_ASSERT.getKey();
                    message = ResultCodeEnum.NOT_ASSERT.getValue();
                }


            }

            logger.info("解析返回报文完成！");
//        }catch (ExecutionException | InterruptedException  e){
        }catch (Exception  e){
            // 捕获到InterruptedException是会的线程的中断状态标志从true变为false，影像上层线程栈对当前线程状态的判断
            // 因此需要调用 interrupt() 方法将标志重新设置为 true
            if(e instanceof InterruptedException) {
                //interrupt 方法不会中断当前线程，只是将状态标记为true，这样可以完整保留当前线程的信息，方便上层捕获和处理
                Thread.currentThread().interrupt();
            }
            logger.error("请求超时：",e);
            code = ResultCodeEnum.NOT_ASSERT.getKey();
            message = ResultCodeEnum.NOT_ASSERT.getValue();
        }

//        logger.info("解析返回报文结果：{}",errorMsg);

        returnJson.put("CODE",code);
        returnJson.put("MESSAGE",message);
        returnJson.put("DATA",dataJson);

        return returnJson;
    }

    /**
     * 验证开关是否关闭，如果关闭则组装返回对应的报文
     * @param channelName 渠道名称  总开关用ALL表示
     * @return
     */
    private JSONObject validateOnOff(String channelName) {
        JSONObject returnJson = null;
        //验证总开关是否关闭
        OnOff onOffAll = onOffService.selectByPrimaryKey(DEFAULT_ALL);
        logger.info("当前总开关的状态为： {}",onOffAll.getChannelStatus());
        if(onOffAll != null && "0".equalsIgnoreCase(onOffAll.getChannelStatus())) {
            returnJson = new JSONObject();
            returnJson.put("CODE",ResultCodeEnum.SWITCH_OFF_ALL.getKey());
            returnJson.put("MESSAGE",ResultCodeEnum.SWITCH_OFF_ALL.getValue());

        }else {
            //验证指定渠道开关是否关闭
            OnOff onOffChannel = onOffService.selectByPrimaryKey(channelName);
            logger.info("当前渠道开关的状态为： {}",onOffChannel.getChannelStatus());
            if(onOffChannel != null &&"0".equalsIgnoreCase(onOffChannel.getChannelStatus())) {
                returnJson = new JSONObject();
                returnJson.put("CODE",ResultCodeEnum.SWITCH_OFF.getKey());
                returnJson.put("MESSAGE",ResultCodeEnum.SWITCH_OFF.getValue());
            }
        }

        return returnJson;
    }

    /**
     * 通过查询四个核心小表，组装企业法人、财务负责人、受益人等信息
     * @param custNo
     * @param personNum
     * @param target
     * @param config
     * @return
     */
    private JSONArray queryPersonPlanB(String custNo,int personNum,String target,String config) {
        JSONArray array = new JSONArray();

        //json公共信息
        JSONObject baseJson = new JSONObject();
        baseJson.put("TYPE","Person");
        baseJson.put("TARGET",target);
        baseJson.put("CONFIG",config);

        //组装法人，负责人信息
        BcsCusvd4 bcsCusvd4Query = new BcsCusvd4();
        bcsCusvd4Query.setCustNo(custNo);
        List<BcsCusvd4> bcsCusvd4s = bcsCusvd4Service.listByObj(bcsCusvd4Query);
        if(bcsCusvd4s != null & bcsCusvd4s.size() >= 1) {
            for (BcsCusvd4 cus: bcsCusvd4s) {
                if(!StringUtils.isEmpty(cus.getBossIdNo())) {
                    JSONObject cusvdJson = (JSONObject) baseJson.clone();
                    cusvdJson.put("MSGID",personNum < 10 ? "Person0"+personNum : "Person"+personNum);
                    cusvdJson.put("DESC",cus.getBossInd().equals("1") ? "法人" : "负责人");

                    JSONObject dataJson = new JSONObject();
                    dataJson.put("IDS",cus.getBossIdNo());
                    dataJson.put("NAME",cus.getBossName());

                    cusvdJson.put("DATA",dataJson);
                    array.add(cusvdJson);
                    personNum++;
                }

            }
        }

        //组装控股人信息
        BcsCumi bcsCumiQuery = new BcsCumi();
        bcsCumiQuery.setCustNo(custNo);
        List<BcsCumi> bcsCumis = bcsCumiService.listByObj(bcsCumiQuery);
        if(bcsCumis != null && bcsCumis.size() >= 1) {
            for (BcsCumi cumi: bcsCumis) {
                if(!StringUtils.isEmpty(cumi.getSharePerIdnum())) {
                    JSONObject cumiJson = (JSONObject) baseJson.clone();
                    cumiJson.put("MSGID",personNum < 10 ? "Person0"+personNum : "Person"+personNum);
                    cumiJson.put("DESC","控股人");
                    JSONObject dataJson = new JSONObject();
                    dataJson.put("IDS",cumi.getSharePerIdnum());
                    dataJson.put("NAME",cumi.getShareHoldPerson());

                    cumiJson.put("DATA",dataJson);
                    array.add(cumiJson);
                    personNum++;
                }
            }
        }

        //组装财务负责人信息
        BcsCtax bcsCtaxQuery = new BcsCtax();
        bcsCtaxQuery.setCustNo(custNo);
        List<BcsCtax> bcsCtaxes = bcsCtaxService.listByObj(bcsCtaxQuery);
        if(bcsCtaxes != null && bcsCtaxes.size() >= 1) {
            for (BcsCtax ctax : bcsCtaxes) {
                if(!StringUtils.isEmpty(ctax.getFinMasIdNo())) {
                    JSONObject ctaxJson = (JSONObject) baseJson.clone();
                    ctaxJson.put("MSGID",personNum < 10 ? "Person0"+personNum : "Person"+personNum);
                    ctaxJson.put("DESC","财务负责人");
                    JSONObject dataJson = new JSONObject();
                    dataJson.put("IDS",ctax.getFinMasIdNo());
                    dataJson.put("NAME",ctax.getFinMaster());

                    ctaxJson.put("DATA",dataJson);
                    array.add(ctaxJson);
                    personNum++;
                }
            }
        }

        //组装受益人信息
        BcsCbnf bcsCbnfQuery = new BcsCbnf();
        bcsCbnfQuery.setCustNo(custNo);
        List<BcsCbnf> bcsCbnfs = bcsCbnfService.listByObj(bcsCbnfQuery);
        if(bcsCbnfs != null && bcsCbnfs.size() >= 1) {
            for (BcsCbnf cbnf: bcsCbnfs) {
                //由于核心受益人表设计为：多个受益人存储在一行数据的多个字段中，最多允许有四个，因此采用map的方式处理
                BeanMap beanMap = BeanMap.create(cbnf);
                if(beanMap != null ) {
                    for (int i = 1; i <= 4; i++) {
                        if(!StringUtils.isEmpty(beanMap.get("bnfIdNo"+i))) {
                            JSONObject cbnfJson = (JSONObject) baseJson.clone();
                            cbnfJson.put("MSGID",personNum < 10 ? "Person0"+personNum : "Person"+personNum);
                            cbnfJson.put("DESC","受益人");
                            JSONObject dataJson = new JSONObject();
                            dataJson.put("IDS",beanMap.get("bnfIdNo"+i));
                            dataJson.put("NAME",beanMap.get("bnfName"+i));
                            dataJson.put("ADDRESS",beanMap.get("bnfAddr"+i));

                            cbnfJson.put("DATA",dataJson);
                            array.add(cbnfJson);
                            personNum++;
                        }
                    }
                }
            }
        }

        logger.info("组装公司法人、财务负责人、受益人等信息结果为： {}", JSON.toJSONString(array));
        return array;
    }

}
