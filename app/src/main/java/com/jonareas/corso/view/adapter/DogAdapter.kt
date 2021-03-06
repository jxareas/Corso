package com.jonareas.corso.view.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jonareas.corso.data.model.Dog
import com.jonareas.corso.databinding.ListItemDogBinding
import com.jonareas.corso.utils.help
import com.jonareas.corso.utils.loadImage
import com.jonareas.corso.utils.navigateByAction
import com.jonareas.corso.utils.progressDrawable
import com.jonareas.corso.view.dogs.DogViewPagerFragmentDirections

class DogAdapter(private val onHolderClick : (View) -> Unit) :
    ListAdapter<Dog, DogAdapter.DogViewHolder>(DiffCallback) {

    private companion object DiffCallback : DiffUtil.ItemCallback<Dog>() {
        override fun areItemsTheSame(oldItem: Dog, newItem: Dog): Boolean =
            oldItem.uuid == newItem.uuid

        override fun areContentsTheSame(oldItem: Dog, newItem: Dog): Boolean =
            (oldItem.dogBreed == newItem.dogBreed) && (oldItem.lifeSpan == newItem.lifeSpan)

    }

    inner class DogViewHolder(private val binding: ListItemDogBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener { navigateToDetails(getItem(adapterPosition).uuid, it) }
            binding.buttonDetails.setOnClickListener {
                navigateToDetails(
                    getItem(adapterPosition).uuid,
                    it
                )
            }
            binding.buttonHide.setOnClickListener { removeItem(adapterPosition) }
        }


        fun bind(dog: Dog): Unit = binding.run {
            textViewDogBreed.text = dog.dogBreed
            textViewDogLifespan.text = dog.lifeSpan
            imageViewDogPhoto.loadImage(dog.imageUrl, progressDrawable(itemView.context))
        }
    }

    private fun removeItem(position: Int) {
        val currentList = currentList.toMutableList()
        currentList.removeAt(position)
        submitList(currentList)
    }

    private fun navigateToDetails(dogId: Int, view: View) {
        val action = DogViewPagerFragmentDirections.toDogDetailFragment(dogId)
        view.navigateByAction(action)
        onHolderClick(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder =
        DogViewHolder(parent help ListItemDogBinding::inflate)

    override fun onBindViewHolder(holder: DogViewHolder, position: Int): Unit =
        holder.bind(getItem(position))


}