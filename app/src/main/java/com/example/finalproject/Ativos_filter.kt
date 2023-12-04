package com.example.finalproject

import SymbolViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.Stocks.RecyclerViewAdapterAtivosFilter
import com.example.finalproject.Stocks.SymbolsRepository
import retrofit.SymbolRepository
import retrofit.SymbolSummary

class Ativos_filter : Fragment() {

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
        val swipeRefreshLayout = view.findViewById<androidx.swiperefreshlayout.widget.SwipeRefreshLayout>(R.id.swipeRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener {
            Toast.makeText(context, "Atualizando", Toast.LENGTH_SHORT).show()
            val fragment = Ativos_filter()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()

            fragmentTransaction.replace(R.id.frame_layout, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
            swipeRefreshLayout.isRefreshing = false
        }
        val progressBar : ProgressBar = view.findViewById(R.id.progressBar)

        // variavel com o id do botao
        val addStackButton: ImageButton = view.findViewById(R.id.add_stack)

        // ação de clique
        addStackButton.setOnClickListener {
            Toast.makeText(context, "Adicionar bolsa", Toast.LENGTH_SHORT).show()

            val fragment = Ativos()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()

            fragmentTransaction.replace(R.id.frame_layout, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        // Show progress bar initially
        progressBar.visibility = View.VISIBLE

        val symbolViewModel = SymbolViewModel()
        symbolViewModel.fetchSymbolSummaryInRepo()
        symbolViewModel.SymbolSummary.observe(viewLifecycleOwner){symbolSummary ->

            // Hide progress bar once data is loaded
            progressBar.visibility = View.GONE

            // Convert the received List<SymbolSummary> to a MutableList<SymbolSummary>
            val mutableSymbolSummary: MutableList<SymbolSummary> = symbolSummary.toMutableList()

            // Adiciona os dados recebidos à lista
            //symbolList.clear() // Limpa a lista para evitar duplicados
            //symbolList.symbolList.addAll(symbolSummary) // Adiciona os novos dados à lista

            // Cria um adaptador para o RecyclerView com base na lista de dados
            val itemAdapter = RecyclerViewAdapterAtivosFilter(mutableSymbolSummary)
            val recyclerView : RecyclerView = requireView().findViewById(R.id.recyclerViewAtivosFil)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = itemAdapter
        }
        return view
    }
}