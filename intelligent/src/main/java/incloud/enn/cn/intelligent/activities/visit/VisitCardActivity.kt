package incloud.enn.cn.intelligent.activities.visit

import android.content.Intent
import android.os.Handler
import android.view.ViewGroup
import incloud.enn.cn.commonlib.BaseActivity
import incloud.enn.cn.intelligent.R
import incloud.enn.cn.intelligent.activities.intelligent.IntelligentActivity
import incloud.enn.cn.intelligent.view.visitwindow.VisitNameWindow
import kotlinx.android.synthetic.main.activity_visit_card_layout.*

/**
 * @author  AsureLiu on 2018/9/7.
 */
class VisitCardActivity: BaseActivity(){

    private var mWindow: VisitNameWindow?=null

    override fun getToolBarResID(): Int= R.layout.title_visit_card_layout

    override fun setTitleColor(): Int =R.color.all_transparent

    override fun getMainContentResId(): Int= R.layout.activity_visit_card_layout

    override fun isKeepFullScreen(): Boolean=false

    var handler:Handler=Handler()

    override fun afterView() {
        root_content.setBackgroundResource(R.mipmap.visit_card_bg)
        visit_commit.setOnClickListener { startActivity(Intent(mContext,IntelligentActivity::class.java)) }
        visit_name.setOnClickListener {
            if (mWindow==null){
                mWindow=VisitNameWindow(mContext)
                mWindow?.setOnDismissListener {
                    handler.postDelayed(object :Runnable{
                        override fun run() {
                            visit_name.setBackgroundResource(R.drawable.visit_name_bg)
                        }

                    },100)

                }
            }
            mWindow?.height= ViewGroup.LayoutParams.WRAP_CONTENT
            mWindow?.width=visit_name.width
            mWindow?.setData()
            mWindow?.showAsDropDown(visit_name)
            visit_name.setBackgroundResource(R.drawable.visit_name_half_top_bg)

        }


    }


}