package com.example.learning226

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.learning226.landingfragments.ArrayAdapterFragment
import com.example.learning226.landingfragments.BlankFragment
import com.example.learning226.landingfragments.HomeFragment
import com.example.learning226.landingfragments.SettingsFragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.learning226.databinding.ActivityMainBinding;

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        val headnav: MaterialToolbar = findViewById(R.id.toolbar)
        headnav.setOnMenuItemClickListener {
            when(it.itemId){
//                R.id.customLVAA->{
//                    startActivity(Intent(this@MainActivity, CustomListViewArrayAdapterExample::class.java))
//                    true
//                }
//                R.id.customLVBA->{
//                    startActivity(Intent(this@MainActivity, CustomListViewBaseAdapterExample::class.java))
//                    true
//                }
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
                R.id.home -> replaceFragment(HomeFragment())
                R.id.action_settings -> replaceFragment(SettingsFragment())
                R.id.arrayadapter -> replaceFragment(ArrayAdapterFragment())
                R.id.customfrag -> replaceFragment(BlankFragment())
            }
            true
//            when(menuItem.itemId){
//                R.id.alarmmanager->{
////                    Toast.makeText(this, "searched", Toast.LENGTH_SHORT).show()
//                    true
//                }
//                R.id.action_settings->{
////                    Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show()
//                    true
//                }
//                R.id.home->{
////                    Toast.makeText(this, "homed", Toast.LENGTH_SHORT).show()
//                    true
//                }
//                R.id.exit->{
//                    finish()
//                    true
//                }
//                else-> false
//            }

        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}