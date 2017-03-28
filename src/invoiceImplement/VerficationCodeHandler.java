package invoiceImplement;


import datamodels.VerficationCodeInfo;
import datamodels.VerficationCodeRequestInfo;
import invoiceInterface.IVerficationCodeInfo;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.entity.ContentType;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

/**
 * Created by SPREADTRUM\jiannan.liu on 17-3-28.
 */
public class VerficationCodeHandler implements ResponseHandler<VerficationCodeInfo>, IVerficationCodeInfo {

    private VerficationCodeRequestInfo verficationCodeRequestInfo;

    public VerficationCodeHandler(VerficationCodeRequestInfo verficationCodeRequestInfo) {
        this.verficationCodeRequestInfo = verficationCodeRequestInfo;
    }

    @Override
    public VerficationCodeInfo handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
        StatusLine statusLine = httpResponse.getStatusLine();
        if (statusLine.getStatusCode() < 300 && statusLine.getStatusCode() >= 200) {
            HttpEntity entity = httpResponse.getEntity();
            if (entity == null) {
                throw new ClientProtocolException("Response contains no content");
            }

            ContentType contentType = ContentType.getOrDefault(entity);
            Charset charset = contentType.getCharset();
            Reader reader = new InputStreamReader(entity.getContent(), charset);

            JsonReader rdr = Json.createReader(reader);
            JsonObject object = rdr.readObject();

            VerficationCodeInfo verficationCodeInfo = new VerficationCodeInfo(verficationCodeRequestInfo);
            verficationCodeInfo.setImageBase64(object.getString("key1"))
                    .setTime(object.getString("key2"))
                    .setIndex(object.getString("key3"))
                    .setSign(object.getString("key4"));
            return verficationCodeInfo;
        }

        return null;
    }

    @Override
    public void setVerficationCodeInfoConvert(VerficationCodeRequestInfo verficationCodeRequestInfo) {
        this.verficationCodeRequestInfo = verficationCodeRequestInfo;
        return;
    }
}


