package com.jonareas.corso.view.dogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jonareas.corso.databinding.FragmentDogListBinding
import com.jonareas.corso.view.adapter.DogAdapter
import com.jonareas.corso.viewmodel.DogListViewModel
import kotlinx.coroutines.launch

class DogListFragment : Fragment() {

    private var _binding : FragmentDogListBinding? = null
    private val binding : FragmentDogListBinding get() = _binding!!

    private val dogViewModel : DogListViewModel = DogListViewModel()

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
        adapter = DogAdapter()
//        (adapter as DogAdapter).submitList(listOf(Dog("1123", "Rottweiller", "20 years",
//        "Some Grouop", "Playing", "Aggressive", "dfjdkjsaj")))
        lifecycleScope.launch {
            (adapter as DogAdapter).submitList(dogViewModel.fetchFromRemote())
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}