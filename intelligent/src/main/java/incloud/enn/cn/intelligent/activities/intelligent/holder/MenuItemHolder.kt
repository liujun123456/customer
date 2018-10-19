package incloud.enn.cn.intelligent.activities.intelligent.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import incloud.enn.cn.intelligent.activities.intelligent.beans.LabelItem
import kotlinx.android.synthetic.main.chat_menu_child_item.view.*

/**
 * @author  AsureLiu on 2018/9/10.
 */
class MenuItemHolder(var view:View):RecyclerView.ViewHolder(view){
    fun setData(item: LabelItem){
        view.item_img.showSizeImage(item.iconUrl)
    }
}