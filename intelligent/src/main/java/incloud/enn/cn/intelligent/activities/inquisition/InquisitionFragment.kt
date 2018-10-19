package incloud.enn.cn.intelligent.activities.inquisition

import android.os.Handler
import android.os.Message
import incloud.enn.cn.commonlib.BaseFragment
import incloud.enn.cn.commonlib.utils.ScreenUtils
import incloud.enn.cn.commonlib.view.GridSpacingItemDecoration
import incloud.enn.cn.commonlib.view.sRecyclerView.SRecyclerListener
import incloud.enn.cn.intelligent.R
import incloud.enn.cn.intelligent.activities.inquisition.adapter.InquisitionAdapter
import kotlinx.android.synthetic.main.frag_inquisition_layout.*

/**
 * @author  AsureLiu on 2018/9/19.
 */
class InquisitionFragment:BaseFragment() {

    override fun createView(): Int= R.layout.frag_inquisition_layout

    override fun createTitle(): Int=0

    override fun setTitleColor(): Int=0

    var mAdapter:InquisitionAdapter?=null

    var handler:Handler=object :Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            inquisition_list.complete()
        }
    }
    override fun afterView() {
        initRecyclerView()
    }
    private fun initRecyclerView(){
        mAdapter=InquisitionAdapter(mContext)
        inquisition_list.mRecyclerView.adapter=mAdapter
        inquisition_list.mRecyclerView.addItemDecoration(GridSpacingItemDecoration(1,ScreenUtils.dip2px(mContext,10f),false))
        inquisition_list.setSRecyclerListener(object :SRecyclerListener{
            override fun loadMore() {
                handler.sendEmptyMessageDelayed(1000,1500)
            }

            override fun refresh() {

                handler.sendEmptyMessageDelayed(1000,1500)
            }

        })
    }

}