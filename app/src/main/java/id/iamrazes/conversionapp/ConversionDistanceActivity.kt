package id.iamrazes.conversionapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import id.iamrazes.conversionapp.databinding.ActivityConversionDistanceBinding
import java.text.DecimalFormat

class ConversionDistanceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConversionDistanceBinding
    private val distanceTypes = arrayOf(
        "Meter ke Kilometer",
        "Kilometer ke Meter"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConversionDistanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, distanceTypes)
        (binding.inputTypeConversion as? AutoCompleteTextView)?.setAdapter(adapter)

        binding.inputTypeConversion.setOnClickListener {
            (binding.inputTypeConversion as? AutoCompleteTextView)?.showDropDown()
        }

        binding.inputDistance.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                convertDistance()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not used
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Not used
            }
        })

        binding.resultTextView.text = "0"
    }

    private fun convertDistance() {
        val inputText = binding.inputDistance.text.toString()
        val inputValue = inputText.toDoubleOrNull()

        if (inputValue != null) {
            val selectedTypeIndex = binding.inputTypeConversion.text.toString().let { type ->
                distanceTypes.indexOf(type)
            }

            if (selectedTypeIndex != -1) {
                val convertedValue = when (selectedTypeIndex) {
                    0 -> convertMeterToKilometer(inputValue)
                    1 -> convertKilometerToMeter(inputValue)
                    else -> 0.0
                }

                val decimalFormat = DecimalFormat("#.##")
                binding.resultTextView.text = decimalFormat.format(convertedValue)
            }
        }
    }

    private fun convertMeterToKilometer(meter: Double): Double {
        return meter / 1000
    }

    private fun convertKilometerToMeter(kilometer: Double): Double {
        return kilometer * 1000
    }
}
