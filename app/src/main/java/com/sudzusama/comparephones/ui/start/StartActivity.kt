package com.sudzusama.comparephones.ui.start

import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationItemView
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
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var presenter: StartContract.Presenter

    lateinit var viewPager: ViewPager
    lateinit var bottomNavigationView: BottomNavigationView

    lateinit var selectionFragment: SelectionFragment
    lateinit var recentFragment: RecentFragment
    lateinit var selectedMenuItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        presenter.onAttach(this)

        val fm = supportFragmentManager

        if (savedInstanceState != null) {
            recentFragment = fm.findFragmentByTag(getPagerFragmentTag(0)) as RecentFragment
            selectionFragment = fm.findFragmentByTag(getPagerFragmentTag(1)) as SelectionFragment
        } else {
            recentFragment = RecentFragment.newInstance()
            selectionFragment = SelectionFragment.newInstance()
        }

        setupViews()
        setupViewPager()
        setStartFragment()

        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                this@StartActivity.onPageSelected(position)
            }
        })
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    private fun setStartFragment() {
        viewPager.currentItem = 1
        bottomNavigationView.selectedItemId = R.id.navigation_item_compare
        selectedMenuItem = bottomNavigationView.menu.getItem(1)
    }


    private fun setupViews() {
        bottomNavigationView = findViewById(R.id.activity_start_bottom_navigation)
        viewPager = findViewById(R.id.activity_start_vp)
    }

    private fun onPageSelected(position: Int) {
        selectedMenuItem.isChecked = false
        selectedMenuItem = bottomNavigationView.menu.getItem(position)
        selectedMenuItem.isChecked = true

        val fragment =
            supportFragmentManager.findFragmentByTag(getPagerFragmentTag(viewPager.currentItem)) as FragmentLifecycle

        fragment.onResumeFragment()
    }

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_item_recent -> {
                    viewPager.currentItem = 0
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_item_compare -> {
                    viewPager.currentItem = 1
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_item_devices -> {
                    val toast = Toast.makeText(this, "Currently in development", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.BOTTOM, 0, 250)
                    toast.show()
                }
            }
            false
        }

    private fun setupViewPager() {
        val adapter = BottomNavigationAdapter(supportFragmentManager)
        adapter.count = 2
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 2
    }

    private fun getPagerFragmentTag(position: Int): String {
        return "android:switcher:" + R.id.activity_start_vp + ":" + position
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    inner class BottomNavigationAdapter(manager: FragmentManager) :
        FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        private var tabCount = 0

        override fun getCount(): Int {
            return tabCount
        }

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> recentFragment
                1 -> selectionFragment
                else -> throw IndexOutOfBoundsException()
            }
        }

        fun setCount(count: Int) {
            this.tabCount = count
        }
    }

}

