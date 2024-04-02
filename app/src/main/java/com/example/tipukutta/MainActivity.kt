package com.example.tipukutta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tipukutta.databinding.ActivityMainBinding
import com.example.tipukutta.screens.home.pagingAdapter.HomeAdapter
import com.example.tipukutta.screens.home.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val viewModel by viewModels<HomeViewModel> ()
    lateinit var binding:ActivityMainBinding
    var adapter:HomeAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRv()
    }

    private fun initRv()
    {
        adapter = HomeAdapter()
        binding.recyclerView.adapter = adapter
        viewModel.photoData.observe(this, Observer {
            adapter?.submitData(lifecycle,it)
        })
    }

}