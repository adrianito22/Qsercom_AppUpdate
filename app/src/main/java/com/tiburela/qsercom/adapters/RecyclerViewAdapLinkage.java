package com.tiburela.qsercom.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.tiburela.qsercom.R;
import com.tiburela.qsercom.models.CheckedAndAtatch;
import com.tiburela.qsercom.utils.Utils;
import com.tiburela.qsercom.utils.Variables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringJoiner;


public class RecyclerViewAdapLinkage extends RecyclerView.Adapter<RecyclerViewAdapLinkage.MyViewHolder>  implements   View.OnClickListener  {
    private static ClickListener clickListener;
    private View.OnClickListener listener;

    private LayoutInflater inflater;
    public static ArrayList<CheckedAndAtatch> listCheckedAndAtatch;

    public static String idsFormsVinucladosControlCalidadString="";
    public static String idCudroMuestreoStringVinuclado ="";
    public static HashMap<String,String> mapWhitIDScontrolCaldVinclds=new HashMap<>();
    public static HashMap<String,String> mapWhitIdsCuadroMuestreo=new HashMap<>();




    private Context ctx;

    public RecyclerViewAdapLinkage(Context ctx, ArrayList<CheckedAndAtatch> listCheckedAndAtatch) {

        try {

            inflater = LayoutInflater.from(ctx);
            this.listCheckedAndAtatch = listCheckedAndAtatch;
            this.ctx = ctx;

        } catch (Exception e) {
            e.printStackTrace();
        }


   
    }



    @Override
    public RecyclerViewAdapLinkage.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_control_cald_checkbx, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener(this);

