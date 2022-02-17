package com.example.cnb.dogbreeddetails

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.cnb.DogBreedApplication
import com.example.cnb.R
import com.example.cnb.repository.DogBreedRepo
import kotlinx.android.synthetic.main.dog_breed_details_activity.*
import javax.inject.Inject


open class DogBreedDetailsActivity : AppCompatActivity() {

    companion object {
        const val DOG_BREED_KEY = "DOG_BREED_KEY"
        const val SUB_BREED_KEY = "SUB_BREED_KEY"
    }

    @Inject
    lateinit var presenter : DogBreedDetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dog_breed_details_activity)
        (application as DogBreedApplication).applicationComponent.inject(this)
        val breed = intent.getStringExtra(DOG_BREED_KEY)
        val subBreed = intent.getStringExtra(SUB_BREED_KEY)
        if (breed.isNullOrEmpty()) {
            Toast.makeText(this@DogBreedDetailsActivity, "Error loading breed", Toast.LENGTH_LONG).show()
        } else {
            loadDogBreedDetails(breed, subBreed)
        }
    }

    private fun loadDogBreedDetails(breed: String, subBreed: String?) {
        val displayName = "$breed $subBreed"
        dog_breed_title.text = displayName
        presenter.loadImageURL(breed, subBreed, dogBreedDetailsListener)

    }

    private val dogBreedDetailsListener: DogBreedRepo.DogBreedDetailsListener = object : DogBreedRepo.DogBreedDetailsListener {
        override fun onImageUrlLoaded(url: String) {
            val circularProgressDrawable = CircularProgressDrawable(this@DogBreedDetailsActivity)
            circularProgressDrawable.strokeWidth = 10f
            circularProgressDrawable.centerRadius = 45f
            circularProgressDrawable.start()

            Glide.with(this@DogBreedDetailsActivity)
                .load(url)
                .apply(RequestOptions().placeholder(circularProgressDrawable))
                .into(dog_breed_image)
        }

        override fun onFailure() {
            Toast.makeText(this@DogBreedDetailsActivity, "Image load failed", Toast.LENGTH_LONG).show()
        }
    }

}