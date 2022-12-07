package com.example.timewarpscan.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.timewarpscan.R
import com.example.timewarpscan.adapters.TrendingAdapter
import com.example.timewarpscan.databinding.FragmentTrendingBinding


class TrendingFragment : Fragment() {

    lateinit var binding: FragmentTrendingBinding
    private val spanCount = 2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentTrendingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val trendingList: MutableList<Pair<Int, String>> = ArrayList()
        trendingList.add(Pair(0, "https://s0.rbk.ru/v6_top_pics/media/img/7/20/756662541234207.jpg"))
        trendingList.add(Pair(0, "https://s0.rbk.ru/v6_top_pics/media/img/7/20/756662541234207.jpg"))
        trendingList.add(Pair(0, "https://s0.rbk.ru/v6_top_pics/media/img/7/20/756662541234207.jpg"))
        trendingList.add(Pair(0, "https://s0.rbk.ru/v6_top_pics/media/img/7/20/756662541234207.jpg"))
        trendingList.add(Pair(0, "https://s0.rbk.ru/v6_top_pics/media/img/7/20/756662541234207.jpg"))
        trendingList.add(Pair(0, "https://s0.rbk.ru/v6_top_pics/media/img/7/20/756662541234207.jpg"))
        trendingList.add(Pair(0, "https://s0.rbk.ru/v6_top_pics/media/img/7/20/756662541234207.jpg"))
        trendingList.add(Pair(0, "https://s0.rbk.ru/v6_top_pics/media/img/7/20/756662541234207.jpg"))
        val trendingLayoutManager = GridLayoutManager(activity, spanCount)
        val trendingAdapter = TrendingAdapter(trendingList, trendingItemListener)
        binding.apply {
            trendingRecyclerView.layoutManager = trendingLayoutManager
            trendingRecyclerView.adapter = trendingAdapter
        }
    }

    private val trendingItemListener = TrendingAdapter.OnClickListener {
        Log.i("TAG", "You click on $it")
    }

}