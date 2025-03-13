package com.example.localization

import android.app.Activity
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ResourceCursorAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
        val languageSpinner: Spinner = findViewById(R.id.languageSpinner)
        val languages = arrayOf("Select Language", "English", "Spanish", "Portuguese")

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, languages)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        languageSpinner.adapter = adapter
        languageSpinner.setSelection(0)

        // Create an ArrayAdapter using the string array and a default spinner layout
       /* ArrayAdapter.createFromResource(
            this,
            R.array.languages_array,  // Array defined in strings.xml for Spinner options
            android.R.layout.simple_spinner_item
        ).also { adapter ->*/
            // Specify the layout to use when the list of choices appears
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
//            languageSpinner.adapter = adapter
//        }

        // Handle language selection
        languageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                val selectedLanguage = parent.getItemAtPosition(position).toString()
                /*when (selectedLanguage) {
                    "English" -> setLocale("en")
                    "Spanish" -> setLocale("es")
                    "Portuguese" -> setLocale("pt")
                }*/
                if (selectedLanguage.equals("English")) {
                    setLocale(this@MainActivity, "en")
                    finish()
                    startActivity(intent)
                } else if (selectedLanguage.equals("Spanish")) {
                    setLocale(this@MainActivity, "es")
                    finish()
                    startActivity(intent)
                } else if (selectedLanguage.equals("Portuguese")) {
                    setLocale(this@MainActivity, "pt")
                    finish()
                    startActivity(intent)
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun setLocale(activity: Activity, languageCode: String) {
        val locale = java.util.Locale(languageCode)
        java.util.Locale.setDefault(locale)
        val resources: Resources = activity.resources
        val config: Configuration = resources.configuration
        config.locale = locale
//        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
        resources.updateConfiguration(config, resources.displayMetrics)
        recreate() // Restart activity to apply new locale
    }
}