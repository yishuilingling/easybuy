package sdkd.com.ec.model;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/7/14.
 */
public class EbPOrder {
    private Integer eoId;
    private Date eoCreateTime;
    private Double eoCost;
    private Integer eoStatus;
    private List<EbOrderDetail> proList;

    public void setEoId(Integer eoId) {
        this.eoId = eoId;
    }

    public Integer getEoId() {
        return eoId;
    }

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

    public void setEoStatus(Integer eoStatus) {
        this.eoStatus = eoStatus;
    }

    public Integer getEoStatus() {
        return eoStatus;
    }

    public void setProList(List<EbOrderDetail> proList) {
        this.proList = proList;
    }

    public List<EbOrderDetail> getProList() {
        return proList;
    }
}
