package com.nazar.kulyk_lab;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    protected TextView result;
    protected EditText first_name;
    protected EditText last_name;
    protected EditText password;
    protected EditText confirm_password;
    protected Button submit_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.result);
        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirm_password);
        submit_button = findViewById(R.id.submit_button);
        onClickButtonsHandler();
    }

    public void onClickButtonsHandler(){

        submit_button.setOnClickListener(new View.OnClickListener() {

            @Override
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                result.setText("");
                String first_name_value = String.valueOf(first_name.getText());
                NameValidator(first_name_value, "first name", first_name);
                String last_name_value = String.valueOf(last_name.getText());
                NameValidator(last_name_value, "last name", last_name);
            }
        });

    }

    public void NameValidator(String value, String field, EditText field_id){
        if(value.equals("")){
            field_id.setHighlightColor(Color.RED);
            String text = String.valueOf(result.getText()) + "\nEmpty " + field;
            result.setText(text);
        }
        else if(!(value.matches("[A-Z][a-zA-Z]+"))){
            field_id.setHighlightColor(Color.RED);
            String text = String.valueOf(result.getText()) + "\nIncorrect " + field;
            result.setText(text);
        }
    }

    public void PasswordValidator(String password, String confirm_password){
    }
}
