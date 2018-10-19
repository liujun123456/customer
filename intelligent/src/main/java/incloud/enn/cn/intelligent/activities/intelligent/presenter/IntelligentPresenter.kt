package incloud.enn.cn.intelligent.activities.intelligent.presenter

import android.content.Context
import incloud.enn.cn.intelligent.activities.intelligent.model.IntelligentListener
import incloud.enn.cn.intelligent.activities.intelligent.model.IntelligentModel
import incloud.enn.cn.intelligent.activities.intelligent.model.IntelligentModelImpl
import incloud.enn.cn.intelligent.activities.intelligent.view.IntelligentView
import incloud.enn.cn.intelligent.activities.intelligent.beans.AnswerModel
import incloud.enn.cn.intelligent.activities.intelligent.beans.LabelModel
import incloud.enn.cn.intelligent.request.AnswerReq
import incloud.enn.cn.intelligent.request.LabelReq

/**
 * @author  AsureLiu on 2018/9/10.
 */
class IntelligentPresenter(var mContext:Context,var view:IntelligentView):IntelligentListener{

    var reqModel:IntelligentModel = IntelligentModelImpl(this)

    fun doChat(entityId:Int,factorId:Int,intentId:Int,intentType:Int,question:String,userID:Int,index:Int){
        var req=AnswerReq(entityId,factorId,intentId,intentType,question,userID)
        reqModel.getChat(req,index)
    }
    override fun onChat(isSuccess: Boolean, data: List<AnswerModel>?,index: Int) {
         view.onChat(isSuccess,data?: arrayListOf(),index)
    }

    fun doLabel(userID:Int){
        var req=LabelReq(userID)
        reqModel.getLabel(req)
    }
    override fun onLabel(isSuccess: Boolean, data: LabelModel?) {
        if (isSuccess&&data!=null){
            view.onLabel(data)
        }
    }

}
