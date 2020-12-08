package com.beebank.monitor.monitorconst;

public class XMLTepmlate {

    // 覆盖public的无参构造，避免被实例化
    private XMLTepmlate() {
    }

    //要发送的报文模板
    public static final String BLACK_LIST_SOAP = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Header/><soapenv:Body><S05800101BMS1001><RequestHeader>\n" +
            "\t<ReqTm>ReqTm_re</ReqTm>\n" +
            "\t<ReqDt>ReqDt_re</ReqDt>\n" +
            "</RequestHeader>\n" +
            "<RequestBody>\n" +
            "\t<ORGID>ORGID_re</ORGID>\n" +
            "\t<OWNTASKDESC>OWNTASKDESC_re</OWNTASKDESC>\n" +
            "\t<OWN>OWN_re</OWN>\n" +
            "\t<EMAILFLAG></EMAILFLAG>\n" +
            "\t<OWNREF>OWNREF_re</OWNREF>\n" +
            "\t<BESREF></BESREF>\n" +
            "\t<ICODE>ICODE_re</ICODE>\n" +
            "\t<MSGS>\n" +
            "\t\t<MSG>\n" +
            "\t\t\t<MSGID>MSGID_re</MSGID>\n" +
            "\t\t\t<DESC>DESC_re</DESC>\n" +
            "\t\t\t<TYPE>TYPE_re</TYPE>\n" +
            "\t\t\t<DATA>\n" +
            "\t\t\t\t<GENDER></GENDER>\n" +
            "\t\t\t\t<NAME></NAME>\n" +
            "\t\t\t\t<ROLE></ROLE>\n" +
            "\t\t\t\t<DATE>DATE_re</DATE>\n" +
            "\t\t\t\t<ADDRESS></ADDRESS>\n" +
            "\t\t\t\t<COUNTRY></COUNTRY>\n" +
            "\t\t\t\t<IDS>IDS_re</IDS>\n" +
            "\t\t\t</DATA>\n" +
            "\t\t\t<VALUETYPE></VALUETYPE>\n" +
            "\t\t\t<VALUE></VALUE>\n" +
            "\t\t\t<TARGET>TARGET_re</TARGET>\n" +
            "\t\t\t<CONFIG>CONFIG_re</CONFIG>\n" +
            "\t\t</MSG>\n" +
            "\t</MSGS>\n" +
            "\t<USERID>USERID_re</USERID>\n" +
            "\t<AUTHFLAG>AUTHFLAG_re</AUTHFLAG>\n" +
            "\t<SMSFLAG></SMSFLAG>\n" +
            "\t<TRXREF>TRXREF_re</TRXREF>\n" +
            "\t<AUTHORG>AUTHORG_re</AUTHORG>\n" +
            "\t<BRANCHID>BRANCHID_re</BRANCHID>\n" +
            "</RequestBody>\n" +
            "</S05800101BMS1001></soapenv:Body></soapenv:Envelope>";




