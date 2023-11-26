package com.example.finalproject.Stocks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.squareup.picasso.Picasso
import retrofit.SymbolDetails

class RecyclerViewAdapterDetalhes(private val mList: List<SymbolDetails>) : RecyclerView.Adapter<RecyclerViewAdapterDetalhes.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_detalhes, parent, false)

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
        holder.CEO.text = ItemsViewModel.CEO

        // sets the text to the textview from our itemHolder class
        holder.sector.text = ItemsViewModel.sector
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView)
    {
        val imageLogo: ImageView = itemView.findViewById(R.id.imageLogoDetalhes)
        val CEO: TextView = itemView.findViewById(R.id.tvCEO)
        val sector: TextView = itemView.findViewById(R.id.Sector)
    }
}