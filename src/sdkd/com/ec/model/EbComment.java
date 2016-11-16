package sdkd.com.ec.model;

/**
 * Created by gkl on 2016/7/12.
 */
public class EbComment {
    private int ecId;
    private String ecReply;
    private String ecContent;
    private String ecCreateTime;
    private String ecReplyTime;
    private String ecNickName;

    public int getEcId() {
        return ecId;
    }

    public void setEcId(int ecId) {
        this.ecId = ecId;
    }

    public String getEcReply() {
        return ecReply;
    }

    public void setEcReply(String ecReply) {
        this.ecReply = ecReply;
    }

    public String getEcContent() {
        return ecContent;
    }

    public void setEcContent(String ecContent) {
        this.ecContent = ecContent;
    }

    public String getEcCreateTime() {
        return ecCreateTime;
    }

    public void setEcCreateTime(String ecCreateTime) {
        this.ecCreateTime = ecCreateTime;
    }

    public String getEcReplyTime() {
        return ecReplyTime;
    }

    public void setEcReplyTime(String ecReplyTime) {
        this.ecReplyTime = ecReplyTime;
    }

    public String getEcNickName() {
        return ecNickName;
    }

    public void setEcNickName(String ecNickName) {
        this.ecNickName = ecNickName;
    }
}
