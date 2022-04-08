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

    private fun onOperationClick(operation:String){
        Toast.makeText(this,operation,Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG,"o metedo onCreate foi invocado")
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        Log.i(TAG,"o metedo onDestroy foi invocado")
        super.onDestroy()
    }

    override fun onStart() {
        super.onStart()

        adapter.updateItems(operations)
        binding.rvHistoric?.layoutManager =LinearLayoutManager(this)
        binding.rvHistoric?.adapter = adapter
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
            Log.i(TAG,"botao clear")
            if(binding.textVisor.text.toString()=="0"){

            }else{
                binding.textVisor.text="0"
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
            val inicioConta=binding.textVisor.text.toString()
            val sinalIgual="="
            val resultado=ExpressionBuilder(binding.textVisor.text.toString()).build()
            val contaTotal=inicioConta+sinalIgual+resultado.evaluate().toString()
            Log.i(TAG,"é ${contaTotal}")
            operations.add(contaTotal)
            adapter.updateItems(operations)
            binding.textVisor.text=resultado.evaluate().toString()
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