package com.azimut4946777.semafor

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.azimut4946777.semafor.databinding.ActivityMainBinding
import java.util.*

class MainActivity : Activity() {
    lateinit var binding: ActivityMainBinding
    var imagesArray = intArrayOf(R.drawable.semafor_red,R.drawable.semafor_yellow,
        R.drawable.semafor_green)
    var counter = 0
    var timer: Timer?= null
    var is_run = false
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.imageButton.setOnClickListener {
            onClickStartStop()
        }
    }
    private fun onClickStartStop(){
        if(!is_run) {
            timer = Timer()
            StartStop()
            with(binding){
                imageButton.setImageResource(R.drawable.button_stop)

            }
            is_run = true
        }else{
            timer?.cancel()
            counter = 0
            with(binding){
                imageButton.setImageResource(R.drawable.button_start)
                imageView.setImageResource(R.drawable.semafor_grey)
            }
            is_run = false
        }


    }

    private fun StartStop(){
     timer?.schedule(object: TimerTask(){
         override fun run() {
             runOnUiThread {
                 binding.imageView.setImageResource(imagesArray[counter])
                 counter++
                 if(counter == imagesArray.size) {
                     counter = 0
                 }

             }

         }

     }, 0,1000)
      }
  }