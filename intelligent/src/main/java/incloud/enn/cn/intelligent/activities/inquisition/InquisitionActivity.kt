package incloud.enn.cn.intelligent.activities.inquisition

import incloud.enn.cn.commonlib.BaseActivity
import incloud.enn.cn.intelligent.R
import incloud.enn.cn.intelligent.activities.inquisition.adapter.InquisitionTypeAdapter
import kotlinx.android.synthetic.main.activity_inquisition_layout.*
import kotlinx.android.synthetic.main.title_evaluate_layout.*

/**
 * @author  AsureLiu on 2018/9/19.
 */
class InquisitionActivity:BaseActivity(){

    override fun getToolBarResID(): Int=R.layout.title_evaluate_layout

    override fun setTitleColor(): Int=R.color.white

    override fun getMainContentResId(): Int= R.layout.activity_inquisition_layout

    override fun isKeepFullScreen(): Boolean=false

    var mAdapter:InquisitionTypeAdapter?=null

    override fun afterView() {
        initTitle()
        mAdapter=InquisitionTypeAdapter(supportFragmentManager)
        inquisition_pager.adapter=mAdapter
        inquisition_tab.setupWithViewPager(inquisition_pager)
    }

    private fun initTitle(){
        title_name.text="我的问诊"
        cancel.setOnClickListener { finish() }
    }


}