package com.sudzusama.comparephones.ui.deviceinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sudzusama.comparephones.DEVICE_EXTRA
import com.sudzusama.comparephones.R
import com.sudzusama.comparephones.domain.entities.Device
import com.sudzusama.comparephones.domain.entities.Specification
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class DeviceInfoFragment : Fragment(), DeviceInfo.View {
    @Inject
    lateinit var presenter: DeviceInfo.Presenter

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DeviceInfoListAdapter

    private var specifications = ArrayList<Specification>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_device, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
        setupRecyclerView(view)
        val device: Device? = arguments?.getParcelable(DEVICE_EXTRA)
        if (device != null) {
            presenter.onCreate(specifications, device)
        } else {
            //TODO presenter.OnError
        }
    }

    override fun updateRecyclerView() {
        adapter.notifyDataSetChanged()
    }

    private fun setupViews(view: View) {

    }

    private fun setupRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.deviceinfo_rv)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        adapter = DeviceInfoListAdapter(specifications)
        recyclerView.adapter = adapter
    }


}
