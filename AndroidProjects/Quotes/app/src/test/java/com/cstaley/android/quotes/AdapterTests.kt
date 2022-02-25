package com.cstaley.android.quotes

import android.content.Context
import com.cstaley.android.quotes.data.FixedSource
import com.cstaley.android.quotes.view.QuoteAdapter
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito.mock

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class AdapterTests {
    @Test
    fun checkAdapterSize() {
        val data = FixedSource().loadQuotes()

        assertEquals("Bad adapter size",
         QuoteAdapter(mock(Context::class.java), data).itemCount, data.size)
    }
}