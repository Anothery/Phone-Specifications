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
import com.sudzusama.comparephones.data.model.DeviceInfo
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

        btnChooseFirstDevice.setOnClickListener {
            disableButtons()
            presenter.onChooseFirstDeviceButtonClicked()
        }

        btnChooseSecondDevice.setOnClickListener {
            disableButtons()
            presenter.onChooseSecondDeviceButtonClicked()
        }

        btnCloseFirstDeviceView.setOnClickListener {
            presenter.onCloseFirstDeviceView()
        }
        btnCloseSecondDeviceView.setOnClickListener {
            presenter.onCloseSecondDeviceView()
        }

        btnCompare.setOnClickListener {
            disableButtons()
            presenter.onCompareButtonPressed()
        }
    }

    private fun initViews() {
        btnChooseFirstDevice = findViewById(R.id.btnChooseDevice)
        btnChooseSecondDevice = findViewById(R.id.btnChooseSecondDevice)
        tvFirstDevice = findViewById(R.id.tvFirstDevice)
        tvSecondDevice = findViewById(R.id.tvSecondDevice)
        cvFirstDevice = findViewById(R.id.activity_main_cv_first_device)
        cvSecondDevice = findViewById(R.id.activity_main_cv_second_device)
        btnCompare = findViewById(R.id.btnCompare)
        btnCloseFirstDeviceView = findViewById(R.id.activity_main_btn_close_first_device)
        btnCloseSecondDeviceView = findViewById(R.id.activity_main_btn_close_second_device)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.onViewResult(requestCode, resultCode, data)
    }

    override fun loadFirstDeviceInfo(deviceName: String) {
        //TODO 1
        tvFirstDevice.text = deviceName
    }

    override fun loadSecondDeviceInfo(deviceName: String) {
        //TODO 1
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
        enableButtons()
    }

    override fun startComparingActivity(firstDevice: DeviceInfo, secondDevice: DeviceInfo) {
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
