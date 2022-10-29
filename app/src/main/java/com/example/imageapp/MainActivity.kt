package com.example.imageapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imageapp.adapter.ImageAdapter
import com.example.imageapp.databinding.ActivityMainBinding
import com.example.imageapp.viewmodel.ImageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var imageAdapter: ImageAdapter
    private val viewModel:ImageViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        imageAdapter= ImageAdapter()
        binding.recyclerView.apply {
            adapter=imageAdapter
            layoutManager=LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }

        viewModel.responseImages.observe(this, Observer {
            if(it !=null){
                binding.recyclerView.visibility=View.VISIBLE
                imageAdapter.submitList(it)
            }
        })

        viewModel.progresBar.observe(this, Observer {
            if (it){
                binding.progressBar.visibility=View.VISIBLE
                binding.recyclerView.visibility=View.GONE
            }else{
                binding.progressBar.visibility=View.GONE
            }
        })
    }
}