package id.iamrazes.conversionapp

import android.R
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat
import id.iamrazes.conversionapp.databinding.ActivityConversionTemperatureBinding

class ConversionTemperatureActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConversionTemperatureBinding
    private val temperatureTypes = arrayOf(
        "Celsius ke Fahrenheit",
        "Celsius ke Kelvin",
        "Celsius ke Réaumur",
        "Fahrenheit ke Celsius",
        "Fahrenheit ke Kelvin",
        "Fahrenheit ke Réaumur",
        "Kelvin ke Celsius",
        "Kelvin ke Fahrenheit",
        "Kelvin ke Réaumur",
        "Réaumur ke Celsius",
        "Réaumur ke Fahrenheit",
        "Réaumur ke Kelvin"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConversionTemperatureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ArrayAdapter(this, R.layout.simple_dropdown_item_1line, temperatureTypes)
        (binding.inputTypeConversion as? AutoCompleteTextView)?.setAdapter(adapter)

        binding.inputTypeConversion.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val editText = binding.inputTypeConversion
                if (event.rawX >= editText.right - editText.compoundDrawables[2].bounds.width()) {
                    editText.showDropDown()
                    return@setOnTouchListener true
                }
            }
            return@setOnTouchListener false
        }

        binding.inputTemperature.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                convertTemperature()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        binding.resultTextView.text = "0"
    }

    private fun convertTemperature() {
        val inputText = binding.inputTemperature.text.toString()
        val inputValue = inputText.toDoubleOrNull()

        if (inputValue != null) {
            val selectedTypeIndex = binding.inputTypeConversion.text.toString().let { type ->
                temperatureTypes.indexOf(type)
            }

            if (selectedTypeIndex != -1) {
                val convertedValue = when (selectedTypeIndex) {
                    0 -> convertCelsiusToFahrenheit(inputValue)
                    1 -> convertCelsiusToKelvin(inputValue)
                    2 -> convertCelsiusToReaumur(inputValue)
                    3 -> convertFahrenheitToCelsius(inputValue)
                    4 -> convertFahrenheitToKelvin(inputValue)
                    5 -> convertFahrenheitToReaumur(inputValue)
                    6 -> convertKelvinToCelsius(inputValue)
                    7 -> convertKelvinToFahrenheit(inputValue)
                    8 -> convertKelvinToReaumur(inputValue)
                    9 -> convertReaumurToCelsius(inputValue)
                    10 -> convertReaumurToFahrenheit(inputValue)
                    11 -> convertReaumurToKelvin(inputValue)
                    else -> 0.0
                }

                val decimalFormat = DecimalFormat("#.##")
                binding.resultTextView.text = decimalFormat.format(convertedValue)
            }
        }
    }

    private fun showTemperatureTypeDialog() {
        if (binding.inputTypeConversion.text.isEmpty()) {
            showToast("Pilih Tipe Konversi Suhu")
        } else {
            convertTemperature()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun convertCelsiusToFahrenheit(celsius: Double): Double {
        return celsius * 9 / 5 + 32
    }

    private fun convertFahrenheitToCelsius(fahrenheit: Double): Double {
        return (fahrenheit - 32) * 5 / 9
    }

    private fun convertCelsiusToKelvin(celsius: Double): Double {
        return celsius + 273.15
    }

    private fun convertKelvinToCelsius(kelvin: Double): Double {
        return kelvin - 273.15
    }

    private fun convertFahrenheitToKelvin(fahrenheit: Double): Double {
        return (fahrenheit + 459.67) * 5 / 9
    }

    private fun convertKelvinToFahrenheit(kelvin: Double): Double {
        return kelvin * 9 / 5 - 459.67
    }

    private fun convertCelsiusToReaumur(celsius: Double): Double {
        return celsius * 4 / 5
    }

    private fun convertFahrenheitToReaumur(fahrenheit: Double): Double {
        return (fahrenheit - 32) * 4 / 9
    }

    private fun convertKelvinToReaumur(kelvin: Double): Double {
        return (kelvin - 273.15) * 4 / 5
    }

    private fun convertReaumurToCelsius(reaumur: Double): Double {
        return reaumur * 5 / 4
    }

    private fun convertReaumurToFahrenheit(reaumur: Double): Double {
        return reaumur * 9 / 4 + 32
    }

    private fun convertReaumurToKelvin(reaumur: Double): Double {
        return reaumur * 5 / 4 + 273.15
    }
}