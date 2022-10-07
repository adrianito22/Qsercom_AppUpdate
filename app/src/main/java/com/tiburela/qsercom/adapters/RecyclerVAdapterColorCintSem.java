package com.tiburela.qsercom.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.models.ColorCintasSemns;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.utils.Utils;
import com.tiburela.qsercom.utils.Variables;

import java.util.ArrayList;

public class RecyclerVAdapterColorCintSem extends RecyclerView.Adapter<RecyclerVAdapterColorCintSem.RecyclerViewHolder>  implements   View.OnClickListener  {

   // private static ClickListener clickListener;

    private View.OnClickListener listener;

    private static ClickListener clickListener;


    private ArrayList<ColorCintasSemns> listSemns;
    private Context mcontext;
    private Activity activity;

    public RecyclerVAdapterColorCintSem(ArrayList<ColorCintasSemns> ColorCintasSemnsArrayList, Context mcontext,Activity activity) {
        this.listSemns = ColorCintasSemnsArrayList;
        this.mcontext = mcontext;

        this.activity = activity;

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

         //si el valor es diferente de 0..

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


        holder.myCustomEditTextListener.updatePosition(holder.getAdapterPosition());


       /// holder.myCustomEditTextListener.getClass()
      //  holder.ediColum9.upDa

     //  holder.ediColum14.setText(StringlistSemns.get(holder.getAdapterPosition()));




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


            this.ediColum9.addTextChangedListener(myCustomEditTextListener);
            this.ediColum10.addTextChangedListener(myCustomEditTextListener);
            this.ediColum11.addTextChangedListener(myCustomEditTextListener);
            this.ediColum12.addTextChangedListener(myCustomEditTextListener);
            this.ediColum13.addTextChangedListener(myCustomEditTextListener);
            this.ediColum14.addTextChangedListener(myCustomEditTextListener);


        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(), view);

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
        private String tag;
      //  Activity activity;



        public void updatePosition(int position) {
            this.position = position;
        }


        public void getTag(String tag) {
            this.tag = tag;
        }



        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            // no op
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

           // mDataset[position] = charSequence.toString();

            Log.i("eltex","el texto es "+charSequence.toString());

         String currentText=charSequence.toString();


View fockview=activity.getCurrentFocus();
if(fockview!=null){
    EditText ediColum14=(EditText) fockview.findViewById(R.id.ediColum14);
    EditText ediColum13=(EditText) fockview.findViewById(R.id.ediColum13);
    EditText ediColum12=(EditText) fockview.findViewById(R.id.ediColum12);
    EditText ediColum11=(EditText) fockview.findViewById(R.id.ediColum11);
    EditText ediColum10=(EditText) fockview.findViewById(R.id.ediColum10);
    EditText ediColum9=(EditText) fockview.findViewById(R.id.ediColum9);


    if(ediColum14!=null){

        ColorCintasSemns objec=listSemns.get(position);
        objec.setColumFieldNUm14(Integer.parseInt(currentText));
        Variables.mapColorCintasSemanas.put(listSemns.get(position).getUniqueId(),objec);

        String d=ediColum14.getTag().toString();
        Log.i("eltex","el position es : "+position);
    }


    if(ediColum13!=null){

        ColorCintasSemns objec=listSemns.get(position);
        objec.setColumFieldNUm13(Integer.parseInt(currentText));
        Variables.mapColorCintasSemanas.put(listSemns.get(position).getUniqueId(),objec);

        String d=ediColum13.getTag().toString();
        Log.i("eltex","el position es : "+position);
    }


    if(ediColum12!=null){

        ColorCintasSemns objec=listSemns.get(position);
        objec.setColumFieldNUm12(Integer.parseInt(currentText));
        Variables.mapColorCintasSemanas.put(listSemns.get(position).getUniqueId(),objec);
        Log.i("eltex","el position es : "+position);

    }


    if(ediColum11!=null){

        ColorCintasSemns objec=listSemns.get(position);
        objec.setColumFieldNUm11(Integer.parseInt(currentText));
        Variables.mapColorCintasSemanas.put(listSemns.get(position).getUniqueId(),objec);

        String d=ediColum11.getTag().toString();
        Log.i("eltex","el position es : "+position);

    }


    if(ediColum10!=null){
        Log.i("eltex","el position es : "+position);


        ColorCintasSemns objec=listSemns.get(position);
        objec.setColumFieldNUm10(Integer.parseInt(currentText));
        Variables.mapColorCintasSemanas.put(listSemns.get(position).getUniqueId(),objec);

        String d=ediColum10.getTag().toString();
        Log.i("eltex","el position es : "+position);
    }


    if(ediColum9!=null){
        String d=ediColum9.getTag().toString();

          ColorCintasSemns objec=listSemns.get(position);
          objec.setColumFieldNUm9(Integer.parseInt(currentText));
        Variables.mapColorCintasSemanas.put(listSemns.get(position).getUniqueId(),objec);

        Log.i("eltex","el position es : "+position);
    }




    //si el tag es este



}






        }

        @Override
        public void afterTextChanged(Editable editable) {
            // no op
        }
    }



}
