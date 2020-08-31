package br.com.aguiar.aguiarmovies.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import br.com.aguiar.aguiarmovies.R

class PagerAdapter(
    private val context: Context,
    manager: FragmentManager
) : FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragmentList: ArrayList<Fragment> = ArrayList()
    private val title = listOf(R.string.text_acao, R.string.text_drama, R.string.text_fantasia, R.string.text_ficcao)

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.getString(title[position])
    }

    fun addFragment(fragment: Fragment) {
        fragmentList.add(fragment)
    }

}