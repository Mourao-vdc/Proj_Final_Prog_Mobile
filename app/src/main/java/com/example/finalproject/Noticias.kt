package com.example.finalproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.News.NoticiasViewModel
import com.example.finalproject.News.RecyclerViewAdapterNews

class Noticias : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_noticias, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewNews)
        val progressBar: ProgressBar = view.findViewById(R.id.progressBar)
        val swipeRefreshLayout = view.findViewById<androidx.swiperefreshlayout.widget.SwipeRefreshLayout>(R.id.swipeRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener {
            Toast.makeText(context, "Atualizando", Toast.LENGTH_SHORT).show()
            val fragment = Noticias()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()

            fragmentTransaction.replace(R.id.frame_layout, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
            swipeRefreshLayout.isRefreshing = false
        }

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