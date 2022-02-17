package com.example.cnb.dogbreedlist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.cnb.DogBreedApplication
import com.example.cnb.dogbreeddetails.DogBreedDetailsActivity
import com.example.cnb.R
import com.example.cnb.repository.DogBreedRepo
import kotlinx.android.synthetic.main.dog_breed_activity.*
import javax.inject.Inject


class DogBreedActivity : AppCompatActivity() {

    @Inject
    lateinit var presenter: DogBreedPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dog_breed_activity)
        (application as DogBreedApplication).applicationComponent.inject(this)
        loadDogBreedList()
    }

    private fun loadDogBreedList() {
        presenter.getDogBreedList(dogListListener)
    }

    private val dogListListener: DogBreedRepo.DogListListener = object : DogBreedRepo.DogListListener {
        override fun onDogListLoaded(dogBreedMap: Map<String, List<String>>) {
            dog_breed_rv.adapter = DogBreedListAdapter(presenter.createDogBreedList(dogBreedMap), viewHolderListener)
            dog_breed_rv.addItemDecoration(DividerItemDecoration(this@DogBreedActivity, DividerItemDecoration.VERTICAL))
        }

        override fun onFailure() {
            Toast.makeText(this@DogBreedActivity, "List load failed", Toast.LENGTH_LONG).show()
        }

    }

    private val viewHolderListener: DogBreedListAdapter.ViewHolderListener = object : DogBreedListAdapter.ViewHolderListener {
        override fun onBreedClicked(breed: String, subBreed: String) {
            val intent = Intent(this@DogBreedActivity, DogBreedDetailsActivity::class.java)
                .putExtra(DogBreedDetailsActivity.DOG_BREED_KEY, breed)
                .putExtra(DogBreedDetailsActivity.SUB_BREED_KEY, subBreed)
            startActivity(intent)
        }
    }
}