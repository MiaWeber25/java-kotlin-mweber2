package com.example.currencyconverter

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
//import com.example.currencyconverter.Util.calculate
import com.example.currencyconverter.databinding.ActivityMainBinding
import java.util.*
import java.util.concurrent.ThreadLocalRandom

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    //private val calculateButton: EditText? = binding.calculateButton

    private val editFrom: EditText? = binding.editTextFrom
    private val editTo: EditText? = binding.editTextTo
    private val editAmount: EditText? = binding.editTextAmount
    private val editResult: EditText? = binding.editTextResult
    private val buttonConvert: Button = binding.buttonConvert


    /*var amount: EditText? = null
        private set
    var result: EditText? = null
        private set
    var button: Button? = null
        private set*/
    var rate = 0.0
        private set

    /*double calculate(double amount, double exchangeRate) {
     return amount * exchangeRate;
 }*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_main)
        //from = findViewById(R.id.editTextFrom)
        //to = findViewById(R.id.editTextTo)
        //editAmount = findViewById(R.id.editTextAmount)
        //editResult = findViewById(R.id.editTextResult)
        //buttonConvert = findViewById(R.id.buttonConvert)
        buttonConvert.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val from = editFrom?.getText().toString().uppercase(Locale.getDefault())
                val to = editTo?.getText().toString().uppercase(Locale.getDefault())
                val amountStr = editAmount?.getText().toString()

                if (from.isEmpty() || to.isEmpty() || amountStr.isEmpty()) {
                    Toast.makeText(this@MainActivity, "Please enter all fields", Toast.LENGTH_SHORT)
                        .show()
                    return
                }
                val amount = amountStr.toDouble()
                if (from == to) {
                    Toast.makeText(
                        this@MainActivity,
                        "From and To currencies must be different",
                        Toast.LENGTH_SHORT
                    ).show()
                    return
                }
                if (from == "USD" && to == "EUR") {
                    calculate(amount, rate)
                    rate = 0.907
                    val result = calculate(
                        amount,
                        rate
                    )
                    editResult?.setText(String.format("%.2f", result))
                } else if (from == "USD" && to == "JPY") {
                    rate = 136.165
                    val result = calculate(
                        amount,
                        rate
                    )
                    editResult?.setText(String.format("%.2f", result))
                } else if (from == "EUR" && to == "USD") {
                    rate = 1.102
                    val result = calculate(
                        amount,
                        rate
                    )
                    editResult?.setText(String.format("%.2f", result))
                } else if (from == "EUR" && to == "JPY") {
                    rate = 150.092
                    val result = calculate(
                        amount,
                        rate
                    )
                    editResult?.setText(String.format("%.2f", result))
                } else if (from == "JPY" && to == "USD") {
                    rate = 0.007
                    val result = calculate(
                        amount,
                        rate
                    )
                    editResult?.setText(String.format("%.2f", result))
                } else if (from == "JPY" && to == "EUR") {
                    rate = 0.006
                    val result = calculate(
                        amount,
                        rate
                    )
                    editResult?.setText(String.format("%.2f", result))
                } else {
                    // otherwise, just use random exchange rate.
                    rate = getExchangeRate(from, to)
                    val result = calculate(
                        amount,
                        rate
                    )
                    editResult?.setText(String.format("%.2f", result))
                }
            }
        })
    }

    private fun getExchangeRate(from: String, to: String): Double {
        // Implement your currency exchange rate retrieval logic here
        // This could be done by calling an API, querying a database, or any other means
        // For simplicity, we will return a random exchange rate between 0.5 and 2.0
        return ThreadLocalRandom.current().nextDouble(0.5, 2.0)
    }
}