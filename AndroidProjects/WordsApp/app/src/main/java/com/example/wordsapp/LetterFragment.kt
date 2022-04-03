package com.example.wordsapp

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.FragmentLetterBinding

class LetterFragment : Fragment() {
    private var _binding: FragmentLetterBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private var layoutSwitcher : MenuItem? = null
    private var linearLayout = true

    companion object {
        const val gridWidth = 4
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e("LetterFragment", "OnCreate")
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    // General view creator.  Called once??
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("LetterFragment", "OnCreateView")

        // Inflate the layout for this fragment and return its root as the view
        _binding = FragmentLetterBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Close analogy to Activity.onCreate
    override fun onViewCreated(view: View, state: Bundle?) {
        recyclerView = binding.recyclerView
        recyclerView.adapter = LetterAdapter()
        configRecycler()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        layoutSwitcher = menu.findItem(R.id.layout_switcher)
        setSwitcherIcon()
    }

    fun setSwitcherIcon() : Unit {
        layoutSwitcher?.icon = getDrawable(requireContext(),
         if (linearLayout) R.drawable.ic_view_grid
         else R.drawable.ic_view_linear)
    }

    fun configRecycler() : Unit {
        recyclerView.layoutManager = if (linearLayout)
            LinearLayoutManager(context)
        else
            GridLayoutManager(context, gridWidth)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}