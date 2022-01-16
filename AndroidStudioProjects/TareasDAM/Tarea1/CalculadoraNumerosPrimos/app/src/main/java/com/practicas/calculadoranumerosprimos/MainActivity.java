package com.practicas.calculadoranumerosprimos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText et = (EditText) findViewById(R.id.editTextTextPersonName);
        TextView tv = findViewById(R.id.textView3);
        tv.setText(et.getText());

    }
}