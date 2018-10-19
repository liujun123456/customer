package incloud.enn.cn.intelligent.activities.intelligent.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import incloud.enn.cn.intelligent.activities.intelligent.beans.AnswerModel
import kotlinx.android.synthetic.main.chat_rebot_msg_item.view.*

/**
 * @author  AsureLiu on 2018/9/10.
 */
class RobotMsgHolder(var view: View): RecyclerView.ViewHolder(view){
    fun setData(model:AnswerModel){
        view.robot_msg.text=model.answer
    }
}