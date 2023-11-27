package com.example.finalproject

import SymbolViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.Stocks.RecyclerViewAdapterAtivosFilter
import retrofit.SymbolSummary

class Ativos_filter : Fragment() {

    // Lista mutável para armazenar os dados recebidos
    private val symbolList: MutableList<SymbolSummary> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ativos_filter, container, false)
        val progressBar : ProgressBar = view.findViewById(R.id.progressBar)

        // Show progress bar initially
        progressBar.visibility = View.VISIBLE

        val symbolViewModel = SymbolViewModel()
        symbolViewModel.fetchSymbolSummary()
        symbolViewModel.SymbolSummary.observe(viewLifecycleOwner){symbolSummary ->
            // Hide progress bar once data is loaded
            progressBar.visibility = View.GONE

            // Adiciona os dados recebidos à lista
            symbolList.clear() // Limpa a lista para evitar duplicados
            symbolList.addAll(symbolSummary) // Adiciona os novos dados à lista

            // Cria um adaptador para o RecyclerView com base na lista de dados
            val itemAdapter = RecyclerViewAdapterAtivosFilter(symbolList)
            val recyclerView : RecyclerView = requireView().findViewById(R.id.recyclerViewAtivosFil)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = itemAdapter
        }
        return view
    }
}