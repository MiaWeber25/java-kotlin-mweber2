package com.example.codecracker;

import static com.example.codecracker.CalculateMD5.getMD5;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.ui.AppBarConfiguration;

import com.example.codecracker.databinding.ActivityMainBinding;
import com.google.android.material.slider.Slider;

import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editTextMd5Hash;
    private Button buttonDecrypt;
    private TextView textViewDecryptedCode;

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private SeekBar seekBarValue;
    Integer selectedValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        editTextMd5Hash = findViewById(R.id.editTextMd5Hash);
        buttonDecrypt = findViewById(R.id.buttonDecrypt);
        textViewDecryptedCode = findViewById(R.id.textViewDecryptedCode);
        seekBarValue = findViewById(R.id.seekBarValue);
        final TextView textViewSelectedValue = findViewById(R.id.textViewSelectedValue);


        seekBarValue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                selectedValue = progress;
                textViewSelectedValue.setText(String.valueOf(selectedValue));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Not used in this implementation
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Not used in this implementation
            }

            });

        buttonDecrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> result = new ArrayList<>();
                //Integer numThreads = 5; // WORKING!!
                Integer numThreads = selectedValue;
                //CrackThread oneThread = new CrackThread(result, seekBarValue.toInteger()
                int chunk = Math.round(99999/numThreads);
                for (Integer i=0; i<99999;) {
                    int end = i+chunk<=99999? i+chunk:99999; // To account for potential rounding errors (sometimes you might get 6 threads)
                    CrackThread newThread = new CrackThread(result, i, end, editTextMd5Hash.getText().toString());
                    //optimization: add threads to arrayList to exit safely out of loop if hash isn't present in search space
                    newThread.start();
                    i=end+1;
                }



                //CrackThread oneThread = new CrackThread(result, 0, 300, editTextMd5Hash.getText().toString());
                //oneThread.start();
                while (result.size() <1) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                textViewDecryptedCode.setText(result.get(0));

                /*String attempt = "0";
                for (int i=0; i<999; i++) { // Dictionary?
                    attempt = String.format("%03d", i);
                   // String decryptedCode = getMD5(attempt);
                   // textViewDecryptedCode.setText(decryptedCode);

                    String hash = getMD5(attempt);
                    if (getMD5(attempt).compareTo(editTextMd5Hash.getText().toString()) == 0) {
                        String decryptedCode = attempt;

                        textViewDecryptedCode.setText(attempt);
                    }
                }*/

                //String md5Hash = editTextMd5Hash.getText().toString();
                //String decryptedCode = getMD5(md5Hash);

                //textViewDecryptedCode.setText(decryptedCode);
            }
        });
    }


}