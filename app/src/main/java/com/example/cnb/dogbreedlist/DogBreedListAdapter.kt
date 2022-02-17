package com.example.cnb.dogbreedlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cnb.R
import kotlinx.android.synthetic.main.dog_breed_list_itemview.view.*

class DogBreedListAdapter(private val listDogBreeds: List<Pair<String, String>>, private val viewHolderListener: ViewHolderListener) : RecyclerView.Adapter<DogBreedListAdapter.DogBreedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogBreedViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.dog_breed_list_itemview, parent, false)
        return DogBreedViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DogBreedViewHolder, position: Int) {
        val breed = listDogBreeds[position].first
        val subBreed = listDogBreeds[position].second
        val displayName = "$breed $subBreed"
        holder.breedName.text = displayName
        holder.setOnClick(viewHolderListener, breed, subBreed)
    }

    override fun getItemCount(): Int {
        return listDogBreeds.size
    }

    class DogBreedViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var breedName: TextView = itemView.breed_name

        fun setOnClick(viewHolderListener: ViewHolderListener, breed: String, subBreed: String) {
            itemView.setOnClickListener { viewHolderListener.onBreedClicked(breed, subBreed) }
        }
    }

    interface ViewHolderListener {
        fun onBreedClicked(breed: String, subBreed: String)
    }

}
