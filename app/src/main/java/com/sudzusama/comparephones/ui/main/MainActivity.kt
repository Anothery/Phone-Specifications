package com.sudzusama.comparephones.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.sudzusama.comparephones.FIRST_DEVICE_EXTRA
import com.sudzusama.comparephones.R
import com.sudzusama.comparephones.SECOND_DEVICE_EXTRA
import com.sudzusama.comparephones.data.model.Device
import com.sudzusama.comparephones.ui.comparing.ComparingActivity
import com.sudzusama.comparephones.ui.addDevice.AddDeviceActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

//TODO kotlin ext

class MainActivity : AppCompatActivity(), Main.View {
    @Inject
    lateinit var presenter: Main.Presenter
    private lateinit var btnChooseFirstDevice: Button
    private lateinit var btnChooseSecondDevice: Button
    private lateinit var cvFirstDevice: CardView
    private lateinit var cvSecondDevice: CardView
    private lateinit var tvFirstDevice: TextView
    private lateinit var tvSecondDevice: TextView
    private lateinit var btnCompare: Button
    private lateinit var btnCloseFirstDeviceView: Button
    private lateinit var btnCloseSecondDeviceView: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        btnChooseFirstDevice.setOnClickListener { presenter.onChooseFirstDeviceButtonClicked() }
        btnChooseSecondDevice.setOnClickListener { presenter.onChooseSecondDeviceButtonClicked() }
        btnCloseFirstDeviceView.setOnClickListener { presenter.onCloseFirstDeviceView() }
        btnCloseSecondDeviceView.setOnClickListener { presenter.onCloseSecondDeviceView() }
        btnCompare.setOnClickListener { presenter.onCompareButtonPressed() }
    }

    private fun initViews() {
        btnChooseFirstDevice = findViewById(R.id.main_btn_choosedevice)
        btnChooseSecondDevice = findViewById(R.id.main_btn_chooseseconddevice)
        tvFirstDevice = findViewById(R.id.main_tv_firstdevice)
        tvSecondDevice = findViewById(R.id.main_tv_seconddevice)
        cvFirstDevice = findViewById(R.id.main_cv_first_device)
        cvSecondDevice = findViewById(R.id.main_cv_second_device)
        btnCompare = findViewById(R.id.main_btn_compare)
        btnCloseFirstDeviceView = findViewById(R.id.main_btn_close_first_device)
        btnCloseSecondDeviceView = findViewById(R.id.main_btn_close_second_device)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.onViewResult(requestCode, resultCode, data)
    }

    override fun loadFirstDeviceInfo(deviceName: String) {
        //TODO Image
        tvFirstDevice.text = deviceName
    }

    override fun loadSecondDeviceInfo(deviceName: String) {
        //TODO Image
        tvSecondDevice.text = deviceName
    }

    override fun disableButtons() {
        btnCompare.isEnabled = false
        btnChooseFirstDevice.isEnabled = false
        btnChooseSecondDevice.isEnabled = false
    }


    override fun enableButtons() {
        btnCompare.isEnabled = true
        btnChooseFirstDevice.isEnabled = true
        btnChooseSecondDevice.isEnabled = true
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun startComparingActivity(firstDevice: Device, secondDevice: Device) {
        val intent = Intent(this@MainActivity, ComparingActivity::class.java)
        intent.putExtra(FIRST_DEVICE_EXTRA, firstDevice)
        intent.putExtra(SECOND_DEVICE_EXTRA, secondDevice)
        startActivity(intent)
    }

    override fun startAddDeviceActivity(requestCode: Int) {
        val intent = Intent(this@MainActivity, AddDeviceActivity::class.java)
        startActivityForResult(intent, requestCode)
    }

    override fun disableFirstDeviceButton() {
        btnChooseFirstDevice.visibility = View.GONE
    }

    override fun enableFirstDeviceButton() {
        btnChooseFirstDevice.visibility = View.VISIBLE
    }

    override fun disableSecondDeviceButton() {
        btnChooseSecondDevice.visibility = View.GONE
    }

    override fun enableSecondDeviceButton() {
        btnChooseSecondDevice.visibility = View.VISIBLE
    }

    override fun disableSecondDeviceView() {
        cvSecondDevice.visibility = View.GONE
    }

    override fun enableSecondDeviceView() {
        cvSecondDevice.visibility = View.VISIBLE
    }

    override fun disableFirstDeviceView() {
        cvFirstDevice.visibility = View.GONE
    }

    override fun enableFirstDeviceView() {
        cvFirstDevice.visibility = View.VISIBLE
    }

    override fun enableCompareButton() {
        btnCompare.visibility = View.VISIBLE
    }

    override fun disableCompareButton() {
        btnCompare.visibility = View.GONE
    }
}
