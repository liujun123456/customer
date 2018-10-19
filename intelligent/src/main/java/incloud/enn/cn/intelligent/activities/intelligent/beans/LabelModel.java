package incloud.enn.cn.intelligent.activities.intelligent.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AsureLiu on 2017/12/20.
 */

public class LabelModel extends BaseChatBean{
    private String firstTalk;
    private List<LabelItem> itemList=new ArrayList<>();

    public List<LabelItem> getLabelList() {
        return itemList;
    }

    public void setLabelList(List<LabelItem> itemList) {
        this.itemList = itemList;
    }

    public String getFirstTalk(){
        return firstTalk;
    }
    public void setFirstTalk(String firstTalk){
        this.firstTalk=firstTalk;
    }
}
