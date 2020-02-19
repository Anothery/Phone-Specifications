package com.sudzusama.comparephones.ui.start

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sudzusama.comparephones.R
import com.sudzusama.comparephones.ui.selection.SelectionFragment
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

    lateinit var selectionFragment : SelectionFragment

    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        initViews(savedInstanceState)
    }

    private fun initViews(savedInstanceState: Bundle?) {
        bottomNavigationView = findViewById(R.id.activity_start_bottom_navigation)

        if (savedInstanceState == null) {
            selectionFragment = SelectionFragment.newInstance()
            supportFragmentManager.beginTransaction().replace(
                R.id.activity_start_frame_layout,
                selectionFragment,
                selectionFragment.javaClass.simpleName
            ).commit()
            bottomNavigationView.selectedItemId = R.id.bottom_navigation_item_compare
        }

        //TODO move logic to presenter
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_navigation_item_recent -> {
                }
                R.id.bottom_navigation_item_compare -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.activity_start_frame_layout,
                            selectionFragment,
                            selectionFragment.javaClass.simpleName
                        ).commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.bottom_navigation_item_devices -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.activity_start_frame_layout,
                            selectionFragment,
                            selectionFragment.javaClass.simpleName
                        ).commit()
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }
}

