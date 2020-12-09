package com.ek.requestexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    TextView tvResult;
    EditText etLogin;
    EditText etPass;
    Button bGetUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();

        tvResult = findViewById(R.id.tvResult);
        etLogin = findViewById(R.id.etLogin);
        etPass = findViewById(R.id.etPass);
        bGetUser = findViewById(R.id.bGetUser);

        bGetUser.setOnClickListener(v ->
                {
                    try {
                        tvResult.setText(new HttpRequestTask().execute(
                                Constants.BASE_URL,
                                etLogin.getText().toString(),
                                etPass.getText().toString()
                        ).get());
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
    }
}