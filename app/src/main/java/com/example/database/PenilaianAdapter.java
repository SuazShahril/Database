package com.example.database;

import androidx.recyclerview.widget.RecyclerView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class PenilaianAdapter extends FirebaseRecyclerAdapter<PeniModel, PenilaianAdapter.myviewholder> {

    public PenilaianAdapter(@NonNull FirebaseRecyclerOptions<PeniModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PenilaianAdapter.myviewholder holder, int position, @NonNull PeniModel model) {
        holder.kod.setText(model.getKod());
        holder.nama.setText(model.getNama());
        holder.status.setText(model.getStatus());

        holder.kemaskini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.status.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialogcontent2))
                        .setExpanded(true, 1000)
                        .create();

                View myview = dialogPlus.getHolderView();
                final EditText kod = myview.findViewById(R.id.p2kod);
                final EditText nama = myview.findViewById(R.id.p2nama);
                final EditText status = myview.findViewById(R.id.p2status);
                Button submit = myview.findViewById(R.id.p2submit);

                kod.setText(model.getKod());
                nama.setText(model.getNama());
                status.setText(model.getStatus());


                dialogPlus.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("kod", kod.getText().toString());
                        map.put("nama", nama.getText().toString());
                        map.put("status", status.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Jenis Penilaian")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });

                holder.hapus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(holder.status.getContext());
                        builder.setTitle("Hapuskan Panel");
                        builder.setMessage("Hapus...?");

                        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseDatabase.getInstance().getReference().child("Jenis Penilaian")
                                        .child(getRef(position).getKey()).removeValue();
                            }
                        });

                        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {

                            }
                        });

                        builder.show();
                    }
                });
            }
        });
    }

    @NonNull
    @Override
    public PenilaianAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.penilaianrow,parent, false);
        return new PenilaianAdapter.myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{


        TextView kod, nama, status;
        Button kemaskini, hapus;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            kod=(TextView)itemView.findViewById(R.id.kodtext2);
            nama=(TextView)itemView.findViewById(R.id.namatext2);
            status=(TextView)itemView.findViewById(R.id.statustext2);

            kemaskini=(Button) itemView.findViewById(R.id.editButton2);
            hapus=(Button) itemView.findViewById(R.id.deleteButton2);

        }
    }

}
