package com.example.cumlaudegaya

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.cumlaudegaya.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculate() }

        binding.ipInputText.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(view, keyCode)
        }
    }

    private fun calculate() {
        val ipTemp = binding.ipInputText.text.toString().toDoubleOrNull()
        val sksTemp = binding.sksTempInputText.text.toString().toIntOrNull()
        val sksAll = binding.sksAllInputText.text.toString().toIntOrNull()
        val ipkCum = binding.ipkInputText.text.toString().toDoubleOrNull()

        val resultTemp = (sksAll?.times(ipkCum!!))?.minus((ipTemp?.times(sksTemp!!)!!))
        val result = resultTemp?.div((sksAll.minus(sksTemp!!)))

        displayIP(result!!)
    }

    private fun displayIP(ip: Double) {
        binding.resultIp.text = getString(R.string.result_ip, NumberFormat.getNumberInstance().format(ip))
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if(keyCode == KeyEvent.KEYCODE_ENTER) {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}