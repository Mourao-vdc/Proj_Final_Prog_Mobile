package com.example.finalproject.News

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.squareup.picasso.Picasso
import retrofit.News

class RecyclerViewAdapterNews(private val mList: List<News>) : RecyclerView.Adapter<RecyclerViewAdapterNews.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_news, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        ItemsViewModel.image_url.let {
            Picasso.get().load(it).into(holder.imageView)
        }

        // sets the text to the textview from our itemHolder class
        holder.textViewData.text = ItemsViewModel.date

        // sets the text to the textview from our itemHolder class
        holder.textViewTitle.text = ItemsViewModel.title

        // sets the text to the textview from our itemHolder class
        holder.textViewDescription.text = ItemsViewModel.description
    }

    // return the number of the items in the list
    override fun getItemCount(): Int
    {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView)
    {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textViewData: TextView = itemView.findViewById(R.id.textviewdata)
        val textViewTitle: TextView = itemView.findViewById(R.id.textviewtitle)
        val textViewDescription: TextView = itemView.findViewById(R.id.textviewdescription)
    }
}