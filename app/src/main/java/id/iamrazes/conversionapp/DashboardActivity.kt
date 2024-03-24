package id.iamrazes.conversionapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import id.iamrazes.conversionapp.models.dataDummy

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)


        val btnTemperature = findViewById<ImageButton>(R.id.btn_temperature)
        val btnDistance = findViewById<ImageButton>(R.id.btn_distance)
        val btnMass = findViewById<ImageButton>(R.id.btn_mass)
        val btnTime = findViewById<ImageButton>(R.id.btn_time)
        val usernameTextView: TextView = findViewById(R.id.name)
        val user = dataDummy.firstOrNull()
        val exit_icon = findViewById<ImageButton>(R.id.exit_icon)

        user?.let {
            usernameTextView.text = "Hello, ${it.name}"
        }
        exit_icon.setOnClickListener {
            // Start LoginActivity when ImageButton is clicked
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            // Finish the current activity to prevent going back to it when pressing back button
            finish()
        }

        btnTemperature.setOnClickListener {
            startActivity(Intent(this@DashboardActivity, ConversionTemperatureActivity::class.java))
        }

        btnDistance.setOnClickListener {
            startActivity(Intent(this@DashboardActivity, ConversionDistanceActivity::class.java))
        }

        btnMass.setOnClickListener {
            startActivity(Intent(this@DashboardActivity, ConversionMassActivity::class.java))
        }

        btnTime.setOnClickListener {
            startActivity(Intent(this@DashboardActivity, ConversionTimeActivity::class.java))
        }

    }
}