package com.sudzusama.comparephones.ui.comparing

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.sudzusama.comparephones.utils.DEVICE_EXTRA
import com.sudzusama.comparephones.R
import com.sudzusama.comparephones.domain.entities.Device
import com.sudzusama.comparephones.ui.deviceinfo.DeviceInfoFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class ComparingActivity : AppCompatActivity(), ComparingContract.View, HasSupportFragmentInjector {
    @Inject
    lateinit var presenter: ComparingContract.Presenter
    @Inject
    lateinit var dispatchingAndroidInjector : DispatchingAndroidInjector<Fragment>

    private lateinit var vpDevices: ViewPager
    private lateinit var tlDevices: TabLayout
    private lateinit var toolbar : Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comparing)
        setupToolbar()
        setupViews()
        presenter.onCreate(intent)
    }

    private fun setupToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupViews() {
        vpDevices = findViewById(R.id.comparing_vp_devices)
        tlDevices = findViewById(R.id.comparing_tl_devices)
    }


    override fun setupViewPager(
        firstDevice: Device,
        secondDevice: Device,
        firstDeviceName: String,
        secondDeviceName: String
    ) {
        val adapter = ViewPagerAdapter(supportFragmentManager)

        val firstFragment = DeviceInfoFragment()
        val firstDeviceBundle = Bundle()
        firstDeviceBundle.putParcelable(DEVICE_EXTRA, firstDevice)
        firstFragment.arguments = firstDeviceBundle

        val secondFragment = DeviceInfoFragment()
        val secondDeviceBundle = Bundle()
        secondDeviceBundle.putParcelable(DEVICE_EXTRA, secondDevice)
        secondFragment.arguments = secondDeviceBundle

        adapter.addFragment(firstFragment, firstDeviceName)
        adapter.addFragment(secondFragment, secondDeviceName)

        vpDevices.adapter = adapter
        tlDevices.setupWithViewPager(vpDevices)

    }

    internal inner class ViewPagerAdapter(manager: FragmentManager) :
        FragmentPagerAdapter(manager) {
        private val mFragmentList: MutableList<Fragment> = ArrayList()
        private val mFragmentTitleList: MutableList<String> = ArrayList()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }
}
