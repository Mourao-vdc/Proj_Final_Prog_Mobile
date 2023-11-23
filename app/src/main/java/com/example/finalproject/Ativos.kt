package com.example.finalproject

import SymbolViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.Stocks.RecyclerViewAdapterAtivos

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Ativos : Fragment() {
    // TODO: Rename and change types of parameters
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
        val view = inflater.inflate(R.layout.fragment_ativos, container, false)
        val progressBar: ProgressBar = view.findViewById(R.id.progressBar)

        // variavel com o id do botao
        val addStackButton: ImageButton = view.findViewById(R.id.add_stack)

        // ação de clique
        addStackButton.setOnClickListener {
            // mostra a mensagem apos o clique
            Toast.makeText(context, "Adicionar bolsa", Toast.LENGTH_SHORT).show()
        }

        // Show progress bar initially
        progressBar.visibility = View.VISIBLE

        val symbolViewModel = SymbolViewModel()
        symbolViewModel.fetchSymbolSummary()
        symbolViewModel.SymbolSummary.observe(viewLifecycleOwner){symbolSummary ->
            // Hide progress bar once data is loaded
            progressBar.visibility = View.GONE

            val itemAdapter = RecyclerViewAdapterAtivos(symbolSummary)
            val recyclerView : RecyclerView = requireView().findViewById(R.id.recyclerViewAtivos)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = itemAdapter
        }
        return view
    }
}