package incloud.enn.cn.intelligent.view.visitwindow

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import incloud.enn.cn.commonlib.utils.LogUtil
import incloud.enn.cn.commonlib.utils.ScreenUtils
import incloud.enn.cn.intelligent.R

/**
 * @author  AsureLiu on 2018/9/7.
 */
class VisitNameAdapter(var mContext:Context):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun getItemCount(): Int=3

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder{
        var view=View.inflate(mContext, R.layout.visit_name_item,null)
        view.layoutParams=LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,ScreenUtils.dip2px(mContext,50f))
        return VisitNameHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as VisitNameHolder
        holder.setData("nihao")
        LogUtil.e("position")
    }

}