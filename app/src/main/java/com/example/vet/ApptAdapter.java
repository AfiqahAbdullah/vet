package com.example.vet;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ApptAdapter extends FirebaseRecyclerAdapter<ApptModel,ApptAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ApptAdapter(@NonNull FirebaseRecyclerOptions options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull ApptModel model) {
        holder.petname.setText(model.getPetname());
        holder.custname.setText(model.getCustname());
        holder.custIC.setText(model.getCustIC());
        holder.contact.setText(model.getContact());
        holder.date.setText(model.getDate());
        holder.time.setText(model.getTime());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.petname.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_appt_popup))
                        .setExpanded(true, 2000)
                        .create();

                View view = dialogPlus.getHolderView();

                EditText petname = view.findViewById(R.id.petname);
                EditText custname = view.findViewById(R.id.custname);
                EditText custIC = view.findViewById(R.id.custIC);
                EditText contact = view.findViewById(R.id.contact);
                EditText date = view.findViewById(R.id.et_date);
                EditText time = view.findViewById(R.id.et_time);

                TextView tvDate = view.findViewById(R.id.tv_date);
                TextView tvTime = view.findViewById(R.id.tv_time);

                Button btnUpdate = view.findViewById(R.id.btnUpdate);

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
                        Map<String, Object> map = new HashMap<>();
                        map.put("petname", petname.getText().toString());
                        map.put("custname", custname.getText().toString());
                        map.put("custIC", custIC.getText().toString());
                        map.put("contact", contact.getText().toString());
                        map.put("date", date.getText().toString());
                        map.put("time", time.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("appt")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.petname.getContext(), "Data Updated Successfully.", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.petname.getContext(), "Error While Updating.", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });

                    }

                });
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.petname.getContext());
                builder.setTitle("Are you Sure?");
                builder.setMessage("Deleted data can't be undo.");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("appt")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.petname.getContext(), "Cancelled.", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adminappt_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView petname,custname,custIC,contact,date,time;

        Button btnEdit, btnDelete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            petname = (TextView) itemView.findViewById(R.id.petname);
            custname = (TextView) itemView.findViewById(R.id.custname);
            custIC = (TextView) itemView.findViewById(R.id.custIC);
            contact = (TextView) itemView.findViewById(R.id.contact);
            date = (TextView) itemView.findViewById(R.id.et_date);
            time = (TextView) itemView.findViewById(R.id.et_time);

            btnEdit = (Button) itemView.findViewById(R.id.btnEdit);
            btnDelete = (Button) itemView.findViewById(R.id.btnDelete);

        }
    }
}

