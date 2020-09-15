package com.ihebchiha.hiltapp.ui.view.adapters

import android.annotation.SuppressLint
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ihebchiha.hiltapp.databinding.QuoteItemBinding
import com.ihebchiha.hiltapp.networking.result.models.Quote
import com.ihebchiha.hiltapp.utils.extensions.HTML_TAG_PATTERN
import com.ihebchiha.hiltapp.utils.extensions.SPECIAL_CHARS_PATTERN
import com.ihebchiha.hiltapp.utils.extensions.removeUnwantedChars
import javax.inject.Inject


class QuotesAdapter @Inject constructor(private val listener: QuoteItemListener) :
    RecyclerView.Adapter<QuoteViewHolder>() {

    interface QuoteItemListener {
        fun onClickedQuote(quoteId: Int)
    }

    private val items = ArrayList<Quote>()

    fun setItems(items: ArrayList<Quote>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val binding: QuoteItemBinding =
            QuoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuoteViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) =
        holder.bind(items[position])
}

class QuoteViewHolder(
    private val itemBinding: QuoteItemBinding,
    private val listener: QuotesAdapter.QuoteItemListener
) :
    RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

    private lateinit var quote: Quote

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: Quote) {
        this.quote = item
        itemBinding.quoteDateTv.text = item.date
        itemBinding.quoteAuthorTv.text = item.author
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            itemBinding.quoteContentTv.text = Html.fromHtml(item.content,Html.FROM_HTML_MODE_COMPACT)
        }else{
           itemBinding.quoteContentTv.text = item.content.removeUnwantedChars(HTML_TAG_PATTERN).removeUnwantedChars(SPECIAL_CHARS_PATTERN)
        }
    }

    override fun onClick(p0: View?) {
        listener.onClickedQuote(quote.id)
    }
}