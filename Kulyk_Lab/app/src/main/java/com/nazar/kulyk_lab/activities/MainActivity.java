package com.nazar.kulyk_lab.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    public static final String NAME_REGEX = "^[A-Z][a-zA-Z]+$";
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9+_.-]+@[a-zA-Z]+\\.[A-Za-z]{2,4}$";
    public static final String PHONE_REGEX = "^\\+?[0-9]{10,16}$";
    public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])" +
            "(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
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
    protected Button list_users_button;

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
        list_users_button = findViewById(R.id.list_view_button);
        onSubmitButtonHandler();
        onListButtonHandler();
    }
    public void onListButtonHandler() {
        list_users_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent user_list = new Intent(getBaseContext(), UserListActivity.class);
                startActivity(user_list);
            }
        });
    }

    public void onSubmitButtonHandler() {

        submit_button.setOnClickListener(new View.OnClickListener() {

            @Override
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                result.setText("");
                validatorResult = true;
                stringValidator(first_name, NAME_REGEX, "first name");
                stringValidator(last_name, NAME_REGEX, "last name");
                stringValidator(email, EMAIL_REGEX, "email");
                stringValidator(phone, PHONE_REGEX, "phone");
                stringValidator(password, PASSWORD_REGEX, "password");
                passwordsCheck();
                if (validatorResult) {
                    result.setText("All fields are ok");
                    saveUser();
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void stringValidator(EditText field_id, String regex, String field_name) {
        String value = String.valueOf(field_id.getText());
        String already_in_result = String.valueOf(result.getText());
        if (value.equals("")) {
            validatorResult = false;
            result.setText(already_in_result + "\nEmpty " + field_name);
            field_id.setError("Empty " + field_name);
        } else if (!(value.matches(regex))) {
            validatorResult = false;
            result.setText(already_in_result + "\nIncorrect " + field_name);
            field_id.setError("Incorrect " + field_name);
        }
    }

    @SuppressLint("SetTextI18n")
    public void passwordsCheck() {
        String password_value = String.valueOf(password.getText());
        String confirm_password_value = String.valueOf(confirm_password.getText());
        if (confirm_password_value.equals("") && (password_value.equals(""))) {
            validatorResult = false;
            confirm_password.setError("Empty confirm password");
            String already_in_result = String.valueOf(result.getText());
            result.setText(already_in_result + "\nEmpty confirm password");
        }
        if (!(password_value.equals(confirm_password_value))) {
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

        Gson gson = new Gson();
        String json = gson.toJson(userArrayList);
        Log.i("users", json);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(
                "user_list",
                Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_list",json);
        editor.apply();
    }
}
