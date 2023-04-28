package com.example.currencyconverter;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.currencyconverter.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    private EditText editTextFrom;
    private EditText editTextTo;
    private EditText editTextAmount;
    private EditText editTextResult;
    private Button buttonConvert;

    private double exchangeRate;

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

                exchangeRate = getExchangeRate(from, to);
                double result = amount * exchangeRate;
                editTextResult.setText(String.format("%.2f", result));
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
