package com.beebank.monitor.util;

import com.alibaba.druid.util.StringUtils;
import com.beebank.monitor.monitorconst.XMLTepmlate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * httpclient工具类，使用jdk11自带的 httpClient
 */
public class HttpClientUtil {
    private HttpClientUtil(){}
    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
    public static final String DEFAULT_CHARSET = "UTF-8";
    private static HttpClient httpClient;
    static {
        httpClient = HttpClient.newHttpClient();
    }
    /**
     * 发送post请求，并返回响应体内容
     * @param url
     * @param param
     * @param timeOut
     * @return
     */
    public static String sendPostRequest(String url, String param,long timeOut,String charset) throws ExecutionException, InterruptedException {

        String returnMessage ;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofMillis(timeOut))
                .POST(HttpRequest.BodyPublishers.ofString(param))
                .build();
        logger.info("开始发送http请求，请求地址为： {}",url);
        charset = StringUtils.isEmpty(charset) ? DEFAULT_CHARSET : charset;
        //异步方式调用
//        CompletableFuture<HttpResponse<byte[]>> httpResponseCompletableFuture = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofByteArray());
        CompletableFuture<HttpResponse<String>> httpResponseCompletableFuture = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString(Charset.forName(charset)));
        HttpResponse<String> httpResponse = httpResponseCompletableFuture.get();
        returnMessage = httpResponse.body();

        // 模拟不调用黑名单，直接返回
//        returnMessage = XMLTepmlate.RETURN_XML_STR;

        logger.info("返回报文：{}",returnMessage);
        return returnMessage;
    }
}
