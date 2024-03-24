package id.iamrazes.conversionapp

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import id.iamrazes.conversionapp.databinding.ActivityConversionMassBinding
import java.text.DecimalFormat

class ConversionMassActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConversionMassBinding
    private val massTypes = arrayOf(
        "Gram ke Kilogram",
        "Kilogram ke Gram"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConversionMassBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ArrayAdapter(this, R.layout.simple_dropdown_item_1line, massTypes)
        (binding.inputTypeConversion as? AutoCompleteTextView)?.setAdapter(adapter)

        binding.inputTypeConversion.setOnClickListener {
            (binding.inputTypeConversion as? AutoCompleteTextView)?.showDropDown()
        }

        binding.inputMass.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                convertMass()
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

    private fun convertMass() {
        val inputText = binding.inputMass.text.toString()
        val inputValue = inputText.toDoubleOrNull()

        if (inputValue != null) {
            val selectedTypeIndex = binding.inputTypeConversion.text.toString().let { type ->
                massTypes.indexOf(type)
            }

            if (selectedTypeIndex != -1) {
                val convertedValue = when (selectedTypeIndex) {
                    0 -> convertGramToKilogram(inputValue)
                    1 -> convertKilogramToGram(inputValue)
                    else -> 0.0
                }

                val decimalFormat = DecimalFormat("#.##")
                binding.resultTextView.text = decimalFormat.format(convertedValue)
            }
        }
    }

    private fun convertGramToKilogram(gram: Double): Double {
        return gram / 1000
    }

    private fun convertKilogramToGram(kilogram: Double): Double {
        return kilogram * 1000
    }
}
