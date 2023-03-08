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

import com.bumptech.glide.Glide;
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

public class CustPetAdapter extends FirebaseRecyclerAdapter<PetModel,CustPetAdapter.myViewHolder>{

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CustPetAdapter(@NonNull FirebaseRecyclerOptions<PetModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull PetModel model) {
        holder.petName.setText(model.getPetName());
        holder.petType.setText(model.getPetType());
        holder.petBreed.setText(model.getPetBreed());
        holder.petSex.setText(model.getPetSex());
        holder.petDOB.setText(model.getPetDOB());
        //holder.petSurl.setText(model.getPetSurl());
        holder.petOwnerIC.setText(model.getPetOwnerIC());

        Glide.with(holder.img.getContext())
                .load(model.getPetSurl())
                .placeholder(com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);
        //sy tambah
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.updatepet_popup))
                        .setExpanded(true, 1200)
                        .create();

                View v = dialogPlus.getHolderView();
                EditText petName = v.findViewById(R.id.nametext);
                EditText petType = v.findViewById(R.id.typetext);
                //EditText petOwnerIC = v.findViewById(R.id.petOwnerIC);
                EditText petBreed= v.findViewById(R.id.breedtext);
                EditText petDOB = v.findViewById(R.id.dobtext);
                EditText petSex = v.findViewById(R.id.gendertext);
                EditText petSurl = v.findViewById(R.id.urltext);

                Button btnUpdate = v.findViewById(R.id.btnUpdate);

                petName.setText(model.getPetName());
                petBreed.setText(model.getPetBreed());
                petType.setText(model.getPetType());
                petSex.setText(model.getPetSex());
                petDOB.setText(model.getPetDOB());
                petSurl.setText(model.getPetSurl());
                //petOwnerIC.setText(model.getPetOwnerIC());

                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("petName",petName.getText().toString());
                        map.put("petType",petType.getText().toString());
                        map.put("petBreed",petBreed.getText().toString());
                        //map.put("petOwnerIC",petOwnerIC.getText().toString());
                        map.put("petSex",petSex.getText().toString());
                        map.put("petSurl",petSurl.getText().toString());
                        map.put("petDOB",petDOB.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("pet")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.petName.getContext(), "Data Updated Successfully",Toast.LENGTH_LONG).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.petName.getContext(), "Error While Updating...",Toast.LENGTH_LONG).show();
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
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.petName.getContext());
                builder.setTitle("Are you Sure?");
                builder.setMessage("Deleted data cannot be undo");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("pet")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.petName.getContext(), "Delete Cancelled",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        Button btnEdit, btnDelete;
        CircleImageView img;
        TextView petName,petType,petSex,petBreed,petDOB,petOwnerIC;
        //,petSurl

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView)itemView.findViewById(R.id.img1);
            petName = (TextView)itemView.findViewById(R.id.nametext);
            petType = (TextView)itemView.findViewById(R.id.typetext);
            petSex = (TextView)itemView.findViewById(R.id.gendertext);
            petBreed = (TextView)itemView.findViewById(R.id.breedtext);
            petDOB = (TextView)itemView.findViewById(R.id.dobtext);
            //petSurl = (TextView)itemView.findViewById(R.id.urltext);
            petOwnerIC = (TextView)itemView.findViewById(R.id.petOwnerIC);

            btnEdit = (Button) itemView.findViewById(R.id.btnEdit);
            btnDelete = (Button) itemView.findViewById(R.id.btnDelete);
        }
    }

}
