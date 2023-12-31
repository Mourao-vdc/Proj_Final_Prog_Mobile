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
import com.example.finalproject.Stocks.RecyclerViewAdapterAtivosMon
import com.example.finalproject.Stocks.SymbolsRepository
import retrofit.SymbolSummary

class Ativos : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ativos_mon, container, false)
        val progressBar: ProgressBar = view.findViewById(R.id.progressBar)

        // variavel com o id do botao
        val backAtivos: ImageButton = view.findViewById(R.id.back_ativos)

        // ação de clique
        backAtivos.setOnClickListener {
            val fragment = Ativos_filter()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()

            fragmentTransaction.replace(R.id.frame_layout, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        // Show progress bar initially
        progressBar.visibility = View.VISIBLE

        val symbolViewModel = SymbolViewModel()
        symbolViewModel.fetchSymbolSummary()
        symbolViewModel.SymbolSummary.observe(viewLifecycleOwner){symbolSummary ->

            // Convert the received List<SymbolSummary> to a MutableList<SymbolSummary>
            val mutableSymbolSummary: MutableList<SymbolSummary> = symbolSummary.toMutableList()
            // Hide progress bar once data is loaded
            progressBar.visibility = View.GONE

            // Adiciona os dados recebidos à lista
            //symbolList2.clear() // Limpa a lista para evitar duplicados
            //symbolList2.symbolList2.addAll(symbolSummary) // Adiciona os novos dados à lista

            // Cria um adaptador para o RecyclerView com base na lista de dados
            val itemAdapter = RecyclerViewAdapterAtivosMon(mutableSymbolSummary)
            val recyclerView : RecyclerView = requireView().findViewById(R.id.recyclerViewAtivosMon)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = itemAdapter
        }
        return view
    }
}