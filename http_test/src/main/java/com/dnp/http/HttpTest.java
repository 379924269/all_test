package com.dnp.http;

import com.dnp.http.utils.HttpUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huazai on 2018/12/11.
 */
public class HttpTest {

    public static void main(String[] args) {
//        String xx = new BASE64Encoder().encode("ssoclient:ssosecret".getBytes());
//        System.out.println("xx = " + xx);
////        /oauth/token
//        String uri = "http://localhost/oauth/token";
//        List<NameValuePair> param = new ArrayList<NameValuePair>();
//        param.add(new BasicNameValuePair("grant_type", "password"));
//        param.add(new BasicNameValuePair("username", "user"));
//        param.add(new BasicNameValuePair("password", "user"));
//        try {
//            sendPost(param, xx, uri);
//        } catch (IO   Exception e) {
//            e.printStackTrace();
//        }
//        login();
//        foo();
//        say();
// loginTest();
//        conc;
//        atList();
        springBootBookGetTokenTest();
        springBootBookGetTokenTest1();
    }

    public static void loginTest() {
        String uri = "http://localhost/baomidou/login";
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("username", "admin"));
        param.add(new BasicNameValuePair("password", "123456"));
        try {
            HttpUtils.sendPost(param, null, uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void login() {
        String uri = "http://localhost:9090/user/login";
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("username", "fzp"));
        param.add(new BasicNameValuePair("password", "123456"));
        try {
            HttpUtils.sendPost(param, null, uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void springbootSsoGettoken() {
        //password
        String xx = new BASE64Encoder().encode("uaa-service:123456".getBytes());
        System.out.println("xx = " + xx);
        String uri = "http://localhost/oauth/token";
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("grant_type", "password"));
        param.add(new BasicNameValuePair("username", "frz"));
        param.add(new BasicNameValuePair("password", "123456"));
        Header header = new BasicHeader("Authorization", String.format("Basic %s", xx));
        try {
            HttpUtils.sendPost(param, header, uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void springBootBookGetTokenTest() {
        String xx = new BASE64Encoder().encode("zuul_server:secret".getBytes());
        System.out.println("xx = " + xx);
        String uri = "http://localhost:9998/auth/oauth/token";
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("grant_type", "password"));
        param.add(new BasicNameValuePair("username", "guest"));
        param.add(new BasicNameValuePair("password", "guest"));
        Header header = new BasicHeader("Authorization", String.format("Basic %s", xx));
        try {
            HttpUtils.sendPost(param, header, uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void springBootBookGetTokenTest1() {
        String xx = new BASE64Encoder().encode("zuul_server:secret".getBytes());
        System.out.println("xx = " + xx);
        String uri = "http://localhost:7777/uaa/oauth/token";
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("grant_type", "password"));
        param.add(new BasicNameValuePair("username", "guest"));
        param.add(new BasicNameValuePair("password", "guest"));
        Header header = new BasicHeader("Authorization", String.format("Basic %s", xx));
        try {
            HttpUtils.sendPost(param, header, uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void foo() {
        String uri = "http://localhost:9090/foo";
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NDcwMjA3MjksInVzZXJfbmFtZSI6ImZ6cCIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiIsIlJPTEVfQURNSU4iXSwianRpIjoiZTFhZTVhMzgtYzVhNS00NzA1LWIwNjgtNDZlM2U5YmRhOGQ4IiwiY2xpZW50X2lkIjoidXNlci1zZXJ2aWNlIiwic2NvcGUiOlsic2VydmljZSJdfQ.PyfUig7UR4F9Hx9oDBOcW7mKmEoGS6dfXkVG1PGRxP4MVoNrjtdpWMWT5ZCu7bDjm3UtPhM_SLMjloGqXJo6sr8uG-8BiSSgWHf8y-HGK76cnd-QqzbYb31TwtN3n1W0zPs8nnzLCDgVDYDLIgTO_KVPGROpnnGIg0q5ersHmk4PCWN273sX2EXXUeLYLrrqDqItxgMxIauKPKYGbTm3uUUYWpBcaMNZOrgub-rRTw0hr4617R21L_v-7tA-rFwU8uiaAurG3Ao3RX89jQ2cjWOyVM3lZTJeu3yfuZSWzckKRnx7DTCe34gOglJZr4V2havIApcVBayg1HnMurtw_g\",\"token_type\":\"bearer\",\"refresh_token\":\"eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJmenAiLCJzY29wZSI6WyJzZXJ2aWNlIl0sImF0aSI6ImUxYWU1YTM4LWM1YTUtNDcwNS1iMDY4LTQ2ZTNlOWJkYThkOCIsImV4cCI6MTU0OTYwOTEyOSwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiJdLCJqdGkiOiI1OGQ1MDY2My02ZWZhLTRjYmItYWU3Ny1kZGRhZGEzOGIxOWUiLCJjbGllbnRfaWQiOiJ1c2VyLXNlcnZpY2UifQ.YHkf2Vf_QhOhRkQlPoY-FaBjFgmLt0ZgNVGKFGKeridncKq4zOfls8SD-D7PxOjlUsmiwTf6mCR-bEAW5WQh9Hh5x_LXy14QMtH45YOM39hEaXVB5cd-mWEaCC-qbSP_8bQA7cE7YHnRgW3S7jWgCT4IHRTZfcSYI3JyumxqD9ah3oSYkCLQhDfdraHEnfUJVfWHA_1PZBEQ9C7G6WrzVENfqRObtws20vqB-fglc5X1Ntlv2_qQtMZ0r9qusuEzbWZ6yjqOd26D-kYeTnElGnnvKRhZpB24rTcIH7kSSFUzO5Wmebc-8wNrvmQ72XpT8RD59SWOznKKLwTSrdX-fg";
        Header header = new BasicHeader("Authorization", "Bearer " +  token);
        try {
            UrlTest.get(param, uri, header);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    public static void say() {
        String uri = "http://localhost:9090/user/say";
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NDcwMTk0NzMsInVzZXJfbmFtZSI6ImZ6cCIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiIsIlJPTEVfQURNSU4iXSwianRpIjoiMmYwMTMzNzEtYTBjOS00YmVhLTk5YTYtNTU5MWU4ZGY0NDQyIiwiY2xpZW50X2lkIjoidXNlci1zZXJ2aWNlIiwic2NvcGUiOlsic2VydmljZSJdfQ.JnNa75ddfcCpOWeswTBk2IVzsGkZ_eOihSrEDTCq65HCcLX9y_X--9NyWyPROa2V8uiaDfaq9_gCRjAB-cK-1wQ9PWiuWzD7dv2EPXzDJHY0AuBjtLqTM0aSpva8Sjln4aVe3gQwf7DrnP-9w-83lvV8heLAzKrClGqghTVzH2dQZtCVPor23VON_eMJM1XBUEfv-jZVR4hR2s9S-PXLric-ui0QnbtBG6aBo-g-GHECQ5togNZHkK4znuOINag1PlvBvXAp_pAvRS3Y6OcqvsJs1Jspf7QQUUkNQuavEFhYqbnsSVVFZRmpL-D4JZoZUs-_b1csMDQ2ptE4MhPz0Q\",\"token_type\":\"bearer\",\"refresh_token\":\"eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJmenAiLCJzY29wZSI6WyJzZXJ2aWNlIl0sImF0aSI6IjJmMDEzMzcxLWEwYzktNGJlYS05OWE2LTU1OTFlOGRmNDQ0MiIsImV4cCI6MTU0OTYwNzg3MywiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiJdLCJqdGkiOiJiNTFkZDdhNy03ZjgzLTQ0MGMtYTM4Yi0xZDAzNTNiOTJmYWMiLCJjbGllbnRfaWQiOiJ1c2VyLXNlcnZpY2UifQ.dzL5c9CQV7NEZK4AcbTXwov7ZKszGSD0Fc6aDCjUji22UiTb7KOk4iICnszQuif_bZpablc904Zar8Qzl3SmaDrwmiehyyAg60ULKMVwsRtHVybaNtflEOWVZYJDGaSG7d9SR8J3W4V7vU0kOaiJ2zfY4GsiwLU7WONe6olNA-LBckwxuWwRgEp-17Azcjf7dP983M4R6ETQFJMXUS9r5hAwloh21Mt5efY-aoeOUXmjUolV_zgTlu_uDJdfxWGdJrj5kzAtcNW-iuXC8loKzWmVQFFmA4qk4J12gVe6xD4fPN5G2pICeJaq7CihaTmvbY-RAKklOW8Sewh44cIgng";
        Header header = new BasicHeader("Authorization", "Bearer " + token);
        try {
            UrlTest.get(param, uri, header);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    public static void concatList() {
        String uri = "http://test.delinp.cn:8001/ptt/client/contactList.do";
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("yourselfNumber", "10057"));
//        String token = "1eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NDQ1OTk4MTAsInVzZXJfbmFtZSI6ImZ6cCIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiIsIlJPTEVfQURNSU4iXSwianRpIjoiZjNlNzJkNjItMzYzMC00MzNkLTg3OWMtM2VmNmNjNzJiMzJkIiwiY2xpZW50X2lkIjoidXNlci1zZXJ2aWNlIiwic2NvcGUiOlsic2VydmljZSJdfQ.fGmJDJE4bTVypArs-F43VLOE7tOWFE38g0qk-YUeIm2Kuym5XLGEA6qjanY_DLG0iSsyFcm9EwZ9h5jzkKZMZ-_13FMpDAqUWKta_GdpLkeQrOojunmPfxFOKaZJyMKkc_Nfb0WWHF0_J5zII8xO_I6NDJVGFbNBV0atM2gU2NABIL801nOt7rn9qXb2TaQdh7T9vpcWjtGBwctLYb5LNXWFHT2Ll6s-9HeVxcW7o-0iTFuvo2Ny7ne0rdNhRGLgmxv0tyS7_zik9g0uH9vWmvcIYZPPo2ZBw5yNk-Us3O7aauGj0PAsCVK43Wt1hKyJVgmQMx42qfUzGUTvYXTTxw";
//        Header header = new BasicHeader("Authorization", "bearer " + token);
        try {
            UrlTest.get(param, uri, null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
