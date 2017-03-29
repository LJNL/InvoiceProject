//import datamodels.InvoiceInfo;
//import datamodels.VerficationCodeInfo;
//import datamodels.VerficationCodeRequestInfo;
//import datamodels.VerficationCodeResult;
//import invoiceImplement.GetVerficationCodeURL;
//import invoiceImplement.VerficationCodeHandler;
//import invoiceImplement.VerficationCodeRecognition;
//import invoiceInterface.IProvinceURL;
//import org.apache.http.client.ResponseHandler;
//import util.HttpManager;
//import java.net.URI;
//
///**
// * Created by SPREADTRUM\jiannan.liu on 17-3-28.
// */
//public class Scence {
//    public static void main(String[] args) {
//        // 1.user input a Invoice
//
//        InvoiceInfo inv = new InvoiceInfo("4403161320", "88888888", "20170301", "666666");
//
//        //2.get Verification code image
//        //(1) use httpmanager
//        HttpManager httpManager = HttpManager.getInstance();
//
//        //(2) getURL
//        VerficationCodeRequestInfo vcq = new VerficationCodeRequestInfo(inv.getInvoiceCode());
//        IProvinceURL provinceURL = new GetVerficationCodeURL(vcq);
//        URI uri = provinceURL.getProvinceURL();
//
//        //(3)make response handler
//        ResponseHandler<VerficationCodeInfo> rs = new VerficationCodeHandler(vcq);
//
//        //(4)exec http
//        VerficationCodeInfo vci = httpManager.httpProcess(uri, rs);
//
//        //(5)IGetForImage
//
//        VerficationCodeRecognition recognition = new VerficationCodeRecognition();
//
//
//        //(6) 用户输入
//        // TODO: 17-3-28
//        String result = null;
//        //(7)获取结果
//        VerficationCodeResult v = recognition.VerficationCodeResultConvert(vci, result);
//
//
//    }
//}
