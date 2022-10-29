package com.example.imageapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imageapp.model.ImageItem
import com.example.imageapp.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel
@Inject constructor(private val imageRepository: ImageRepository) : ViewModel(){
    //Koda dışarıdan erişebilirliği kapattım.Yani sadece get işlemini yapabiliriz.Set yapamayız.
    //Buda güvenirliği sağlıyor
    private val _response=MutableLiveData<List<ImageItem>>()
    val responseImages:LiveData<List<ImageItem>>
    get() = _response

    private val _progresBar:MutableLiveData<Boolean> = MutableLiveData(true)
    val progresBar:LiveData<Boolean>
    get() = _progresBar

    init {
        getAllImage()
    }

    private fun getAllImage() =  viewModelScope.launch {
        _progresBar.value=true
        imageRepository.getAllImages().let {
            if(it.isSuccessful){
                _response.postValue(it.body())
                _progresBar.value=false
            }
        }
    }

    }