package com.example.learning226.locationandmaps.currentlocation

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.learning226.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.Locale

class CurrentLocationMain : AppCompatActivity() {

    private val LOCATION_PERMISSION_REQ_CODE = 1000;
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private lateinit var btGetLocation: Button
    private lateinit var btOpenMap:Button
    private lateinit var tvLatitude: TextView
    private lateinit var tvLongitude:TextView
    private lateinit var tvProvider:TextView
    private lateinit var tvCountry:TextView
    private lateinit var tvAddress:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_location_main)

        btGetLocation = findViewById(R.id.currlocgetloc)
        btOpenMap=findViewById(R.id.currlocopenmap)
        tvLatitude=findViewById(R.id.currloclatitude)
        tvLongitude=findViewById(R.id.currloclongitude)
        tvProvider=findViewById(R.id.currlocprovider)
        tvCountry=findViewById(R.id.currloccountry)
        tvAddress=findViewById(R.id.currlocaddress)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        btGetLocation.setOnClickListener {
            getCurrentLocation()
        }
        btOpenMap.setOnClickListener {
            openMap()
        }
    }
    private fun getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQ_CODE);
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                val geocoder = Geocoder(this, Locale.getDefault())
                val list: List<Address> =
                    geocoder.getFromLocation(location.latitude, location.longitude,
                        1) as List<Address>

                latitude = list[0].latitude
                longitude = list[0].longitude

                tvLatitude.text = "Latitude: ${list[0].latitude}"
                tvLongitude.text = "Longitude: ${list[0].longitude}"
                tvProvider.text = "Provider: ${location.provider}"
                tvCountry.text="Country: ${list[0].countryName}"
                tvAddress.text="Address: ${list[0].getAddressLine(0)}"

                btOpenMap.visibility = View.VISIBLE
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed on getting current location",
                    Toast.LENGTH_SHORT).show()
            }
    }
    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        when (requestCode) {
            LOCATION_PERMISSION_REQ_CODE -> {
                if (grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    Toast.makeText(this, "You need to grant " +
                            "permission to access location",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun openMap() {
        val uri = Uri.parse("geo:${latitude},${longitude}")
        val mapIntent = Intent(Intent.ACTION_VIEW, uri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }
}