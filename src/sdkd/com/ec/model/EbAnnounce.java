package sdkd.com.ec.model;

import java.util.Date;

/**
 * Created by Administrator on 2016/7/8.
 */
public class EbAnnounce {
    private Integer eaId;
    private String eaTitle;
    private String eaContent;
    private Date eaCreateTime;

    public Integer getEaId() {
        return eaId;
    }

    public void setEaId(Integer eaId) {
        this.eaId = eaId;
    }

    public String getEaTitle() {
        return eaTitle;
    }

    public void setEaTitle(String eaTitle) {
        this.eaTitle = eaTitle;
    }

    public String getEaContent() {
        return eaContent;
    }

    public void setEaContent(String eaContent) {
        this.eaContent = eaContent;
    }

    public Date getEaCreateTime() {
        return eaCreateTime;
    }

    public void setEaCreateTime(Date eaCreateTime) {
        this.eaCreateTime = eaCreateTime;
    }
}
