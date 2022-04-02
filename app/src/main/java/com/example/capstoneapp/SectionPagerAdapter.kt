package com.example.capstoneapp

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.capstoneapp.home.MoviesFragment

class SectionPagerAdapter(private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> MoviesFragment()
            else -> MoviesFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence = mContext.resources.getString(
        TAB_TITLES[position]
    )

    override fun getCount(): Int = TAB_TITLES.size

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movies,R.string.favorite_movies)
    }
}