package com.example.finalproject.Stocks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import retrofit.SymbolSummary

class RecyclerViewAdapterAtivos(private val mList: List<SymbolSummary>) : RecyclerView.Adapter<RecyclerViewAdapterAtivos.ViewHolder>()
{
    // create Symbol views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        //ItemsViewModel.image_url.let {
        //    Picasso.get().load(it).into(holder.imageView)
       //}

        // sets the text to the textview from our itemHolder class
        holder.symbol.text = ItemsViewModel.symbol

        // sets the text to the textview from our itemHolder class
        //holder.textViewTitle.text = ItemsViewModel.title

        // sets the text to the textview from our itemHolder class
        //holder.textViewDescription.text = ItemsViewModel.description
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView)
    {
        //val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val symbol: TextView = itemView.findViewById(R.id.tvSymbol)
        //val textViewTitle: TextView = itemView.findViewById(R.id.textviewtitle)
        //val textViewDescription: TextView = itemView.findViewById(R.id.textviewdescription)
    }
}