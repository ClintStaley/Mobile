package com.cstaley.android.quotes.view

import android.content.Context
import android.graphics.Typeface.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cstaley.android.quotes.R
import com.cstaley.android.quotes.model.Quote

class QuoteAdapter (
    private val context: Context,
    private val dataset: List<Quote>
) : RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>() {

    class QuoteViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.quote_layout)
        val imgView = view.findViewById<ImageView>(R.id.quote_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
         .inflate(R.layout.quote_layout, parent, false)

        // Log.e("tester", "ocvh")
        return QuoteViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val quote = dataset[position]
        holder.textView.text = context.resources.getString(quote.txtResourceId)
        holder.imgView.setImageResource(quote.imageResourceId)
        holder.textView.setTypeface(
            if (position % 2 == 1) DEFAULT else DEFAULT_BOLD)
    }

    override fun getItemCount() = dataset.size
}