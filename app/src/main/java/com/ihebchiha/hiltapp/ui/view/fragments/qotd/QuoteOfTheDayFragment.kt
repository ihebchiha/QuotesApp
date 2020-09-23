package com.ihebchiha.hiltapp.ui.view.fragments.qotd

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ihebchiha.hiltapp.R

class QuoteOfTheDayFragment : Fragment() {

    companion object {
        fun newInstance() = QuoteOfTheDayFragment()
    }

    private lateinit var viewModel: QuoteOfTheDayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quote_of_the_day_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(QuoteOfTheDayViewModel::class.java)
        // TODO: Use the ViewModel
    }

}