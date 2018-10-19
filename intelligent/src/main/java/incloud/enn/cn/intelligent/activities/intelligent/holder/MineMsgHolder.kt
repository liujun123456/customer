package incloud.enn.cn.intelligent.activities.intelligent.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import incloud.enn.cn.intelligent.R
import incloud.enn.cn.intelligent.activities.intelligent.beans.AnswerModel
import incloud.enn.cn.intelligent.activities.intelligent.beans.ChatMsgType
import kotlinx.android.synthetic.main.chat_mine_msg_item.view.*

/**
 * @author  AsureLiu on 2018/9/10.
 */
class MineMsgHolder(var view: View,var mContext: Context): RecyclerView.ViewHolder(view){
    var anim:Animation=AnimationUtils.loadAnimation(mContext,R.anim.load_rotate_anim)

    fun setData(model: AnswerModel){
        if (model.msgType==ChatMsgType.MSG_LOADING){
            view.seed_mark.visibility=View.VISIBLE
            view.seed_mark.startAnimation(anim)
            view.seed_mark.setImageResource(R.mipmap.loading_ios)
        }else if (model.msgType==ChatMsgType.MSG_FAIL){
            view.seed_mark.visibility=View.VISIBLE
            anim.cancel()
            view.seed_mark.setImageResource(R.mipmap.seed_fail)
        }else{
            anim.cancel()
            view.seed_mark.visibility=View.INVISIBLE
        }
        view.mine_msg.text=model.answer
    }
}