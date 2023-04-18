package dev.dgomes.businesscard.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import dev.dgomes.businesscard.App
import dev.dgomes.businesscard.data.BusinessCard
import dev.dgomes.businesscard.data.BusinessCardDao
import dev.dgomes.businesscard.data.BusinessCardDatabase
import dev.dgomes.businesscard.data.BusinessCardRepository
import dev.dgomes.businesscard.databinding.ActivityAddCardBinding

class AddCardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddCardBinding
    private val mainViewModel : MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
    }

    private fun setListeners() {
        binding.closeButton.setOnClickListener {
            finish()
        }

        binding.addNewCardButton.setOnClickListener {
            val businessCard = BusinessCard(
                name = binding.nameInput.editText?.text.toString(),
                company = binding.companyInput.editText?.text.toString(),
                phone = binding.phoneInput.editText?.text.toString(),
                email = binding.emailInput.editText?.text.toString(),
                color = binding.colorInput.editText?.text.toString(),
            )
            mainViewModel.insert(businessCard)
            Snackbar.make(binding.root, "Success!", Snackbar.LENGTH_LONG).show()
            finish()
        }
    }
}