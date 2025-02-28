package com.example.carrapp

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

object NetworkUtil {
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivtyManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivtyManager.activeNetwork ?: return false
        val capabilities = connectivtyManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    suspend fun isInternetAvaible(): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val socket = Socket()
                socket.connect(InetSocketAddress("8.8.8.8", 53), 1500)
                socket.close()
                true
            } catch (e: IOException) {
                false
            }
        }
    }
}

class HomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.gettind_started)

        checkInternet()

        val linkToLogin: Button = findViewById(R.id.logInButton)
        val linkToRegister: Button = findViewById(R.id.registerButton)

        linkToLogin.setOnClickListener {
            val intent = Intent(this@HomePageActivity, LoginActivity::class.java)
            startActivity(intent)
            checkInternet()
        }
        linkToRegister.setOnClickListener {
            val intent = Intent(this@HomePageActivity, RegisterActivity1::class.java)
            startActivity(intent)
            checkInternet()
        }
    }

    private fun checkInternet() {
        val intent = Intent(this@HomePageActivity, NoInternet::class.java)
        if (NetworkUtil.isNetworkAvailable(this@HomePageActivity)) {
            CoroutineScope(Dispatchers.Main).launch {
                if (!NetworkUtil.isInternetAvaible()) {
                    startActivity(intent)
                }
            }
        } else {
            startActivity(intent)
        }

    }
}