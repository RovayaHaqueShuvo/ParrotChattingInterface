package com.webcode.parrotmasseage

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.webcode.parrotmasseage.databinding.ActivityOtpacitvityBinding
import java.util.concurrent.TimeUnit

class OTPAcitvity : AppCompatActivity() {
    private lateinit var binding: ActivityOtpacitvityBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityOtpacitvityBinding.inflate(layoutInflater)
        var verificationId: String? = null
        var auth: FirebaseAuth? = null
        var dialog: ProgressDialog? = null

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        dialog = ProgressDialog(this@OTPAcitvity)
        dialog!!.setMessage("Sending OTP...")
        dialog.setCancelable(true)
        dialog!!.show()

        auth= FirebaseAuth.getInstance()

        val phonenumber = intent.getStringExtra("number_passing")

        binding.VerifytextView.text = "Verifying $phonenumber"

        val option = PhoneAuthOptions.newBuilder(auth!!)
            .setPhoneNumber(phonenumber!!)
            .setActivity(this@OTPAcitvity)
            .setTimeout(30L, TimeUnit.SECONDS)
            .setCallbacks(object: PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    TODO("Not yet implemented")
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    TODO("Not yet implemented")
                }

                override fun onCodeSent(verifyid: String, ForcesResponding: PhoneAuthProvider.ForceResendingToken) {
                    super.onCodeSent(verifyid, ForcesResponding)
                    dialog!!.dismiss()
                    verificationId = verifyid
                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0)
                    binding.otpNum.requestFocus()
                }


            }).build()

        PhoneAuthProvider.verifyPhoneNumber(option)
        binding.otpNum.setOtpCompletionListener {
            val credential = PhoneAuthProvider.getCredential(verificationId!!,it)
            auth!!.signInWithCredential(credential)
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        startActivity(Intent(this@OTPAcitvity, SetupPageActivity::class.java))
                        finish()
                    }
                    else{
                        Toast.makeText(this@OTPAcitvity,"Failed",Toast.LENGTH_SHORT).show()
                    }
                }
        }


    }
}