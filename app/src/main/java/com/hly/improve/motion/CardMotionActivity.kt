package com.hly.improve.motion

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.helper.widget.Carousel
import com.hly.improve.R
import com.hly.improve.databinding.ActivityCardMotionBinding

class CardMotionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCardMotionBinding

    var images = intArrayOf(
        R.drawable.card1,
        R.drawable.card2,
        R.drawable.card3,
        R.drawable.card4,
        R.drawable.card5
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardMotionBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val carsoul = binding.carousel
        carsoul.setAdapter(object : Carousel.Adapter {
            override fun count(): Int {
                return 5
            }

            override fun populate(view: View?, index: Int) {
                if(view is ImageView){
                    view.setImageResource(images[index])
                }
            }

            override fun onNewItem(index: Int) {
                // called when an item is set
            }
        })
    }
}