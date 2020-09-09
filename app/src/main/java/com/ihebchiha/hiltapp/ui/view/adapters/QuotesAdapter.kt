package com.ihebchiha.hiltapp.ui.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ihebchiha.hiltapp.databinding.QuoteItemBinding
import com.ihebchiha.hiltapp.networking.result.models.Quote
import javax.inject.Inject


class QuotesAdapter @Inject constructor() : RecyclerView.Adapter<QuoteViewHolder>() {



    private val items = ArrayList<Quote>()

    fun setItems(items: ArrayList<Quote>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val binding:  QuoteItemBinding = QuoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuoteViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) = holder.bind(items[position])
}

class QuoteViewHolder(private val itemBinding: QuoteItemBinding) : RecyclerView.ViewHolder(itemBinding.root){

    private lateinit var quote: Quote

    @SuppressLint("SetTextI18n")
    fun bind(item: Quote) {
        this.quote = item
        itemBinding.quoteDateTv.text = item.date
        itemBinding.quoteAuthorTv.text = item.author
        itemBinding.quoteContentTv.text = item.content
    }
}