    public static final String RETURN_JSON_STR = "{\n" +
            "\t\"soapenv:Envelope\" : {\n" +
            "\t\t\"@xmlns:soapenv\" : \"http://schemas.xmlsoap.org/soap/envelope/\",\n" +
            "\t\t\"@xmlns:ns\" : \"www.cqrcb.com.cn\",\n" +
            "\t\t\"soapenv:Header\" : null,\n" +
            "\t\t\"soapenv:Body\" : {\n" +
            "\t\t\t\"ns:S05800101BMS1001\" : {\n" +
            "\t\t\t\t\"ResponseHeader\" : {\n" +
            "\t\t\t\t\t\"VerNo\" : null,\n" +
            "\t\t\t\t\t\"RespSysCd\" : null,\n" +
            "\t\t\t\t\t\"RespSecCd\" : null,\n" +
            "\t\t\t\t\t\"TxnCd\" : null,\n" +
            "\t\t\t\t\t\"TxnNme\" : null,\n" +
            "\t\t\t\t\t\"ReqDt\" : 20191213,\n" +
            "\t\t\t\t\t\"ReqTm\" : 163513,\n" +
            "\t\t\t\t\t\"ReqSeqNo\" : null,\n" +
            "\t\t\t\t\t\"SvrDt\" : null,\n" +
            "\t\t\t\t\t\"SvrTm\" : null,\n" +
            "\t\t\t\t\t\"SvrSeqNo\" : null,\n" +
            "\t\t\t\t\t\"AuthFlg\" : null,\n" +
            "\t\t\t\t\t\"BrchNo\" : null,\n" +
            "\t\t\t\t\t\"BrchNme\" : null,\n" +
            "\t\t\t\t\t\"DevCd\" : null,\n" +
            "\t\t\t\t\t\"TlrNo\" : null,\n" +
            "\t\t\t\t\t\"TlrLvl\" : null,\n" +
            "\t\t\t\t\t\"TlrNme\" : null,\n" +
            "\t\t\t\t\t\"TlrTyp\" : null,\n" +
            "\t\t\t\t\t\"TlrPwd\" : null,\n" +
            "\t\t\t\t\t\"TrmNo\" : null,\n" +
            "\t\t\t\t\t\"TrmIP\" : null,\n" +
            "\t\t\t\t\t\"ChnlNo\" : null,\n" +
            "\t\t\t\t\t\"RcvFileNme\" : null,\n" +
            "\t\t\t\t\t\"TotNum\" : null,\n" +
            "\t\t\t\t\t\"CurrRecNum\" : null,\n" +
            "\t\t\t\t\t\"FileHMac\" : null,\n" +
            "\t\t\t\t\t\"HMac\" : null\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"ResponseBody\" : {\n" +
            "\t\t\t\t\t\"ICODE\" : \"REP1001\",\n" +
            "\t\t\t\t\t\"OWN\" : \"SJYH\",\n" +
            "\t\t\t\t\t\"OWNREF\" : 35820221202000214290298731600011,\n" +
            "\t\t\t\t\t\"BESREF\" : \"0d2eedcd98d34489a688d6736efaaa58\",\n" +
            "\t\t\t\t\t\"STATUS\" : 1,\n" +
            "\t\t\t\t\t\"NOTE\" : null,\n" +
            "\t\t\t\t\t\"RLEVEL\" : \"L\",\n" +
            "\t\t\t\t\t\"RPERCENT\" : 0.0,\n" +
            "\t\t\t\t\t\"RKIND\" : null,\n" +
            "\t\t\t\t\t\"RBLID\" : null,\n" +
            "\t\t\t\t\t\"MSGS\" : {\n" +
            "\t\t\t\t\t\t\"MSG\" : {\n" +
            "\t\t\t\t\t\t\t\"MSGID\" : \"Person01\",\n" +
            "\t\t\t\t\t\t\t\"DESC\" : \"申请人\",\n" +
            "\t\t\t\t\t\t\t\"TYPE\" : \"Person\",\n" +
            "\t\t\t\t\t\t\t\"SVALUE\" : \"{}\",\n" +
            "\t\t\t\t\t\t\t\"RCONFIG\" : \"{\\\\\\\"OWNER\\\\\\\":\\\\\\\"SJYH\\\\\\\",\\\\\\\"CFG_NAME\\\\\\\":\\\\\\\"SJYH-config91\\\\\\\",\\\\\\\"CFG_DESC\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"PERCENT\\\\\\\":100,\\\\\\\"SHOWNUM\\\\\\\":1,\\\\\\\"CREATE_TIME\\\\\\\":\\\\\\\"2019-10-14 09:49:23\\\\\\\",\\\\\\\"CREATE_USER\\\\\\\":\\\\\\\"0000\\\\\\\",\\\\\\\"DEF_FLAG\\\\\\\":\\\\\\\"Y\\\\\\\",\\\\\\\"NOTE\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"SCHEMAL_NAME\\\\\\\":\\\\\\\"I_FULL\\\\\\\",\\\\\\\"FILTER_CFG\\\\\\\":{\\\\\\\"D\\\\\\\":\\\\\\\"3-1,4-3,3-2,4-4,BMS\\\\\\\"}}\",\n" +
            "\t\t\t\t\t\t\t\"RTARGET\" : \"[]\",\n" +
            "\t\t\t\t\t\t\t\"RLEVEL\" : \"L\",\n" +
            "\t\t\t\t\t\t\t\"RPERCENT\" : 0.0,\n" +
            "\t\t\t\t\t\t\t\"RBLID\" : null,\n" +
            "\t\t\t\t\t\t\t\"RKIND\" : null,\n" +
            "\t\t\t\t\t\t\t\"KTYPE\" : null,\n" +
            "\t\t\t\t\t\t\t\"BLISTS\" : {\n" +
            "\t\t\t\t\t\t\t\t\"BLIST\" : {\n" +
            "\t\t\t\t\t\t\t\t\t\"RBLID\" : null,\n" +
            "\t\t\t\t\t\t\t\t\t\"RBLTIME\" : null,\n" +
            "\t\t\t\t\t\t\t\t\t\"RTYPE\" : null,\n" +
            "\t\t\t\t\t\t\t\t\t\"RSOURCE\" : null,\n" +
            "\t\t\t\t\t\t\t\t\t\"RVALUE\" : null,\n" +
            "\t\t\t\t\t\t\t\t\t\"RPERCENT\" : null,\n" +
            "\t\t\t\t\t\t\t\t\t\"RKIND\" : null\n" +
            "\t\t\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"Fault\" : {\n" +
            "\t\t\t\t\t\"FaultCode\" : \"058001000000\",\n" +
            "\t\t\t\t\t\"FaultString\" : \"SUCCESS\",\n" +
            "\t\t\t\t\t\"Detail\" : {\n" +
            "\t\t\t\t\t\t\"TxnStat\" : \"SUCCESS\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t}\n" +
            "} ";



