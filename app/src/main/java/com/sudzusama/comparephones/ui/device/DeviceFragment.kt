package com.sudzusama.comparephones.ui.device

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.sudzusama.comparephones.DEVICE_EXTRA
import com.sudzusama.comparephones.R
import com.sudzusama.comparephones.data.model.DeviceInfo
import javax.inject.Inject

class DeviceFragment : Fragment(), Device.View {
    @Inject
    lateinit var presenter: Device.Presenter

    private lateinit var tvTest : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_device, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)

        val device:DeviceInfo? = arguments?.getParcelable(DEVICE_EXTRA)
        tvTest.text = device?.DeviceName
    }

    private fun setupViews(view: View){
        tvTest = view.findViewById(R.id.tvTestTest)
    }


}
