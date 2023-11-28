package com.example.finalproject.Stocks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.StockList
import com.squareup.picasso.Picasso
import retrofit.SymbolSummary

class RecyclerViewAdapterAtivosMon(private val mList: MutableList<SymbolSummary>) : RecyclerView.Adapter<RecyclerViewAdapterAtivosMon.ViewHolder>()
{
    private val symbolList = StockList()
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
        private val removeButton: ImageView = itemView.findViewById(R.id.remove_stackMon)
        val imageLogo: ImageView = itemView.findViewById(R.id.imageLogoMon)
        val symbol: TextView = itemView.findViewById(R.id.tvSymbolMon)
        val change_percent: TextView = itemView.findViewById(R.id.change_percentMon)
        val current_price: TextView = itemView.findViewById(R.id.current_priceMon)

        init {
            removeButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    mList.removeAt(position) // Remove o item da lista
                    symbolList.symbolList.add(mList[position])
                    notifyItemRemoved(position) // Notifica o adaptador sobre a remoção
                }
            }
        }
    }
}