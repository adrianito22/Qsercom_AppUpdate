package com.tiburela.qsercom.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tiburela.qsercom.R;
import com.tiburela.qsercom.activities.formularios.ActivityContenedores;
import com.tiburela.qsercom.activities.othersActivits.ActivityMenu;
import com.tiburela.qsercom.callbacks.CallbackUpdateNumsRepVincls;
import com.tiburela.qsercom.models.CheckedAndAtatch;
import com.tiburela.qsercom.utils.Utils;
import com.tiburela.qsercom.utils.Variables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringJoiner;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>  implements   View.OnClickListener  {
    private static ClickListener clickListener;
    private View.OnClickListener listener;

    private LayoutInflater inflater;
    public static ArrayList<CheckedAndAtatch> listCheckedAndAtatch;
    public static ArrayList<String> idsFormsControlCalidVinculados;

    public static String idsFormsVinucladosCntres;
    public static HashMap<String,String>idOFfORMScontrolCaldVds=new HashMap<>();


    private Context ctx;

    public CustomAdapter(Context ctx, ArrayList<CheckedAndAtatch> listCheckedAndAtatch,ArrayList<String >idsFormsControlCalidVinculados) {

        inflater = LayoutInflater.from(ctx);
        this.listCheckedAndAtatch = listCheckedAndAtatch;
        this.ctx = ctx;
        this.idsFormsControlCalidVinculados=idsFormsControlCalidVinculados;
   
    }

    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_control_cald_checkbx, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener(this);

        return holder;
    }

    @Override
    public void onBindViewHolder(final CustomAdapter.MyViewHolder holder, int position) {

        holder.checkBx.setText("Control Calidad ");


        if(listCheckedAndAtatch.get(position).isItemChek()){

                holder.checkBx.setChecked(true);


        }else {

            holder.checkBx.setChecked(false);


        }



        holder.txtDataFirst.setText("FECHA:  "+listCheckedAndAtatch.get(position).getDataFirst());

        holder.txtDataSecond.setText("HDA "+listCheckedAndAtatch.get(position).getDataSecond());

        // holder.checkBx.setTag(R.integer.btnplusview, convertView);
      //  holder.checkBx.setTag(position,"");

        holder.checkBx.setTag(R.id.posicion,position);
        holder.checkBx.setTag(R.id.idOfoBJECT,listCheckedAndAtatch.get(position).getUniqueID());



        holder.checkBx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer pos = (Integer) holder.checkBx.getTag(R.id.posicion);
              //  Toast.makeText(ctx, listCheckedAndAtatch.get(pos).getAnimal() + " clicked!", Toast.LENGTH_SHORT).show();
              //  int adapterPosition = getAdapterPosition();

                Log.i("posicion","al posicioon es "+pos);

                if (listCheckedAndAtatch.get(pos).isItemChek()) {
                    //el anterior estaba en cheked y ahora es falso....lo removemos

                    Log.i("datamapitkka","removemos");


                    listCheckedAndAtatch.get(pos).setItemChek(false);
                    //REMOVE VALUE OF HASMAP

                   // listCheckedAndAtatch.get(adapterPosition).setChecked(false);


                    if(idOFfORMScontrolCaldVds.containsKey(String.valueOf(v.getTag(R.id.idOfoBJECT)))) {
                        idOFfORMScontrolCaldVds.remove(String.valueOf(v.getTag(R.id.idOfoBJECT)));

                        Log.i("comerdd","contiene y lo removemos y el size ahora es: "+idOFfORMScontrolCaldVds.size());
                        udpdate();

                    }else {

                        Log.i("datamapitkka","no hay nada que quitar");

                    }




                } else {
                    Log.i("comerdd","agregamos");

                    listCheckedAndAtatch.get(pos).setItemChek(true);


                    idOFfORMScontrolCaldVds.put(String.valueOf(v.getTag(R.id.idOfoBJECT)),String.valueOf(v.getTag(R.id.idOfoBJECT))); //agregamos o quitamos este del hasmap..

                    udpdate();

                }
            }
        });


    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }


    @Override
    public int getItemCount() {
        return listCheckedAndAtatch.size();
    }

    @Override
    public void onClick(View view) {


        if (listener!=null){
            listener.onClick(view);
        }


    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected CheckBox checkBx;
        private TextView txtDataFirst;
        private TextView txtDataSecond;
        private ImageView imgSee;
        
        
        public MyViewHolder(View itemView) {
            super(itemView);

            checkBx = (CheckBox) itemView.findViewById(R.id.checkBx);
            txtDataFirst = (TextView) itemView.findViewById(R.id.txtDataFirst);
            txtDataSecond = (TextView) itemView.findViewById(R.id.txtDataSecond);
            imgSee =  itemView.findViewById(R.id.imgSee);

            imgSee.findViewById(R.id.imgSee).setOnClickListener(new View.OnClickListener() {
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

void udpdate() {



    for(CheckedAndAtatch value : listCheckedAndAtatch){
        Log.i("comerdd","itrando control calida list ye s "+value.isItemChek());


    }

        //construimos un string a partir del map

    if(CustomAdapter.idOFfORMScontrolCaldVds.size() >0){

        Log.i("comerdd","hay alñmenos 1 vinculado");

        StringJoiner joiner = new StringJoiner(",");


        //iteramos
        for(String value : CustomAdapter.idOFfORMScontrolCaldVds.values()){
            Log.i("datamapitkka","el string valu es  : "+value);
            joiner.add(value);
            CustomAdapter.idsFormsVinucladosCntres = joiner.toString(); // "01,02,03"
        }





      //  callbackUpdateNumsRepVincls.updateReportsVincyulados(CustomAdapter.idOFfORMScontrolCaldVds.size());

        Utils.numReportsVinculads=CustomAdapter.idOFfORMScontrolCaldVds.size();

        Log.i("datamapitkka","el text final es : "+ CustomAdapter.idsFormsVinucladosCntres);

    }



    else{ //significa que no hay ninguno vinculado
        // CustomAdapter.idsFormsVinucladosCntres);

        Utils.numReportsVinculads=0;


        Log.i("comerdd","no hay ninguno vinculado");

        CustomAdapter.idsFormsVinucladosCntres = null;

        // CustomAdapter.idOFfORMScontrolCaldVds =null;

    }

}

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);


    }
    public void setOnItemClickListener(ClickListener clickListener) {
        CustomAdapter.clickListener = clickListener;


    }


}