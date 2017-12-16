package com.example.usuario.peliculastmdb.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.usuario.peliculastmdb.R;

public class MainActivity extends AppCompatActivity {
    EditText tc;
    Button c;
    Boolean textoValido= false;
    String cer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tc = (EditText) findViewById(R.id.textCerca);
        c = (Button) findViewById(R.id.cerca);
        tc.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                tc.setText("");
                tc.setTextColor(Color.BLACK);
                tc.setTypeface(Typeface.DEFAULT);
                textoValido=true;
            }
        });
        c.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(textoValido && !tc.getText().equals("")){
                    cer = tc.getText().toString();
                    Intent it = new Intent(getApplicationContext(),ResultActivity.class);
                    it.putExtra("clave",cer);
                    startActivity(it);




                }
                return false;
            }
        });


    }
}
