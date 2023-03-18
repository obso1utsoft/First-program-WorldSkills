package com.example.firstprogramworldskills;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_Screen extends AppCompatActivity {
    private String valid_email;
    private Button btnSendPostRequest;

    private EditText emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        btnSendPostRequest = findViewById(R.id.login_button);
        btnSendPostRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSendPostRequestClicked();
            }
        });
        emailText = findViewById(R.id.editText);
        emailText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                Is_Valid_Email(emailText);
                if (valid_email == null){
                    btnSendPostRequest.setEnabled(false);
                }
                else {
                    btnSendPostRequest.setEnabled(true);
                }
            }
            public void Is_Valid_Email(EditText edt) {
                if (edt.getText().toString() == null) {
                    edt.setError("Неверно указан E-mail");
                    valid_email = null;
                } else if (isEmailValid(edt.getText().toString()) == false) {
                    edt.setError("Неверный указан E-mail");
                    valid_email = null;
                } else {
                    valid_email = edt.getText().toString();
                }
            }
            boolean isEmailValid(CharSequence email) {
                return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                        .matches();
            }
        });

    }
    private void btnSendPostRequestClicked() {
        ApiInterface apiInterface = RetrofitSendCode.getRetrofitInstance().create(ApiInterface.class);
        Call<Email> call = apiInterface.getEmailInformation(emailText.getText().toString());
        call.enqueue(new Callback<Email>() {
            @Override
            public void onResponse(Call<Email> call, Response<Email> response) {
                if (response.code() == 200)
                    Toast.makeText(Login_Screen.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Login_Screen.this, response.body().getErrors(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Email> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

}