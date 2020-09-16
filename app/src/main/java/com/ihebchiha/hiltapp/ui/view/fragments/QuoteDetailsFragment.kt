package com.ihebchiha.hiltapp.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.ihebchiha.hiltapp.R
import com.ihebchiha.hiltapp.networking.result.models.Quote
import com.ihebchiha.hiltapp.ui.presentation.QuoteDetailsViewModel
import com.ihebchiha.hiltapp.utils.extensions.HTML_TAG_PATTERN
import com.ihebchiha.hiltapp.utils.extensions.SPECIAL_CHARS_PATTERN
import com.ihebchiha.hiltapp.utils.extensions.removeUnwantedChars
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_quote_details.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [QuoteDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class QuoteDetailsFragment : Fragment() {

    private val quotesDetailsViewModel: QuoteDetailsViewModel by viewModels()
    @Inject
    lateinit var gson: Gson
    private lateinit var quote: Quote


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quote_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("quote")?.let {
            quote = gson.fromJson(it, Quote::class.java)
            bindQuote(quote)
            quotesDetailsViewModel.start(quote.id)
        }
        setupObservers()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = QuoteDetailsFragment().apply {}
    }

    private fun setupObservers(){
        quotesDetailsViewModel.quote.observe(viewLifecycleOwner, Observer {
            it.apply {
                authorNameTV.text = it.author
                quoteItem.findViewById<TextView>(R.id.quote_content_tv).text = it.content
            }
        })
    }

    private fun bindQuote(quote: Quote){
        authorNameTV.text = quote.author
        quoteItem.findViewById<TextView>(R.id.quote_content_tv).text = quote.content.removeUnwantedChars(
            HTML_TAG_PATTERN).removeUnwantedChars(SPECIAL_CHARS_PATTERN)
    }
}