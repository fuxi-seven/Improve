package com.hly.improve.motion

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.helper.widget.Carousel
import com.hly.improve.R
import com.hly.improve.databinding.ActivityCarMotionBinding

class CarMotionActivity : AppCompatActivity() {

    private lateinit var binding:ActivityCarMotionBinding

    var images = intArrayOf(
        R.drawable.car1,
        R.drawable.car3,
        R.drawable.car4,
        R.drawable.car2
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarMotionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val carsoul = binding.carousel
        carsoul.setAdapter(object : Carousel.Adapter {
            override fun count(): Int {
                return 4
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