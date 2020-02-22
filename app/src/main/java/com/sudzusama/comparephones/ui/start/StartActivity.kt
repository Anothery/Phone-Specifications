package com.sudzusama.comparephones.ui.start

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sudzusama.comparephones.R
import com.sudzusama.comparephones.ui.recent.RecentFragment
import com.sudzusama.comparephones.ui.selection.SelectionFragment
import com.sudzusama.comparephones.utils.FragmentLifecycle
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class StartActivity : AppCompatActivity(), StartContract.View, HasSupportFragmentInjector {
    @Inject
    lateinit var presenter: StartPresenter
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    lateinit var viewPager: ViewPager
    lateinit var bottomNavigationView: BottomNavigationView

    lateinit var selectedMenuItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        initViews()
    }

    private fun initViews() {
        bottomNavigationView = findViewById(R.id.activity_start_bottom_navigation)
        viewPager = findViewById(R.id.activity_start_vp)

        setupViewPager()

        viewPager.currentItem = 1
        bottomNavigationView.selectedItemId = R.id.navigation_item_compare
        selectedMenuItem = bottomNavigationView.menu.getItem(1)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_item_recent -> {
                    viewPager.currentItem = 0
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_item_compare -> {
                    viewPager.currentItem = 1
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                selectedMenuItem.isChecked = false
                selectedMenuItem = bottomNavigationView.menu.getItem(position)
                selectedMenuItem.isChecked = true


                val fragment = supportFragmentManager.findFragmentByTag(
                    "android:switcher:"
                            + R.id.activity_start_vp
                            + ":"
                            + viewPager.currentItem
                ) as FragmentLifecycle
                fragment.onResumeFragment()
            }
        })

    }

    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(RecentFragment.newInstance()) // 0
        adapter.addFragment(SelectionFragment.newInstance()) // 1
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 1
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    internal inner class ViewPagerAdapter(manager: FragmentManager) :
        FragmentPagerAdapter(manager) {
        private val mFragmentList: MutableList<Fragment> = ArrayList()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }


        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment) {
            mFragmentList.add(fragment)
        }
    }

}

