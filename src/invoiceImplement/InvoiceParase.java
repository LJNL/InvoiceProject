package invoiceImplement;

import datamodels.InvoiceInfo;
import datamodels.InvoiceResult;
import invoiceInterface.IInvoiceResult;

import static util.LogRecord.logger;

/**
 * Created by SPREADTRUM\jiannan.liu on 17-3-31.
 */
public class InvoiceParase implements IInvoiceResult {
    public InvoiceParase() {
    }

    @Override


    public InvoiceResult getInvoiceResult(InvoiceInfo invoiceInfo) {

        String sign = invoiceInfo.getKey1();

        if (sign.equals("001")) {

            logger.info("[INFO]========== INVOICE SUCCESS");

        } else if (sign.equals("002")) {

            logger.info("[INFO]==========超过该张发票当日查验次数(请于次日再次查验)");

        } else if (sign.equals("003")) {

            logger.info("[INFO]==========发票查验请求太频繁，请稍后再试！");

        } else if (sign.equals("004")) {

            logger.info("[INFO]==========超过服务器最大请求数，请稍后访问!");

        } else if (sign.equals("005")) {

            logger.info("[INFO]==========请求不合法!");

        } else if (sign.equals("020")) {

            logger.info("[INFO]==========由于查验行为异常，涉嫌违规，当前无法使用查验服务！");

        } else if (sign.equals("006")) {

            logger.info("[INFO]==========不一致");

        } else if (sign.equals("007")) {

            logger.info("[INFO]==========验证码失效!");

        } else if (sign.equals("008")) {

            logger.info("[INFO]==========验证码错误!");

        } else if (sign.equals("009")) {

            logger.info("[INFO]==========查无此票");

        } else if (sign.equals("rqerr")) {

            logger.info("[INFO]==========当日开具发票可于次日进行查验！");

        } else if (sign.equals("010")) {

            logger.info("[INFO]==========网络超时，请重试！");

        } else if (sign.equals("010_")) {

            logger.info("[INFO]==========网络超时，请重试！");

        } else {

            logger.info("[INFO]==========网络超时，请重试！");
        }


        return new InvoiceResult(sign, invoiceInfo);
    }
}
