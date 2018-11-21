package com.example.frfr01.norderstedt

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions

//TODO set bootom app bar: Zonen&Routen, Notfall, Gemeinschaft, Abenteuer, Einstellungen

abstract class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    //get Map
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Set the Map
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        //mMap.setMinZoomPreference(15.5f)
        mMap.setMinZoomPreference(14.0f)

        // Add a marker in Sydney and move the camera
        val norderstedt = LatLng(53.7107521, 9.9787904)
/*      val school = LatLng(53.7100853, 9.9814082)
        val friend = LatLng(53.712759,9.9794663)
        val sport = LatLng(53.712759, 9.9794663)
        val music = LatLng(53.7593066, 10.0029798)
        val playground = LatLng(53.712759, 9.9794663)
        val grandma = LatLng(53.712759, 9.9794663)*/

        mMap.addMarker(MarkerOptions().position(norderstedt).title("Zuhause"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(norderstedt))

/*        mMap.addMarker(MarkerOptions().position(school).title("Schule"))
        mMap.addMarker(MarkerOptions().position(friend).title("Schulfreund"))
        mMap.addMarker(MarkerOptions().position(sport).title("Sportclub"))
        mMap.addMarker(MarkerOptions().position(music).title("Musikschule"))
        mMap.addMarker(MarkerOptions().position(playground).title("Spielplatz"))
        mMap.addMarker(MarkerOptions().position(grandma).title("Oma"))*/

        // Create a LatLngBounds that includes Australia.
       /* val Norderstedt = LatLngBounds(
            LatLng(53.0, 8.0), LatLng(54.0, 11.0)
        )*/

        // Set the camera to the greatest possible zoom level that includes the
        // bounds
        //mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(norderstedt_home, 0))

        //TODO implement the GeoJsonLayer
        //TODO parse data.geoJson
        //TODO Proxy to http://dcat-ap.de/def/politicalGeocoding/regionalKey/010600063063 - which data we get?
/*        val layer = GeoJsonLayer(mMap, geoJsonData)

        val layer = GeoJsonLayer(
            mMap, R.raw.geoJsonFile,
            context
        )

        layer.addLayerToMap();*/

        /*val json: JSONObject // A JSONObject formatted as GeoJSON



        val layer = GeoJsonLayer(
            getMap(), R.raw.geoJsonFile,
            applicationContext
        )
        layer.addLayerToMap()

        mLayer.setOnFeatureClickListener(object:GeoJsonLayer.OnFeatureClickListener() {
            fun onFeatureClick(feature: Feature) {
                Log.i("GeoJsonClick", "Feature clicked: " + feature.name())
            }
        })*/



        //val layer = GeoJsonLayer(getMap(), geoJsonData)

    }
}
