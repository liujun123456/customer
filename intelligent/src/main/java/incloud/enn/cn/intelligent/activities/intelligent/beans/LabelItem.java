package incloud.enn.cn.intelligent.activities.intelligent.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AsureLiu on 2018/5/20.
 */

public class LabelItem {
    public String  androidAction;
    public String  iconUrl;
    public int  id;
    public String  iosAction;
    public String  itemDesc;
    public String  itemName;
    public String  webUrl;
    public List<ConditionItem> conditionList=new ArrayList<>();

}
