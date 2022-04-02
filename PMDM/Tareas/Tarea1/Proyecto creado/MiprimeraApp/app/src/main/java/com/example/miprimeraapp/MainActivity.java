package com.example.miprimeraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final int VISIBLE = 0x00000000;

        TextView text = findViewById(R.id.texto);
        ImageView imagen = findViewById(R.id.zamora);

        Button boton = findViewById(R.id.boton);
        boton.setOnClickListener(new View.OnClickListener() {
            int cont = 0;
            @SuppressLint({"ResourceAsColor", "WrongConstant"})
            @Override
            public void onClick(View v) {

                cont++;
                imagen.setVisibility(VISIBLE);

                if (cont == 2){
                    text.setVisibility(VISIBLE);

                }
            }
        });
    }
}