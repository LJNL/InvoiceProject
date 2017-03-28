package invoiceImplement;

import datamodels.VerficationCodeRequestInfo;
import invoiceInterface.IProvinceURL;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;

import static util.LogRecord.logger;

/**
 * Created by SPREADTRUM\jiannan.liu on 17-3-28.
 */
public class GetVerficationCodeURL implements IProvinceURL {

    final String PATH = "/WebQuery/yzmQuery";
    final String CALLBACK = "callback";
    final String FPDM = "fpdm";
    final String RAND = "r";
    private VerficationCodeRequestInfo verficationCodeRequestInfo;

    public GetVerficationCodeURL(VerficationCodeRequestInfo verficationCodeRequestInfo) {
        this.verficationCodeRequestInfo = verficationCodeRequestInfo;
    }

    public VerficationCodeRequestInfo getVerficationCodeRequestInfo() {
        return verficationCodeRequestInfo;
    }

    public void setVerficationCodeRequestInfo(VerficationCodeRequestInfo verficationCodeRequestInfo) {
        this.verficationCodeRequestInfo = verficationCodeRequestInfo;
    }

    @Override
    public URI getProvinceURL() {
        String url = getHostAddress(verficationCodeRequestInfo.getInvoiceCode());
        URI uri = null;
        try {
            uri = new URIBuilder(url.toString())
                    .setPath(PATH)
                    .setParameter(CALLBACK, verficationCodeRequestInfo.getCallback())
                    .setParameter(FPDM, verficationCodeRequestInfo.getInvoiceCode())
                    .setParameter(RAND, verficationCodeRequestInfo.getRand())
                    .build();

            logger.fine("[fine]============URL is " + url.toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return uri;
    }
}
