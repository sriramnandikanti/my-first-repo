package com.example.imei

/*
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telephony.TelephonyManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var tvSerialNumber: TextView
    private lateinit var tvImeiNumber: TextView

    companion object {
        const val PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvSerialNumber = findViewById(R.id.tvSerialNumber)
        tvImeiNumber = findViewById(R.id.tvImeiNumber)

        if (checkAndRequestPermissions()) {
            displayDeviceInfo()
        }
    }

    private fun checkAndRequestPermissions(): Boolean {
        val permissionPhoneState = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
        val listPermissionsNeeded = ArrayList<String>()

        if (permissionPhoneState != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_PHONE_STATE)
        }

        if (listPermissionsNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toTypedArray(), PERMISSION_REQUEST_CODE)
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    displayDeviceInfo()
                } else {
                    // Permission denied
                }
                return
            }
        }
    }

    private fun displayDeviceInfo() {
        // Get Serial Number
        val serialNumber: String = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Build.getSerial()
        } else {
            Build.SERIAL
        }
        tvSerialNumber.text = "Serial Number: $serialNumber"

        // Get IMEI Number
        val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            val imeiNumber: String = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                telephonyManager.imei
            } else {
                telephonyManager.deviceId
            }
            tvImeiNumber.text = "IMEI Number: $imeiNumber"
        }
    }
}
*/

/*
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telephony.TelephonyManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var tvSerialNumber: TextView
    private lateinit var tvImeiNumber: TextView

    companion object {
        const val PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvSerialNumber = findViewById(R.id.tvSerialNumber)
        tvImeiNumber = findViewById(R.id.tvImeiNumber)

        if (checkAndRequestPermissions()) {
            displayDeviceInfo()
        }
    }

    private fun checkAndRequestPermissions(): Boolean {
        val permissionPhoneState = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
        val listPermissionsNeeded = ArrayList<String>()

        if (permissionPhoneState != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_PHONE_STATE)
        }

        if (listPermissionsNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toTypedArray(), PERMISSION_REQUEST_CODE)
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    displayDeviceInfo()
                } else {
                    // Permission denied
                    tvSerialNumber.text = "Permission Denied"
                    tvImeiNumber.text = "Permission Denied"
                }
                return
            }
        }
    }

    private fun displayDeviceInfo() {
        try {
            // Get Serial Number
            val serialNumber: String = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Build.getSerial()
            } else {
                Build.SERIAL
            }
            tvSerialNumber.text = "Serial Number: $serialNumber"
        } catch (e: SecurityException) {
            tvSerialNumber.text = "Serial Number: Permission Denied"
        }

        try {
            // Get IMEI Number
            val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                val imeiNumber: String? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    telephonyManager.imei
                } else {
                    telephonyManager.deviceId
                }
                tvImeiNumber.text = "IMEI Number: $imeiNumber"
            } else {
                tvImeiNumber.text = "IMEI Number: Permission Denied"
            }
        } catch (e: SecurityException) {
            tvImeiNumber.text = "IMEI Number: Permission Denied"
        }
    }
}
*/

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.telephony.TelephonyManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var tvSerialNumber: TextView
    private lateinit var tvImeiNumber: TextView
    private lateinit var tvAndroidId: TextView

    companion object {
        const val PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvSerialNumber = findViewById(R.id.tvSerialNumber)
        tvImeiNumber = findViewById(R.id.tvImeiNumber)
        tvAndroidId = findViewById(R.id.tvAndroidId)

        if (checkAndRequestPermissions()) {
            displayDeviceInfo()
        } else {
            tvAndroidId.text = "Android ID: ${getAndroidId()}"
        }
    }

    private fun checkAndRequestPermissions(): Boolean {
        val permissionPhoneState = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
        val listPermissionsNeeded = ArrayList<String>()

        if (permissionPhoneState != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_PHONE_STATE)
        }

        if (listPermissionsNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toTypedArray(), PERMISSION_REQUEST_CODE)
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    displayDeviceInfo()
                } else {
                    // Permission denied
                    tvSerialNumber.text = "Serial Number: Permission Denied"
                    tvImeiNumber.text = "IMEI Number: Permission Denied"
                }
                tvAndroidId.text = "Android ID: ${getAndroidId()}"
                return
            }
        }
    }

    private fun displayDeviceInfo() {
        try {
            // Get Serial Number
            val serialNumber: String = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Build.getSerial()
            } else {
                Build.SERIAL
            }
            tvSerialNumber.text = "Serial Number: $serialNumber"
        } catch (e: SecurityException) {
            tvSerialNumber.text = "Serial Number: Permission Denied"
        }

        try {
            // Get IMEI Number
            val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                val imeiNumber: String? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    telephonyManager.imei
                } else {
                    telephonyManager.deviceId
                }
                tvImeiNumber.text = "IMEI Number: $imeiNumber"
            } else {
                tvImeiNumber.text = "IMEI Number: Permission Denied"
            }
        } catch (e: SecurityException) {
            tvImeiNumber.text = "IMEI Number: Permission Denied"
        }

        // Get Android ID
        tvAndroidId.text = "Android ID: ${getAndroidId()}"
    }

    private fun getAndroidId(): String {
        return Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
    }
}

