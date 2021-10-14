package com.akin.figmatask

import android.R
import android.app.ProgressDialog
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.akin.figmatask.databinding.ActivityLiveDataBinding
import com.akin.figmatask.databinding.ActivityMainBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.load.resource.gif.GifDrawableResource
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LiveData : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val message: MutableLiveData<String> by lazy { MutableLiveData<String>() }
        super.onCreate(savedInstanceState)
        val binding = ActivityLiveDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listView = binding.listView
        val messageText = binding.botMessage



        //dummyList

        val inputs = listOf(
            "Hello",
            "How are you?",
            "Who are you?",
            "Who created you?",
            "What is your name?",
            "Tell me a joke?"
        )
        val outPuts = listOf(
            "Hi, welcome the Akin`s livedata homework!",
            "I am super cool but feel lonely...",
            "I'm the best chat bot you've ever seen",
            "You already know him! It's Akın \uD83D\uDE0E",
            "You can call me super robot Akita",
            "What's a robot's favourite country? Bots-wana. \uD83E\uDD23"
        )



        //adapter init
        val adapter = ArrayAdapter<String>(this, R.layout.simple_list_item_1, inputs)
        listView.adapter = adapter

        //main logic
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->


                message.value = parent.getItemAtPosition(position) as String
                message.observeForever {
                    when (it) {
                        inputs[position] -> {

                            messageText.text = outPuts[position]
                        }

                    }

                }
            }

    }
}