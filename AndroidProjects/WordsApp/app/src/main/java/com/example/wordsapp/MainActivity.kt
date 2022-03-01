/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.wordsapp

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.ActivityMainBinding

/**
 * Main Activity and entry point for the app. Displays a RecyclerView of letters.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var layoutSwitcher : MenuItem? = null
    private var linearLayout = true

    companion object {
        const val gridWidth = 4
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerView
        configRecycler();
        recyclerView.adapter = LetterAdapter()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        layoutSwitcher = menu?.findItem(R.id.layout_switcher)
        setSwitcherIcon()

        return true;
    }

    fun setSwitcherIcon() : Unit {
        layoutSwitcher?.icon = getDrawable(
         if (linearLayout) R.drawable.ic_view_grid else R.drawable.ic_view_linear)
    }

    fun configRecycler() : Unit {
        recyclerView.layoutManager = if (linearLayout)
            LinearLayoutManager(this)
        else
            GridLayoutManager(this, gridWidth)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.layout_switcher) {
            linearLayout = !linearLayout
            configRecycler()
            setSwitcherIcon()

            return true;
        }

        return super.onOptionsItemSelected(item)
    }
}
