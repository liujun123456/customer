package incloud.enn.cn.intelligent.activities.evaluate

import android.view.View
import incloud.enn.cn.commonlib.BaseActivity
import incloud.enn.cn.intelligent.R
import kotlinx.android.synthetic.main.title_evaluate_layout.*

/**
 * @author  AsureLiu on 2018/9/19.
 */
class EvaluateActivity:BaseActivity(){

    override fun getToolBarResID(): Int =R.layout.title_evaluate_layout

    override fun setTitleColor(): Int = R.color.white

    override fun getMainContentResId(): Int =R.layout.activity_evaluate_layout

    override fun isKeepFullScreen(): Boolean =false

    override fun afterView() {
        initTitle()
    }

    private fun initTitle(){
        title_name.text="我的评价"
        title_division.visibility=View.VISIBLE
        cancel.setOnClickListener { finish() }
    }

}