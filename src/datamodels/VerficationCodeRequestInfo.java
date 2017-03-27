package datamodels;

/**
 * Created by mac on 2017/3/27.
 */
public class VerficationCodeRequestInfo {
    private String callback;
    private String invoiceCode;
    private double rand;
    private long timestamps;

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public double getRand() {
        return rand;
    }

    public void setRand(double rand) {
        rand=Math.random();
    }

    public long getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(long timestamps) {
        timestamps=System.currentTimeMillis();
    }
}
