package com.sudzusama.comparephones.ui.recent

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sudzusama.comparephones.R
import com.sudzusama.comparephones.domain.entity.Comparsion
import com.sudzusama.comparephones.domain.entity.Device
import com.sudzusama.comparephones.ui.comparing.ComparingActivity
import com.sudzusama.comparephones.utils.COMPARSION_ID_EXTRA
import com.sudzusama.comparephones.utils.FragmentLifecycle
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class RecentFragment : Fragment(), RecentContract.View, FragmentLifecycle {
    @Inject
    lateinit var presenter: RecentContract.Presenter

    lateinit var rvRecentComparsions: RecyclerView
    lateinit var rvRecentDevices: RecyclerView
    lateinit var tvNoComparsions: TextView
    lateinit var tvNoDevices: TextView

    private val recentComparsions = ArrayList<Comparsion>()
    private val recentDevices = ArrayList<Device>()

    private lateinit var recentComparsionsAdapter: RecentComparsionsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        retainInstance = true
        return inflater.inflate(R.layout.fragment_recent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupRvRecentComparsions()
        presenter.onAttach(this)
        presenter.onViewCreated(recentComparsions, recentDevices)
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }

    private fun setupViews() {
        rvRecentComparsions = activity!!.findViewById(R.id.recent_rv_comparsions)
        rvRecentDevices = activity!!.findViewById(R.id.recent_rv_devices)
        tvNoComparsions = activity!!.findViewById(R.id.recent_tv_no_comparsions_label)
        tvNoDevices = activity!!.findViewById(R.id.recent_tv_no_devices_label)
    }

    private fun setupRvRecentComparsions() {
        rvRecentComparsions.layoutManager = LinearLayoutManager(activity)
        recentComparsionsAdapter =
            RecentComparsionsAdapter(recentComparsions) {
                presenter.onRecentComparsionsItemClicked(it)
            }
        rvRecentComparsions.adapter = recentComparsionsAdapter
    }

    private fun setupRvRecentDevices() {
        //todo
    }

    override fun startComparingActivity(id: Int) {
        val intent = Intent(activity, ComparingActivity::class.java)
        intent.putExtra(COMPARSION_ID_EXTRA, id)
        startActivity(intent)
    }

    override fun updateRecentComparsionsList() {
        recentComparsionsAdapter.notifyDataSetChanged()
    }

    override fun showRecentComparsionsList() {
        rvRecentComparsions.visibility = View.VISIBLE
        tvNoComparsions.visibility = View.GONE
    }

    override fun hideRecentComparsionsList() {
        rvRecentComparsions.visibility = View.GONE
        tvNoComparsions.visibility = View.VISIBLE
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    companion object {
        val TAG: String = RecentFragment::class.java.simpleName
        fun newInstance(): RecentFragment {
            return RecentFragment()
        }
    }

    override fun onPauseFragment() {}

    override fun onResumeFragment() {
        presenter.onViewResumed()
    }
}
