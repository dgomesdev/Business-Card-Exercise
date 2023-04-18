package dev.dgomes.businesscard.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import dev.dgomes.businesscard.App
import dev.dgomes.businesscard.databinding.ActivityMainBinding
import dev.dgomes.businesscard.util.Image

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy {BusinessCardAdapter()}
    private val mainViewModel : MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvBusinessCard.adapter = adapter
        getAllBusinessCards()

        setListeners()
    }

    private fun setListeners() {
        binding.addCardButton.setOnClickListener {
            val intent = Intent(this, AddCardActivity::class.java)
            startActivity(intent)
        }

        adapter.listenerShare = {card ->
            Image.share(this, card)
        }
    }

    private fun getAllBusinessCards() {
        mainViewModel.getAll().observe(this) { businessCards ->
            adapter.submitList(businessCards)
        }
    }

}