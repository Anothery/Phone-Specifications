package com.sudzusama.comparephones.ui.selection

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.sudzusama.comparephones.R
import com.sudzusama.comparephones.domain.entities.Device
import com.sudzusama.comparephones.ui.adddevice.AddDeviceActivity
import com.sudzusama.comparephones.ui.comparing.ComparingActivity
import com.sudzusama.comparephones.utils.FIRST_DEVICE_EXTRA
import com.sudzusama.comparephones.utils.FragmentLifecycle
import com.sudzusama.comparephones.utils.SECOND_DEVICE_EXTRA
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class SelectionFragment : Fragment(), SelectionContract.View, FragmentLifecycle {
    @Inject
    lateinit var presenter: SelectionContract.Presenter
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
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_selection, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)

        btnChooseFirstDevice.setOnClickListener { presenter.onChooseFirstDeviceButtonClicked() }
        btnChooseSecondDevice.setOnClickListener { presenter.onChooseSecondDeviceButtonClicked() }
        btnCloseFirstDeviceView.setOnClickListener { presenter.onCloseFirstDeviceView() }
        btnCloseSecondDeviceView.setOnClickListener { presenter.onCloseSecondDeviceView() }
        btnCompare.setOnClickListener { presenter.onCompareButtonPressed() }
    }

    private fun initViews(view: View) {
        btnChooseFirstDevice = view.findViewById(R.id.main_btn_choosedevice)
        btnChooseSecondDevice = view.findViewById(R.id.main_btn_chooseseconddevice)
        tvFirstDevice = view.findViewById(R.id.main_tv_firstdevice)
        tvSecondDevice = view.findViewById(R.id.main_tv_seconddevice)
        cvFirstDevice = view.findViewById(R.id.main_cv_first_device)
        cvSecondDevice = view.findViewById(R.id.main_cv_second_device)
        btnCompare = view.findViewById(R.id.main_btn_compare)
        btnCloseFirstDeviceView = view.findViewById(R.id.main_btn_close_first_device)
        btnCloseSecondDeviceView = view.findViewById(R.id.main_btn_close_second_device)
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
        val intent = Intent(activity, ComparingActivity::class.java)
        intent.putExtra(FIRST_DEVICE_EXTRA, firstDevice)
        intent.putExtra(SECOND_DEVICE_EXTRA, secondDevice)
        startActivity(intent)
    }

    override fun startAddDeviceActivity(requestCode: Int) {
        val intent = Intent(activity, AddDeviceActivity::class.java)
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

    companion object {
        fun newInstance(): SelectionFragment {
            return SelectionFragment()
        }
    }

    override fun onPauseFragment() {}

    override fun onResumeFragment() {}


}
