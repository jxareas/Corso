package com.jonareas.corso.view.dogs

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jonareas.corso.R
import com.jonareas.corso.databinding.FragmentDogListBinding
import com.jonareas.corso.utils.attachGoToTopButton
import com.jonareas.corso.utils.gone
import com.jonareas.corso.utils.toast
import com.jonareas.corso.utils.visible
import com.jonareas.corso.view.adapter.DogAdapter
import com.jonareas.corso.viewmodel.DogListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DogListFragment : Fragment(), SearchView.OnQueryTextListener {

    private var _binding: FragmentDogListBinding? = null
    private val binding: FragmentDogListBinding get() = _binding!!
    private lateinit var searchView: SearchView

    private val dogViewModel: DogListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDogListBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        setupRecyclerView()
        setupSwipeRefreshLayout()
        observeDogList()
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        val searchMenuItem = menu.findItem(R.id.menu_search)
        searchView = (searchMenuItem?.actionView as SearchView).apply {
            isSubmitButtonEnabled = true
            setOnQueryTextListener(this@DogListFragment)
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        if (searchView.query.toString().isNotEmpty()) {
            dismissSearchView()
        }
    }

    private fun setupSwipeRefreshLayout(): Unit = binding.refreshLayout.setOnRefreshListener {
        binding.recyclerViewDogList.gone()
        dogViewModel.fetchFromRemote()
        binding.recyclerViewDogList.visible()
        toast(getString(R.string.fetched_dogs_from_server))
        binding.refreshLayout.isRefreshing = false
        searchView.onActionViewCollapsed()
        binding.recyclerViewDogList.scrollToPosition(0)

    }

    private fun observeDogList() {
        lifecycleScope.launch {
            dogViewModel.fetchFromDatabase()
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

    override fun onQueryTextSubmit(query: String?): Boolean = false


    override fun onQueryTextChange(query: String?): Boolean =
        if(query != null){
            searchDatabase(query)
            true
        } else true

    private fun dismissSearchView() : Unit = searchView.run {
        onActionViewCollapsed()
    }

    private fun searchDatabase(query : String) {
        val searchQuery = "%$query%"

        dogViewModel.searchDatabase(searchQuery).observe(viewLifecycleOwner) { dogs ->
            dogs?.let {
                (binding.recyclerViewDogList.adapter as DogAdapter).submitList(it)
            }

        }
    }

}