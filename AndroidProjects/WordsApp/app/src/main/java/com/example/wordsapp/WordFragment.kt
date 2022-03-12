package com.example.wordsapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordsapp.databinding.FragmentLetterBinding
import com.example.wordsapp.databinding.FragmentWordBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WordFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WordFragment : Fragment() {
    companion object {
        const val LETTER = "LETTER"
        const val GGL_QUERY = "https://www.google.com/search?q="
        const val DDG_QUERY = "https://www.duckduckgo.com/search?q="
    }

    private var _binding: FragmentWordBinding? = null
    private val binding get() = _binding!!

    private lateinit var letter: String

    // Basic setup of object, including argument unloading
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("WordFragment", "OnCreate")
        arguments?.let {
            letter = it.getString(LETTER) ?: "A"
        }
    }

    // Inflate layout and return top-level view
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("WordFragment", "OnCreateView")
        _binding = FragmentWordBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Close analogy to Activity.onCreate
    override fun onViewCreated(view: View, state: Bundle?) {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = WordAdapter(letter, requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}