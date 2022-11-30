package com.tiburela.qsercom.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
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
import com.tiburela.qsercom.SharePref.SharePref;
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
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview and imageview.

        ColorCintasSemns objectCurrent = listSemns.get(position);
         //si el valor es diferente de 0..
        Log.i("adinaiot","la posicion en obinbiewholder "+position);


        //holder.ediColum9.setTag(position);
        holder.ediColum9.setTag(R.id.ediColum9, position);
        holder.ediColum10.setTag(R.id.ediColum10, position);
        holder.ediColum11.setTag(R.id.ediColum11, position);
        holder.ediColum12.setTag(R.id.ediColum12, position);
        holder.ediColum13.setTag(R.id.ediColum13, position);
        holder.ediColum14.setTag(R.id.ediColum14, position);


        holder.ediColum9.addTextChangedListener(new MyCustomEditTextListener(position,holder.ediColum9));
        holder.ediColum10.addTextChangedListener(new MyCustomEditTextListener(position,holder.ediColum10));
        holder.ediColum11.addTextChangedListener(new MyCustomEditTextListener(position,holder.ediColum11));
        holder.ediColum12.addTextChangedListener(new MyCustomEditTextListener(position,holder.ediColum12));
        holder.ediColum13.addTextChangedListener(new MyCustomEditTextListener(position,holder.ediColum13));
        holder.ediColum14.addTextChangedListener(new MyCustomEditTextListener(position,holder.ediColum14));


       // holder.ediColum9.addTextChangedListener(new MyCustomEditTextListener(position));
    //    holder.ediColum10.addTextChangedListener(new MyCustomEditTextListener(position));
        String keyCurrent="";


        if(Variables.hayUnFormIcompletoTOrEYCLER){
            if(SharePref.mihashMapFieldsToRecycler.containsKey(position+","+holder.ediColum9.getId())){
                keyCurrent=position+","+holder.ediColum9.getId();

                holder.ediColum9.setText(SharePref.mihashMapFieldsToRecycler.get(keyCurrent));
            }



            if(SharePref.mihashMapFieldsToRecycler.containsKey(position+","+holder.ediColum10.getId())){

                keyCurrent=position+","+holder.ediColum10.getId();

                holder.ediColum10.setText(SharePref.mihashMapFieldsToRecycler.get(keyCurrent));
            }




            if(SharePref.mihashMapFieldsToRecycler.containsKey(position+","+holder.ediColum11.getId())){

                keyCurrent=position+","+holder.ediColum11.getId();

                holder.ediColum11.setText(SharePref.mihashMapFieldsToRecycler.get(keyCurrent));
            }



            if(SharePref.mihashMapFieldsToRecycler.containsKey(position+","+holder.ediColum12.getId())){

                keyCurrent=position+","+holder.ediColum12.getId();

                holder.ediColum12.setText(SharePref.mihashMapFieldsToRecycler.get(keyCurrent));
            }



            if(SharePref.mihashMapFieldsToRecycler.containsKey(position+","+holder.ediColum13.getId())){

                keyCurrent=position+","+holder.ediColum13.getId();

                holder.ediColum13.setText(SharePref.mihashMapFieldsToRecycler.get(keyCurrent));
            }



            if(SharePref.mihashMapFieldsToRecycler.containsKey(position+","+holder.ediColum14.getId())){

                keyCurrent=position+","+holder.ediColum14.getId();

                holder.ediColum14.setText(SharePref.mihashMapFieldsToRecycler.get(keyCurrent));
            }



             }



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
        holder.semnNum.setTag(objectCurrent.getUniqueId());


        //ad atags
       // holder.semnNum.setTag("semnNum");
     //   holder.ediColum9.setTag("ediColum9");
    //    holder.ediColum10.setTag("ediColum10");
    //    holder.ediColum11.setTag("ediColum11");
     //   holder.ediColum12.setTag("ediColum12");
     //   holder.ediColum13.setTag("ediColum13");
      //  holder.ediColum14.setTag("ediColum14");


       // holder.myCustomEditTextListener.updatePosition(holder.getAdapterPosition());


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

        public TextInputEditText semnNum;

        public TextInputEditText ediColum9, ediColum10,ediColum11,ediColum12,ediColum13,ediColum14;

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
            ediColum14 = itemView.findViewById(R.id.ediColum14);

          //  MyCustomEditTextListener textWatcher9 =  new MyCustomEditTextListener(ediColum9);
          //  MyCustomEditTextListener textWatcher10 = new MyCustomEditTextListener(ediColum10);
         //   MyCustomEditTextListener textWatcher11 = new MyCustomEditTextListener(ediColum11);
           // MyCustomEditTextListener textWatcher12 = new MyCustomEditTextListener(ediColum12);
          // MyCustomEditTextListener textWatcher13 = new MyCustomEditTextListener(ediColum13);
          //  MyCustomEditTextListener textWatcher14 = new MyCustomEditTextListener(ediColum14);


