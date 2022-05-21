package com.shoppi.app


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
private const val TAG  = "HI"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //메인 액티비티에 FragmentLayout 사용
        //FragmentLayout에 id와 name을 넣어 지정한 프래그먼트는 앱이 실행될 때 바로 실행
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation_main)
        bottomNavigationView.itemIconTintList = null

        val navController = supportFragmentManager.findFragmentById(R.id.container_main)?.findNavController() //호스트 프래그먼트를 참조하여 navController 객체 반호됨
        navController?.let{
            bottomNavigationView.setupWithNavController(it)
        }
    }


}