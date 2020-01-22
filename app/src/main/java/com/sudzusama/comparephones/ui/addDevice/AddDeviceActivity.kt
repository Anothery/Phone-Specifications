package com.sudzusama.comparephones.ui.addDevice

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.widget.textChanges
import com.sudzusama.comparephones.utils.DEVICE_EXTRA
import com.sudzusama.comparephones.R
import com.sudzusama.comparephones.domain.entities.Device
import dagger.android.AndroidInjection
import javax.inject.Inject

class AddDeviceActivity : AppCompatActivity(), AddDevice.View {

    @Inject
    lateinit var presenter: AddDevice.Presenter
    private lateinit var etInsertDevice: EditText
    private lateinit var tvMatchesCount: TextView
    private lateinit var toolbar : Toolbar

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AddDeviceMatchesAdapter
    private var matches = ArrayList<Device>()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_device)

        setupRecyclerView()
        setupViews()
        setupToolbar()
        presenter.onCreate(matches)
        presenter.observeFromText(etInsertDevice.textChanges())
    }

    private fun setupToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.adddevice_rv)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = AddDeviceMatchesAdapter(matches) { presenter.onDeviceItemClicked(it) }
        recyclerView.adapter = adapter
    }

    private fun setupViews() {
        etInsertDevice = findViewById(R.id.adddevice_et_insert)
        tvMatchesCount = findViewById(R.id.adddevice_tv_matchescount)
    }


    override fun disableMatchesCount() {
        tvMatchesCount.visibility = View.GONE
    }

    override fun enableMatchesCount() {
        tvMatchesCount.visibility = View.VISIBLE
    }

    override fun setMatchesCount(matchesCount: Int) {
        tvMatchesCount.text = resources.getString(R.string.devices_found, matchesCount)
    }

    override fun updateRecyclerView() {
        adapter.notifyDataSetChanged()
    }

    override fun finishActivity(result: Device) {
        val intent = Intent()
        intent.putExtra(DEVICE_EXTRA, result)
        setResult(1, intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
