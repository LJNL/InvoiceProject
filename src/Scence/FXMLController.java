package Scence;

import datamodels.VerficationCodeInfo;
import datamodels.VerficationCodeRequestInfo;
import datamodels.VerficationCodeResult;
import invoiceImplement.GetVerficationCodeURL;
import invoiceImplement.VerficationCodeHandler;
import invoiceImplement.VerficationCodeRecognition;
import invoiceInterface.IProvinceURL;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
public class FXMLController implements Initializable {

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


    public ImageView getVerficationCodeImg() {
        return VerficationCodeImg;
    }


    @FXML
    public void cancelHandler(MouseEvent mouseEvent) {
    }

    @FXML
    public void commitHandler(MouseEvent mouseEvent) {
    }

    @FXML
    public void requestVercifationCodeHandler(MouseEvent mouseEvent) {
        //1.InvoiceInfo inv = new InvoiceInfo("4403161320", "88888888", "20170301", "666666");
        System.out.println("hello");
        InvoiceCodeTf.setText("4403161320");

        //2.get Verification code image
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


        //(5)IGetForImage
        VerficationCodeRecognition recognition = new VerficationCodeRecognition();
        VerficationCodeImg.setImage(new Image(new ByteArrayInputStream(recognition.getForImage(vci))));


        //(6) 用户输入
        // TODO: 17-3-28
        String display = recognition.selectDisplay(vci);
        String result = "test";
        VerficationCodeTf.setText(display);
        result = VerficationCodeTf.getText();


        //(7)获取结果
        VerficationCodeResult v = recognition.VerficationCodeResultConvert(vci, result);

        logger.info("[INFO]==========TEST FINISHED");
    }

    @FXML
    public void freshVercifationCodeHandler(KeyEvent keyEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logger.info("[INFO]========== ==========INIT UI LOGICAL");
    }
}
