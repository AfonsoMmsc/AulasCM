package com.example.aula4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.aula4.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    var igual=false
    private lateinit var binding: ActivityMainBinding
    private val TAG =MainActivity::class.java.simpleName
    val lista = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        binding.button1.setOnClickListener{onClickNumber("1")}
        binding.button2.setOnClickListener{onClickNumber("2")}
        binding.button3.setOnClickListener{onClickNumber("3")}
        binding.button4.setOnClickListener{onClickNumber("4")}
        binding.button5.setOnClickListener{onClickNumber("5")}
        binding.button6.setOnClickListener{onClickNumber("6")}
        binding.buttonAddition.setOnClickListener{onClickSymbol("+")}
        binding.buttonMinus.setOnClickListener{onClickSymbol("-")}
        binding.buttonMultiplicate.setOnClickListener{onClickSymbol("*")}

        binding.buttonClear.setOnClickListener {
            Log.i(TAG,"botao +")
            if(binding.textVisor.text.toString()=="0"){

            }else{
                binding.textVisor.text=""
            }
            igual=false
        }
        binding.buttonPoint.setOnClickListener {
            Log.i(TAG,"botao .")
            if(binding.textVisor.text.toString()=="0" || igual){

            }else{
                binding.textVisor.append(".")
            }
        }
        binding.buttonEquals.setOnClickListener {
            Log.i(TAG,"botao =")
            lista.add(binding.textVisor.text.toString())
            val expression=ExpressionBuilder(binding.textVisor.text.toString()).build()
            binding.textVisor.text=expression.evaluate().toString()
            Log.i(TAG,"O resultado é ${binding.textVisor.text}")
            igual=true


        }
    }

    private fun onClickNumber(number: String){
        Log.i(TAG,"button $number")
        if(binding.textVisor.text.toString()=="0" || igual){
            binding.textVisor.text=number
        }else{
            binding.textVisor.append(number)
        }
    }

    private fun onClickSymbol(symbol: String){
        Log.i(TAG,"botao $symbol")
        if(binding.textVisor.text.toString()=="0"){

        }else{
            binding.textVisor.append(symbol)
        }
        igual=false
    }

}