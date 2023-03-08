package com.example.vet;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class CustAppointmentAdapter extends FirebaseRecyclerAdapter <AppointmentModel,CustAppointmentAdapter.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CustAppointmentAdapter(@NonNull FirebaseRecyclerOptions<AppointmentModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull AppointmentModel model) {
        holder.petname.setText(model.getPetname());
        holder.custname.setText(model.getCustname());
        holder.custIC.setText(model.getCustIC());
        holder.contact.setText(model.getContact());
        holder.date.setText(model.getDate());
        holder.time.setText(model.getTime());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.petname.getContext())
                        .setContentHolder(new ViewHolder(R.layout.updateappointment_popup))
                        .setExpanded(true, 1200)
                        .create();

                View v = dialogPlus.getHolderView();
                EditText petname = v.findViewById(R.id.petname);
                EditText custname = v.findViewById(R.id.custname);
                EditText custIC = v.findViewById(R.id.custic);
                EditText contact= v.findViewById(R.id.contact);
                EditText date = v.findViewById(R.id.et_date);
                EditText time = v.findViewById(R.id.et_time);

                Button btnUpdate = v.findViewById(R.id.btnUpdate);

                petname.setText(model.getPetname());
                custname.setText(model.getCustname());
                custIC.setText(model.getCustIC());
                contact.setText(model.getContact());
                date.setText(model.getDate());
                time.setText(model.getTime());

                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("petname",petname.getText().toString());
                        map.put("custname",custname.getText().toString());
                        map.put("custIC",custIC.getText().toString());
                        map.put("contact",contact.getText().toString());
                        map.put("date",date.getText().toString());
                        map.put("time",time.getText().toString());
                        FirebaseDatabase.getInstance().getReference().child("appt")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.petname.getContext(), "Data Updated Successfully",Toast.LENGTH_LONG).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.petname.getContext(), "Error While Updating...",Toast.LENGTH_LONG).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.petname.getContext());
                builder.setTitle("Are you Sure?");
                builder.setMessage("Deleted data cannot be undo");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("appt")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.petname.getContext(), "Delete Cancelled",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appointment_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView petname,custname,custIC,contact,date,time;
        Button btnEdit, btnDelete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            petname = (TextView) itemView.findViewById(R.id.petname);
            custname = (TextView) itemView.findViewById(R.id.custname);
            custIC = (TextView) itemView.findViewById(R.id.custic);
            contact = (TextView) itemView.findViewById(R.id.contact);
            date = (TextView) itemView.findViewById(R.id.tv_date);
            time = (TextView) itemView.findViewById(R.id.tv_time);

            btnEdit = (Button) itemView.findViewById(R.id.btnEdit);
            btnDelete = (Button) itemView.findViewById(R.id.btnDelete);
        }
    }
}
