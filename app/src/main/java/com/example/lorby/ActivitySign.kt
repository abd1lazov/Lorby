package com.example.lorby

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lorby.databinding.ActivitySignBinding

class ActivitySign : AppCompatActivity() {

    private lateinit var binding: ActivitySignBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()
    }

    private fun initListener() {
        binding.btnNext.setOnClickListener {

            if (binding.etEmail.text.toString().isEmpty()) {
                binding.etEmailContainer.error = "Enter Email"
            }
            else if (binding.etCreatePassword.text.toString().isEmpty()) {
                binding.etPasswordContainer.error = "Enter Password"
            }
            else if (binding.etRepeatPassword.text.toString().isEmpty()) {
                binding.etRepeatPasswordContainer.error = "Enter Password"
            }
            else if (!(binding.etCreatePassword.text.toString()
                    .equals(binding.etRepeatPassword.text.toString()))
            ) {
                binding.etRepeatPasswordContainer.error = "Password must be same"
            } else if (binding.etName.text.toString().isEmpty()){
                binding.etNameContainer.error = "Enter name"
            }
            else {
                var intent = Intent(this@ActivitySign, OtpActivity::class.java)
                intent.putExtra("email",binding.etEmail.text.toString())
                intent.putExtra("password",binding.etCreatePassword.text.toString())
                startActivity(intent)
            }
        }
    }
}