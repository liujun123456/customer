package incloud.enn.cn.intelligent.request;

/**
 * @author AsureLiu on 2017/12/20.
 */

public class LabelReq {
    private int userId=1;

    public LabelReq(int userId){
        this.userId=userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