        return holder;
    }





    @Override
    public void onBindViewHolder(final RecyclerViewAdapLinkage.MyViewHolder holder, int position) {

        holder.checkBx.setText(listCheckedAndAtatch.get(position).getDataChecboxTxt());


        holder.imgSee.setTag(R.id.tagImgUniqueIdItem,listCheckedAndAtatch.get(position).getUniqueID());

        Log.i("samerr", "el value de ag es "+listCheckedAndAtatch.get(position).getUniqueID());



        if(listCheckedAndAtatch.get(position).isItemChek()){

                holder.checkBx.setChecked(true);

        }



        else {

            holder.checkBx.setChecked(false);

        }



        holder.txtCodeHere.setText(listCheckedAndAtatch.get(position).getUniqueID());

if(listCheckedAndAtatch.get(position).getDataChecboxTxt().equalsIgnoreCase("CUADRO MUESTREO")){

    holder.imgSee.setTag(R.id.tagImgCategory,"CUADRO MUESTREO");






}else{

    holder.imgSee.setTag(R.id.tagImgCategory,"");



}


        Log.i("hsyeyrr","el size DE cuadr muestro es  "+mapWhitIdsCuadroMuestreo.size());



        String datexxx="FECHA "+listCheckedAndAtatch.get(position).getDataFirst()+" ";

        String hda="De : "+listCheckedAndAtatch.get(position).getDataSecond();


        Log.i("datasg","date es "+datexxx);
        Log.i("datasg","HDA es "+hda);

        holder.txtDataFirst.setText(datexxx);
        holder.txtDataSecond.setText(hda);


        // holder.checkBx.setTag(R.integer.btnplusview, convertView);
      //  holder.checkBx.setTag(position,"");

        holder.checkBx.setTag(R.id.posicion,position);
        holder.checkBx.setTag(R.id.idOfoBJECT,listCheckedAndAtatch.get(position).getUniqueID());
        holder.checkBx.setTag(R.id.tipoInforme,listCheckedAndAtatch.get(position).getDataChecboxTxt() );


        holder.checkBx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer pos = (Integer) holder.checkBx.getTag(R.id.posicion);

                String tipoInforme = (String) holder.checkBx.getTag(R.id.tipoInforme);


                if (listCheckedAndAtatch.get(pos).isItemChek()) {

                    listCheckedAndAtatch.get(pos).setItemChek(false);
                    //REMOVE VALUE OF HASMAP
                   // listCheckedAndAtatch.get(adapterPosition).setChecked(false);

                    if( tipoInforme.equals("CUADRO MUESTREO")){

                        if(mapWhitIdsCuadroMuestreo.containsKey(String.valueOf(v.getTag(R.id.idOfoBJECT)))) {
                            mapWhitIdsCuadroMuestreo.remove(String.valueOf(v.getTag(R.id.idOfoBJECT)));
                            Log.i("comerciales","contiene key y lo removemos y el size ahora es: "+ mapWhitIDScontrolCaldVinclds.size());
                            udpdateStringVinucldsReports("CUADRO MUESTREO");

                        }

                    }



                    //quiere decir que del otro report
                    else{

                        Log.i("somere","elimnamos es control calidad report");


                        if(mapWhitIDScontrolCaldVinclds.containsKey(String.valueOf(v.getTag(R.id.idOfoBJECT)))) {
                            mapWhitIDScontrolCaldVinclds.remove(String.valueOf(v.getTag(R.id.idOfoBJECT)));



                            Log.i("comerciales","contiene key y lo removemos y el size ahora es: "+ mapWhitIDScontrolCaldVinclds.size());
                            udpdateStringVinucldsReports("");

                        }else{


                            Log.i("hsyeyrr","no hay key no eliminamos ");

                        }


                    }







                } else {  ///si el cehckeed esta en falseo, lo marcamos


                    if(tipoInforme.equals("CUADRO MUESTREO") && mapWhitIdsCuadroMuestreo.size()==1){ //si ya hay uno ...return
                        Toast.makeText(ctx, "Solo permitido un reporte Cuadro Muestreo", Toast.LENGTH_SHORT).show();
                        holder.checkBx.setChecked(false);

                      //  listCheckedAndAtatch.get(pos).setItemChek(false);

                        Log.i("sopillass","no mas cuadros muetreo");

                        return;

                    }


                    Variables.currentkEYcuadroMuetreo= idCudroMuestreoStringVinuclado; //



                    listCheckedAndAtatch.get(pos).setItemChek(true);

                   ///AGREGAMOS DEPEDINEDO DE CUAL ES...

                    if(tipoInforme.equals("CUADRO MUESTREO")){

                        Log.i("hsyeyrr","se ejecuto este es cuado muestreo");



                        mapWhitIdsCuadroMuestreo.put(String.valueOf(v.getTag(R.id.idOfoBJECT)),String.valueOf(v.getTag(R.id.idOfoBJECT))); //agregamos o quitamos este del hasmap..
                        udpdateStringVinucldsReports("CUADRO MUESTREO");


                    }else{

                        Log.i("debfggf"," sellamo el else aqui actualiamos mapWhitIDScontrolCaldVinclds y es mayor a cero y es  "+ mapWhitIDScontrolCaldVinclds.size());

                        Log.i("debfggf"," sellamo el else aqui actualiamos mapWhitIdsCuadroMuestreo es  "+ mapWhitIdsCuadroMuestreo.size());


                        mapWhitIDScontrolCaldVinclds.put(String.valueOf(v.getTag(R.id.idOfoBJECT)),String.valueOf(v.getTag(R.id.idOfoBJECT))); //agregamos o quitamos este del hasmap..
                        udpdateStringVinucldsReports("");


                    }




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
        TextView txtCodeHere;
        
        public MyViewHolder(View itemView) {
            super(itemView);

            checkBx = (CheckBox) itemView.findViewById(R.id.checkBx);
            txtDataFirst =  itemView.findViewById(R.id.txtDataFirstx);
            txtDataSecond =  itemView.findViewById(R.id.txtDataSecond);
            imgSee =  itemView.findViewById(R.id.imgSee);
            txtCodeHere=itemView.findViewById(R.id.txtCodeHere);
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



void udpdateStringVinucldsReports(String tipiInforme) {


        if(tipiInforme.equals("CUADRO MUESTREO")){

            if(mapWhitIdsCuadroMuestreo.size() >0){

                Log.i("comercial","es cuadro muestreo mayor a cero ");


                //  mapWhitIDScontrolCaldVinclds

                StringJoiner joiner = new StringJoiner(",");

                //iteramos
                for(String value : mapWhitIdsCuadroMuestreo.values()){
                    //  Log.i("datamapitkka","el string valu es  : "+value);
                    if(!value.trim().isEmpty()){
                        joiner.add(value);

                    }

                    idCudroMuestreoStringVinuclado = joiner.toString(); // lo convertimos en una linea de string sperad por comas.. id01,id02,
                }

                Log.i("debfggf","el string generado idCudroMuestreoStringVinuclado  es "+ idCudroMuestreoStringVinuclado);



                //eliminamos un control calidad y agregamos un muestro...


                updateNumReportsVinculadosALL(mapWhitIDScontrolCaldVinclds, mapWhitIdsCuadroMuestreo);



            }

            else{

                //significa que no hay ninguno vinculado
                // RecyclerViewAdapLinkage.idsFormsVinucladosCntres);

                Log.i("comercial","es cuadro muestreo se eejxuto el else ");


               idCudroMuestreoStringVinuclado = "";

                updateNumReportsVinculadosALL(mapWhitIDScontrolCaldVinclds, mapWhitIdsCuadroMuestreo);

                // RecyclerViewAdapLinkage.idOFfORMScontrolCaldVds =null;

            }


        }else{

            if(mapWhitIDScontrolCaldVinclds.size() >0){


                Log.i("hsyeyrr","actualiamos idOFfORMScontrolCaldVinclds si es  mayor a cero y es  "+ mapWhitIDScontrolCaldVinclds.size());
                Log.i("hsyeyrr","actualiamos idOFfORMScontrolCaldVinclds y el mapa de idOFfORMSCuadroMuestreo tien size : "+ mapWhitIdsCuadroMuestreo.size());


                StringJoiner joiner = new StringJoiner(",");

                //iteramos
                for(String value : mapWhitIDScontrolCaldVinclds.values()){
                    //  Log.i("datamapitkka","el string valu es  : "+value);

                    if(!value.trim().isEmpty()){
                        joiner.add(value);

                    }

                    idsFormsVinucladosControlCalidadString = joiner.toString(); // lo convertimos en una linea de string sperad por comas.. id01,id02,
                }

                Log.i("hakuna","el idsFormsVinucladosControlCalidadString value  es "+  idsFormsVinucladosControlCalidadString);


                updateNumReportsVinculadosALL(mapWhitIDScontrolCaldVinclds, mapWhitIdsCuadroMuestreo);


                Log.i("somere","el size de all rportes vunculados  es "+  Utils.numReportsVinculadsAll);
            }

            else{





                idsFormsVinucladosControlCalidadString = "";

                Log.i("hakuna","el idsFormsVinucladosControlCalidadString value  es "+  idsFormsVinucladosControlCalidadString);


                updateNumReportsVinculadosALL(mapWhitIDScontrolCaldVinclds, mapWhitIdsCuadroMuestreo);


                Log.i("somere","el size de all rportes vunculados  es "+  Utils.numReportsVinculadsAll);

                // RecyclerViewAdapLinkage.idOFfORMScontrolCaldVds =null;

            }



        }


        //construimos un string a partir del map


}

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);


    }
    public void setOnItemClickListener(ClickListener clickListener) {
        RecyclerViewAdapLinkage.clickListener = clickListener;


    }



    private void updateNumReportsVinculadosALL(HashMap<String,String> idOFfORMScontrolCaldVinclds
           , HashMap<String,String> idOFfORMSCuadroMuestreo){

        int valorMpa1=0;
        int valorMpa2=0;


        if(idOFfORMScontrolCaldVinclds.size()>0){
            valorMpa1=idOFfORMScontrolCaldVinclds.size();

        }


        if(idOFfORMSCuadroMuestreo.size()>0){
            valorMpa2=idOFfORMSCuadroMuestreo.size();

        }



        Utils.numReportsVinculadsAll=valorMpa1+valorMpa2;

        Log.i("SIZEALLLS","el size de all rportes vunculados  es "+  Utils.numReportsVinculadsAll);


        ////////
        Log.i("somere","el size de map 1  es "+  idOFfORMScontrolCaldVinclds.size());
        Log.i("somere","el size de map2   es "+  idOFfORMSCuadroMuestreo.size());


    }

}