package incloud.enn.cn.intelligent.response;

import java.util.List;

import incloud.enn.cn.commonlib.retrofit.bean.EnnResponseData;
import incloud.enn.cn.intelligent.activities.intelligent.beans.AnswerModel;


/**
 * @author AsureLiu on 2017/12/20.
 */

public class AnswerResponse extends EnnResponseData {
    private List<AnswerModel> data;

    public List<AnswerModel> getData() {
        return data;
    }

    public void setData(List<AnswerModel> data) {
        this.data = data;
    }
}
