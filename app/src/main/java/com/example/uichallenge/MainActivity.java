package com.example.uichallenge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText edtTxtName, edtTxtEmail, edtTxtPassword, edtTxtPassRepeat;
    private Button btnPickImg, btnRegister;
    private TextView txtWarnName, txtWarnEmail, txtWarnPassword, txtWarnPassRepeat;
    private Spinner  countriesSpinner;
    private RadioGroup rgGender;
    private CheckBox agreementCheck;
    private ConstraintLayout parent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        btnPickImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Yes to talk about", Toast.LENGTH_SHORT).show();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initRegister();
            }
        });
    }
    private void initRegister() {
            Log.d(TAG, "initRegister: Started");

            if(validateData()) {
                if(agreementCheck.isChecked()) {
                    showSnackBar();
                } else {
                    Toast.makeText(this, "You need to agree to the licence agreement", Toast.LENGTH_SHORT).show();
                }
            }

    }
    private void showSnackBar() {
        Log.d(TAG, "showSnackBar: Started");
        txtWarnName.setVisibility(View.GONE);
        txtWarnEmail.setVisibility(View.GONE);
        txtWarnPassword.setVisibility(View.GONE);
        txtWarnPassRepeat.setVisibility(View.GONE);

        String name = edtTxtName.getText().toString();
        String email = edtTxtEmail.getText().toString();
        String countries = countriesSpinner.getSelectedItem().toString();
        String gender = "";
        switch (rgGender.getCheckedRadioButtonId()) {
            case R.id.rbMale:
                gender = "Male";
                break;
            case R.id.rbFemale:
                gender="Female";
                break;
            case R.id.rbOther:
                gender = "Other";
                break;
            default:
                gender = "Unknown";
                break;
        }
        // hiển thị hộp đen dưới sau khi nhấn button
        String snackText = "Name: " + name + "\n" +
                "Email: " + email + "\n" +
                "Gender: " + gender + "\n" +
                "Country: " + countries;
        Log.d(TAG, "showSnackbar: Snack Bar Text: " + snackText);
        Snackbar.make(parent, snackText, Snackbar.LENGTH_INDEFINITE)
                .setAction("Dismiss", new View.OnClickListener() {
                    public void onClick(View view) {
                        edtTxtName.setText("");
                        edtTxtEmail.setText("");
                        edtTxtPassword.setText("");
                        edtTxtPassRepeat.setText("");
                    }
                }).show();
    }
    // khi chưa nhập đủ thông tin input thì hiện thông báo cần nhập
    private boolean validateData() {
        Log.d(TAG, "boolean: Started");
        if(edtTxtName.getText().toString().equals("")) {
            txtWarnName.setVisibility(View.VISIBLE);
            txtWarnName.setText("Enter your name");
            return false;
        }
        if(edtTxtEmail.getText().toString().equals("")) {
            edtTxtEmail.setVisibility(View.VISIBLE);
            txtWarnName.setText("Enter your email");
            return false;
        }
        if(edtTxtPassword.getText().toString().equals("")) {
            edtTxtPassword.setVisibility(View.VISIBLE);
            txtWarnName.setText("Enter your pasword");

            return false;
        }
        if(edtTxtPassRepeat.getText().toString().equals("")) {
            edtTxtPassRepeat.setVisibility(View.VISIBLE);
            txtWarnName.setText("Re Enter your pasword");
            return false;
        }
        if(!edtTxtPassword.getText().toString().equals(edtTxtPassRepeat.getText().toString())) {
            txtWarnPassRepeat.setVisibility(View.VISIBLE);
            txtWarnPassRepeat.setText("Password doesn't match");
            return false;
        }
        return true;
    }
    private void initViews() {
        Log.d(TAG, "initViews: Started");
        edtTxtName = findViewById((R.id.edtTxtName));
        edtTxtEmail = findViewById((R.id.edtTxtEmail));
        edtTxtPassword = findViewById((R.id.edtTxtPassword));
        edtTxtPassRepeat = findViewById((R.id.edtTxtPassRepeat));

        btnPickImg = findViewById(R.id.btnPickImg);
        btnRegister = findViewById(R.id.btnRegister);


        txtWarnName = findViewById(R.id.txtWarnName);
        txtWarnEmail = findViewById(R.id.txtWarnEmail);
        txtWarnPassword = findViewById(R.id.txtWarnPass);
        txtWarnPassRepeat = findViewById(R.id.txtWarnPassRepeat);


        countriesSpinner = findViewById(R.id.countriesSpinner);
        rgGender = findViewById(R.id.rgGender);
        parent = findViewById(R.id.parent);
    }
}