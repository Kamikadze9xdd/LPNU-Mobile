package com.nazar.kulyk_lab.activities;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nazar.kulyk_lab.R;
import com.nazar.kulyk_lab.models.UserModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public ArrayList<UserModel> userArrayList;
    protected TextView result;
    protected String text;
    protected EditText first_name;
    protected EditText last_name;
    protected EditText email;
    protected EditText phone;
    protected EditText password;
    protected EditText confirm_password;
    protected Button submit_button;
    protected Boolean validatorResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userArrayList = new ArrayList<>();
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.result);
        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirm_password);
        submit_button = findViewById(R.id.submit_button);
        onSubmitButtonHandler();
        onListButtonHandler();
    }
    public void onListButtonHandler(){

    }

    public void onSubmitButtonHandler() {

        submit_button.setOnClickListener(new View.OnClickListener() {

            @Override
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                result.setText("");
                validatorResult = true;
                StringValidator(first_name, "^[A-Z][a-zA-Z]+$", "first name");
                StringValidator(last_name, "^[A-Z][a-zA-Z]+$", "last name");
                StringValidator(email, "^[a-zA-Z0-9+_.-]+@[a-zA-Z]+\\.[A-Za-z]{2,4}$",
                        "email");
                StringValidator(phone, "^\\+?[0-9]{10,16}$", "phone");
                StringValidator(password,
                        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{10,}$",
                        "password");
                PasswordsCheck();
                if(validatorResult){
                    saveUser();
                    result.setText("All fields are ok");
                }
            }
        });

    }

    @SuppressLint("SetTextI18n")
    public void StringValidator(EditText field_id, String regex, String field_name){
        field_id.setError(null);
        String value = String.valueOf(field_id.getText());
        String already_in_result = String.valueOf(result.getText());
        if(value.equals("")){
            validatorResult = false;
            result.setText(already_in_result + "\nEmpty " + field_name);
            field_id.setError("Empty " + field_name);
        } else if (!(value.matches(regex))){
            validatorResult = false;
            result.setText(already_in_result + "\nIncorrect " + field_name);
            field_id.setError("Incorrect " + field_name);
        }
    }

    @SuppressLint("SetTextI18n")
    public void PasswordsCheck(){
        confirm_password.setError(null);
        String password_value = String.valueOf(password.getText());
        String confirm_password_value = String.valueOf(confirm_password.getText());
        if(confirm_password_value.equals("") && (password_value.equals(""))){
            validatorResult = false;
            confirm_password.setError("Empty confirm password");
            String already_in_result = String.valueOf(result.getText());
            result.setText(already_in_result + "\nEmpty confirm password");
        }
        if(!(password_value.equals(confirm_password_value))){
            validatorResult = false;
            confirm_password.setError("Password don`t match");
            String already_in_result = String.valueOf(result.getText());
            result.setText(already_in_result + "\nPasswords don`t match");
        }
    }

    public void saveUser(){
        String first_name_value = String.valueOf(first_name.getText());
        String last_name_value = String.valueOf(last_name.getText());
        String email_value = String.valueOf(email.getText());
        String phone_value = String.valueOf(phone.getText());
        String password_value = String.valueOf(password.getText());

        UserModel user = new UserModel(first_name_value,
                last_name_value, email_value, phone_value, password_value);

        userArrayList.add(user);

        SharedPreferences sharedPreferences = getSharedPreferences("Shared preferences",
                MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(userArrayList);
        editor.putString("user list",json);
        editor.apply();
    }
}
