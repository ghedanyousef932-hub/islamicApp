package com.example.islamicapp

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.islamicapp.databinding.HomeScreenBinding
import com.example.islamicapp.taps.HadethFragment
import com.example.islamicapp.taps.QuranFragment
import com.example.islamicapp.taps.RadioFragment
import com.example.islamicapp.taps.TasbehFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: HomeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        enableEdgeToEdge()
        
        super.onCreate(savedInstanceState)
        
        binding = HomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom)
            insets
        }

        binding.bottomNavigation.setOnItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.quran -> {
                    replaceFragment(QuranFragment())
                    true
                }
                R.id.ahadith -> {
                    replaceFragment((HadethFragment()))
                    true
                }
                R.id.radio -> {
                    replaceFragment(RadioFragment())
                    true
                }
                R.id.tspeh -> {
                    replaceFragment(TasbehFragment())
                    true
                }
                else -> false
            }
        }

        if (savedInstanceState == null) {
            binding.bottomNavigation.selectedItemId = R.id.quran
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
