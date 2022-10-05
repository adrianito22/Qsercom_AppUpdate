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
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.utils.Utils;

import java.util.ArrayList;

public class RecyclerVAdapterColorCintSem extends RecyclerView.Adapter<RecyclerVAdapterColorCintSem.RecyclerViewHolder>  implements   View.OnClickListener  {

   // private static ClickListener clickListener;

    private View.OnClickListener listener;

    private static ClickListener clickListener;


    private ArrayList<ColorCintasSemns> listSemns;
    private Context mcontext;

    public RecyclerVAdapterColorCintSem(ArrayList<ColorCintasSemns> ColorCintasSemnsArrayList, Context mcontext) {
        this.listSemns = ColorCintasSemnsArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_color_cintas_semana, parent, false);
        view.setOnClickListener(this);

        return new RecyclerViewHolder(view,new MyCustomEditTextListener());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // Set the data to textview and imageview.





        ColorCintasSemns objectCurrent = listSemns.get(position);

        holder.semnNum.setText(objectCurrent.getSemanNum());
        holder.ediColum9.setText(objectCurrent.getColumFieldNUm9());
        holder.ediColum10.setText(objectCurrent.getColumFieldNUm10());
        holder.ediColum11.setText(objectCurrent.getColumFieldNUm11());
        holder.ediColum12.setText(objectCurrent.getColumFieldNUm12());
        holder.ediColum13.setText(objectCurrent.getColumFieldNUm13());
        holder.ediColum14.setText(objectCurrent.getColumFieldNUm14());

        //ad atags
        holder.semnNum.setTag(objectCurrent.getUniqueId());
        holder.ediColum9.setTag(objectCurrent.getUniqueId());
        holder.ediColum10.setTag(objectCurrent.getUniqueId());
        holder.ediColum11.setTag(objectCurrent.getUniqueId());
        holder.ediColum12.setTag(objectCurrent.getUniqueId());
        holder.ediColum13.setTag(objectCurrent.getUniqueId());
        holder.ediColum14.setTag(objectCurrent.getUniqueId());


        holder.myCustomEditTextListener.updatePosition(holder.getAdapterPosition());
     //   holder.ediColum14.setText(StringlistSemns.get(holder.getAdapterPosition()));




    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return listSemns.size();
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

        MyCustomEditTextListener  myCustomEditTextListener;


        public RecyclerViewHolder(@NonNull View itemView,MyCustomEditTextListener myCustomEditTextListener) {
            super(itemView);

            this.myCustomEditTextListener = myCustomEditTextListener;


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


        void enableTextWatcher() {
            ediColum9.addTextChangedListener(myCustomEditTextListener);
        }

        void disableTextWatcher() {
            ediColum9.removeTextChangedListener(myCustomEditTextListener);
        }




    }


    public void setOnItemClickListener(ClickListener clickListener) {
        RecyclerVAdapterColorCintSem.clickListener = clickListener;


    }


    public interface ClickListener {
        void onItemClick(int position, View v);


    }





    private class MyCustomEditTextListener implements TextWatcher {
        private int position;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            // no op
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

           // mDataset[position] = charSequence.toString();


        }

        @Override
        public void afterTextChanged(Editable editable) {
            // no op
        }
    }



}
