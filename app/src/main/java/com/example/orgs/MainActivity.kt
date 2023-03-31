package com.example.orgs

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Toast.makeText(this, "Pipoca", Toast.LENGTH_SHORT).show()
//        val view = TextView(this)
//        view.setText("cesta de frutas")
        setContentView(R.layout.activity_main)
    }
}
