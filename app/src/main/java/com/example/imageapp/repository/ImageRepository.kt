package com.example.imageapp.repository

import com.example.imageapp.api.ImageService
import javax.inject.Inject

class ImageRepository
@Inject constructor(private val api:ImageService){
    suspend fun getAllImages()=api.getAllImages()
}