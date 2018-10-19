package incloud.enn.cn.intelligent.activities.intelligent.view

import incloud.enn.cn.intelligent.activities.intelligent.beans.AnswerModel
import incloud.enn.cn.intelligent.activities.intelligent.beans.LabelModel

/**
 * @author  AsureLiu on 2018/9/8.
 */
interface IntelligentView{
    fun mscValue(value:String)

    fun onChat(isSuccess: Boolean,data:List<AnswerModel>,index:Int)

    fun onLabel(data: LabelModel)
}