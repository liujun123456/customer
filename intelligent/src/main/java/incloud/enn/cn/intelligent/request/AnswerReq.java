package incloud.enn.cn.intelligent.request;

/**
 * @author AsureLiu on 2017/12/20.
 */

public class AnswerReq {
    private int entityId=0;  //领域/实体ID
    private int factorId=0;  //因素ID
    private int intentId=0;  //意图ID
    private int intentType=0;  //意图类型
    private String question;
    private int userId=1;


    public AnswerReq(int entityId, int factorId, int intentId, int intentType, String question, int userId){
        this.entityId=entityId;
        this.factorId=factorId;
        this.intentId=intentId;
        this.intentType=intentType;
        this.question=question;
        this.userId=userId;
    }


}
