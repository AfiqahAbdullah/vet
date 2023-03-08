package com.example.vet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class CustReportAdapter extends FirebaseRecyclerAdapter<ReportModel,CustReportAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CustReportAdapter(@NonNull FirebaseRecyclerOptions<ReportModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull ReportModel model) {
        holder.custIC.setText(model.getCustIC());
        holder.petname.setText(model.getPetname());
        holder.date.setText(model.getDate());
        holder.start_time.setText(model.getStart_time());
        holder.end_time.setText(model.getEnd_time());
        holder.medicine.setText(model.getMedicine());
        holder.vetname.setText(model.getVetname());
        holder.treatment.setText(model.getTreatment());
        holder.payment.setText(model.getPayment());

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView custIC, date, end_time, medicine, payment, petname, start_time, treatment, vetname;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            custIC = (TextView) itemView.findViewById(R.id.custic);
            date = (TextView) itemView.findViewById(R.id.datetext);
            end_time = (TextView) itemView.findViewById(R.id.end_time);
            medicine = (TextView) itemView.findViewById(R.id.medicine);
            payment = (TextView) itemView.findViewById(R.id.total);
            petname = (TextView) itemView.findViewById(R.id.petname);
            start_time = (TextView) itemView.findViewById(R.id.start_time);
            treatment = (TextView) itemView.findViewById(R.id.treatment);
            vetname = (TextView) itemView.findViewById(R.id.vetname);
        }

    }
}
