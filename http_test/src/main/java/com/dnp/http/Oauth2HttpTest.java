package com.dnp.http;

import com.dnp.http.utils.HttpUtils;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huazai on 2018/12/29.
 */
public class Oauth2HttpTest {
    public static void main(String[] args) {
        try {
            new Oauth2HttpTest().login();
//            new Oauth2HttpTest().say();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//{"jwt":{"access_token":"eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NDYwNTAwMDAsInVzZXJfbmFtZSI6ImZ6cCIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiIsIlJPTEVfQURNSU4iXSwianRpIjoiYzY2MDVmMDQtZTRhOC00ZTY1LWI2MWYtZGJjODUzM2ZlYjI5IiwiY2xpZW50X2lkIjoidXNlci1zZXJ2aWNlIiwic2NvcGUiOlsic2VydmljZSJdfQ.T5_m5O5U8Ol_NPAW18xOE2WlWDFLL5iiplPKCI-bbUqWzzBHOF8ZvnnRcvwn0zmXoOYaepnWclXV4D5lf5AV8U_Nky72qmqkRYphDCyq3kPonpwFZunDV8cViPuQUdMlLLAB9mnEYipvSjj9R0ely_EFIpHZF46eQqDS8U0zKEwQq2dz0XO1HFw4qMjeyosfSBDrY1xrZ8byf30doVn9e2Iuq_98YiAN7PGc4qc8FGI0TStJG4yXxp6kdyRthetHk84_e8cduOtF5wEDN2Sd11EYSpxuKZTgTrTmqx7FyjazHH1SNarMXlz2HaJk8641FIUkAd4ww7OKi0vb2ue2eQ","token_type":"bearer","refresh_token":"eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJmenAiLCJzY29wZSI6WyJzZXJ2aWNlIl0sImF0aSI6ImM2NjA1ZjA0LWU0YTgtNGU2NS1iNjFmLWRiYzg1MzNmZWIyOSIsImV4cCI6MTU0ODYzODQwMCwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiJdLCJqdGkiOiIwNDA3MzM1NC04M2ZlLTQ5NWItOTJiNy1kNzEwNWZkMjEzZTAiLCJjbGllbnRfaWQiOiJ1c2VyLXNlcnZpY2UifQ.d4SE2z52dpgxvx9Ydxbe0Ox58zM3QiA8qIEvRAX07UVKQ1xZrjMaMNSGsoIh20FVpSMKdMEH8qwAJbFEKHQSpIgjk-Jmg-L8NfEPFM8iXi-XNWvJEcCm2OuwnZLxAc-yztSAFFJfYzRSu5k5EvPdlShsLWtlygsHXsaBScK4FFh1yBI9oaPoK5_J3_PVRsaP2E25CT_QoVziv6km5JuNALaj8HmaCOH76120iFYJnKOkZs4oAKSX2KiHzak9t9OBRD9UM3GeGE-luHJ6XMt_4mPVibNYWntQvYH2Uf5gtjsHFmhjrHGg_k0oQmf4ao7UkIJzFKRcyD6xX_CGHKVb9w","expires_in":3599,"scope":"service","jti":"c6605f04-e4a8-4e65-b61f-dbc8533feb29"},"user":{"id":1,"username":"fzp","password":"$2a$10$rlM./Q4dh5qXYmxFxUqkRetMPf6JewV/Hj/s4qBg/6U1.mzcue2oK","authorities":[{"id":1,"authority":"ROLE_USER"},{"id":2,"authority":"ROLE_ADMIN"}],"enabled":true,"accountNonExpired":true,"accountNonLocked":true,"credentialsNonExpired":true}}

    public void login() throws IOException {
        String requestPath = "http://127.0.0.1:9090/user/login";
        List param = new ArrayList();
        param.add(new BasicNameValuePair("username", "fzp"));
        param.add(new BasicNameValuePair("password", "123456"));
        Header header = null;
        HttpUtils.sendPost(param, header, requestPath);
    }

    // token：
//   eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NDYwNTEwNzUsInVzZXJfbmFtZSI6ImZ6cCIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiIsIlJPTEVfQURNSU4iXSwianRpIjoiNWIyNjBjOTAtNmQwYS00YWE3LThjZTQtZDM5MjNkZWU4MTU5IiwiY2xpZW50X2lkIjoidXNlci1zZXJ2aWNlIiwic2NvcGUiOlsic2VydmljZSJdfQ.ECIIMHkA3s7PVNGZf3Nm7rt1kv9pqh_Em0hHIHWKtHb00Ys8iMvW5NWjzb0l1A0I4B8yD0IgK8Mat60X-R7sD0bfvTHZkJvFvVJP6u73lRBYB4563ybdo-D9cBEDQvgxdeeFL6-J6eBFFKM8KKz2nBekmCw4v5xF-TtOP8V9hYeZsxEHsJVRLb0xnnwxhNIAEcjudGF8tfnnJD4lAp3eUak3_CWjNLB1ftpezF0c_5-4Enl2V8Gd3P7J1_sDigS7r3LpfJSN_bMHI9hpjchwEn77_jLh2o2zUILxOYjKXM4FRk7ZthcEFI_Xz77LPga77peeEbnhFTNcWctDyCROtg","token_type":"bearer","refresh_token":"eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJmenAiLCJzY29wZSI6WyJzZXJ2aWNlIl0sImF0aSI6IjViMjYwYzkwLTZkMGEtNGFhNy04Y2U0LWQzOTIzZGVlODE1OSIsImV4cCI6MTU0ODYzOTQ3NSwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiJdLCJqdGkiOiJiMDJiMWViMy0zY2M0LTRlOGMtYTdmNi04ZTI5NTY5YjY1NDYiLCJjbGllbnRfaWQiOiJ1c2VyLXNlcnZpY2UifQ.Sd6VIJgbACLhTVADL8Q2qO_FTapYTnIeqI-M75gs_87cXo1VVEm0gxFQ-vE73up8Z2HTYnB3Bd1n6nfheBegJf2oISevzx9UkEQct06_vThD6tyHUYCWPuORqQr_4XmK8wd2YOp1ZzDRERmaY_O_SF4UFqm3KdzmWidKNSgjYTWjgn9dnjMNEvBij_zRgXG8tZmiKWdDsLpJtNxOvxJbIuAMx2Yjw0oqi03G_FX4QQ3Kk0ZNhCbG8KAQLn2dEkqkEwlCj84j17L_IJ6muJwUjgyCOhCTl6W6EpYbwMD_8fka-Vg7aRWlp0JGEj_qn11JtiaNFGAkbrZrtSeIoDZ6MQ
    public void say() throws IOException, URISyntaxException {
        String requestPath = "http://127.0.0.1:9090/user/say";
        List param = new ArrayList();
        Header header = new BasicHeader("Authorization", "bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NDYwNTI3NDEsInVzZXJfbmFtZSI6ImZ6cCIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiIsIlJPTEVfQURNSU4iXSwianRpIjoiYjc3NDRjZDMtZDZhYy00ODAwLWI5ZTQtMzhkOTQyYjRmMWU3IiwiY2xpZW50X2lkIjoidXNlci1zZXJ2aWNlIiwic2NvcGUiOlsic2VydmljZSJdfQ.a8KKgUnyceAj70pYTD7Bf6_APmKxQVit-VjwjytgO_kNNsHQLSEh7aO2VKJ1y1bs3UxspXg27qqzrfmABKExU0lW4V2jDRHIbFHlvJbsF5cu1jk5GkJePdSXOwc68b9B0KyYm7ctbzhGObZLdmBy-tQsii7ALLi2ueBchD_57QNtKfa2OBacpOj98okE2buKxGt6mpZifmBRli6u6SVJryloPdNtyq8XNqglJzcWbb5vekLZZgPgt7d00V4aWWfaeGMM-UVboWvY_m3xhEU74CrFdyRoW0eerNFWbGjC26Q61C5kKZ4bXIVKayn6wqrWEY2w-RUtEbCLi4An9b868Q\",\"token_type\":\"bearer\",\"refresh_token\":\"eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJmenAiLCJzY29wZSI6WyJzZXJ2aWNlIl0sImF0aSI6ImI3NzQ0Y2QzLWQ2YWMtNDgwMC1iOWU0LTM4ZDk0MmI0ZjFlNyIsImV4cCI6MTU0ODY0MTE0MSwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiJdLCJqdGkiOiI0N2U2OWYzOC05ZjFjLTRmY2MtYmJmZi01ODViNWZhMzA1ZmQiLCJjbGllbnRfaWQiOiJ1c2VyLXNlcnZpY2UifQ.SMMB2sCCDPG9Rh6zXpcI9hEcXhMDWqWa0iunz1TTRAWLvRyh3AT_ReROR1aihfODQ9nDs50h9XHZQepHdWAU-gGaaYxpHc70336MgMKuQkdXzFC4xM6t3AUDXB4Qo8pQJTPTmkpiQXFiXTyuLf3CxAA7YROxZndTLM6mIM8KwTgxc-gOOT-77Ot-gJCtVah_qOWsdx9yGcce_LF1e7bDlA7aPbd0NX7lIi_lxE52kbGcXUrAB0j4FTFc7G4v40tbf5QO_GEcD8dw32kQ8qFFEBonEReOa2QZplp6c7nYWZ5iIKPxhO-UYQJ6m3O0DJL7SlQbFgoL9YiFM9U3VWb1_A");
        HttpUtils.sendGet(param, header, requestPath);
    }

}
