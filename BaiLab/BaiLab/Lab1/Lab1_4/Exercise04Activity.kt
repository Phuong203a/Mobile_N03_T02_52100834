package com.example.lab01

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

class Exercise04Activity : AppCompatActivity() {

    private lateinit var edtDollar: EditText
    private lateinit var edtEuro: EditText
    private lateinit var edtVnd: EditText
    private lateinit var btnClear: Button
    private lateinit var btnConvert: Button

    private var dollarToVnd = 24410
    private var dollarToEuro = 0.94


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise04)

        edtDollar = findViewById(R.id.edtDollar)
        edtEuro = findViewById(R.id.edtEuro)
        edtVnd = findViewById(R.id.edtVnd)
        btnClear = findViewById(R.id.btnClear)
        btnConvert = findViewById(R.id.btnConvert)

        btnClear.setOnClickListener {
            edtDollar.text = null
            edtEuro.text = null
            edtVnd.text = null
        }
        btnConvert.setOnClickListener {
            if (!edtDollar.text.isEmpty()) {
                var usdValue: Double = edtDollar.text.toString().toDoubleOrNull() ?: 0.0
                var euroValue = usdValue * dollarToEuro
                var vndValue = usdValue * dollarToVnd

                edtEuro.setText(String.format("%.2f", euroValue))
                edtVnd.setText(String.format("%.2f", vndValue))

//                runOnUiThread {
//                    edtEuro.setText(euroValue.toString())
//                }
//
//                runOnUiThread {
//                    edtVnd.setText(vndValue.toString())
//                }
            }
        }

    }
}