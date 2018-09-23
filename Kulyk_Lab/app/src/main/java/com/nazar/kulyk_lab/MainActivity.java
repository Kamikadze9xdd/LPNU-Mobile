package com.nazar.kulyk_lab;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String NAME_REGEX = "^[A-Z][a-zA-Z]+$";
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9+_.-]+@[a-zA-Z]+\\.[A-Za-z]{2,4}$";
    public static final String PHONE_REGEX = "^\\+?[0-9]{10,16}$";
    public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
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
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.result);
        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirm_password);
        submit_button = findViewById(R.id.submit_button);
        onClickButtonsHandler();
    }

    public void onClickButtonsHandler() {

        submit_button.setOnClickListener(new View.OnClickListener() {

            @Override
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                result.setText("");
                validatorResult = true;
                StringValidator(first_name, NAME_REGEX, "first name");
                StringValidator(last_name, NAME_REGEX, "last name");
                StringValidator(email, EMAIL_REGEX, "email");
                StringValidator(phone, PHONE_REGEX, "phone");
                StringValidator(password, PASSWORD_REGEX, "password");
                PasswordsCheck();
                if (validatorResult) {
                    result.setText("All fields are ok");
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void StringValidator(EditText field_id, String regex, String field_name) {
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
    public void PasswordsCheck() {
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
}
