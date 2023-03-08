package com.example.vet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustServiceAdapter extends FirebaseRecyclerAdapter<ServiceModel,CustServiceAdapter.myViewHolder>{
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CustServiceAdapter(@NonNull FirebaseRecyclerOptions<ServiceModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull ServiceModel model) {
        holder.name.setText(model.getName());
        holder.price.setText(model.getPrice());

        //Glide.with(holder.img.getContext())
                //.load(model.getSurl())
                //.placeholder(com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_dark)
                //.circleCrop()
                //.error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                //.into(holder.img);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        //CircleImageView img;
        TextView name,price;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.nametext);
            price = (TextView) itemView.findViewById(R.id.pricetext);
        }
    }
}
