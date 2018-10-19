package incloud.enn.cn.intelligent.activities.intelligent.holder

import android.content.Context
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import incloud.enn.cn.commonlib.utils.ScreenUtils
import incloud.enn.cn.intelligent.activities.intelligent.adapter.ChatMenuAdapter
import incloud.enn.cn.intelligent.activities.intelligent.adapter.MenuItemDecoration
import incloud.enn.cn.intelligent.activities.intelligent.beans.LabelModel
import kotlinx.android.synthetic.main.chat_menu_item.view.*

/**
 * @author  AsureLiu on 2018/9/10.
 */
class MenuHolder(var view: View,var mContext:Context): RecyclerView.ViewHolder(view){
    var mAdapter: ChatMenuAdapter=ChatMenuAdapter(mContext,arrayListOf())

    init {
        val mLayoutManager= LinearLayoutManager(mContext)
        mLayoutManager.orientation=LinearLayoutManager.HORIZONTAL
        view.menu_list.layoutManager=mLayoutManager
        view.menu_list.itemAnimator = DefaultItemAnimator()
        view.menu_list.setHasFixedSize(true)
        view.menu_list.addItemDecoration(MenuItemDecoration( ScreenUtils.dip2px(mContext,10f),true))
        view.menu_list.adapter=mAdapter

    }

    fun setData(model:LabelModel){
        mAdapter.mData=model.labelList
    }
}