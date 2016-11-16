package sdkd.com.ec.model;

/**
 * Created by Administrator on 2016/7/11.
 */
public class EbOrderDetail {
    private Integer eodId;
    private Integer eoId;
    private Integer epId;
    private String epName;
    private Integer eodQuantity;
    private double eodCost;

    public void setEpName(String epName) {
        this.epName = epName;
    }

    public String getEpName() {
        return epName;
    }

    public void setEoId(Integer eoId) {
        this.eoId = eoId;
    }

    public Integer getEoId() {
        return eoId;
    }

    public void setEodCost(double eodCost) {
        this.eodCost = eodCost;
    }

    public double getEodCost() {
        return eodCost;
    }

    public void setEodId(Integer eodId) {
        this.eodId = eodId;
    }

    public Integer getEodId() {
        return eodId;
    }

    public void setEodQuantity(Integer eodQuantity) {
        this.eodQuantity = eodQuantity;
    }

    public Integer getEodQuantity() {
        return eodQuantity;
    }

    public void setEpId(Integer epId) {
        this.epId = epId;
    }

    public Integer getEpId() {
        return epId;
    }
}
