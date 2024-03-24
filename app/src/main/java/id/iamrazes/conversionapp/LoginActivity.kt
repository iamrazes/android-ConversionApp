package id.iamrazes.conversionapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import id.iamrazes.conversionapp.models.User
import id.iamrazes.conversionapp.models.dataDummy


class LoginActivity : AppCompatActivity() {

    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button
    private lateinit var user : User

    private var loginAttempts = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameInput = findViewById(R.id.username_input)
        passwordInput = findViewById(R.id.password_input)
        loginButton = findViewById(R.id.login_btn)

        user = dataDummy.firstOrNull() ?: User()

        loginButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            if (username == user.username && password == user.password) {

                navigateToDashboardActivity()
            } else {
                loginAttempts++
                if (loginAttempts < 3) {
                    showToast("Wrong username or password. Attempts left: ${3 - loginAttempts}")
                } else {
                    showResetPasswordDialog()
                    loginButton.isEnabled = false
                }
            }
        }
    }

    private fun navigateToDashboardActivity() {

        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showResetPasswordDialog() {
        AlertDialog.Builder(this)
            .setTitle("Opps!")
            .setMessage("Wrong username or password")
            .setPositiveButton("Reset Password") { dialogInterface: DialogInterface, _: Int ->
                showToast("Service not available")
                dialogInterface.dismiss()
            }
            .setNegativeButton("Close") { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
                finishAffinity()
            }
            .setCancelable(false)
            .show()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}