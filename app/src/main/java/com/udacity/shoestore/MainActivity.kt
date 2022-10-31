package com.udacity.shoestore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.ActivityMainBinding
import com.udacity.shoestore.models.ShoeStoreViewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var _binding: ActivityMainBinding? = null
    private lateinit var shoeStoreViewModel: ShoeStoreViewModel
    var shoeListFragmentActive = false
    private lateinit var navController: NavController

    private val binding: ActivityMainBinding get() = binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shoeStoreViewModel = ViewModelProvider(this).get(ShoeStoreViewModel::class.java)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
/*
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
            navController = navHostFragment.findNavController()
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController)
*/

        val navController = this.findNavController(R.id.navHostFragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        Timber.plant(Timber.DebugTree())
    }

    override fun onSupportNavigateUp(): Boolean {
        if (!shoeStoreViewModel.shoeListFragmentActive) {
            val navController = this.findNavController(R.id.navHostFragment)
            return NavigationUI.navigateUp(navController, appBarConfiguration)
        }
        return true
    }

    override fun onBackPressed() {
        if (!shoeStoreViewModel.shoeListFragmentActive) {
            super.onBackPressed()
        }
    }

}
