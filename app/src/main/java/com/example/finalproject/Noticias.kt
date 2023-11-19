package com.example.finalproject

import RecyclerViewAdapterNews
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class Noticias : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_noticias, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewNews)

        // Set up RecyclerView layout manager and adapter (assuming you have these defined)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager

        val viewmodel = NoticiasViewModel()

        viewmodel.newsList.observe(viewLifecycleOwner) { newsList ->
            val adapter = RecyclerViewAdapterNews(newsList)
            recyclerView.adapter = adapter
        }

        return view
    }
}