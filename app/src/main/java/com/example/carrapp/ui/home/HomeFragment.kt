package com.example.carrapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carrapp.Item
import com.example.carrapp.ItemsAdapter
import com.example.carrapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val itemsList = binding.rView
        val items = arrayListOf<Item>()

        items.add(Item(1, "mers", "S 500 Sedan", "Mersedes-Benz", 2500))
        items.add(Item(1, "mers", "S 300 Sedan", "Mersedes-Benz", 2300))
        items.add(Item(1, "mers", "S 200 Sedan", "Mersedes-Benz", 2200))

        itemsList.layoutManager = LinearLayoutManager(requireContext())
        itemsList.adapter = ItemsAdapter(items, requireContext())

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}