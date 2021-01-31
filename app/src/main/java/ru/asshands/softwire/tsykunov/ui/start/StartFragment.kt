package ru.asshands.softwire.tsykunov.ui.start

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import ru.asshands.softwire.tsykunov.R
import ru.asshands.softwire.tsykunov.databinding.FragmentRandomBinding
import ru.asshands.softwire.tsykunov.databinding.FragmentStartBinding
import ru.asshands.softwire.tsykunov.ui.hot.HotFragment
import ru.asshands.softwire.tsykunov.ui.latest.LatestFragment
import ru.asshands.softwire.tsykunov.ui.random.RandomFragment
import ru.asshands.softwire.tsykunov.ui.top.TopFragment

class StartFragment : Fragment(R.layout.fragment_start) {

    private val bind get() = _bind!!
    private var _bind: FragmentStartBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _bind = FragmentStartBinding.bind(view)
        val pagerAdapter = PagerAdapter(requireActivity().supportFragmentManager)
        bind.fragmentStartViewPager.adapter = pagerAdapter
        bind.fragmentStartTabLayout.setupWithViewPager(bind.fragmentStartViewPager)
    }

    override fun onDestroyView() {
        _bind = null
        super.onDestroyView()
    }
}

class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(
    fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    private val PAGE_COUNT = 4
    private val tabTitles = listOf("Random", "Latest", "Top", "Hot")

    override fun getCount(): Int = PAGE_COUNT

    override fun getItem(i: Int): Fragment {
        return when (i) {
            0 -> RandomFragment()
            1 -> LatestFragment()
            2 -> TopFragment()
            3 -> HotFragment()
            else -> throw IllegalArgumentException()
        }

    }

    override fun getPageTitle(position: Int): CharSequence {
        return tabTitles[position]
    }
}