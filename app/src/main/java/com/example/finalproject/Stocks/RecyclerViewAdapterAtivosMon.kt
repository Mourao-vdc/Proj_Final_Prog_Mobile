package com.example.finalproject.Stocks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.Detalhes
import com.example.finalproject.R
import com.example.finalproject.StockList
import com.squareup.picasso.Picasso
import retrofit.SymbolSummary

class RecyclerViewAdapterAtivosMon(private val mList: MutableList<SymbolSummary>) : RecyclerView.Adapter<RecyclerViewAdapterAtivosMon.ViewHolder>()
{
    private var symbolList: StockList? = null
    // create Symbol views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_ativos_mon, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val ItemsViewModel = mList[position]

        //sets the image to the imageview from our itemHolder class
        ItemsViewModel.logo_url.let {
            Picasso.get().load(it).into(holder.imageLogo)
        }

        // sets the text to the textview from our itemHolder class
        holder.symbol.text = ItemsViewModel.symbol

        // sets the text to the textview from our itemHolder class
        holder.change_percent.text = ItemsViewModel.change_percent.toString()

        // sets the text to the textview from our itemHolder class
        holder.current_price.text = ItemsViewModel.current_price.toString()

        // Update text color based on change_percent
        val changePercent = ItemsViewModel.change_percent
        if (changePercent >= 0) {
            // Change text color to green for positive change
            holder.change_percent.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.green))
        } else {
            // Change text color to red for negative change
            holder.change_percent.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.red))
        }
    }

    // Retorna o numero de elementos da lista
    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Referências aos elementos
        private val addButton: ImageView = itemView.findViewById(R.id.add_stackMon)
        private val detalhesButton: Button = itemView.findViewById(R.id.view_detalhesMon)
        val imageLogo: ImageView = itemView.findViewById(R.id.imageLogoMon)
        val symbol: TextView = itemView.findViewById(R.id.tvSymbolMon)
        val change_percent: TextView = itemView.findViewById(R.id.change_percentMon)
        val current_price: TextView = itemView.findViewById(R.id.current_priceMon)

        init {
            addButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val Item = mList[position] // Armazena o item a ser movido
                    mList.removeAt(position) // Remove o item da lista

                    SymbolsRepository.getRepository().symbolList.add(Item.symbol) // Adiciona o item à lista de símbolos

                    Toast.makeText(itemView.context, "Adicionado à lista de ativos", Toast.LENGTH_SHORT).show()

                    notifyItemRemoved(position) // Notifica o adaptador sobre a remoção

                }
            }

            detalhesButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    // Aqui você pode abrir o fragmento desejado
                    val Item = mList[position]
                    val fragment =
                        Detalhes(Item.symbol)

                    // Inicializa o FragmentManager
                    val fragmentManager: FragmentManager =
                        (itemView.context as AppCompatActivity).supportFragmentManager

                    // Inicia a transação para adicionar/substituir o fragmento
                    val fragmentTransaction: FragmentTransaction =
                        fragmentManager.beginTransaction()

                    // Substitui ou adiciona o fragmento dependendo do seu caso
                    fragmentTransaction.replace(R.id.frame_layout, fragment)
                    //fragmentTransaction.add(R.id.fragment_detalhes, fragment)  // Use isso se deseja adicionar o fragmento ao invés de substituir

                    // Adiciona a transação à pilha de volta (opcional)
                    fragmentTransaction.addToBackStack(null)

                    // Commit da transação
                    fragmentTransaction.commit()
                }
            }
        }
    }
}