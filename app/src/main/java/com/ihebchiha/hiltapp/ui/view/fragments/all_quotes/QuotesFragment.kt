package com.ihebchiha.hiltapp.ui.view.fragments.all_quotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ihebchiha.hiltapp.R
import com.ihebchiha.hiltapp.networking.result.models.Quote
import com.ihebchiha.hiltapp.ui.presentation.QuotesViewModel
import com.ihebchiha.hiltapp.ui.view.adapters.QuotesAdapter
import com.ihebchiha.hiltapp.utils.extensions.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_quotes.*

@AndroidEntryPoint
class QuotesFragment : Fragment(), QuotesAdapter.QuoteItemListener {

    private val quoteViewModel: QuotesViewModel by viewModels()

    lateinit var quotesAdapter: QuotesAdapter

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quotes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupRecyclerView()
        quoteViewModel.getQuotes()


    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuotesFragment().apply {}
    }

    private fun setupObservers() {
        quoteViewModel.qoutes.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    progress_bar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) {
                        quotesAdapter.setItems(ArrayList(it.data))
                    } else {
                        Toast.makeText(activity, "empty", Toast.LENGTH_SHORT).show()
                    }
                }
                Resource.Status.ERROR ->
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    progress_bar.visibility = View.VISIBLE
            }
        })
    }

    private fun setupRecyclerView() {
        quotesAdapter = QuotesAdapter(this)
        quotes_rv.layoutManager = LinearLayoutManager(requireContext())
        quotes_rv.adapter = quotesAdapter
    }

    override fun onClickedQuote(quote: Quote) {
        findNavController().navigate(
            R.id.action_quotesFragment_to_quoteDetailFragment
        )
    }
}