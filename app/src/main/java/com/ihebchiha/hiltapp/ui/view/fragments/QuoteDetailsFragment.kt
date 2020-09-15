package com.ihebchiha.hiltapp.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ihebchiha.hiltapp.R
import com.ihebchiha.hiltapp.ui.presentation.QuoteDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [QuoteDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class QuoteDetailsFragment : Fragment() {

    private val quotesDetailsViewModel: QuoteDetailsViewModel by viewModels()

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

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = QuoteDetailsFragment().apply {}
    }

    fun setupObservers(){
        quotesDetailsViewModel.quote.observe(viewLifecycleOwner, Observer {

        })
    }
}