// (EditText editMyCustomEditTextListenerText)
           // this.ediColum9.addTextChangedListener(new myCustomEditTextListener(ediColum13));


          ///  this.ediColum9.addTextChangedListener(textWatcher9);
           // ediColum10.addTextChangedListener(textWatcher10);
           // ediColum11.addTextChangedListener(textWatcher11);
           // ediColum12.addTextChangedListener(textWatcher12);
          //  ediColum13.addTextChangedListener(textWatcher13);
          //  ediColum14.addTextChangedListener(textWatcher14);

/*
            //  this.ediColum9.addTextChangedListener(myCustomEditTextListener);
            this.ediColum10.addTextChangedListener(myCustomEditTextListener);
            this.ediColum11.addTextChangedListener(myCustomEditTextListener);
            this.ediColum12.addTextChangedListener(myCustomEditTextListener);
            this.ediColum13.addTextChangedListener(myCustomEditTextListener);
            this.ediColum14.addTextChangedListener(myCustomEditTextListener);
     */


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
       // private EditText editText;

        private EditText editTextx;

        private int mPos;

        public MyCustomEditTextListener(int position,EditText editText) {
            editTextx=editText;
            mPos = position;
        }


        public MyCustomEditTextListener(EditText editText) {
            this.editTextx = editText;
        }

        public MyCustomEditTextListener() {
        }

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
          //  int position = (int) editText.getTag();

            try {

                int position9 = (int) editTextx.getTag(R.id.ediColum9);
                int position10 = (int) editTextx.getTag(R.id.ediColum10);
                int position11 = (int) editTextx.getTag(R.id.ediColum11);
                int position12 = (int) editTextx.getTag(R.id.ediColum12);
                int position13 = (int) editTextx.getTag(R.id.ediColum13);
                int position14 = (int) editTextx.getTag(R.id.ediColum14);

                Log.i("eltex","el texto position 9 y el tag es es "+position9+" "+editTextx.getTag());
                Log.i("eltex","el texto position 10 es "+position10);
                Log.i("eltex","el texto position 11 es "+position11);
                Log.i("eltex","el texto position 12 es "+position12);
                Log.i("eltex","el texto position 13 es "+position13);
                Log.i("eltex","el texto position 14 es "+position14);


            } catch (Exception e) {

                e.printStackTrace();

                Log.i("eltex","el problem es "+e.getMessage());

            }


          //  Log.i("eltex","el texto es "+charSequence.toString());

            String currentText=charSequence.toString();


         if(!Utils.isNumeric(currentText)) {

             return;

         }


      //    Log.i("adinaiot","la posici tiene tag  "+editTextx.getId());
       ///   Log.i("adinaiot","la posicion methjod 2 e  "+mPos);
           // Log.i("adinaiot","la posicion en obinbiewholder "+position);

            //aqui va el terxto


            Log.i("adinaiot","la posici tiene tag  "+editTextx.getId());
            Log.i("adinaiot","la posicion methjod 2 e  "+mPos);

            SharePref.mihashMapFieldsToRecycler.put(String.valueOf(mPos+","+editTextx.getId()),currentText);

            SharePref.saveMapPreferFields(SharePref.mihashMapFieldsToRecycler,SharePref.KEY_CUADRO_MUESTRA_CALIB_RECHAZDS);
            //Variables.mapColorCintasSemanas.put();

             //lo guardamos...




            //aqui el valro




           // ColorCintasSemns objec=listSemns.get(position);
//View fockview=activity.getCurrentFocus();


        }

        @Override
        public void afterTextChanged(Editable editable) {
            // no op
        }
    }






}
