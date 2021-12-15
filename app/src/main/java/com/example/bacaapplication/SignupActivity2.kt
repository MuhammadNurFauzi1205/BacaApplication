package com.example.bacaapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup2.*

class SignupActivity2 : AppCompatActivity() {

    lateinit var usernamee: EditText
    lateinit var passwordd: EditText
    lateinit var  emaill : EditText

    private lateinit var myreference: DatabaseReference

    private var USERNAME_KEY = "usernamekey"
    private var username_key = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup2)

        usernamee = findViewById(R.id.username1)
        passwordd = findViewById(R.id.password1)
        emaill = findViewById(R.id.email1)

        buat1.setOnClickListener {


            //simpan username pada local
            val sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString(username_key, usernamee.text.toString())
            editor.apply()


            //simpan username pada firebase
            myreference = FirebaseDatabase.getInstance().reference
                .child("users").child(usernamee.text.toString())

            myreference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    dataSnapshot.ref.child("username").setValue(usernamee.text.toString())
                    dataSnapshot.ref.child("password").setValue(passwordd.text.toString())
                    dataSnapshot.ref.child("email").setValue(emaill.text.toString())
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })

            val bu = Intent(this, MlebuActivity::class.java)
            startActivity(bu)
//            Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show()
        }

        back1.setOnClickListener {
            val balikk = Intent (this, SignActivity::class.java)
            startActivity(balikk)
        }


    }
}