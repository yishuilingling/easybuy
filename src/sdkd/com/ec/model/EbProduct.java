package sdkd.com.ec.model;

/**
 * Created by 10929 on 2016/7/6.
 */
public class EbProduct {
    private int epId;
    private String epName;
    private String epDescription;
    private double epPrice;
    private String epStock;
    private int epcId;
    private int epcChildId;
    private String epFileName;
    private int epScan;
    private int ep_discount;

    public String getEpStock() {
        return epStock;
    }

    public int getEpScan() {
        return epScan;
    }

    public int getEp_discount() {
        return ep_discount;
    }

    public void setEpStock(String epStock) {
        this.epStock = epStock;
    }

    public void setEpScan(int epScan) {
        this.epScan = epScan;
    }

    public void setEp_discount(int ep_discount) {
        this.ep_discount = ep_discount;
    }

    public void setEpId(int epId) {
        this.epId = epId;
    }

    public void setEpName(String epName) {
        this.epName = epName;
    }

    public void setEpPrice(double epprice) {
        this.epPrice = epprice;
    }

    public void setEpDescription(String epDescription) {
        this.epDescription = epDescription;
    }

    public void setEpstock(String epstock) {
        this.epStock = epstock;
    }

    public void setEpcId(int epcId) {
        this.epcId = epcId;
    }

    public void setEpcChildId(int epcChildId) {
        this.epcChildId = epcChildId;
    }

    public void setEpFileName(String epFileName) {
        this.epFileName = epFileName;
    }

    public int getEpId() {
        return epId;
    }

    public String getEpName() {
        return epName;
    }

    public String getEpDescription() {
        return epDescription;
    }

    public double getEpPrice() {
        return epPrice;
    }

    public String getEpstock() {
        return epStock;
    }

    public int getEpcId() {
        return epcId;
    }

    public int getEpcChildId() {
        return epcChildId;
    }

    public String getEpFileName() {
        return epFileName;
    }
}
