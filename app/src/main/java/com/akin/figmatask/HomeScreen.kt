package com.akin.figmatask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.akin.figmatask.adapters.CustomViewPager
import com.akin.figmatask.adapters.Genres
import com.akin.figmatask.api.SimpleApi
import com.akin.figmatask.util.Constants.Companion.Base_Url
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class HomeScreen : AppCompatActivity() {

    companion object {
        var url: String? = null
    }


    private val viewPager by lazy { findViewById<ViewPager2>(R.id.m_viewPager) }
    private val rcHome by lazy { findViewById<RecyclerView>(R.id.rc_home) }
    private val homeScreenLayout by lazy { findViewById<ConstraintLayout>(R.id.homeScreenLayout) }
    private val webView by lazy { findViewById<WebView>(R.id.webView) }
    private val rotate = RotateViewPagerItem()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)



        initRc()

        ///Dummy Data
        val images = listOf(
            R.drawable.alitaa,
            R.drawable.alita,
            R.drawable.leon,
            R.drawable.strangerthings,
            R.drawable.interstellar,
            R.drawable.ghost2,

            )
        val names = listOf(
            "Alita",
            "Alita",
            "Leon",
            "Stranger Things",
            "Interstellar",
            "Ghost in The Shell",

            )
        val movieIdList = listOf(
            "tt0437086",
            "tt0437086",
            "tt0110413",
            "tt4574334",
            "tt0816692",
            "tt1219827",
        )




        //ViewPager Logics

        viewPager.clipToPadding = false
        viewPager.clipChildren = false
        viewPager.offscreenPageLimit = 3
        viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        viewPager.setPageTransformer(rotate)
        viewPager.adapter = CustomViewPager(images, names) {

            showDialog(names[it])
        }
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                getCurrentData(movieIdList[position])

                when (position) {
                    position -> homeScreenLayout.setBackgroundResource(images[position])

                }
            }
        })


    }

    private fun initRc() {
        val genreList =
            listOf("Action", "Crime", "Comedy", "Drama", "Mystery", "Fantasy", "Romance", "Horror")
        val lM = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rcHome.layoutManager = lM
        val rcAdapter = Genres(genreList)
        rcHome.adapter = rcAdapter

    }


    private fun getCurrentData(movieId: String) {
        val api =
            Retrofit.Builder().baseUrl(Base_Url).addConverterFactory(GsonConverterFactory.create())
                .build().create(SimpleApi::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val response = api.getPost(movieId).awaitResponse()
            val data = response.body()?.linkEmbed

            withContext(Dispatchers.Main) {
                url = data ?: "https://www.imdb.com/video/imdb/vi2959588889/imdb/embed"

            }
        }
    }

    private fun showDialog(movieName: String) {

        val builder = AlertDialog.Builder(this)

        builder.setTitle(movieName)

        builder.setMessage("Do you want to watch trailer ?")
        builder.setIcon(android.R.drawable.ic_menu_help)

        builder.setPositiveButton("Yes") { dialogInterface, which ->
            val intent = Intent(this, WebviewActivity::class.java)
            intent.putExtra("url", url)

            startActivity(intent)
        }

        builder.setNegativeButton("No") { dialogInterface, which ->
            dialogInterface.dismiss()
        }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }


}