package com.dnp.http.utils;

import com.sun.javafx.fxml.builder.URLBuilder;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by huazai on 2018/12/29.
 */
public class HttpUtils {
    /**
     * @param params      请求参数,如：List<NameValuePair> param = new ArrayList<NameValuePair>();param.add(new BasicNameValuePair("grant_type", "password"));
     * @param header      请求头， 如：Header header = new BasicHeader("Authorization", "bearer " + token);
     * @param requestPath 请求路径
     * @throws IOException io异常
     */
    public static void sendPost(List<NameValuePair> params, Header header, String requestPath) throws IOException {
        HttpEntity httpEntity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
        HttpClient httpClient = HttpClientBuilder.create().build();
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(100000).setSocketTimeout(100000).build();
        HttpPost httpPost = new HttpPost(requestPath);
        httpPost.setConfig(requestConfig);
        httpPost.setHeader(header);
        httpPost.setEntity(httpEntity);

        HttpResponse response = httpClient.execute(httpPost);
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("statusCode = " + statusCode);
        out(response, statusCode);

    }

    /**
     * @param params      请求参数,如：List<NameValuePair> param = new ArrayList<NameValuePair>();param.add(new BasicNameValuePair("grant_type", "password"));
     * @param header      请求头， 如：Header header = new BasicHeader("Authorization", "bearer " + token);
     * @param requestPath 请求路径
     * @throws IOException io异常
     */
    public static void sendGet(List<NameValuePair> params, Header header, String requestPath) throws IOException, URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(requestPath);
        uriBuilder.setParameters(params);
        HttpClient httpClient = HttpClientBuilder.create().build();
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(100000).setSocketTimeout(100000).build();
        HttpGet httpGet = new HttpGet(requestPath);
        httpGet.setConfig(requestConfig);
        httpGet.setHeader(header);

        HttpResponse response = httpClient.execute(httpGet);
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("statusCode = " + statusCode);
        out(response, statusCode);

    }

    private static void out(HttpResponse response, int statusCode) throws IOException {
        if (statusCode == 200) {
            String message = EntityUtils.toString(response.getEntity());
            System.out.println("message = " + message);
        } else {
            String message = EntityUtils.toString(response.getEntity());
            System.out.println("message = " + message);
            System.out.println("请求失败");
        }
    }
}
