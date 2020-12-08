package com.beebank.monitor.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.io.*;

@Component
public class SoapTemplateInit implements ServletContextAware {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${soap.dir}")
    private String soapDir;

    @Value("${soap.subfix}")
    private String subfix;

    @Value("${soap.blackList}")
    private String blackListFile;

    @Value("${soap.returnSoap}")
    private String returnSoapFile;

    @Value("${soap.MSG}")
    private String MSGFile;

    @Value("${soap.inJar}")
    private boolean inJar;

    public static String blackList;
    public static String returnSoap;
    public static String MSG;

    private SoapTemplateInit() {}


    @Override
    public void setServletContext(ServletContext servletContext) {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(soapDir+File.separator+blackListFile+subfix);
        blackList = inputStreamToString(inputStream);

        InputStream inputStreamSoap = Thread.currentThread().getContextClassLoader().getResourceAsStream(soapDir+File.separator+returnSoapFile+subfix);
        returnSoap = inputStreamToString(inputStreamSoap);

        InputStream inputStreamMSG = Thread.currentThread().getContextClassLoader().getResourceAsStream(soapDir+File.separator+MSGFile+subfix);
        MSG = inputStreamToString(inputStreamMSG);


        logger.info("初始化黑名单接口模板为： {},返会soap模板为 ：{}, MSG 模板为： {}",blackList,returnSoap,MSG);

    }


    public String inputStreamToString(InputStream inputStream) {
        StringBuilder sb = new StringBuilder(2048);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String len;
            while ((len = reader.readLine()) != null){
                sb.append(len);
            }
            reader.close();
        }catch (IOException e) {
            logger.error("读取文件异常： ",e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                logger.error("关闭输入流异常： ",e);
            }
        }

        return sb.toString();
    }



}
