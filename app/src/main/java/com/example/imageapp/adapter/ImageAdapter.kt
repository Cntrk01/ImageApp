package com.example.imageapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.imageapp.databinding.ImageLayoutBinding
import com.example.imageapp.model.ImageItem

class ImageAdapter:RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(val binding:ImageLayoutBinding):RecyclerView.ViewHolder(binding.root){

    }

    private val diffCallBack=object :DiffUtil.ItemCallback<ImageItem>(){
        override fun areItemsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean {
           return oldItem.id==newItem.id
        }
        override fun areContentsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean {
            return oldItem==newItem
        }

    }

    private val differ=AsyncListDiffer(this,diffCallBack)

    fun submitList(list:List<ImageItem>)=differ.submitList(list)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
       return ImageViewHolder(ImageLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val currImage=differ.currentList[position]

        holder.binding.apply {
            tvDescription.text=currImage.description
            imageView.load(currImage.urls.regular){
                crossfade(true)
                crossfade(100)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}