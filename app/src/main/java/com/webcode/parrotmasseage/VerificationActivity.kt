package com.webcode.parrotmasseage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.webcode.parrotmasseage.databinding.ActivityVerificationBinding

class VerificationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVerificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityVerificationBinding.inflate(layoutInflater)
        var auth:FirebaseAuth?
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        if (auth!!.currentUser!= null){
            startActivity(Intent(this@VerificationActivity,MainActivity::class.java))
            finish()

        }
        binding.phnnumEdtxt.requestFocus()

        binding.nextBtn.setOnClickListener {
                var intent = Intent(this@VerificationActivity, OTPAcitvity::class.java)
                intent.putExtra("number_passing", binding.phnnumEdtxt.text.toString())
                startActivity(intent)
            }


    }
}