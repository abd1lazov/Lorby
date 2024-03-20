package com.example.lorby

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.lorby.databinding.ActivityOtpBinding
import com.google.firebase.auth.FirebaseAuth
import papaya.`in`.sendmail.SendMail
import kotlin.random.nextInt


class OtpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOtpBinding
    lateinit var auth: FirebaseAuth
    var email: String = ""
    var password: String = ""
    var random: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        email = intent.getStringExtra("email").toString()
        password = intent.getStringExtra("password").toString()

        auth = FirebaseAuth.getInstance()

        random()


        binding.tvResendOtp.setOnClickListener {
            random()
        }

        binding.etFirst.doOnTextChanged { text, start, before, count ->
            if (!binding.etFirst.text.toString().isEmpty()) {
                binding.etSecond.requestFocus()
            }
            if (!binding.etSecond.text.toString().isEmpty()) {
                binding.etSecond.requestFocus()
            }
        }
        binding.etSecond.doOnTextChanged { text, start, before, count ->
            if (!binding.etSecond.text.toString().isEmpty()) {
                binding.etThird.requestFocus()
            } else {
                binding.etFirst.requestFocus()
            }
        }
        binding.etThird.doOnTextChanged { text, start, before, count ->
            if (!binding.etThird.text.toString().isEmpty()) {
                binding.etFourth.requestFocus()
            } else {
                binding.etSecond.requestFocus()
            }
        }
        binding.etFourth.doOnTextChanged { text, start, before, count ->
            if (!binding.etFourth.text.toString().isEmpty()) {
//                binding.etFirst.requestFocus()
            }
            binding.btnDone.setOnClickListener {
                var otp1 = binding.etFirst.text.toString()
                var otp2 = binding.etSecond.text.toString()
                var otp3 = binding.etThird.text.toString()
                var otp4 = binding.etFourth.text.toString()

                var otp = "$otp1$otp2$otp3$otp4"

                if (binding.etFirst.text.toString().isEmpty() ||
                    binding.etSecond.text.toString().isEmpty() ||
                    binding.etThird.text.toString().isEmpty() ||
                    binding.etFourth.text.toString().isEmpty()
                ) {
                    Toast.makeText(this@OtpActivity, "Enter code", Toast.LENGTH_SHORT).show()
                } else if (!otp.equals(random.toString())) {
                    Toast.makeText(this@OtpActivity, "Wrong code", Toast.LENGTH_SHORT).show()
                } else {
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                        if (it.isSuccessful) {
                            var intent = Intent(this@OtpActivity, HomeScreen::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                this@OtpActivity,
                                it.exception?.message.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }

    fun random() {
        random = kotlin.random.Random.nextInt(1000..9999)
        val mail = SendMail(
            "aabdilazov10@gmail.com", "mwtupeqwomjczbba", email, "Login Sign up app's",
            "Your code is -> $random"
        )
        mail.execute()
    }
}
