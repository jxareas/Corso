package com.jonareas.corso.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jonareas.corso.data.model.Dog
import com.jonareas.corso.databinding.ListItemDogBinding
import com.jonareas.corso.utils.help
import com.jonareas.corso.utils.loadImage
import com.jonareas.corso.utils.progressDrawable

class DogAdapter :
    ListAdapter<Dog, DogAdapter.DogViewHolder>(DiffCallback)  {

    private companion object DiffCallback : DiffUtil.ItemCallback<Dog>() {
        override fun areItemsTheSame(oldItem: Dog, newItem: Dog): Boolean =
            oldItem.uuid == newItem.uuid

        override fun areContentsTheSame(oldItem: Dog, newItem: Dog): Boolean =
            (oldItem.dogBreed == newItem.dogBreed) && (oldItem.lifeSpan == newItem.lifeSpan)

    }

    inner class DogViewHolder(private val binding : ListItemDogBinding) :
            RecyclerView.ViewHolder(binding.root) {
                fun bind(dog : Dog) : Unit = binding.run {
                    textViewDogBreed.text = dog.dogBreed
                    textViewDogLifespan.text = dog.lifeSpan
                    imageViewDogPhoto.loadImage(dog.imageUrl, progressDrawable(itemView.context))
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder =
        DogViewHolder(parent help ListItemDogBinding::inflate)

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) : Unit =
        holder.bind(getItem(position))

}