package com.sudzusama.comparephones.ui.recent

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sudzusama.comparephones.R
import com.sudzusama.comparephones.utils.FragmentLifecycle
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class RecentFragment : Fragment(), RecentContract.View, FragmentLifecycle {
    @Inject
    lateinit var presenter: RecentPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recent, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    companion object {
        fun newInstance() = RecentFragment()
    }

    override fun onPauseFragment() {}

    //TODO update views on resume
    override fun onResumeFragment() {}
}
