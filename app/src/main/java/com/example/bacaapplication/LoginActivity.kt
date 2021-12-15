package com.example.bacaapplication

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var xemail: EditText
    private lateinit var xpassword: EditText

    private lateinit var myreference: DatabaseReference

    var USERNAME_KEY = "usernamekey"
    var username_key = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        xemail = findViewById(R.id.email2)
        xpassword = findViewById(R.id.password2)

        masuk.setOnClickListener {
            val emailll = xemail.text.toString()
            val password = xpassword.text.toString()

            myreference = FirebaseDatabase.getInstance().reference
                .child("users").child(emailll)


            myreference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {

                        //ambil data password dari firebase
                        val passwordFromFirebase = dataSnapshot.child("password").value.toString()

                        //validasi password dengan firebase
                        if (password == passwordFromFirebase) {

                            //simpan username pada local
                            val sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE)
                            val editor = sharedPreferences.edit()
                            editor.putString(username_key, xemail.text.toString())
                            editor.apply()
                            //berpindah activity
                            val two = Intent(this@LoginActivity , MlebuActivity::class.java)
                            startActivity(two)
                        } else {
                            Toast.makeText(applicationContext, "Password salah", Toast.LENGTH_SHORT)
                                .show()
                        }
                    } else {
                        Toast.makeText(applicationContext, "username tidak ada", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Toast.makeText(applicationContext, "Database error", Toast.LENGTH_SHORT).show()
                }
            })
        }

        back.setOnClickListener {
            val balik = Intent(this, SignActivity::class.java)
            startActivity(balik)
        }
    }
}