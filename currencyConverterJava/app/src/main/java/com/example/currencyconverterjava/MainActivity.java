package com.example.currencyconverterjava;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    private EditText editTextFrom;
    public EditText getFrom() {
        return editTextFrom;
    }
    private EditText editTextTo;
    public EditText getTo() {
        return editTextTo;
    }
    private EditText editTextAmount;

    public EditText getAmount() {
        return editTextAmount;
    }
    private EditText editTextResult;

    public EditText getResult() {
        return editTextResult;
    }
    private Button buttonConvert;

    public Button getButton() {
        return buttonConvert;
    }

    private double exchangeRate;

    public double getRate() {
        return exchangeRate;
    }

    /*double calculate(double amount, double exchangeRate) {
        return amount * exchangeRate;
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextFrom = findViewById(R.id.editTextFrom);
        editTextTo = findViewById(R.id.editTextTo);
        editTextAmount = findViewById(R.id.editTextAmount);
        editTextResult = findViewById(R.id.editTextResult);
        buttonConvert = findViewById(R.id.buttonConvert);

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String from = editTextFrom.getText().toString().toUpperCase();
                String to = editTextTo.getText().toString().toUpperCase();
                String amountStr = editTextAmount.getText().toString();

                if (from.isEmpty() || to.isEmpty() || amountStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                double amount = Double.parseDouble(amountStr);

                if (from.equals(to)) {
                    Toast.makeText(MainActivity.this, "From and To currencies must be different", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (from.equals("USD") && to.equals("EUR")) {
                    Util.calculate(amount, exchangeRate);
                    exchangeRate = 0.907;
                    double result = Util.calculate(amount, exchangeRate);
                    editTextResult.setText(String.format("%.2f", result));
                }
                else if (from.equals("USD") && to.equals("JPY")) {
                    exchangeRate = 136.165;
                    double result =Util.calculate(amount, exchangeRate);
                    editTextResult.setText(String.format("%.2f", result));
                }
                else if (from.equals("EUR") && to.equals("USD")) {
                    exchangeRate = 1.102;
                    double result = Util.calculate(amount, exchangeRate);
                    editTextResult.setText(String.format("%.2f", result));
                }
                else if (from.equals("EUR") && to.equals("JPY")) {
                    exchangeRate = 150.092;
                    double result = Util.calculate(amount, exchangeRate);
                    editTextResult.setText(String.format("%.2f", result));
                }
                else if (from.equals("JPY") && to.equals("USD")) {
                    exchangeRate = 0.007;
                    double result = Util.calculate(amount, exchangeRate);
                    editTextResult.setText(String.format("%.2f", result));
                }
                else if (from.equals("JPY") && to.equals("EUR")) {
                    exchangeRate = 0.006;
                    double result = Util.calculate(amount, exchangeRate);
                    editTextResult.setText(String.format("%.2f", result));
                }
                else {
                    // otherwise, just use random exchange rate.
                    exchangeRate = getExchangeRate(from, to);
                    double result = Util.calculate(amount, exchangeRate);
                    editTextResult.setText(String.format("%.2f", result));
                }
            }
        });
    }

    private double getExchangeRate(String from, String to) {
        // Implement your currency exchange rate retrieval logic here
        // This could be done by calling an API, querying a database, or any other means
        // For simplicity, we will return a random exchange rate between 0.5 and 2.0

        return ThreadLocalRandom.current().nextDouble(0.5, 2.0);
    }
}