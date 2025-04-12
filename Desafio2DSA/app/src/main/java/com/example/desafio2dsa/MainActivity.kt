package com.example.desafio2dsa

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio2dsa.controller.PaisApiClient
import com.example.desafio2dsa.view.CountryAdapter
import com.example.desafio2dsa.view.CountryDetailActivity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewCountries)
        recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            try {
                val countries = PaisApiClient.service.getAllCountries()
                adapter = CountryAdapter(countries) { selectedCountry ->
                    val intent = Intent(this@MainActivity, CountryDetailActivity::class.java)
                    intent.putExtra("country_name", selectedCountry.name.official)
                    startActivity(intent)
                }
                recyclerView.adapter = adapter
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}