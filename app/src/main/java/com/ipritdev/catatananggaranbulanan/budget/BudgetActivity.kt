package com.ipritdev.catatananggaranbulanan.budget

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ipritdev.catatananggaranbulanan.R
import com.ipritdev.catatananggaranbulanan.databinding.ActivityBudgetBinding

class BudgetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBudgetBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBudgetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup NavHost
        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHost.navController

        // Setup actionbar with NavHost
        setupActionBarWithNavController(navController)

        // Setup bottomNav with NavHost
        binding.bottomNav.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}