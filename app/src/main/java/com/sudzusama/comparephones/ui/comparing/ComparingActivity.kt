package com.sudzusama.comparephones.ui.comparing

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sudzusama.comparephones.R
import com.sudzusama.comparephones.domain.entity.Specification
import dagger.android.AndroidInjection
import javax.inject.Inject

class ComparingActivity : AppCompatActivity(), ComparingContract.View {
    @Inject
    lateinit var presenter: ComparingContract.Presenter

    private lateinit var adapter: ComparingListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var toolbar: Toolbar
    lateinit var tvFirstDevice: TextView
    lateinit var tvSecondDevice: TextView
    private var specifications: ArrayList<Specification> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comparing)
        setupViews()
        setupRecyclerView()
        setupToolbar()
        presenter.onCreate(specifications)
    }

    private fun setupViews() {
        tvFirstDevice = findViewById(R.id.activity_comparing_tv_first)
        tvSecondDevice = findViewById(R.id.activity_comparing_tv_second)
    }

    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.activity_comparing_rv)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ComparingListAdapter(specifications)
        recyclerView.adapter = adapter
    }

    override fun updateRecyclerView() {
        adapter.notifyDataSetChanged()
    }

    private fun setupToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setFirstDeviceTitle(title: String) {
        tvFirstDevice.text = title
    }

    override fun setSecondDeviceTitle(title: String) {
        tvSecondDevice.text = title
    }
}
