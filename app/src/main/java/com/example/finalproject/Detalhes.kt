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

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class Detalhes : Fragment() {
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
        symbolViewModel.fetchSymbolDetails()
        symbolViewModel.SymbolDetails.observe(viewLifecycleOwner) { symbolDetails ->
            // Hide progress bar once data is loaded
            progressBar.visibility = View.GONE

            val itemAdapter = RecyclerViewAdapterDetalhes(symbolDetails)
            val recyclerView : RecyclerView = requireView().findViewById(R.id.recyclerViewDetails)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = itemAdapter
        }
        return view
    }
}