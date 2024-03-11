package com.example.lorby

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.lorby.databinding.ActivityOtpBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.Random

class OtpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOtpBinding
    lateinit var auth: FirebaseAuth
    var email: String = ""
    var password: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        email = intent.getStringExtra("email").toString()
        password = intent.getStringExtra("password").toString()

        binding.etFirst.doOnTextChanged{text, start, before, count ->
            if (!binding.etFirst.text.toString().isEmpty()){
                binding.etSecond.requestFocus()
            }
            if (!binding.etSecond.text.toString().isEmpty()){
                binding.etSecond.requestFocus()
            }
        }
        binding.etSecond.doOnTextChanged{text, start, before, count ->
            if (!binding.etSecond.text.toString().isEmpty()){
                binding.etThird.requestFocus()
            }else{
                binding.etFirst.requestFocus()
            }
        }
        binding.etThird.doOnTextChanged{text, start, before, count ->
            if (!binding.etThird.text.toString().isEmpty()){
                binding.etFourth.requestFocus()
            }else{
                binding.etSecond.requestFocus()
            }
        }
        binding.etFourth.doOnTextChanged{text, start, before, count ->
            if (!binding.etFourth.text.toString().isEmpty()){
//                binding.etFirst.requestFocus()
            }
            binding.btnDone.setOnClickListener{
                var otp1 = binding.etFirst.text.toString()
                var otp2 = binding.etSecond.text.toString()
                var otp3 = binding.etThird.text.toString()
                var otp4 = binding.etFourth.text.toString()

                var otp = "$otp1$otp2$otp3$otp4"

                if (binding.etFirst.text.toString().isEmpty()||
                    binding.etSecond.text.toString().isEmpty()||
                    binding.etThird.text.toString().isEmpty()||
                    binding.etFourth.text.toString().isEmpty()){
                    Toast.makeText(this@OtpActivity, "Enter code", Toast.LENGTH_SHORT).show()
                }
//                else if(!otp.equals())
                          {

                }
            }

        }
//        fun random(){
//            random=Random.nextInt(100000..999999)
//        }
    }
}