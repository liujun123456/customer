package incloud.enn.cn.intelligent.activities.inquisition.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import incloud.enn.cn.intelligent.activities.inquisition.InquisitionFragment

/**
 * @author  AsureLiu on 2018/9/19.
 */
class InquisitionTypeAdapter(var manager:FragmentManager):FragmentPagerAdapter(manager) {
    override fun getItem(position: Int): Fragment {
        var fragment=InquisitionFragment()
        return fragment
    }

    override fun getCount(): Int=2

    override fun getPageTitle(position: Int): CharSequence {
        return if (position==0) "等待中" else "已完成"
    }
}