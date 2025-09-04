package com.example.week_5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner spin;
    private EditText txtN;
    private EditText txtP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing here ONCE.. available throughout the program
        this.spin = (Spinner) findViewById(R.id.cmbUser);
        this.txtN = (EditText) findViewById(R.id.txtName);
        this.txtP = (EditText) findViewById(R.id.txtPass);
    }

    public void btnLogon(View view) {

        if (txtP.getText().toString().equals("hit238")) {

            // Adding information to the Intent to carry forward
            Intent i = new Intent(this, Activity2.class);
            i.putExtra("user_name", txtN.getText().toString());
            i.putExtra("user_type", spin.getSelectedItem().toString());

            startActivity(i);
        } else {
            Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_LONG).show();
        }
    }

    public void openInformationApp(View view) {
        Intent intent = new Intent(this, InformationAppActivity.class);
        startActivity(intent);
    }

}