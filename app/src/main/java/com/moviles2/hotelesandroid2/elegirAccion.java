package com.moviles2.hotelesandroid2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class elegirAccion extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elegir_accion);
    }


    public void RegistrarApto (View view){
        Intent RegistrarApto = new Intent(this, RegistrarApto.class);
        startActivity(RegistrarApto);
    }

    public void ReadApto (View view){

        DocumentReference docRef = db.collection("apartaments").document("47DvSc88MOUVL9vKTa6t");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("firebase", "DocumentSnapshot data: " + document.getData());

                    } else {

                    }
                } else {

                }
            }
        });


    }

}