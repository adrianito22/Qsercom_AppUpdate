package com.tiburela.qsercom.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.tiburela.qsercom.R;
import com.tiburela.qsercom.models.ReportsAllModel;

import java.util.ArrayList;

public class RecyclerVAdapterReportsList extends RecyclerView.Adapter<RecyclerVAdapterReportsList.RecyclerViewHolder>
        implements   View.OnClickListener  {


    private View.OnClickListener listener;

    private static ClickListener clickListener;


    private ArrayList<ReportsAllModel> listReports;
    private Context mcontext;

    public RecyclerVAdapterReportsList(ArrayList<ReportsAllModel> ReportsAllModelArrayList, Context mcontext) {
        this.listReports = ReportsAllModelArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_of_recycler_report, parent, false);
        view.setOnClickListener(this);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // Set the data to textview and imageview.
        ReportsAllModel ReportsAllModel = listReports.get(position);

       // Format formatter = new SimpleDateFormat("dd-MM-yyyy");
       // String dateString = formatter.format(ReportsAllModel.getFechaCreacionInf());

        holder.txtReportCode.setText("Reporte cod: 00012");
        holder.txtDate.setText(ReportsAllModel.getDateReport());
        holder.txtCategoria.setText(ReportsAllModel.getNombreCategoria());
        holder.cardview.setTag(ReportsAllModel.idInforme);

      //  holder.txtUploadBy.setText(dateString);



    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return listReports.size();
    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtReportCode,txtDate, txtCategoria;
       private  CardView cardview;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            txtReportCode = itemView.findViewById(R.id.txtReportCode);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtCategoria = itemView.findViewById(R.id.txtCategoria);


            cardview= itemView.findViewById(R.id.cardview);


            cardview.findViewById(R.id.cardview).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    clickListener.onItemClick(getAdapterPosition(), v);

                }
            });



        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(), view);

        }
    }


    public void setOnItemClickListener(ClickListener clickListener) {
        RecyclerVAdapterReportsList.clickListener = clickListener;


    }


    public interface ClickListener {
        void onItemClick(int position, View v);


    }

}
