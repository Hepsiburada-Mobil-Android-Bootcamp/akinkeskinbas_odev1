package com.akin.figmatask


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.akin.figmatask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
       // hideSystemUI()
        setContentView(binding.root)

        val buttonFigma = binding.figmaScreen
        val buttonLiveData = binding.liveDataExample
        val buttonDrawer = binding.figmaDrawer
        val buttunSign = binding.signIn


        buttonFigma.setOnClickListener {
            startActivity(Intent(this,HomeScreen::class.java))
        }
        buttonLiveData.setOnClickListener {
            startActivity(Intent(this,LiveData::class.java))
        }
        buttonDrawer.setOnClickListener {
            startActivity(Intent(this,DrawerExample::class.java))
        }
        buttunSign.setOnClickListener {
            startActivity(Intent(this,SignScreen::class.java))
        }


    }
    private fun hideSystemUI() {

        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE

                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }
}