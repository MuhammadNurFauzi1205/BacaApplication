package com.example.bacaapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign.*

class SignActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        buttonbuat.setOnClickListener {
            val butbuat = Intent (this, SignupActivity2::class.java)
            startActivity(butbuat)
        }

        masukakun.setOnClickListener {
            val masukk = Intent (this, LoginActivity::class.java)
            startActivity(masukk)
        }


    }
}