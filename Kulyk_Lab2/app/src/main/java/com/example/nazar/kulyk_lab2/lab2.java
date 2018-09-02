package com.example.nazar.kulyk_lab2;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.nazar.kulyk_lab2.R.*;

public class lab2 extends AppCompatActivity {

    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_lab2);

        final TextView result = findViewById(id.result);
        final Button submitButton = findViewById(id.submitButton);
        final EditText editText = findViewById(id.editText);

        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                String name = String.valueOf(editText.getText());
                if (name.equals("")) {
                    result.setText("Please write your name");
                } else {
                    result.setText("Hello " + name);
                }
            }
        });

        final Button clearButton = findViewById(id.clearButton);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });
    }
}
