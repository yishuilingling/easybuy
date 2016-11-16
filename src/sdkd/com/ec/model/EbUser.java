package sdkd.com.ec.model;

import javax.xml.bind.PrintConversionEvent;

/**
 * Created by sdust on 2016/7/6.
 */
public class EbUser {
    private int euUserId;
    private String euUserName;
    private String euUserPassword;
    private String euUserSex;
    private String euBrithday;
    private String euIdentityCode;
    private String euEmail;
    private String euMobile;
    private String euAddress;
    private int euStatus;

    public String getEuUserSex() {
        return euUserSex;
    }

    public String getEuBrithday() {
        return euBrithday;
    }

    public String getEuIdentityCode() {
        return euIdentityCode;
    }

    public String getEuEmail() {
        return euEmail;
    }

    public String getEuMobile() {
        return euMobile;
    }

    public String getEuAddress() {
        return euAddress;
    }

    public int getEuStatus() {
        return euStatus;
    }

    public void setEuUserSex(String euUserSex) {
        this.euUserSex = euUserSex;
    }

    public void setEuBrithday(String euBrithday) {
        this.euBrithday = euBrithday;
    }

    public void setEuIdentityCode(String euIdentityCode) {
        this.euIdentityCode = euIdentityCode;
    }

    public void setEuEmail(String euEmail) {
        this.euEmail = euEmail;
    }

    public void setEuMobile(String euMobile) {
        this.euMobile = euMobile;
    }

    public void setEuAddress(String euAddress) {
        this.euAddress = euAddress;
    }

    public void setEuStatus(int euStatus) {
        this.euStatus = euStatus;
    }

    public void setEuUserId(int euUserId) {
        this.euUserId = euUserId;
    }

    public void setEuUserName(String euUserName) {
        this.euUserName = euUserName;
    }

    public void setEuUserPassword(String euUserPassword) {
        this.euUserPassword = euUserPassword;
    }

    public int getEuUserId() {

        return euUserId;
    }

    public String getEuUserName() {
        return euUserName;
    }

    public String getEuUserPassword() {
        return euUserPassword;
    }
}
