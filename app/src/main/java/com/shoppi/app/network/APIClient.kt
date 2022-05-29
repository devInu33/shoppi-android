package com.shoppi.app.network

import com.shoppi.app.model.Category
import com.shoppi.app.model.CategoryDetail
import com.shoppi.app.model.HomeData
import com.shoppi.app.model.Product

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path

interface APIClient{
    @GET("categories.json")
    suspend fun getCategories():List<Category>

    @GET("{categoryId}.json")
    suspend fun getCategoryDetail(@Path("categoryId") categoryId:String): CategoryDetail

    @GET("products/{productId}.json")
    suspend fun getProductDetail(@Path("productId") productId:String): Product

    companion object {
        private const val baseUrl = "https://shoppi-428ff-default-rtdb.asia-southeast1.firebasedatabase.app/"
        fun Create(): APIClient {
            val logger = HttpLoggingInterceptor().apply {
                level=HttpLoggingInterceptor.Level.BASIC
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIClient::class.java)
        }
    }
}