package com.jonareas.corso.view.dogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jonareas.corso.databinding.FragmentDogListBinding

class DogListFragment : Fragment() {

    private var _binding : FragmentDogListBinding? = null
    private val binding : FragmentDogListBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDogListBinding.inflate(inflater, container, false)
        setupRecyclerView()
        return binding.root
    }

    private fun setupRecyclerView() = binding.recyclerViewDogList.run {
        layoutManager = LinearLayoutManager(activity)


    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}