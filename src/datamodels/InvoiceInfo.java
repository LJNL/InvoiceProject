package datamodels;

/**
 * Created by mac on 2017/3/27.
 */
public class InvoiceInfo {
    private String invoiceCode;
    private String invoiceNumber;
    private String date;
    private String checkCode;

    public InvoiceInfo(String invoiceCode, String invoiceNumber, String date, String checkCode) {
        this.invoiceCode = invoiceCode;
        this.invoiceNumber = invoiceNumber;
        this.date = date;
        this.checkCode = checkCode;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public InvoiceInfo setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
        return this;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public InvoiceInfo setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
        return this;
    }

    public String getDate() {
        return date;
    }

    public InvoiceInfo setDate(String date) {
        this.date = date;
        return this;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public InvoiceInfo setCheckCode(String checkCode) {
        this.checkCode = checkCode;
        return this;
    }
}
