package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.tiptime.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateTip.setOnClickListener{calculateTip()}
    }

    private fun calculateTip() {
        val cost = binding.serviceCostEt.text.toString().toDoubleOrNull()
        val tipPct = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_20 -> 0.20
            R.id.option_18 -> 0.18
            else -> 0.15
        }
        val roundUp = binding.tipRound.isChecked

        if (cost != null) {
            Log.e("com.example.tiptime","Cost is $cost")
            val tip = if (roundUp) kotlin.math.ceil(cost * tipPct) else cost * tipPct

            binding.tipResult.text = "Tip: $tip"
            binding.tipRound.setChecked(false)
        }
        else
            binding.tipResult.text = ""
    }
}