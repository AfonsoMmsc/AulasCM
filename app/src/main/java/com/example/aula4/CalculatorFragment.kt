package com.example.aula4

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aula4.databinding.ActivityMainBinding
import com.example.aula4.databinding.FragmentCalculatorBinding
import net.objecthunter.exp4j.ExpressionBuilder

class CalculatorFragment : Fragment() {
    private lateinit var binding: FragmentCalculatorBinding
    var igual=false
    private val TAG =MainActivity::class.java.simpleName
    private val operations= mutableListOf<String>()
    private val adapter=HistoryAdapter(::onOperationClick)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_calculator,container,false)
        binding= FragmentCalculatorBinding.bind(view)
        return binding.root
    }

    private fun onOperationClick(operation:String){
        Toast.makeText(activity as Context,operation, Toast.LENGTH_LONG).show()
    }


    override fun onStart() {
        super.onStart()

        adapter.updateItems(operations)
        binding.rvHistoric?.layoutManager = LinearLayoutManager(activity as Context)
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
            val resultado= ExpressionBuilder(binding.textVisor.text.toString()).build()
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