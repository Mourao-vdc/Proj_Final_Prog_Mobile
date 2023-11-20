package com.example.finalproject

import com.example.finalproject.News.RecyclerViewAdapterNews
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.News.NoticiasViewModel

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
        val progressBar: ProgressBar = view.findViewById(R.id.progressBar)

        // Set up RecyclerView layout manager and adapter
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager

        val viewmodel = NoticiasViewModel()

        // Show progress bar initially
        progressBar.visibility = View.VISIBLE

        viewmodel.newsList.observe(viewLifecycleOwner) { newsList ->
            // Hide progress bar once data is loaded
            progressBar.visibility = View.GONE

            val adapter = RecyclerViewAdapterNews(newsList)
            recyclerView.adapter = adapter
        }

        return view
    }
}