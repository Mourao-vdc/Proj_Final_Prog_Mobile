package com.example.finalproject.Stocks

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidplot.xy.LineAndPointFormatter
import com.androidplot.xy.SimpleXYSeries
import com.androidplot.xy.XYPlot
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
        holder.symbol.text = ItemsViewModel.symbol

        // sets the text to the textview from our itemHolder class
        holder.change_percent.text = ItemsViewModel.details.change_percent.toString()

        // sets the text to the textview from our itemHolder class
        holder.current_price.text = ItemsViewModel.details.current_price.toString()

        // sets the text to the textview from our itemHolder class
        holder.ceo.text = ItemsViewModel.CEO

        // sets the text to the textview from our itemHolder class
        holder.sector.text = ItemsViewModel.sector

        // sets the text to the textview from our itemHolder class
        holder.description.text = ItemsViewModel.description

        // Assuming chart_data.October_2022 is a List<Double>
        val seriesData: List<Double> = ItemsViewModel.chart_data.October_2022

        // Create an XYSeries from the data
        val series: SimpleXYSeries = SimpleXYSeries(
            seriesData.toMutableList(),
            SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,
            "October 2022" // Change this to a suitable name for your series
        )

        // Set up a formatter for the series
        val formatter = LineAndPointFormatter(
            Color.RED, // Change to your desired color
            null, // Customize to add different configurations if needed
            null, // Customize to add different configurations if needed
            null  // Customize to add different configurations if needed
        )

        // Add the series to the plot with the formatter
        holder.plot.addSeries(series, formatter)
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView)
    {
        val plot: XYPlot = itemView.findViewById(R.id.plot)
        val imageLogo: ImageView = itemView.findViewById(R.id.imageLogoDetalhes)
        val symbol: TextView = itemView.findViewById(R.id.SymbolDel)
        val change_percent: TextView = itemView.findViewById(R.id.change_percentDel)
        val current_price: TextView = itemView.findViewById(R.id.current_priceDel)
        val ceo: TextView = itemView.findViewById(R.id.CEODel)
        val sector: TextView = itemView.findViewById(R.id.SetorDel)
        val description: TextView = itemView.findViewById(R.id.DescripDel)
    }
}