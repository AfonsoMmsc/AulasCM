package com.example.aula4

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aula4.databinding.ActivityMainBinding
import com.example.aula4.databinding.ItemExpressionBinding


class HistoryAdapter(private val onOperationClick:(String) ->Unit ,private var items:List<String> = listOf()) :
RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>(){
    private lateinit var binding: ItemExpressionBinding

    class HistoryViewHolder(val binding: ItemExpressionBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(ItemExpressionBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            onOperationClick(items[position])
        }
        val parts=items[position]?.split("=")
        holder.binding.textExpression.text=parts?.get(0)
        holder.binding.textResult.text = parts?.get(1)
    }

    override fun getItemCount()=items.size

    fun updateItems(items: List<String>){
        this.items=items
        notifyDataSetChanged()
    }
}