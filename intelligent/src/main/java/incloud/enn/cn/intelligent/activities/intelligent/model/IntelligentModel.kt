package incloud.enn.cn.intelligent.activities.intelligent.model

import incloud.enn.cn.intelligent.request.AnswerReq
import incloud.enn.cn.intelligent.request.LabelReq

/**
 * @author  AsureLiu on 2018/9/10.
 */
interface IntelligentModel{
    fun getChat(req: AnswerReq,index:Int)

    fun getLabel(req:LabelReq)
}