package com.example.finalproject

import SymbolViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.Stocks.RecyclerViewAdapterDetalhes

class Detalhes(symbol: String) : Fragment() {

    private var symbol: String = symbol

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_detalhes, container, false)
        val progressBar: ProgressBar = view.findViewById(R.id.progressBar)

        // variavel com o id do botao
        val backAtivos: ImageButton = view.findViewById(R.id.back_ativos)

        // ação de clique
        backAtivos.setOnClickListener {
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
        symbolViewModel.fetchSymbolDetails(symbol)
        symbolViewModel.SymbolDetails.observe(viewLifecycleOwner) { symbolDetails ->
            // Hide progress bar once data is loaded
            progressBar.visibility = View.GONE


            // Cria um adaptador para o RecyclerView com base na lista de dados
            val itemAdapter = RecyclerViewAdapterDetalhes(symbolDetails)
            val recyclerView : RecyclerView = requireView().findViewById(R.id.recyclerViewDetails)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = itemAdapter

        }
        return view
    }
}