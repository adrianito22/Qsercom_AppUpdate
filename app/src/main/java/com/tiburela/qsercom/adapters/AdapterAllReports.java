package com.tiburela.qsercom.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.util.Util;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.models.InformRegister;
import com.tiburela.qsercom.utils.Utils;

import java.util.ArrayList;

public class AdapterAllReports extends RecyclerView.Adapter<AdapterAllReports.RecyclerViewHolder>  implements   View.OnClickListener  {

   // private static ClickListener clickListener;

    private View.OnClickListener listener;

    private static ClickListener clickListener;




    private ArrayList<InformRegister> listReports;
    private Context mcontext;

    public AdapterAllReports(ArrayList<InformRegister> InformRegisterArrayList, Context mcontext) {
        this.listReports = InformRegisterArrayList;
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
        InformRegister InformRegister = listReports.get(position);

        if(!Utils.isOfflineReport){

            holder.txtReportCode.setText(InformRegister.getInformUniqueIdPertenece());

        }


        holder.txtDate.setText(InformRegister.getSimpleDateForm());
        holder.txtCategoria.setText(InformRegister.getTypeReportString());
        holder.cardview.setTag(InformRegister.getCodeInformtoVinculacion());


        if(! Utils.isOfflineReport){

            if(InformRegister.isSeRevisoForm()){  //si lo marco como revisado
                holder.txtStateForm.setText("Revisado"+" por "+InformRegister.getNombreQUienRevisoForm());
                holder.txtStateForm.setTextColor(Color.parseColor("#338737"));
            }



            else
            {
                holder.txtStateForm.setText("Por revisar");

            }




        }
        else
        { //si es offline

            holder.txtReportCode.setText("Reporte "+(position+1)); //SI ES OFFLINE OCULTAMOS STATE VIEW


    if(InformRegister.isSeSubioFormAlinea()){
        holder.txtStateForm.setText("Subido");

    }
    else{

        holder.txtStateForm.setText("NO SUBIDO"); //SI ES OFFLINE OCULTAMOS STATE VIEW


    }




        }





        holder.txtNameUploadForm.setText(InformRegister.getNombreQUienSubio());



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

        private TextView txtNameUploadForm,txtStateForm;
       private  CardView cardview;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            txtReportCode = itemView.findViewById(R.id.txtReportCode);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtCategoria = itemView.findViewById(R.id.txtCategoria);
            txtNameUploadForm=itemView.findViewById(R.id.txtNameUploadForm);
            txtStateForm=itemView.findViewById(R.id.txtStateForm);


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
        AdapterAllReports.clickListener = clickListener;


    }


    public interface ClickListener {
        void onItemClick(int position, View v);


    }

}
