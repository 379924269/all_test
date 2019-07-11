package com.dnp.http;

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
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huazai on 2018/12/10.
 */
public class UrlTest {
    public static void main(String[] args) {
//        password();
//        clientCredentials();
//        authorization_code();
//        hiInterface();
        String xx = new BASE64Encoder().encode("uaa-service:123456".getBytes());
        System.out.println("xx = " + xx);
    }

    /**
     * 密码模式
     */
    public static void password() {
        //password
        String xx = new BASE64Encoder().encode("service-hi:123456".getBytes());
        System.out.println("xx = " + xx);
        String uri = "http://localhost:5000/uaa/oauth/token";
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("grant_type", "password"));
        param.add(new BasicNameValuePair("username", "fzp"));
        param.add(new BasicNameValuePair("password", "123456"));
        Header header = new BasicHeader("Authorization", String.format("Basic %s", xx));
        try {
            sendPost(param, uri, header);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ClientCredentials客户端模式:
     * Client使用自己的 client证书(如 client_id及client_secret组成的 http basic验证码）来获取 access token,只能用于信任的client场景
     * https://blog.csdn.net/yoywow/article/details/52215607
     */
    public static void clientCredentials() {
        String xx = new BASE64Encoder().encode("service-hi:123456".getBytes());
        System.out.println("xx = " + xx);
        String uri = "http://localhost:5000/uaa/oauth/token";
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("grant_type", "client_credentials"));
        Header header = new BasicHeader("Authorization", String.format("Basic %s", xx));
        try {
            sendPost(param, uri, header);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void authorization_code() {
        String xx = new BASE64Encoder().encode("service-hi:123456".getBytes());
        System.out.println("xx = " + xx);
        String uri = "http://localhost:5000/uaa/oauth/authorize";
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("response_type", "code"));
        param.add(new BasicNameValuePair("redirect_uri", "www.baidu.com"));
        param.add(new BasicNameValuePair("client_id", "service-hi"));
        Header header = new BasicHeader("Authorization", String.format("Basic %s", xx));
//        Header header = null;
        try {
            get(param, uri, header);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用access_token 访问保护接口
     */
    public static void hiInterface() {
        String uri = "http://localhost:8762/hi";
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        Header header = new BasicHeader("Authorization", "Bearer ec5c4a1e-848e-4253-b725-35e1faba3567");
        try {
            sendPost(param, uri, header);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void sendPost(List<NameValuePair> formparams, String uri, Header header) throws IOException {
        HttpEntity reqEntity = new UrlEncodedFormEntity(formparams, "utf-8");

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)//一、连接超时：connectionTimeout-->指的是连接一个url的连接等待时间
                .setSocketTimeout(5000)// 二、读取数据超时：SocketTimeout-->指的是连接上一个url，获取response的返回等待时间
                .setConnectionRequestTimeout(5000)
                .build();

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(uri);
        if (header != null) {
            post.setHeader(header);
        }

        post.setEntity(reqEntity);
        post.setConfig(requestConfig);
        HttpResponse response = client.execute(post);

        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("statusCode = " + statusCode);
        requestResponse(response);
    }

    private static void requestResponse(HttpResponse response) throws IOException {
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity resEntity = response.getEntity();
            String message = EntityUtils.toString(resEntity, "utf-8");
            System.out.println(message);
        } else {
            HttpEntity resEntity = response.getEntity();
            String message = EntityUtils.toString(resEntity, "utf-8");
            System.out.println(message);
        }
    }

    public static void get(List<NameValuePair> formparams, String uri, Header header) throws IOException, URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(uri);
        uriBuilder.addParameters(formparams);

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)//一、连接超时：connectionTimeout-->指的是连接一个url的连接等待时间
                .setSocketTimeout(5000)// 二、读取数据超时：SocketTimeout-->指的是连接上一个url，获取response的返回等待时间
                .setConnectionRequestTimeout(5000)
                .build();

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(uriBuilder.build());
        if (header != null) {
            get.setHeader(header);
        }

        String uriall = get.getURI().toString();
        System.out.println("uriall = " + uriall);

        get.setConfig(requestConfig);
        HttpResponse response = client.execute(get);

        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("statusCode = " + statusCode);
        requestResponse(response);
    }
}
