package util;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URI;

import static util.LogRecord.logger;

/**
 * Created by mac on 2017/3/27.
 */
public class HttpManager {
    private static HttpManager ourInstance = new HttpManager();
    private static CloseableHttpClient httpClient;

    private HttpManager() {
    }

    public static HttpManager getInstance() {
        return ourInstance;
    }

    private static void clientCreate() {
        if (httpClient == null) {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            ourInstance.httpClient = httpclient;
        }
    }

    private static void shutdown() throws IOException {
        if (httpClient != null) {
            httpClient.close();
            httpClient = null;
        }
    }

    public static <T> T httpProcess(URI uri, ResponseHandler<T> responseHandler) {
        T verficationCodeInfo = null;
        clientCreate();

        try {
            verficationCodeInfo = clientExec(getRequestCreate(uri), responseHandler);
        } catch (IOException e) {
            logger.warning("[warning]==========exec GET method IO exception");
            e.printStackTrace();
        }

        try {
            shutdown();
        } catch (IOException e) {
            logger.warning("[warning]==========close httpclient IO exception");
            e.printStackTrace();
        }

        return verficationCodeInfo;

    }

    private static HttpGet getRequestCreate(URI uri) {
        HttpGet httpget = new HttpGet(uri);
        return httpget;
    }

    private static HttpResponse clientExec(HttpGet httpGet) throws IOException {
        if(httpClient==null){
            clientCreate();
        }
        HttpResponse httpResponse=httpClient.execute(httpGet);
        return httpResponse;
    }

    private static <T> T clientExec(HttpGet httpGet, ResponseHandler<T> responseHandler) throws IOException {
        if(httpClient==null){
            clientCreate();
        }
        T httpResponse=httpClient.execute(httpGet,responseHandler);
        return httpResponse;
    }
}
