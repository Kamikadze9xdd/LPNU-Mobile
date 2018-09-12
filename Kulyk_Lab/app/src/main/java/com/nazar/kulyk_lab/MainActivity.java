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
        final Button submit_button = findViewById(R.id.submit_button);
        final EditText first_name = findViewById(R.id.first_name);
        final EditText last_name = findViewById(R.id.last_name);
        final TextView result = findViewById(R.id.result);

        submit_button.setOnClickListener(new View.OnClickListener() {

            @Override
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                String first_name_value = String.valueOf(first_name.getText());
                NameValidator(first_name_value, "first Name");
                String last_name_value = String.valueOf(last_name.getText());
                NameValidator(last_name_value, "last name");
            }
        });

    }

    public void NameValidator(String value, String field){
        final TextView result = findViewById(R.id.result);
        
        if(value.equals("")){
            String text = String.valueOf(result.getText()) + "\nEmpty " + field;
            result.setText(text);
        }
        else if(!(value.matches("[a-zA-Z]+"))){
            String text = String.valueOf(result.getText()) + "\nIncorrect " + field;
            result.setText(text);
        }
    }
}
