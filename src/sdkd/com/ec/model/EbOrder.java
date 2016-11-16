package sdkd.com.ec.model;

import java.util.Date;

/**
 * Created by Administrator on 2016/7/9.
 */
public class EbOrder {
    private Integer eoId;
    private Integer eoUserId;
    private String eoUserName;
    private String eoUserAddress;
    private Date eoCreateTime;
    private Double eoCost;
    private Integer eoStatus;
    private Integer eoType;

    public void setEoCost(Double eoCost) {
        this.eoCost = eoCost;
    }

    public Date getEoCreateTime() {
        return eoCreateTime;
    }

    public void setEoCreateTime(Date eoCreateTime) {
        this.eoCreateTime = eoCreateTime;
    }

    public Double getEoCost() {
        return eoCost;
    }

    public void setEoId(Integer eoId) {
        this.eoId = eoId;
    }

    public Integer getEoId() {
        return eoId;
    }

    public void setEoStatus(Integer eoStatus) {
        this.eoStatus = eoStatus;
    }

    public Integer getEoStatus() {
        return eoStatus;
    }

    public void setEoType(Integer eoType) {
        this.eoType = eoType;
    }

    public Integer getEoType() {
        return eoType;
    }

    public void setEoUserAddress(String eoUserAddress) {
        this.eoUserAddress = eoUserAddress;
    }

    public Integer getEoUserId() {
        return eoUserId;
    }

    public void setEoUserId(Integer eoUserId) {
        this.eoUserId = eoUserId;
    }

    public String getEoUserAddress() {
        return eoUserAddress;
    }

    public void setEoUserName(String eoUserName) {
        this.eoUserName = eoUserName;
    }

    public String getEoUserName() {
        return eoUserName;
    }

}
