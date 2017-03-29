package invoiceImplement;

import datamodels.VerficationCodeInfo;
import datamodels.VerficationCodeResult;
import invoiceInterface.IGetForImage;
import invoiceInterface.IVerficationCodeResult;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.OutputStream;

import static util.LogRecord.logger;

/**
 * Created by SPREADTRUM\jiannan.liu on 17-3-28.
 */
public class VerficationCodeRecognition implements IVerficationCodeResult, IGetForImage {
    @Override
    public VerficationCodeResult VerficationCodeResultConvert(VerficationCodeInfo verficationCodeInfo, String result) {
        if (result != null)
            return new VerficationCodeResult(verficationCodeInfo, result);
        return null;
    }

    @Override
    public byte[] getForImage(VerficationCodeInfo verficationCodeInfo) {

        if (errorHandler(verficationCodeInfo)) {
            return null;
        }
        // selectDisplay(verficationCodeInfo);

        return imageDecode(verficationCodeInfo);
    }

    public String selectDisplay(VerficationCodeInfo verficationCodeInfo) {
        String display;
        String sign = verficationCodeInfo.getSign();
        if (sign == "00") {
            display = "请输入验证码文字";
        } else if (sign == "01") {
            display = "请输入验证码图片中红色文字";
        } else if (sign == "02") {
            display = "请输入验证码图片中黄色文字";
        } else if (sign == "03") {
            display = "请输入验证码图片中蓝色文字";
        } else {
            display = "";
        }
        logger.info("[INFO]========== ==========" + display);
        return display;
    }

    private boolean errorHandler(VerficationCodeInfo verficationCodeInfo) {
        String errorCode = verficationCodeInfo.getImageBase64();
        if (errorCode == "003") {
            logger.warning("[warning]==========验证码请求次数过于频繁，请1分钟后再试！");
        } else if (errorCode == "005") {
            logger.warning("[warning]==========非法请求!");
        } else if (errorCode == "010") {
            logger.warning("[warning]==========网络超时，请重试！(01)");
        } else if (errorCode == "fpdmerr") {
            logger.warning("[warning]==========请输入合法发票代码!");
        } else if (errorCode == "024") {
            logger.warning("[warning]==========24小时内验证码请求太频繁，请稍后再试！");
        } else if (errorCode == "016") {
            logger.warning("[warning]==========服务器接收的请求太频繁，请稍后再试！");
        } else if (errorCode == "020") {
            logger.warning("[warning]==========由于查验行为异常，涉嫌违规，当前无法使用查验服务！");
        } else if (errorCode == "") {

        } else {
            logger.info("[INFO]==========" + "GET IMAGE CODE");
            return false; //ok
        }

        return true;
    }

    private byte[] imageDecode(VerficationCodeInfo verficationCodeInfo) {
        String imgStr = verficationCodeInfo.getImageBase64();
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
//            for (int i = 0; i < b.length; ++i) {
//                if (b[i] < 0) {//调整异常数据
//                    b[i] += 256;
//                }
//            }
            logger.info("[INFO]==========" + "GET IMAGE CODE");
            String imgFilePath = "/home/local/SPREADTRUM/jiannan.liu/Invoice/InvoiceProject/out/222.png";//新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return b;
        } catch (Exception e) {
            logger.warning("[warning]========== ==========" + "IMAGE DECODE FAILED");
            e.printStackTrace();
            return null;
        }

    }
}
