package datamodels;

/**
 * Created by SPREADTRUM\jiannan.liu on 17-3-31.
 */
public class InvoiceResult {
    private InvoiceInfo invoiceInfo;
    private String sign;
    //暂时不管
    private String number;

    public InvoiceResult(String sign, InvoiceInfo invoiceInfo) {
        this.sign = sign;
        this.invoiceInfo = invoiceInfo;
    }

    public InvoiceInfo getInvoiceInfo() {
        return invoiceInfo;
    }

    public void setInvoiceInfo(InvoiceInfo invoiceInfo) {
        this.invoiceInfo = invoiceInfo;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }


}
