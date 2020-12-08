package com.beebank.monitor.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.beebank.monitor.entity.CompanyPeopleInfo;
import com.beebank.monitor.entity.SoapTemplateInit;
import com.beebank.monitor.entity.SoapXML;
import de.odysseus.staxon.json.JsonXMLConfig;
import de.odysseus.staxon.json.JsonXMLConfigBuilder;
import de.odysseus.staxon.json.JsonXMLOutputFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

/**
 * @ClassName JsonAndXmlUtils
 * @Description 实现JSON--XML互转
 * @author watermelon_code
 * @Date 2017年7月19日 上午10:49:48
 * @version 1.0.0
 */
public class JsonAndXmlUtils {
    //私有化构造方法，避免创建对象
    private JsonAndXmlUtils() {}
    //需要替换的元素
    public static final String[] REPLACE_KEY = {"MSGS","ReqTm","ReqDt","ORGID","OWNTASKDESC","OWN","OWNREF","ICODE","USERID","AUTHFLAG","TRXREF","AUTHORG","BRANCHID"};
    public static final String[] REPLACE_MSG_KEY = {"MSGID","DESC","TYPE","DATE","IDS","TARGET","CONFIG","GENDER","NAME","ROLE","ADDRESS","COUNTRY"};
    private static final Logger logger = LoggerFactory.getLogger(JsonAndXmlUtils.class);

    /**
     * @Description: xml string convert to json string
     * @author
     * @date 2019年7月19日 上午10:50:46
     */
    public static String xml2json(String xml) {
        StringReader input = new StringReader(xml);
        StringWriter output = new StringWriter();
        JsonXMLConfig config = new JsonXMLConfigBuilder().autoArray(true).autoPrimitive(true).prettyPrint(true).build();
        try {

            XMLInputFactory factory = XMLInputFactory.newInstance();
            factory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, Boolean.FALSE);
            factory.setProperty(XMLInputFactory.SUPPORT_DTD, Boolean.FALSE);
            XMLEventReader reader = factory.createXMLEventReader(input);
            XMLEventWriter writer = new JsonXMLOutputFactory(config).createXMLEventWriter(output);
            writer.add(reader);
            reader.close();
            writer.close();
        } catch (XMLStreamException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                output.close();
                input.close();
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
            }
        }
        return output.toString();
    }

    public static String soapBeanToXmlString (SoapXML soapXML) {
        return json2XmlString(soapXML.getRequestHeader(),soapXML.getRequestBody());
    }

    public static String json2XmlString(JSONObject requestHeader,JSONObject requestBody) {
//        String finalSoapXml = XMLTepmlate.BLACK_LIST_SOAP;
        String finalSoapXml = SoapTemplateInit.blackList;

        //组装MSGS中的内容
        StringBuilder msgsBuilder = new StringBuilder(256);
        JSONArray msgArray = requestBody.getJSONObject("MSGS").getJSONArray("MSG");

        for (int i = 0; i < msgArray.size(); i++) {
            msgsBuilder.append(soapMSGS(msgArray.getJSONObject(i)));
        }
        for (String key:REPLACE_KEY) {
            if(key.equalsIgnoreCase("ReqTm")|| key.equalsIgnoreCase("ReqDt")) {
                finalSoapXml = finalSoapXml.replace(key+"_re",requestHeader.getString(key));
            }else if(key.equalsIgnoreCase("ORGID")
                    || key.equalsIgnoreCase("OWNTASKDESC")
                    || key.equalsIgnoreCase("OWN")
                    || key.equalsIgnoreCase("OWNREF")
                    || key.equalsIgnoreCase("ICODE")
                    || key.equalsIgnoreCase("USERID")
                    || key.equalsIgnoreCase("AUTHFLAG")
                    || key.equalsIgnoreCase("TRXREF")
                    || key.equalsIgnoreCase("AUTHORG")
                    || key.equalsIgnoreCase("BRANCHID")

            ) {
                finalSoapXml = finalSoapXml.replace(key+"_re",requestBody.getString(key));
            }
        }
        finalSoapXml = finalSoapXml.replace("MSGS"+"_re",msgsBuilder.toString());

        finalSoapXml = finalSoapXml.replaceAll("null","");
        return finalSoapXml;
    }

    /**
     * 组装单个MSG的报文
     * @param MSGJson
     * @return
     */
    public static String soapMSGS(JSONObject MSGJson) {
        String msg = SoapTemplateInit.MSG;

        for (String MSGKey: REPLACE_MSG_KEY) {
            if(MSGKey.equalsIgnoreCase("DATE")
                    || MSGKey.equalsIgnoreCase("IDS")
                    || MSGKey.equalsIgnoreCase("GENDER")
                    || MSGKey.equalsIgnoreCase("NAME")
                    || MSGKey.equalsIgnoreCase("ROLE")
                    || MSGKey.equalsIgnoreCase("ADDRESS")
                    || MSGKey.equalsIgnoreCase("COUNTRY")
            ) {
                msg = msg.replace(MSGKey+"_re",String.valueOf(MSGJson.getJSONObject("DATA").get(MSGKey)));
            }else {
                msg = msg.replace(MSGKey+"_re",String.valueOf(MSGJson.get(MSGKey)));
            }

        }
        return msg;

    }

    /**
     * 将财务负责人，收益人等信息组装到json数组中，发送报文时一并传递过去
     * @param personNum 实体编号
     * @param list 人员信息集合
     * @param target 与渠道相关的固定target值
     * @param config 与渠道相关的固定config值
     * @return
     */
    public static JSONArray peopleInfosToMSGArray(int personNum, List<CompanyPeopleInfo> list,String target,String config) {
        JSONArray array = new JSONArray();
        for (CompanyPeopleInfo info : list) {
            JSONObject json = new JSONObject();
            json.put("MSGID",personNum < 10 ? "Person0"+personNum : "Person"+personNum);
            json.put("DESC",info.getDesc());
            json.put("TYPE","Person");
            json.put("TARGET",target);
            json.put("CONFIG",config);
            JSONObject dataJson = new JSONObject();
            dataJson.put("IDS",info.getIds());
            dataJson.put("GENDER",info.getGender());
            dataJson.put("NAME",info.getName());
            dataJson.put("ROLE",null);
            dataJson.put("DATE",null);
            dataJson.put("ADDRESS",info.getAddress());
            dataJson.put("COUNTRY",info.getCountry());

            json.put("DATA",dataJson);

            personNum++;
            array.add(json);
        }
        return array;
    }
}