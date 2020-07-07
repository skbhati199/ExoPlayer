package com.infoskillstechnology.sinetop.ui.discover.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.infoskillstechnology.sinetop.base.BaseFragment
import com.infoskillstechnology.sinetop.ui.discover.viewmodel.DiscoverViewModel
import com.infoskillstechnology.sinetop.R

class DiscoverFragment : BaseFragment(R.layout.fragment_discover) {
    private lateinit var viewModel: DiscoverViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DiscoverViewModel::class.java)
    }
}