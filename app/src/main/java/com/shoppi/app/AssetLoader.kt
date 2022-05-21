package com.shoppi.app

import android.content.Context
import android.util.Log

class AssetLoader(private val context:Context,) {
    fun getJsonString(fileName:String):String?{
        return runCatching { loadAsset(fileName) }.getOrNull() //Result의 Failure를 Null로 처리하는 함수
    }
    private fun loadAsset(fileName:String):String{
        return context.assets.open(fileName).use{ inputStream->//inputstream은 사용 후 해제해야 함
            val size = inputStream.available()
            val bytes = ByteArray(size)
            inputStream.read(bytes)
            String(bytes)
        }
    }
}