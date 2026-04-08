package com.example.helloworldoptional

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var systemInfo = ArrayList<String>()

        systemInfo.add("Manufacturer: "+ android.os.Build.MANUFACTURER)
        systemInfo.add("Model: "+android.os.Build.MODEL)
        systemInfo.add("Brand: "+android.os.Build.BRAND)
        systemInfo.add("Type: "+android.os.Build.TYPE)
        systemInfo.add("User: "+android.os.Build.USER)
        systemInfo.add("Display: "+android.os.Build.DISPLAY)


        val textView  = findViewById<EditText>(R.id.editTextTextMultiLine)

        textView.setText(systemInfo.joinToString( "\n"))










    }
}