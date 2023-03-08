package com.example.vet;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
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
import com.orhanobut.dialogplus.Holder;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class TreatAdapter extends FirebaseRecyclerAdapter<TreatModel,TreatAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public TreatAdapter(@NonNull FirebaseRecyclerOptions options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull TreatModel model) {
        holder.date.setText(model.getDate());
        holder.petname.setText(model.getPetname());
        holder.vetname.setText(model.getVetname());
        holder.custIC.setText(model.getCustIC());
        holder.treatment.setText(model.getTreatment());
        holder.start_time.setText(model.getStart_time());
        holder.end_time.setText(model.getEnd_time());
        holder.medicine.setText(model.getMedicine());
        holder.payment.setText(model.getPayment());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.date.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_treat_popup))
                        .setExpanded(true, 2000)
                        .create();

                View view = dialogPlus.getHolderView();

                EditText date = view.findViewById(R.id.date);
                EditText petname = view.findViewById(R.id.petname);
                EditText vetname = view.findViewById(R.id.vetname);
                EditText custIC = view.findViewById(R.id.custIC);
                EditText start_time = view.findViewById(R.id.start_time);
                EditText end_time = view.findViewById(R.id.end_time);
                EditText treatment = view.findViewById(R.id.treatment);
                EditText medicine = view.findViewById(R.id.medicine);
                EditText payment = view.findViewById(R.id.payment);

                Button btnUpdate = view.findViewById(R.id.btnUpdate);

                date.setText(model.getDate());
                petname.setText(model.getPetname());
                vetname.setText(model.getVetname());
                custIC.setText(model.getCustIC());
                treatment.setText(model.getTreatment());
                start_time.setText(model.getStart_time());
                end_time.setText(model.getEnd_time());
                medicine.setText(model.getMedicine());
                payment.setText(model.getPayment());
                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("date", date.getText().toString());
                        map.put("petname", petname.getText().toString());
                        map.put("vetname", vetname.getText().toString());
                        map.put("custIC", custIC.getText().toString());
                        map.put("start_time", start_time.getText().toString());
                        map.put("end_time", end_time.getText().toString());
                        map.put("treatment",treatment.getText().toString());
                        map.put("medicine", medicine.getText().toString());
                        map.put("payment", payment.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("treat")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.date.getContext(), "Data Updated Successfully.", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.date.getContext(), "Error While Updating.", Toast.LENGTH_SHORT).show();
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
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.date.getContext());
                builder.setTitle("Are you Sure?");
                builder.setMessage("Deleted data can't be undo.");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("treat")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.date.getContext(), "Cancelled.", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }


        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admintreat_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView date,petname,vetname,custIC,start_time,end_time,treatment, medicine,payment;

        Button btnEdit, btnDelete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            date = (TextView) itemView.findViewById(R.id.date);
            petname = (TextView) itemView.findViewById(R.id.petname);
            vetname = (TextView) itemView.findViewById(R.id.vetname);
            custIC = (TextView) itemView.findViewById(R.id.custIC);
            start_time = (TextView) itemView.findViewById(R.id.start_time);
            end_time = (TextView) itemView.findViewById(R.id.end_time);
            treatment = (TextView) itemView.findViewById(R.id.treatment);
            medicine = (TextView) itemView.findViewById(R.id.medicine);
            payment = (TextView) itemView.findViewById(R.id.payment);

            btnEdit = (Button) itemView.findViewById(R.id.btnEdit);
            btnDelete = (Button) itemView.findViewById(R.id.btnDelete);

        }
    }
}
