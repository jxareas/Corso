package com.jonareas.corso.view.dogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jonareas.corso.databinding.FragmentDogListBinding
import com.jonareas.corso.utils.attachGoToTopButton
import com.jonareas.corso.view.adapter.DogAdapter
import com.jonareas.corso.viewmodel.DogListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DogListFragment : Fragment() {

    private var _binding: FragmentDogListBinding? = null
    private val binding: FragmentDogListBinding get() = _binding!!

    private val dogViewModel: DogListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDogListBinding.inflate(inflater, container, false)
        setupRecyclerView()
        observeDogList()
        return binding.root
    }

    private fun observeDogList() {
        lifecycleScope.launch {
            dogViewModel.fetchFromRemote()
            dogViewModel.dogs.observe(viewLifecycleOwner) { dogs ->
                dogs?.let { (binding.recyclerViewDogList.adapter as DogAdapter).submitList(it) }
            }
        }
    }

    private fun setupRecyclerView() = binding.recyclerViewDogList.run {
        attachGoToTopButton(binding.fabGoToTop)
        layoutManager = LinearLayoutManager(activity)
        adapter = DogAdapter()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}