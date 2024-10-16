package com.example.learning226

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.learning226.landingfragments.ArrayAdapterFragment
import com.example.learning226.landingfragments.StorageOptionsFragment
import com.example.learning226.landingfragments.BackgroundProcessesFragment
import com.example.learning226.landingfragments.DelightfulUXFragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.learning226.databinding.ActivityMainBinding
import com.example.learning226.landingfragments.LocationAndMapsFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(ArrayAdapterFragment())


        val floatingbtn: FloatingActionButton = findViewById(R.id.floatingbtn)
        floatingbtn.setOnClickListener{
            Toast.makeText(this@MainActivity, "FLoating button clicked", Toast.LENGTH_SHORT).show()
        }

        val headnav: MaterialToolbar = findViewById(R.id.toolbar)
        headnav.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.exit->{
                    finish()
                    true
                }
                else -> false
            }
        }

        var bottomnav: BottomNavigationView = findViewById(R.id.bottom_bar)
        bottomnav.setOnItemSelectedListener {item ->
            when (item.itemId) {
                R.id.backgroundprocesses -> replaceFragment(BackgroundProcessesFragment())
                R.id.delightfuluiux -> replaceFragment(DelightfulUXFragment())
                R.id.arrayadapter -> replaceFragment(ArrayAdapterFragment())
                R.id.storageoptions -> replaceFragment(StorageOptionsFragment())
                R.id.locationandmaps -> replaceFragment(LocationAndMapsFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}