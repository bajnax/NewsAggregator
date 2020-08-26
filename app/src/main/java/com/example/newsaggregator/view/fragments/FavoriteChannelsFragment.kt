package com.example.newsaggregator.view.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.newsaggregator.R
import com.example.newsaggregator.app.Constants
import com.example.newsaggregator.databinding.FragmentFavoriteChannelsBinding
import com.example.newsaggregator.view.adapters.ChannelAdapter
import com.example.newsaggregator.view.common.OnChannelClickListener
import com.example.newsaggregator.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FavoriteChannelsFragment : BaseFragment<FragmentFavoriteChannelsBinding>() {

    override val layoutId = R.layout.fragment_favorite_channels

    val mainViewModel by sharedViewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel.setIndex(arguments?.getInt(Constants.ARG_SECTION_NUMBER) ?: 1)
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

        binding.favoriteChannelsRecyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.favoriteChannelsRecyclerView.adapter = adapter

        mainViewModel.fetchFavoriteChannels()
        mainViewModel.pagedFavoriteChannels.observe(viewLifecycleOwner, Observer { pagedChannels ->
            adapter.submitList(pagedChannels)
        })
    }

    companion object {

        @JvmStatic
        fun newInstance(sectionNumber: Int): FavoriteChannelsFragment {
            return FavoriteChannelsFragment().apply {
                arguments = Bundle().apply {
                    putInt(Constants.ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}
