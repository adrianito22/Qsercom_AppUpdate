package com.tiburela.qsercom.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.models.ColorCintasSemns;

import java.util.ArrayList;

public class RecyclerViewAdapterColorCintSem extends RecyclerView.Adapter<RecyclerViewAdapterColorCintSem.RecyclerViewHolder>  implements   View.OnClickListener  {

    private View.OnClickListener listener;
    private static ClickListener clickListener;


    private ArrayList<ColorCintasSemns> listImagenData;
    private Context mcontext;

    public RecyclerViewAdapterColorCintSem(ArrayList<ColorCintasSemns> ColorCintasSemnsArrayList, Context mcontext) {
        this.listImagenData = ColorCintasSemnsArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_color_cintas_semana, parent, false);
        view.setOnClickListener(this);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // Set the data to textview and imageview.

        ColorCintasSemns objectCurrent = listImagenData.get(position);
        //si el valor es diferente de 0..

        Log.i("debugeoxc","call here");


        if(objectCurrent.getColumFieldNUm9()!=0){

            holder.ediColum9.setText(String.valueOf(objectCurrent.getColumFieldNUm9()));

        }


        if(objectCurrent.getColumFieldNUm10()!=0){

            holder.ediColum10.setText(String.valueOf(objectCurrent.getColumFieldNUm10()));

        }


        if(objectCurrent.getColumFieldNUm11()!=0){

            holder.ediColum11.setText(String.valueOf(objectCurrent.getColumFieldNUm11()));


        }


        if(objectCurrent.getColumFieldNUm12()!=0){

            holder.ediColum12.setText(String.valueOf(objectCurrent.getColumFieldNUm12()));


        }


        if(objectCurrent.getColumFieldNUm13()!=0){

            holder.ediColum13.setText(String.valueOf(objectCurrent.getColumFieldNUm13()));
        }


        if(objectCurrent.getColumFieldNUm14()!=0){

            holder.ediColum14.setText(String.valueOf(objectCurrent.getColumFieldNUm14()));

        }


        holder.semnNum.setText(String.valueOf(objectCurrent.getSemanNum()) );

        //ad atags
        // holder.semnNum.setTag("semnNum");
        holder.ediColum9.setTag("ediColum9");
        holder.ediColum10.setTag("ediColum10");
        holder.ediColum11.setTag("ediColum11");
        holder.ediColum12.setTag("ediColum12");
        holder.ediColum13.setTag("ediColum13");
        holder.ediColum14.setTag("ediColum14");


        // holder.myCustomEditTextListener.updatePosition(holder.getAdapterPosition());


        /// holder.myCustomEditTextListener.getClass()
        //  holder.ediColum9.upDa

        //  holder.ediColum14.setText(StringlistSemns.get(holder.getAdapterPosition()));







        //  holder.imageview.setImageResource(ColorCintasSemns.geturiImage());
        holder.ediColum9.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {


                //probableent tambien guardar la descripcion en share prefrences...


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

        private TextInputEditText semnNum;

        private TextInputEditText ediColum9, ediColum10,ediColum11,ediColum12,ediColum13,ediColum14;


        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);



            semnNum = itemView.findViewById(R.id.semnNum);

            ediColum9 = itemView.findViewById(R.id.ediColum9);
            ediColum10 = itemView.findViewById(R.id.ediColum10);
            ediColum11 = itemView.findViewById(R.id.ediColum11);
            ediColum12 = itemView.findViewById(R.id.ediColum12);
            ediColum13 = itemView.findViewById(R.id.ediColum13);
            ediColum13 = itemView.findViewById(R.id.ediColum13);
            ediColum14 = itemView.findViewById(R.id.ediColum14);



        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(), view);

        }
    }


    public void setOnItemClickListener(ClickListener clickListener) {
        RecyclerViewAdapterColorCintSem.clickListener = clickListener;


    }


    public interface ClickListener {
        void onItemClick(int position, View v);


    }

private void checkExistFile(){

     /*
        if(Activi){


        }
*/


}



}
