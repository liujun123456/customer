package incloud.enn.cn.intelligent.response;


import incloud.enn.cn.commonlib.retrofit.bean.EnnResponseData;
import incloud.enn.cn.intelligent.activities.intelligent.beans.LabelModel;

/**
 * @author AsureLiu on 2017/12/20.
 */

public class LabelResponse extends EnnResponseData {
    private LabelModel data;

    public LabelModel getData() {
        return data;
    }

    public void setData(LabelModel data) {
        this.data = data;
    }
}
