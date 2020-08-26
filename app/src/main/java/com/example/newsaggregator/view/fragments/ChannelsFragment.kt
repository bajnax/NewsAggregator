package com.example.newsaggregator.view.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsaggregator.R
import com.example.newsaggregator.app.Constants.ARG_SECTION_NUMBER
import com.example.newsaggregator.databinding.FragmentChannelsBinding
import com.example.newsaggregator.view.adapters.ChannelAdapter
import com.example.newsaggregator.view.common.OnChannelClickListener
import com.example.newsaggregator.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ChannelsFragment : BaseFragment<FragmentChannelsBinding>() {

    val mainViewModel by sharedViewModel<MainViewModel>()

    override val layoutId = R.layout.fragment_channels

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ChannelAdapter(object: OnChannelClickListener {
            override fun onItemClicked(id: String) {
                //todo: request articles by the channel's id
            }

            override fun onFavoriteClicked(selectedState: Boolean, id: String) {
                mainViewModel.updateChannelFavoriteState(selectedState, id)
            }

        })

        binding.channelsRecyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.channelsRecyclerView.adapter = adapter

        mainViewModel.fetchChannels()
        mainViewModel.pagedChannels.observe(viewLifecycleOwner, Observer { pagedChannels ->
            adapter.submitList(pagedChannels)
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(sectionNumber: Int): ChannelsFragment {
            return ChannelsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

}
