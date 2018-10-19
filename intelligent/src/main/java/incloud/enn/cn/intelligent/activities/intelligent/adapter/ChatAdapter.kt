package incloud.enn.cn.intelligent.activities.intelligent.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import incloud.enn.cn.intelligent.R
import incloud.enn.cn.intelligent.activities.intelligent.beans.AnswerModel
import incloud.enn.cn.intelligent.activities.intelligent.beans.BaseChatBean
import incloud.enn.cn.intelligent.activities.intelligent.beans.ChatItemType
import incloud.enn.cn.intelligent.activities.intelligent.beans.LabelModel
import incloud.enn.cn.intelligent.activities.intelligent.holder.*


/**
 * @author  AsureLiu on 2018/9/10.
 */
class ChatAdapter(var mContext:Context,var data:MutableList<BaseChatBean>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun getItemCount(): Int =data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        when(holder){
            is RobotMsgHolder->holder.setData(data[position] as AnswerModel)
            is MineMsgHolder->holder.setData(data[position] as AnswerModel)
            is MineVoiceHolder->holder.setData(data[position] as AnswerModel)
            is MineStateHolder->holder.setData()
            is MenuHolder->holder.setData(data[position] as LabelModel)
            is EvaluateHolder->holder.setData()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        var viewHolder: RecyclerView.ViewHolder? = null
        when(viewType){
            ChatItemType.ROBOT_MSG_TYPE->viewHolder=RobotMsgHolder(View.inflate(mContext,R.layout.chat_rebot_msg_item,null))
            ChatItemType.MINE_MSG_TYPE->viewHolder=MineMsgHolder(View.inflate(mContext,R.layout.chat_mine_msg_item,null),mContext)
            ChatItemType.MINE_VOICE_TYPE->viewHolder=MineVoiceHolder(View.inflate(mContext,R.layout.chat_mine_voice_item,null))
            ChatItemType.MINE_STATE_TYPE->viewHolder=MineStateHolder(View.inflate(mContext,R.layout.chat_mine_state_item,null))
            ChatItemType.MENU_TYPE->viewHolder=MenuHolder(View.inflate(mContext,R.layout.chat_menu_item,null),mContext)
            ChatItemType.EVALUATE_TYPE->viewHolder=EvaluateHolder(View.inflate(mContext,R.layout.chat_evaluate_item,null))
            ChatItemType.REPORT_TYPE->viewHolder=ReportHolder(View.inflate(mContext,R.layout.chat_report_item,null))
            ChatItemType.ABNORMAL_TYPE->viewHolder=AbNormalHolder(View.inflate(mContext,R.layout.chat_abnormal_item,null))
            ChatItemType.END_TYPE->viewHolder=EndHolder(View.inflate(mContext,R.layout.chat_end_item,null))
        }
        return viewHolder

    }

    override fun getItemViewType(position: Int): Int {
        return data[position].itemType
    }

}