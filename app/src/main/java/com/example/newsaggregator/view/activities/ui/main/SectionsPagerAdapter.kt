package com.example.newsaggregator.view.activities.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.newsaggregator.R
import com.example.newsaggregator.view.fragments.ChannelsFragment
import com.example.newsaggregator.view.fragments.FavoriteChannelsFragment
import com.example.newsaggregator.view.fragments.SearchArticlesFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2,
    R.string.tab_text_3
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                ChannelsFragment.newInstance(0)
            }
            1 -> {
                FavoriteChannelsFragment.newInstance(1)
            }
            else -> {
                SearchArticlesFragment.newInstance(2)
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 3
    }
}