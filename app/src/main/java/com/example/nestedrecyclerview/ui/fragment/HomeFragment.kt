package com.example.nestedrecyclerview.ui.fragment

import android.os.Bundle
import com.example.nestedrecyclerview.data.HomeItem
import com.example.nestedrecyclerview.data.HomeItemType
import com.example.nestedrecyclerview.data.dataManger.DataManger
import com.example.nestedrecyclerview.databinding.FragmentHomeBinding
import com.example.nestedrecyclerview.extention.toHomeItem
import com.example.nestedrecyclerview.ui.adapter.HomeAdapter
import com.example.nestedrecyclerview.ui.base.BaseFragment


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val bundle = Bundle()
    lateinit var adapter: HomeAdapter

    override fun bindingInflater(): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)

    override fun setUp() {
        val itemsList: MutableList<HomeItem<Any>> = mutableListOf()
        itemsList.add(HomeItem(DataManger.getStories(), HomeItemType.TYPE_STORIES))
        itemsList.add(HomeItem("What are you do? Share it now", HomeItemType.TYPE_NEW_POST))
        itemsList.addAll(DataManger.getPosts().map { it.toHomeItem() })
        adapter = HomeAdapter(itemsList)
        binding.recyclerViewHome.adapter = adapter
    }


    override fun onStart() {
        super.onStart()
    }
}