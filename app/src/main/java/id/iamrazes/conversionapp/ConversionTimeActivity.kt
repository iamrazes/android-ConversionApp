package id.iamrazes.conversionapp

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import id.iamrazes.conversionapp.databinding.ActivityConversionTimeBinding
import java.text.DecimalFormat

class ConversionTimeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConversionTimeBinding
    private val timeTypes = arrayOf(
        "Jam ke Menit",
        "Jam ke Detik",
        "Menit ke Jam",
        "Menit ke Detik",
        "Detik ke Jam",
        "Detik ke Menit"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConversionTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ArrayAdapter(this, R.layout.simple_dropdown_item_1line, timeTypes)
        (binding.inputTypeConversion as? AutoCompleteTextView)?.setAdapter(adapter)

        binding.inputTypeConversion.setOnClickListener {
            (binding.inputTypeConversion as? AutoCompleteTextView)?.showDropDown()
        }

        binding.inputTime.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                convertTime()
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

    private fun convertTime() {
        val inputText = binding.inputTime.text.toString()
        val inputValue = inputText.toDoubleOrNull()

        if (inputValue != null) {
            val selectedTypeIndex = binding.inputTypeConversion.text.toString().let { type ->
                timeTypes.indexOf(type)
            }

            if (selectedTypeIndex != -1) {
                val convertedValue = when (selectedTypeIndex) {
                    0 -> convertHourToMinute(inputValue)
                    1 -> convertHourToSecond(inputValue)
                    2 -> convertMinuteToHour(inputValue)
                    3 -> convertMinuteToSecond(inputValue)
                    4 -> convertSecondToHour(inputValue)
                    5 -> convertSecondToMinute(inputValue)
                    else -> 0.0
                }

                val decimalFormat = DecimalFormat("#.##")
                binding.resultTextView.text = decimalFormat.format(convertedValue)
            }
        }
    }

    private fun convertHourToMinute(hour: Double): Double {
        return hour * 60
    }

    private fun convertHourToSecond(hour: Double): Double {
        return hour * 3600
    }

    private fun convertMinuteToHour(minute: Double): Double {
        return minute / 60
    }

    private fun convertMinuteToSecond(minute: Double): Double {
        return minute * 60
    }

    private fun convertSecondToHour(second: Double): Double {
        return second / 3600
    }

    private fun convertSecondToMinute(second: Double): Double {
        return second / 60
    }
}