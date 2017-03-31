package Scence;

import datamodels.*;
import invoiceImplement.*;
import invoiceInterface.IGetForInvoiceType;
import invoiceInterface.IProvinceURL;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.apache.http.client.ResponseHandler;
import util.HttpManager;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

import static util.LogRecord.logger;

/**
 * Created by SPREADTRUM\jiannan.liu on 17-3-29.
 */
public class FXMLController implements Initializable, IGetForInvoiceType {


    // components
    @FXML
    private ImageView VerficationCodeImg;
    @FXML
    private TextField InvoiceCodeTf;
    @FXML
    private TextField InvoiceNumberTf;
    @FXML
    private TextField InvoiceDateTf;
    @FXML
    private TextField InvoiceChecksumTf;
    @FXML
    private TextField VerficationCodeTf;
    @FXML
    private Button FreshBtn;
    @FXML
    private Button CommitBtn;
    @FXML
    private Button CancelBtn;
    @FXML
    private Label checksumLab;
    @FXML
    private Label verficationCodeLab;


    private String invoiceType;
    private VerficationCodeResult verficationCodeResult;
    private VerficationCodeInfo verficationCodeInfo;


    // event handler
    // analyze Invoice type
    @FXML
    public void invoiceCodeAnalyseHandler() {
        invoiceType = getForInvoiceType(InvoiceCodeTf.getText());
    }

    //exit btn
    @FXML
    public void cancelHandler(MouseEvent mouseEvent) {

    }

    //commit
    @FXML
    public void commitHandler(MouseEvent mouseEvent) {
        // check not null
        if (verficationCodeInfo == null)
            return;
        if (VerficationCodeTf.getText() == null)
            return;
        if (invoiceType == null)
            return;
        if (InvoiceNumberTf.getText() == null)
            return;
        if (InvoiceDateTf.getText() == null)
            return;
        if (InvoiceChecksumTf.getText() == null)
            return;


        //1.data
        verficationCodeResult = new VerficationCodeResult(verficationCodeInfo, VerficationCodeTf.getText());
        InvoiceInputInfo invoiceInputInfo = new InvoiceInputInfo(InvoiceCodeTf.getText()
                , InvoiceNumberTf.getText()
                , InvoiceDateTf.getText()
                , InvoiceChecksumTf.getText());
        InvoiceRequestInfo invoiceRequestInfo = new InvoiceRequestInfo(invoiceType, invoiceInputInfo, verficationCodeResult);


        //2.HTTP GET
        //(1) use http manager
        HttpManager httpManager = HttpManager.getInstance();
        //(2)get URL
        IProvinceURL provinceURL = new GetInvoiceInquireURL(invoiceRequestInfo);
        URI uri = provinceURL.getProvinceURL();

        if (uri == null)
            return;
        //(3)make response handler
        ResponseHandler<InvoiceInfo> rs = new InvoiceHandler();

        //(4)exec http
        InvoiceInfo invoiceInfo = httpManager.httpProcess(uri, rs);

        //(5)InvoiceResult
        InvoiceResult invoiceResult = new InvoiceParase().getInvoiceResult(invoiceInfo);

        logger.info("[INFO]========== COMMIT FINISHED");
    }

    //Verfication Code request
    @FXML
    public void requestVercifationCodeHandler(MouseEvent mouseEvent) {

        //get Verification code image
        //(1) use httpmanager
        HttpManager httpManager = HttpManager.getInstance();
        //(2) getURL
        VerficationCodeRequestInfo vcq = new VerficationCodeRequestInfo(InvoiceCodeTf.getText());
        IProvinceURL provinceURL = new GetVerficationCodeURL(vcq);
        URI uri = provinceURL.getProvinceURL();

        if (uri == null)
            return;

        //(3)make response handler
        ResponseHandler<VerficationCodeInfo> rs = new VerficationCodeHandler(vcq);

        //(4)exec http
        VerficationCodeInfo vci = httpManager.httpProcess(uri, rs);
        this.verficationCodeInfo = vci;

        //(5)IGetForImage
        VerficationCodeParse recognition = new VerficationCodeParse();
        VerficationCodeImg.setImage(new Image(new ByteArrayInputStream(recognition.getForImage(vci))));

        //(6) 用户输入hint
        verficationCodeLab.setText(recognition.getForImageSelectColor(vci));

        logger.info("[INFO]==========VERFICATION CODE FINISHED");
    }
    @FXML
    public void freshVercifationCodeHandler(KeyEvent keyEvent) {
        requestVercifationCodeHandler(null);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logger.info("[INFO]========== ==========INIT UI LOGICAL");
    }

    //analyze InvoiceType
    @Override
    public String getForInvoiceType(String code) {
        logger.info("[INFO]==========INVOICE CODE " + code);
        String invoiceType = "99";
        char tempstr;
        if (code.length() == 12) {
            if (invoiceType.equals("99")) {
                //增加判断，判断是否为新版电子票
                if (code.charAt(0) == '0' && code.substring(10, 12).equals("11")) {
                    invoiceType = "10";
                }
                if (code.charAt(0) == '0' && (code.substring(10, 12).equals("06") || code.substring(10, 12).equals("07"))) {
                    //判断是否为卷式发票  第1位为0且第11-12位为06或07
                    invoiceType = "11";
                }
            }
            if (invoiceType.equals("99")) { //如果还是99，且第8位是2，则是机动车发票
                if (code.charAt(7) == '2' && code.charAt(0) != '0') {
                    invoiceType = "03";
                }
            }
        } else if (code.length() == 10) {
            tempstr = code.charAt(7);
            if (tempstr == '1' || tempstr == '5') {
                invoiceType = "01";
            } else if (tempstr == '6' || tempstr == '3') {
                invoiceType = "04";
            } else if (tempstr == '7' || tempstr == '2') {
                invoiceType = "02";
            }
        } else {
            logger.warning("[warning]==========INVOICE TYPE ERROR");
        }
        logger.info("[INFO]==========INVOICE TYPE " + invoiceType);
        return invoiceType;
    }


    public ImageView getVerficationCodeImg() {
        return VerficationCodeImg;
    }
}
