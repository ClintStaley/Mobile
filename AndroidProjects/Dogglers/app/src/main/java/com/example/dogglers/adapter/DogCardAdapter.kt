/*
* Copyright (C) 2021 The Android Open Source Project.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.dogglers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogglers.R
import com.example.dogglers.const.Layout
import com.example.dogglers.data.DataSource

class DogCardAdapter(
    private val context: Context?,
    private val layout: Int
): RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {

    val dogs = DataSource.dogs

    class DogCardViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val photo = view.findViewById<ImageView>(R.id.dog_photo)
        val name = view.findViewById<TextView>(R.id.dog_name)
        val age = view.findViewById<TextView>(R.id.dog_age)
        val hobbies = view.findViewById<TextView>(R.id.dog_hobbies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
     DogCardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            if (layout == Layout.GRID) R.layout.grid_list_item
             else R.layout.vertical_horizontal_list_item,
            parent, false
        )
        return DogCardViewHolder(view)
    }

    override fun getItemCount(): Int = dogs.size

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        val resources = context?.resources
        val dog = dogs[position]

        holder.photo.setImageResource(dog.imageResourceId)
        holder.name.text = dog.name
        holder.age.text = resources?.getString(R.string.dog_age, dog.age)
        holder.hobbies.text = resources?.getString(R.string.dog_hobbies,
         dog.hobbies)
    }
}
