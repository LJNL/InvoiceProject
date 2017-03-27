package util;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URI;

/**
 * Created by mac on 2017/3/27.
 */
public class HttpManager {
    private static HttpManager ourInstance = new HttpManager();
    private static CloseableHttpClient httpClient;
    public static HttpManager getInstance() {
        return ourInstance;
    }


    public static void clientCreate() {
        if (httpClient == null) {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            ourInstance.httpClient = httpclient;
        }
    }

    public static HttpGet getRequestCreate(URI uri){
        HttpGet httpget = new HttpGet(uri);
        return httpget;
    }

    public static HttpResponse clientExec(HttpGet httpGet) throws IOException {

        if(httpClient==null){
            clientCreate();
        }
        HttpResponse httpResponse=httpClient.execute(httpGet);
        return httpResponse;
    }



    public static <T> T clientExec(HttpGet httpGet, ResponseHandler<T>responseHandler) throws IOException {

        if(httpClient==null){
            clientCreate();
        }
        T httpResponse=httpClient.execute(httpGet,responseHandler);

        return httpResponse;
    }

    public static void shutdown() throws IOException {
        httpClient.close();
        httpClient=null;
    }
    private HttpManager() {
    }


}
