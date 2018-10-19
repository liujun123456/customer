package incloud.enn.cn.intelligent.view.visitwindow

import android.content.Context
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.PopupWindow
import incloud.enn.cn.intelligent.R
import kotlinx.android.synthetic.main.visit_name_list_layout.view.*

/**
 * @author  AsureLiu on 2018/9/7.
 */
class VisitNameWindow(var mContext:Context):PopupWindow(){
    var view:View?=null
    var mAdapter:VisitNameAdapter?=null
    init {
        view= View.inflate(mContext,R.layout.visit_name_list_layout,null)
        var layoutManager=LinearLayoutManager(mContext)
        layoutManager.orientation=LinearLayoutManager.VERTICAL
        view?.visit_name_list?.layoutManager=layoutManager
        // 设置Item增加、移除默认动画
        view?.visit_name_list?.itemAnimator = DefaultItemAnimator()
        view?.visit_name_list?.setHasFixedSize(true)
        contentView=view
        isFocusable = true
        animationStyle = R.style.Animation
    }
    fun setData(){
        mAdapter=VisitNameAdapter(mContext)
        view?.visit_name_list?.adapter=mAdapter
    }
}
