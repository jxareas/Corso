package com.jonareas.corso.view.dogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jonareas.corso.R
import com.jonareas.corso.data.model.Dog
import com.jonareas.corso.databinding.FragmentDogDetailBinding
import com.jonareas.corso.view.MainActivity
import com.jonareas.corso.viewmodel.DogDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DogDetailFragment : Fragment() {

    private var _binding : FragmentDogDetailBinding? = null
    private val binding : FragmentDogDetailBinding get() = _binding!!

    private var dogUuid : Int = 0
    private var selectedDog : Dog? = null

    private val dogViewModel: DogDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dog_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            dogUuid = DogDetailFragmentArgs.fromBundle(it).dogId
        }
        dogViewModel.fetchDogFromLocalDatabase(dogUuid)
        observeViewModel()
    }

    private fun observeViewModel() {
        dogViewModel.selectedDog.observe(viewLifecycleOwner) { dog ->
            selectedDog = dog
            dog?.let {
                binding.dog = dog
                (activity as MainActivity).supportActionBar?.title = dog.dogBreed
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}