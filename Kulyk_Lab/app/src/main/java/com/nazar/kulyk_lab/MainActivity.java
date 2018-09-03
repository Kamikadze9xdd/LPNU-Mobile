package com.nazar.kulyk_lab;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onClickButtonsHandler();
    }

    public void onClickButtonsHandler(){
        final TextView result = findViewById(R.id.result);
        final Button submitButton = findViewById(R.id.submitButton);
        final EditText editText = findViewById(R.id.text);

        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                String name = String.valueOf(editText.getText());
                if (name.equals("")) {
                    result.setText("Please write your name");
                } else {
                    result.setText("Hello " + name + "!");
                }
            }
        });

        final Button clearButton = findViewById(R.id.btn_clear);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });
    }
}
