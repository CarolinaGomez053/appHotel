package com.moviles2.hotelesandroid2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import Models.ModelsApartment;

public class ListUsersActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    RecyclerView rvUsersList;
    FirestoreRecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);
        rvUsersList = findViewById(R.id.rvUsersList);

        //query a la base de datos()
        Query query = db.collection("users");

        //configurar opciones del adaptador
        FirestoreRecyclerOptions<ModelsApartment> options = new FirestoreRecyclerOptions.Builder<ModelsApartment>()
                .setQuery(query, ModelsApartment.class)
                .build();
        adapter = new FirestoreRecyclerAdapter<ModelsApartment, ApartamentsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ApartamentsViewHolder holder, int position, @NonNull ModelsApartment model) {
            //asigna los datos a cada elemento
                holder.tvCiudadApto.setText(model.getCiudad());
                holder.tvPaisApto.setText(model.getPais());
                holder.tvDireccionApto.setText(model.getDireccion());
                holder.tvNumeroHabitaciones.setText(model.getHabitaciones());
                holder.tvValorApto.setText(model.getValor());
                holder.tvReseña.setText(model.getReseña());
                String id = getSnapshots().getSnapshot(position).getId();

                holder.btnDeleteApto.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ListUsersActivity.this, "" + id, Toast.LENGTH_SHORT).show();
                        deleteUsers(id);

                    }
                });
                /*holder.itemView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ListApartmentActivity.this, "postion"+position, Toast.LENGTH_SHORT).show();
                    }
                });*/

            }

            @NonNull
            @Override
            public ApartamentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                //crea un elemento grafico por cada id
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
                return new ApartamentsViewHolder(view);
            }
        };
        rvUsersList.setHasFixedSize(true);
        rvUsersList.setLayoutManager(new LinearLayoutManager(this));
        rvUsersList.setAdapter(adapter);
    }

    private class ApartamentsViewHolder extends RecyclerView.ViewHolder{

        TextView tvCiudadApto,tvPaisApto,tvDireccionApto,tvValorApto,tvReseña,tvNumeroHabitaciones;
        Button btnDeleteApto, btnEditApto;

        public ApartamentsViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCiudadApto = itemView.findViewById(R.id.tvCiudadApto);
            tvPaisApto = itemView.findViewById(R.id.tvPaisApto);
            tvDireccionApto = itemView.findViewById(R.id.tvDireccionApto);
            tvValorApto = itemView.findViewById(R.id.tvValorApto);
            tvReseña = itemView.findViewById(R.id.tvReseña);
            tvNumeroHabitaciones = itemView.findViewById(R.id.tvNumeroHabitaciones);
            btnDeleteApto = itemView.findViewById(R.id.btnDeleteApto);
            btnEditApto = itemView.findViewById(R.id.btnEditApto);
        }
    }




    public void deleteUsers(String id) {
        db.collection("users").document((id))
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ListUsersActivity.this, "Usuario Eliminado", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ListUsersActivity.this, " Error", Toast.LENGTH_SHORT).show();

                    }
                });
    }
    








    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_overflow, menu);
        return true;
    }
    
    //metodo para asignar las funciones a cada item
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.item_logout) {
            Intent logout = new Intent(getApplicationContext(), MainActivity2.class);
            startActivity(logout);
        }
        else if(id==R.id.itemRegistrarApto){

            Intent intent = new Intent(getApplicationContext(), RegistrarApto.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }





}