package com.moviles2.hotelesandroid2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText etName,etCorreoRegistro,etContraseñaRegistro,etPais,etCiudad;
    Button btnGuardar;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName =findViewById(R.id.etName);
        etContraseñaRegistro= findViewById(R.id.etContraseñaRegistro);
        etCorreoRegistro=findViewById(R.id.etCorreoRegistro);
        etPais=findViewById(R.id.etPais);
        etCiudad=findViewById(R.id.etCiudad);
        btnGuardar=findViewById(R.id.btnGuardar);
    }

     public void saveUser(View view){
        Map<String, Object> user = new HashMap<>();

        String name = etName.getText().toString();
        String correo = etCorreoRegistro.getText().toString();
        String contraseña = etContraseñaRegistro.getText().toString();
        String pais = etPais.getText().toString();
        String ciudad = etCiudad.getText().toString();

        user.put("name", name);
        user.put("correo", correo);
        user.put("contraseña", contraseña);
        user.put("pais", pais);
        user.put("ciudad", ciudad);

        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(MainActivity.this, "Usuario Registrado", Toast.LENGTH_SHORT).show();
                        Intent RegistrarDatos = new Intent(getApplicationContext(), MainActivity2.class);
                        startActivity(RegistrarDatos);
                        Log.d("registroUsuario", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "No se registro", Toast.LENGTH_SHORT).show();
                        Log.w("registroUsuario", "Error adding document", e);
                    }
                });
    }



}