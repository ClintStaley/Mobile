package com.cstaley.android.quotes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.cstaley.android.quotes.view.QuoteAdapter
import com.cstaley.android.quotes.data.FixedSource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quotesView = findViewById<RecyclerView>(R.id.quotes_view)
        quotesView.adapter = QuoteAdapter(this, FixedSource().loadQuotes())
        quotesView.setHasFixedSize(true)
    }
}