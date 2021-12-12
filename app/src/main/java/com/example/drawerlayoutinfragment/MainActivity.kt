package com.example.drawerlayoutinfragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.drawerlayoutinfragment.R
import com.example.drawerlayoutinfragment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var navController:NavController
    private lateinit var drawerlayout:DrawerLayout
    private lateinit var appBarconfig : AppBarConfiguration
    private lateinit var navhostFragment: NavHostFragment
    private lateinit var listener:NavController.OnDestinationChangedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.myToolbar)
        navhostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navhostFragment.navController
        drawerlayout = binding.drawerlayout
        binding.navigationView.setupWithNavController(navController)
        appBarconfig = AppBarConfiguration(navController.graph,drawerlayout)
        setupActionBarWithNavController(navController,appBarconfig)

        listener = NavController.OnDestinationChangedListener{controller, destination, arguments ->
            if (destination.id==R.id.home){
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.teal_700)))
                    Toast.makeText(this, "home clicked", Toast.LENGTH_SHORT).show()
                }
            }else if (destination.id==R.id.fragmentSetting){
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.purple_700)))
                Toast.makeText(this, "setting clicked", Toast.LENGTH_SHORT).show()
            }
        } else {

            }

        }


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarconfig) || super.onSupportNavigateUp()
    }

    override fun onPause() {
        super.onPause()
        navController.addOnDestinationChangedListener(listener)
    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(listener)

    }
}