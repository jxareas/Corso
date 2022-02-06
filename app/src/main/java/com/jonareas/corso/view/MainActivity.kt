package com.jonareas.corso.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.jonareas.corso.R
import com.jonareas.corso.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()
    }

    private fun setupNavigation() : Unit = binding.run {
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as
                NavHostFragment
        navController = navHost.navController
        setupActionBarWithNavController(navController, AppBarConfiguration(navController.graph))
    }
}