package datamodels;

/**
 * Created by mac on 2017/3/27.
 */
public class InvoiceInfo {
    private String invoiceCode;
    private String invoiceNumber;
    private String date;
    private String checkCode;

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public String getDate() {
        return date;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }
}
