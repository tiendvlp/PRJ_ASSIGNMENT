/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sendmail;

import java.io.IOException;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 * @author dangminhtien
 */
public class MailSenderApi {

    private final OkHttpClient httpClient = new OkHttpClient();
    
    public MailSenderApi (){
        
    }
    
    public Response send(Email email) throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("to", email.getReceiverEmail())
                .add("subject", email.getSubject())
                .add("message", email.getMessage())
                .add("html", email.getHtml())
                .build();
        Request sendMailReq = new Request.Builder()
                .url("https://mailwithdevlogs.herokuapp.com/sendmail")
                .addHeader("User-Agent", "OkHttp Bot")
                .post(formBody)
                .build();

        Response sendMailResp = httpClient.newCall(sendMailReq).execute();
        return sendMailResp;
    }
}
