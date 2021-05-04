package com.moviles2.hotelesandroid2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class apartamento extends AppCompatActivity {
    EditText etCiudadApto,etPaisApto,etDireccionAPto,etNumeroHab,etValorNoche,etReseña;
    Button btnGuardarApto;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartamento);

        etCiudadApto =findViewById(R.id.etCiudadApto);
        etPaisApto= findViewById(R.id.etPaisApto);
        etDireccionAPto=findViewById(R.id.etDireccionAPto);
        etNumeroHab=findViewById(R.id.etNumeroHab);
        etValorNoche=findViewById(R.id.etValorNoche);
        etReseña=findViewById(R.id.etValorNoche);
        btnGuardarApto=findViewById(R.id.btnRegistrarApto);


    }

    /*public void readAparment (View view){
        DocumentReference docRef = db.collection("aparments").document("SF");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }*/

    public void SaveApartament(View view){
        Map<String, Object> user = new HashMap<>();

        String ciudadApartamento = etCiudadApto.getText().toString();
        String paisApartamento = etPaisApto.getText().toString();
        String direccionApartamento = etDireccionAPto.getText().toString();
        String numeroHabitaciones = etNumeroHab.getText().toString();
        String valorNoche = etValorNoche.getText().toString();
        String resena = etReseña.getText().toString();

        user.put("ciudad apartamento", ciudadApartamento);
        user.put("pais apartamento",paisApartamento);
        user.put("direccion apartamento", direccionApartamento);
        user.put("numero habitaciones", numeroHabitaciones);
        user.put("valor noche", valorNoche);
        user.put("resena", resena);

        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(apartamento.this, "Apartamento registrado", Toast.LENGTH_SHORT).show();
                        Log.d("registroUsuario", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(apartamento.this, "No se registro", Toast.LENGTH_SHORT).show();
                        Log.w("registroUsuario", "Error adding document", e);
                    }
                });
    }



}