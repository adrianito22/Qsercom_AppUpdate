package com.tiburela.qsercom.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.tiburela.qsercom.R;
import com.tiburela.qsercom.models.SetInformEmbarque1;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RecyclerVAdapterReportsList extends RecyclerView.Adapter<RecyclerVAdapterReportsList.RecyclerViewHolder>  implements   View.OnClickListener  {

   // private static ClickListener clickListener;

    private View.OnClickListener listener;

    private static ClickListener clickListener;


    private ArrayList<SetInformEmbarque1> listImagenData;
    private Context mcontext;

    public RecyclerVAdapterReportsList(ArrayList<SetInformEmbarque1> SetInformEmbarque1ArrayList, Context mcontext) {
        this.listImagenData = SetInformEmbarque1ArrayList;
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
        SetInformEmbarque1 SetInformEmbarque1 = listImagenData.get(position);

        Format formatter = new SimpleDateFormat("dd-MM-yyyy");
        String dateString = formatter.format(SetInformEmbarque1.getFechaCreacionInf());

        holder.txtReportCode.setText(SetInformEmbarque1.getCodeInforme());
        holder.txtDate.setText(dateString);

      //  holder.txtUploadBy.setText(dateString);



    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return listImagenData.size();
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

        private TextView txtReportCode,txtDate,txtUploadBy;
       private  CardView cardview;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            txtReportCode = itemView.findViewById(R.id.txtReportCode);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtUploadBy= itemView.findViewById(R.id.txtUploadBy);


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
