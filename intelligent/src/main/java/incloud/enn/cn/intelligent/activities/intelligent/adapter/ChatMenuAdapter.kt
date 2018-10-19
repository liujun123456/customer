package incloud.enn.cn.intelligent.activities.intelligent.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import incloud.enn.cn.intelligent.R
import incloud.enn.cn.intelligent.activities.intelligent.beans.LabelItem
import incloud.enn.cn.intelligent.activities.intelligent.holder.MenuItemHolder

/**
 * @author  AsureLiu on 2018/9/10.
 */
class ChatMenuAdapter(var mContext:Context,var mData:MutableList<LabelItem>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        holder as MenuItemHolder
        holder.setData(mData[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder= MenuItemHolder(View.inflate(mContext,R.layout.chat_menu_child_item,null))

    override fun getItemCount(): Int =mData.size

}