    public static final String RETURN_XML_STR = "<?xml version=\"1.0\" encoding=\"GB18030\"?>\n" +
            "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"www.cqrcb.com.cn\">\n" +
            "  <soapenv:Header/>\n" +
            "  <soapenv:Body>\n" +
            "    <ns:S05800101BMS1001>\n" +
            "      <ResponseHeader>\n" +
            "        <VerNo/>\n" +
            "        <RespSysCd/>\n" +
            "        <RespSecCd/>\n" +
            "        <TxnCd/>\n" +
            "        <TxnNme/>\n" +
            "        <ReqDt>20191213</ReqDt>\n" +
            "        <ReqTm>172158</ReqTm>\n" +
            "        <ReqSeqNo/>\n" +
            "        <SvrDt/>\n" +
            "        <SvrTm/>\n" +
            "        <SvrSeqNo/>\n" +
            "        <AuthFlg/>\n" +
            "        <BrchNo/>\n" +
            "        <BrchNme/>\n" +
            "        <DevCd/>\n" +
            "        <TlrNo/>\n" +
            "        <TlrLvl/>\n" +
            "        <TlrNme/>\n" +
            "        <TlrTyp/>\n" +
            "        <TlrPwd/>\n" +
            "        <TrmNo/>\n" +
            "        <TrmIP/>\n" +
            "        <ChnlNo/>\n" +
            "        <RcvFileNme/>\n" +
            "        <TotNum/>\n" +
            "        <CurrRecNum/>\n" +
            "        <FileHMac/>\n" +
            "        <HMac/>\n" +
            "      </ResponseHeader>\n" +
            "      <ResponseBody>\n" +
            "        <ICODE>REP1001</ICODE>\n" +
            "        <OWN>SJYH</OWN>\n" +
            "        <OWNREF>35820221202000214290298731600011</OWNREF>\n" +
            "        <BESREF>b9c2cbdd25d34e388fbdff3914dcd1b9</BESREF>\n" +
            "        <STATUS>1</STATUS>\n" +
            "        <NOTE/>\n" +
            "        <RLEVEL>L</RLEVEL>\n" +
            "        <RPERCENT>0.0</RPERCENT>\n" +
            "        <RKIND/>\n" +
            "        <RBLID/>\n" +
            "        <MSGS>\n" +
            "          <MSG>\n" +
            "            <MSGID>Person01</MSGID>\n" +
            "            <DESC>申请人</DESC>\n" +
            "            <TYPE>Person</TYPE>\n" +
            "            <SVALUE>{}</SVALUE>\n" +
            "            <RCONFIG>{\\\"OWNER\\\":\\\"SJYH\\\",\\\"CFG_NAME\\\":\\\"SJYH-config91\\\",\\\"CFG_DESC\\\":\\\"\\\",\\\"PERCENT\\\":100,\\\"SHOWNUM\\\":1,\\\"CREATE_TIME\\\":\\\"2019-10-14 09:49:23\\\",\\\"CREATE_USER\\\":\\\"0000\\\",\\\"DEF_FLAG\\\":\\\"Y\\\",\\\"NOTE\\\":\\\"\\\",\\\"SCHEMAL_NAME\\\":\\\"I_FULL\\\",\\\"FILTER_CFG\\\":{\\\"D\\\":\\\"3-1,4-3,3-2,4-4,BMS\\\"}}</RCONFIG>\n" +
            "            <RTARGET>[]</RTARGET>\n" +
            "            <RLEVEL>L</RLEVEL>\n" +
            "            <RPERCENT>0.0</RPERCENT>\n" +
            "            <RBLID/>\n" +
            "            <RKIND/>\n" +
            "            <KTYPE/>\n" +
            "            <BLISTS>\n" +
            "              <BLIST>\n" +
            "                <RBLID/>\n" +
            "                <RBLTIME/>\n" +
            "                <RTYPE/>\n" +
            "                <RSOURCE/>\n" +
            "                <RVALUE/>\n" +
            "                <RPERCENT/>\n" +
            "                <RKIND/>\n" +
            "              </BLIST>\n" +
            "            </BLISTS>\n" +
            "          </MSG>\n" +
            "        </MSGS>\n" +
            "      </ResponseBody>\n" +
            "      <Fault>\n" +
            "        <FaultCode>058001000000</FaultCode>\n" +
            "        <FaultString>SUCCESS</FaultString>\n" +
            "        <Detail>\n" +
            "          <TxnStat>SUCCESS</TxnStat>\n" +
            "        </Detail>\n" +
            "      </Fault>\n" +
            "    </ns:S05800101BMS1001>\n" +
            "  </soapenv:Body>\n" +
            "</soapenv:Envelope>";


}
