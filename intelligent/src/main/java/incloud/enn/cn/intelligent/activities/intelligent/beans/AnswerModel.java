package incloud.enn.cn.intelligent.activities.intelligent.beans;

import java.util.ArrayList;
import java.util.List;


/**
 * @author AsureLiu on 2017/12/20.
 */

public class AnswerModel extends BaseChatBean{
    public String androidAction;
    public String answer;
    public int entityId;
    public int factorId;
    public int intentId;
    public int intentType;
    public String iosAction;
    public String url;
    public int userId;
    public List<ConditionItem> conditionList=new ArrayList<>();
    public String iconUrl;
    public List<DietItem> dietList=new ArrayList<>();

}
