package datamodels;

import java.time.LocalDateTime;


/**
 * Created by mac on 2017/3/27.
 */
public class VerficationCodeInfo {
    private VerficationCodeInfo verficationCodeInfo;
    private String imageBase64;
    private LocalDateTime localDateTime;
    private String index;
    private String sign;

    public VerficationCodeInfo getVerficationCodeInfo() {
        return verficationCodeInfo;
    }

    public void setVerficationCodeInfo(VerficationCodeInfo verficationCodeInfo) {
        this.verficationCodeInfo = verficationCodeInfo;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
