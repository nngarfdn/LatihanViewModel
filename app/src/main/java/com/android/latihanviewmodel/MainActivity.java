package com.android.latihanviewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private EditText edtLength, edtWidth, edtHeight;
    private Button btnCalculate;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        edtLength = findViewById(R.id.edt_length);
        edtWidth = findViewById(R.id.edt_width);
        edtHeight = findViewById(R.id.edt_height);
        btnCalculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);
        btnCalculate.setOnClickListener(view -> {
            String length = edtLength.getText().toString();
            String width = edtWidth.getText().toString();
            String height = edtHeight.getText().toString();

            if (width.isEmpty()) {
                edtWidth.setError("Masih Kosong");
            } else if (height.isEmpty()) {
                edtHeight.setError("Masih Kosong");
            } else if (length.isEmpty()) {
                edtLength.setError("Masih Kosong");
            } else {
                viewModel.calculate(width, height, length);
                displayResult();
            }
        });
        displayResult();
    }

    private void displayResult() {
//        tvResult.setText(String.valueOf(viewModel.result));
        viewModel.result.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer result) {
                tvResult.setText(String.valueOf(result));
            }
        });
    }

}