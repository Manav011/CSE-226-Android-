package com.example.learning226.coroutines

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.learning226.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class CoroutinesExamples : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private val img1url = "https://stsci-opo.org/STScI-01HZ7HA3A3ZKSJJ90YCZPV7XQ2.png"
    private val imageUrl = "https://img.freepik.com/free-vector/night-time-sky-background-with-glittering-stars_1048-19664.jpg"
    private val pdfUrl = "https://www.iitk.ac.in/esc101/2009Jan/lecturenotes/timecomplexity/TimeComplexity_using_Big_O.pdf"
    private val videoUrl = "https://videos.pexels.com/video-files/3145223/3145223-uhd_2560_1440_30fps.mp4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_coroutines_examples)
        viewPager = findViewById(R.id.coroutineviewPager)
        val fragments = listOf(Image(), Video(), Audio(), PdfDoc(), TextFile(), JsonFile()) // Replace with your fragment instances
        val adapter = ViewPagerAdapter(fragments, supportFragmentManager,lifecycle)
        viewPager.adapter = adapter

        val tabLayout: TabLayout = findViewById(R.id.coroutinetabLayout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            // Set tab text or icon here if needed
            var tabtext = ""
            when(position){
                0 -> tabtext = "Image"
                1 -> tabtext = "Video"
                2 -> tabtext = "Audio"
                3 -> tabtext = "Pdf"
                4 -> tabtext = "Text"
                5 -> tabtext = "Json"
            }
            tab.text = tabtext
        }.attach()
    }
}