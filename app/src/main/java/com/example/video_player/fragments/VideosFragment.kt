package com.example.video_player.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.video_player.R
import com.example.video_player.adapters.VideoAdapter
import com.example.video_player.databinding.FragmentVideoBinding

class VideoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_video, container, false)
        val binding = FragmentVideoBinding.bind(view)
        val tempList = ArrayList<String>()
        tempList.add("ayan")
        tempList.add("syan")
        tempList.add("cyan")
        binding.rvVideos.setHasFixedSize(true)
        binding.rvVideos.setItemViewCacheSize(10)
        binding.rvVideos.layoutManager = LinearLayoutManager(requireContext())
        binding.rvVideos.adapter = VideoAdapter(requireContext(), tempList)

        return view
    }
}