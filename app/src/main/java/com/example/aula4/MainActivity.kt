package com.example.aula4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aula4.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    var igual=false
    private lateinit var binding: ActivityMainBinding
    private val TAG =MainActivity::class.java.simpleName
    private val operations= mutableListOf<String>()
    private val adapter=HistoryAdapter(::onOperationClick)

    override fun onStart(){
        super.onStart()
        NavigationManager.goToCalculatorFragment(supportFragmentManager)
    }

    private fun onOperationClick(operation:String){
        Toast.makeText(this,operation,Toast.LENGTH_LONG).show()
    }
    override fun onDestroy() {
        Log.i(TAG,"o metedo onDestroy foi invocado")
        super.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG,"o metedo onCreate foi invocado")
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}