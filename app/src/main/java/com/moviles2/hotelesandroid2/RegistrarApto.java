package com.moviles2.hotelesandroid2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

public class RegistrarApto extends AppCompatActivity {
    EditText etCiudadApto,etPaisApto,etDireccionAPto,etNumeroHab,etValorNoche,etReseñaApto;
    Button btnRegistrarApto;


    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_apto);

        etCiudadApto =findViewById(R.id.etCiudadApto);
        etPaisApto=findViewById(R.id.etCorreoRegistro);
        etDireccionAPto=findViewById(R.id.etContraseñaRegistro);
        etNumeroHab=findViewById(R.id.etPais);
        etValorNoche=findViewById(R.id.etCiudad);
        etReseñaApto=findViewById(R.id.etCiudad);
        btnRegistrarApto=findViewById(R.id.btnGuardar);
    }



    public void RegistrarApto(View view){
        Map<String, Object> user = new HashMap<>();

        String ciudad = etCiudadApto.getText().toString();
        String pais = etPaisApto.getText().toString();
        String direccion = etDireccionAPto.getText().toString();
        String habitaciones = etNumeroHab.getText().toString();
        String valor = etValorNoche.getText().toString();
        String reseña = etReseñaApto.getText().toString();

        user.put("ciudad", ciudad);
        user.put("pais", pais);
        user.put("direccion", direccion);
        user.put("habitaciones", habitaciones);
        user.put("valor", valor);
        user.put("reseña", reseña);

        db.collection("apartaments")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(RegistrarApto.this, "Apartamento Registrado", Toast.LENGTH_SHORT).show();
                        Log.d("registroUsuario", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegistrarApto.this, "No se registro", Toast.LENGTH_SHORT).show();
                        Log.w("registroUsuario", "Error adding document", e);
                    }
                });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_overflow, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id== R.id.item_logout){
            Intent logout = new Intent(getApplicationContext(), MainActivity2.class);
            startActivity(logout);
        }
        else if (id==R.id.itemRegistrarApto){
            Intent intent = new Intent(getApplicationContext(), RegistrarApto.class);
            startActivity(intent);
        }
        else if(id==R.id.verAptos){
            Intent Ingresar = new Intent(getApplicationContext(), ListApartmentActivity.class);
            startActivity(Ingresar);
        }

        return super.onOptionsItemSelected(item);
        

    }


}