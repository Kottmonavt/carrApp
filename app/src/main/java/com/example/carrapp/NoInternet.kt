package com.example.carrapp

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

object NetworkUtil2 {
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

class NoInternet : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.disconnect_page)
        checkInternet()
        val btn: Button = findViewById(R.id.buttonRetry)
        btn.setOnClickListener {
            checkInternet()
        }
    }

    private fun checkInternet() {
        val intent = Intent(this@NoInternet, HomePageActivity::class.java)
        if (NetworkUtil2.isNetworkAvailable(this@NoInternet)) {
            CoroutineScope(Dispatchers.Main).launch {
                if (NetworkUtil2.isInternetAvaible()) {
                    startActivity(intent)
                }
            }
        }
    }
}