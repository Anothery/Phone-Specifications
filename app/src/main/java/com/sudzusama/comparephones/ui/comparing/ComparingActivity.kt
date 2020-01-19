package com.sudzusama.comparephones.ui.comparing

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.sudzusama.comparephones.DEVICE_EXTRA
import com.sudzusama.comparephones.R
import com.sudzusama.comparephones.data.model.DeviceInfo
import com.sudzusama.comparephones.ui.device.DeviceFragment
import dagger.android.AndroidInjection
import javax.inject.Inject

class ComparingActivity : AppCompatActivity(), Comparing.View {

    @Inject lateinit var presenter: Comparing.Presenter

    private lateinit var vpDevices : ViewPager
    private lateinit var tlDevices : TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comparing)
        setupViews()
        presenter.onCreate(intent)
    }

    private fun setupViews(){
        vpDevices = findViewById(R.id.vpDevices)
        tlDevices = findViewById(R.id.tlDevices)
    }


    override fun setupViewPager(
        firstDevice: DeviceInfo,
        secondDevice: DeviceInfo,
        firstDeviceName: String,
        secondDeviceName: String
    ) {
        val adapter = ViewPagerAdapter(supportFragmentManager)

        val firstFragment = DeviceFragment()
        val firstDeviceBundle = Bundle()
        firstDeviceBundle.putParcelable(DEVICE_EXTRA, firstDevice)
        firstFragment.arguments = firstDeviceBundle

        val secondFragment = DeviceFragment()
        val secondDeviceBundle = Bundle()
        secondDeviceBundle.putParcelable(DEVICE_EXTRA, secondDevice)
        secondFragment.arguments = secondDeviceBundle

        adapter.addFragment(firstFragment,firstDeviceName)
        adapter.addFragment(secondFragment,secondDeviceName)

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

}
