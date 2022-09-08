package com.tiburela.qsercom.adapters;
import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.tiburela.qsercom.models.ImagenX;

import java.util.ArrayList;

import com.tiburela.qsercom.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>  implements   View.OnClickListener  {

   // private static ClickListener clickListener;

    private View.OnClickListener listener;

    private static ClickListener clickListener;


    private ArrayList<ImagenX> listImagenData;
    private Context mcontext;

    public RecyclerViewAdapter(ArrayList<ImagenX> imagenXArrayList, Context mcontext) {
        this.listImagenData = imagenXArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_of_recycler, parent, false);
        view.setOnClickListener(this);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // Set the data to textview and imageview.
        ImagenX imagenX = listImagenData.get(position);
        holder.textImputEditext.setText(imagenX.getDescripcionImagen());

        holder.imageview.setImageURI(listImagenData.get(position).geturiImage());
        holder.imvClose.setTag(imagenX.getUniqueId());


      //  holder.imageview.setImageResource(imagenX.geturiImage());
        holder.textImputEditext.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {


            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String textOftextImputEditext = holder.textImputEditext.getText().toString();

              //  imagenX.listImagesData.get(position).setDescripcionImagen(textOftextImputEditext);

                /***en edicion arreglar que remplzamos el correcto*/

                // save ans to sharedpreferences or Database


            }
        });



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

        private TextInputEditText textImputEditext;
        private ImageView imageview;
        private ImageView imvClose;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            textImputEditext = itemView.findViewById(R.id.textImputEditext);
            imageview = itemView.findViewById(R.id.idIVcourseIV);
            imvClose= itemView.findViewById(R.id.imvClose);



            imvClose.findViewById(R.id.imvClose).setOnClickListener(new View.OnClickListener() {
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
        RecyclerViewAdapter.clickListener = clickListener;


    }


    public interface ClickListener {
        void onItemClick(int position, View v);


    }

}
