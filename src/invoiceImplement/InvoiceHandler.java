package invoiceImplement;

import datamodels.InvoiceInfo;
import datamodels.InvoiceRequestInfo;
import invoiceInterface.IInvoiceInfo;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static util.LogRecord.logger;

/**
 * Created by SPREADTRUM\jiannan.liu on 17-3-31.
 */
public class InvoiceHandler implements ResponseHandler<InvoiceInfo>, IInvoiceInfo {
    private InvoiceRequestInfo invoiceRequestInfo;

    @Override
    public InvoiceInfo handleResponse(HttpResponse httpResponse) throws IOException {
        logger.info("[INFO]========== START HANDLER RESPONSE");
        StatusLine statusLine = httpResponse.getStatusLine();

        if (statusLine.getStatusCode() < 300 && statusLine.getStatusCode() >= 200) {
            logger.info("[INFO]========== RESPONSE OK");
            HttpEntity entity = httpResponse.getEntity();
            if (entity == null) {
                logger.warning("[warning]========== NO MORE ENTITY");
                throw new ClientProtocolException("Response contains no content");
            }


            ContentType contentType = ContentType.getOrDefault(entity);
            Charset charset = contentType.getCharset();

            Reader reader = null;
            try {
                reader = new InputStreamReader(entity.getContent(), charset);
            } catch (IOException e) {
                logger.info("[warning]========== RESPONSE ENTITY CONTENT ERROR");
                e.printStackTrace();
            }


            logger.info("[INFO]========== GET ENTITY CONTENT");


            StringBuffer contentStr = new StringBuffer();
            char[] buffer = new char[512];
            int n = 0;
            while ((n = reader.read(buffer)) != -1) {
                if (n != buffer.length)
                    contentStr.append(buffer, 0, n);
                else
                    contentStr.append(buffer);
            }


            logger.info("[warning]========== entity " + contentStr);

            Pattern pattern = Pattern.compile(".*(\\{.*\\}).*");
            Matcher matcher = pattern.matcher(contentStr);

            if (!matcher.find()) {
                logger.info("[warning]========== REG CONTENT NO MATCHER");
                return null;
            }

            String jsonText = matcher.group(1);

            logger.info("[INFO]==========GET JSON TEXT" + jsonText);
            if (jsonText == null)
                return null;

            JSONObject object = JSONObject.fromObject(jsonText);

            InvoiceInfo invoiceInfo = new InvoiceInfo(invoiceRequestInfo);


            logger.info("[INFO]==========CREATE JSON READER");

            invoiceInfo.setKey1(object.getString("key1"))
                    .setKey2(object.getString("key2"))
                    .setKey3(object.getString("key3"))
                    .setKey4(object.getString("key4"))
                    .setKey5(object.getString("key5"))
                    .setKey6(object.getString("key6"))
                    .setKey7(object.getString("key7"))
                    .setKey8(object.getString("key8"))
                    .setKey9(object.getString("key9"))
                    .setKey10(object.getString("key10"))
                    .setKey11(object.getString("key11"));


            return invoiceInfo;
        }

        return null;
    }

    @Override
    public void setIInvoiceInfoConvert(InvoiceRequestInfo invoiceInfoConvert) {
        this.invoiceRequestInfo = invoiceInfoConvert;
        logger.info("[INFO]==========INJECT REQUESTINFO");
    }

}
