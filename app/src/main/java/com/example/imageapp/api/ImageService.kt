package com.example.imageapp.api

import com.example.imageapp.model.ImageItem
import com.example.imageapp.utils.Constants.CLIENT_ID
import com.example.imageapp.utils.Constants.END_POINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ImageService {
    @Headers("Accept-Version: v1","Authorization: Client-ID ${CLIENT_ID}")
    @GET(END_POINT)
    suspend fun getAllImages():Response<List<ImageItem>>
}