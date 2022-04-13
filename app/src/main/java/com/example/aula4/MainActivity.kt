package com.example.aula4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
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

        setupDrawerMenu()
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
        if(!screenRotated(savedInstanceState)){
            NavigationManager.goToCalculatorFragment(supportFragmentManager)
        }
    }

    private fun screenRotated(savedInstanceState: Bundle?): Boolean{
        return savedInstanceState != null
    }

    private fun setupDrawerMenu(){
        val toggle=ActionBarDrawerToggle(this,binding.drawer, binding.toolbar,R.string.drawer_open,R.string.drawer_close)
        binding.navDrawer.setNavigationItemSelectedListener {
            onClickNavigationItem(it)
        }
        binding.drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun onClickNavigationItem(item: MenuItem) : Boolean {
        when(item.itemId) {
            R.id.nav_calculator ->
                NavigationManager.goToCalculatorFragment(supportFragmentManager)
        }
        binding.drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed(){
        if(binding.drawer.isDrawerOpen(GravityCompat.START)){
            binding.drawer.closeDrawer(GravityCompat.START)
        }else if(supportFragmentManager.backStackEntryCount==1){
            finish()
        }else{
            super.onBackPressed()
        }
    }


}