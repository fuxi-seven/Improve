package com.hly.improve.motion

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.helper.widget.Carousel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.hly.improve.R
import com.hly.improve.databinding.ActivityAnimeBinding
import jp.wasabeef.glide.transformations.BlurTransformation

class AnimeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimeBinding

    var images = intArrayOf(
        R.drawable.dm1,
        R.drawable.dm2,
        R.drawable.dm3,
        R.drawable.dm4,
        R.drawable.dm5
    )

    var imageDrawable = Array<Drawable?>(5) { _ ->
        null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val ivBackground = binding.ivBackground
        val carsoul = binding.carousel
        carsoul.setAdapter(object : Carousel.Adapter {
            override fun count(): Int {
                return 5
            }

            override fun populate(view: View?, index: Int) {
                Log.d("Seven", "index: $index")
                Log.d("Seven", "currentIndex: " + carsoul.currentIndex)
                if (view is ImageView) {
                    view.setImageResource(images[index])
                    // val targetIndex = (carsoul.currentIndex + 2) % 5
                    val drawable = imageDrawable.getOrNull(index)
                    if (drawable == null) {
                        Glide.with(this@AnimeActivity)
                            .load(images[index])
                            .apply(RequestOptions().transform(BlurTransformation(20)))
                            .into(object : SimpleTarget<Drawable>() {
                                override fun onResourceReady(
                                    resource: Drawable,
                                    transition: Transition<in Drawable>?
                                ) {
                                    ivBackground.setImageDrawable(resource)
                                    imageDrawable[index] = resource
                                }
                            })
                    } else {
                        ivBackground.setImageDrawable(drawable)
                    }
                }
            }

            override fun onNewItem(index: Int) {
                // called when an item is set
            }
        })
    }
}