package com.akin.figmatask

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import android.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.ui.setupWithNavController
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class DrawerExample : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    private lateinit var navController: NavController
    private val toolbar by lazy { findViewById<MaterialToolbar>(R.id.toolbar) }
    private val navigationView by lazy { findViewById<NavigationView>(R.id.navigation_view) }
    private val drawerLayout by lazy { findViewById<DrawerLayout>(R.id.drawer_layout) }
    private val container by lazy { findViewById<ConstraintLayout>(R.id.container) }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer_example)
        toolbar.setNavigationOnClickListener { drawerLayout.openDrawer(GravityCompat.START) }
        navController = Navigation.findNavController(this, R.id.host_fragment)
        navigationView.setupWithNavController(navController)
      //  navigationView.setNavigationItemSelectedListener(this)
        navigationView.isSelected
        setupDrawerAnimation()


    }

    private fun setupDrawerAnimation() {
        val scaleControl = 6f

        drawerLayout.setScrimColor(Color.TRANSPARENT)
        drawerLayout.elevation = 0f
        drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {

                val slideX = drawerView.width * slideOffset
                container.translationX = slideX
                container.scaleY = 1 - (slideOffset / scaleControl)
            }

            override fun onDrawerStateChanged(newState: Int) {}
            override fun onDrawerClosed(drawerView: View) {}
            override fun onDrawerOpened(drawerView: View) {}
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        drawerLayout.closeDrawer(GravityCompat.START)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.frag_home -> {
                Toast.makeText(this, "My Doctors Clicked", Toast.LENGTH_LONG).show()
            }

        }
        return true


    }
}