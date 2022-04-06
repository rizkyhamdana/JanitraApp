package com.rizkyhamdana.janitraapp.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth
import com.rizkyhamdana.janitraapp.MainActivity
import com.rizkyhamdana.janitraapp.R
import com.rizkyhamdana.janitraapp.ui.login.LoginActivity

class SplashscreenActivity : AppCompatActivity() {

    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_splashscreen)

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser

        Handler(mainLooper).postDelayed({
            if (user != null){
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }else{
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }, MILLIS)
    }

    companion object{
        private const val MILLIS = 5000L
    }
}