package br.com.fundatec.fundatecheroesti21.character.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.fundatec.core.hide
import br.com.fundatec.core.show
import br.com.fundatec.fundatecheroesti21.R
import br.com.fundatec.fundatecheroesti21.character.presentation.CharacterViewModel
import br.com.fundatec.fundatecheroesti21.character.presentation.model.CharacterViewState
import br.com.fundatec.fundatecheroesti21.databinding.ActivityCharcaterBinding
import br.com.fundatec.fundatecheroesti21.home.view.HomeActivity
import com.google.android.material.snackbar.Snackbar


class CharcaterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharcaterBinding

    private val viewModel: CharacterViewModel by viewModels();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharcaterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeObserver()

        binding.floatingButton.setOnClickListener {
            viewModel.validateInputs(
                name = binding.nameHero.text.toString(),
                description = binding.description.text.toString(),
                age = binding.age.text.toString(),
                birth_date = binding.date.text.toString()
            )
        }
    }

    private fun initializeObserver() {
        viewModel.state.observe(this) { viewState ->
            when (viewState) {
                CharacterViewState.ShowHomeScreen -> showHome()
                CharacterViewState.ShowLoading -> showLoading()
                CharacterViewState.ShowNameError -> showNameError()
                CharacterViewState.ShowMessageError -> showSnackError()
                CharacterViewState.ShowDescriptionError -> showDescriptionError()
                CharacterViewState.ShowAgeError -> showAgeError()
                CharacterViewState.ShowBirthDateError -> showBirthDateError()
            }
        }
    }

    private fun showLoading() {
        binding.pbLoading.show()
    }

    private fun showAgeError() {
        binding.pbLoading.hide()
        binding.age.error = getString(R.string.register_age_error_message)
    }
    private fun showBirthDateError() {
        binding.pbLoading.hide()
        binding.date.error = getString(R.string.register_birthdate_error_message)
    }

    private fun showNameError() {
        binding.pbLoading.hide()
        binding.nameHero.error = getString(R.string.register_name_error_message)
    }

    private fun showHome() {
        binding.pbLoading.hide()
        val intent = Intent(this@CharcaterActivity, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showSnackError() {
        binding.pbLoading.hide()
        Snackbar.make(binding.root, R.string.login_error2_message, Snackbar.LENGTH_LONG).show()
    }

    private fun showDescriptionError() {
        binding.pbLoading.hide()
        binding.description.error = getString(R.string.register_description_error_message)
    }
}