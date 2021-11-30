package com.example.tmdbapp.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tmdbapp.R
import com.example.tmdbapp.views.PopularMoviesFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(activity: AppCompatActivity) :
    FragmentStateAdapter (activity){
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int) = PopularMoviesFragment.newInstance(position)
}