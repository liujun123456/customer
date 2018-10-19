package incloud.enn.cn.intelligent.activities.intelligent.adapter

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * @author  AsureLiu on 2018/9/10.
 */
class MenuItemDecoration(var spacing:Int,var includeEdge:Boolean):RecyclerView.ItemDecoration(){

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view) // item position
        if (includeEdge){
            if (position < 1) { // top edge
                outRect.left = spacing
            }
            outRect.right = spacing
        }else{
            outRect.right = spacing
        }

    }

}