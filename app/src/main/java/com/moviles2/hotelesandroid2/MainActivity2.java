package com.moviles2.hotelesandroid2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity2 extends AppCompatActivity {
    EditText etCorreo,etContraseña;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        etCorreo = findViewById(R.id.etCorreo);
        etContraseña = findViewById(R.id.etContraseña);
    }

        public void RegistrarDatos (View view){
            Intent RegistrarDatos = new Intent(this, MainActivity.class);
            startActivity(RegistrarDatos);
    }

    public void Ingresar (View view){
        Intent Ingresar = new Intent(this, elegirAccion.class);
        startActivity(Ingresar);
    }

        public void RegisterUser (View view){


        String email= etCorreo.getText().toString();
        String password =etContraseña.getText().toString();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {


                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(MainActivity2.this, "", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }








}