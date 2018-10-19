package incloud.enn.cn.intelligent.activities.inquisition.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import incloud.enn.cn.intelligent.R
import incloud.enn.cn.intelligent.activities.inquisition.holder.InquisitionHolder

/**
 * @author  AsureLiu on 2018/9/19.
 */
class InquisitionAdapter(var mContext:Context):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun getItemCount(): Int=5

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder= InquisitionHolder(View.inflate(mContext, R.layout.inquisition_item,null))
}
