package com.shoppi.app

import com.shoppi.app.network.APIClient

object ServiceLocator{
    private var apiClient:APIClient? = null
    fun provideApiClient():APIClient{
        return apiClient?:kotlin.run{
            APIClient.Create().also{
                apiClient=it
            }
        }
    }
}