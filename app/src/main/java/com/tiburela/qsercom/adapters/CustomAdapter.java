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


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>  implements   View.OnClickListener  {
    private static ClickListener clickListener;
    private View.OnClickListener listener;

    private LayoutInflater inflater;
    public static ArrayList<CheckedAndAtatch> listCheckedAndAtatch;
    public static ArrayList<String> idsFormsControlCalidVinculados;

    public static String idsFormsVinucladosControlCalidadString;
    public static String idsFormsVinucladosCudorMuestreoString;

    public static HashMap<String,String> mapWhitIDScontrolCaldVinclds;
    public static HashMap<String,String> mapWhitIdsCuadroMuestreo;




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

        holder.checkBx.setText(listCheckedAndAtatch.get(position).getDataChecboxTxt());

        if(listCheckedAndAtatch.get(position).isItemChek()){

                holder.checkBx.setChecked(true);

        }



        else {

            holder.checkBx.setChecked(false);

        }



        holder.txtCodeHere.setText(listCheckedAndAtatch.get(position).getUniqueID());

if(listCheckedAndAtatch.get(position).getDataChecboxTxt().equals("Cuadro Muestreo")){

    holder.imgSee.setTag("Cuadro Muestreo");


}else{

    holder.imgSee.setTag("");



}


        Log.i("hsyeyrr","el size DE cuadr muestro es  "+mapWhitIdsCuadroMuestreo.size());



        String datexxx="FECHA "+listCheckedAndAtatch.get(position).getDataFirst()+" ";

        String hda="HDA: "+listCheckedAndAtatch.get(position).getDataSecond();


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

                //  Toast.makeText(ctx, listCheckedAndAtatch.get(pos).getAnimal() + " clicked!", Toast.LENGTH_SHORT).show();
              //  int adapterPosition = getAdapterPosition();

                Log.i("posicion","al posicioon es "+pos);

                //chekcamos que solo exista un objeto CUADROmUETREO OBJET...




                if (listCheckedAndAtatch.get(pos).isItemChek()) {





                    //el anterior estaba en cheked y ahora es falso....lo removemos
                    Log.i("somere","removemos");

                    listCheckedAndAtatch.get(pos).setItemChek(false);
                    //REMOVE VALUE OF HASMAP
                   // listCheckedAndAtatch.get(adapterPosition).setChecked(false);

                    if( tipoInforme.equals("Cuadro Muestreo")){
                        //es un cuadro de muestreo

                        Log.i("somere","elimnamos cuadro muestro");



                        if(mapWhitIdsCuadroMuestreo.containsKey(String.valueOf(v.getTag(R.id.idOfoBJECT)))) {
                            mapWhitIdsCuadroMuestreo.remove(String.valueOf(v.getTag(R.id.idOfoBJECT)));
                            Log.i("comerciales","contiene key y lo removemos y el size ahora es: "+ mapWhitIDScontrolCaldVinclds.size());
                            udpdateStringVinucldsReports("Cuadro Muestreo");

                        }

                    }



                    //quiere decir que del otro report
                    else{

                        Log.i("somere","elimnamos es control calidad report");


                        if(mapWhitIDScontrolCaldVinclds.containsKey(String.valueOf(v.getTag(R.id.idOfoBJECT)))) {
                            mapWhitIDScontrolCaldVinclds.remove(String.valueOf(v.getTag(R.id.idOfoBJECT)));

                            Log.i("hsyeyrr","asi contiene key eleomnamos y size ahora es "+ mapWhitIDScontrolCaldVinclds.size());

                            Log.i("hsyeyrr","el size de la otra map es "+ mapWhitIdsCuadroMuestreo.size());

                            Log.i("comerciales","contiene key y lo removemos y el size ahora es: "+ mapWhitIDScontrolCaldVinclds.size());
                            udpdateStringVinucldsReports("");

                        }else{


                            Log.i("hsyeyrr","no hay key no eliminamos ");

                        }


                    }







                } else {  ///si el cehckeed esta en falseo, lo marcamos




                    if(tipoInforme.equals("Cuadro Muestreo") && mapWhitIdsCuadroMuestreo.size()==1){ //si ya hay uno ...return
                        Toast.makeText(ctx, "Solo permitido un reporte Cuadro Muestreo", Toast.LENGTH_SHORT).show();
                        holder.checkBx.setChecked(false);

                      //  listCheckedAndAtatch.get(pos).setItemChek(false);

                        Log.i("sopillass","no mas cuadros muetreo");

                        return;

                    }


                    Variables.currentkEYcuadroMuetreo=idsFormsVinucladosCudorMuestreoString; //



                    listCheckedAndAtatch.get(pos).setItemChek(true);

                   ///AGREGAMOS DEPEDINEDO DE CUAL ES...

                    if(tipoInforme.equals("Cuadro Muestreo")){

                        Log.i("hsyeyrr","se ejecuto este es cuado muestreo");



                        mapWhitIdsCuadroMuestreo.put(String.valueOf(v.getTag(R.id.idOfoBJECT)),String.valueOf(v.getTag(R.id.idOfoBJECT))); //agregamos o quitamos este del hasmap..
                        udpdateStringVinucldsReports("Cuadro Muestreo");


                    }else{
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


        if(tipiInforme.equals("Cuadro Muestreo")){

            if(mapWhitIdsCuadroMuestreo.size() >0){


                Log.i("hsyeyrr","actualiamos idOFfORMSCuadroMuestreo y es mayor a cero y es  "+ mapWhitIdsCuadroMuestreo.size());


                StringJoiner joiner = new StringJoiner(",");

                //iteramos
                for(String value : mapWhitIdsCuadroMuestreo.values()){
                    //  Log.i("datamapitkka","el string valu es  : "+value);
                    joiner.add(value);
                    idsFormsVinucladosCudorMuestreoString = joiner.toString(); // lo convertimos en una linea de string sperad por comas.. id01,id02,
                }



                Log.i("hakuna","el idsFormsVinucladosCudorMuestreoString value  es "+  idsFormsVinucladosCudorMuestreoString);

                //eliminamos un control calidad y agregamos un muestro...


                updateNumReportsVinculadosALL(mapWhitIDScontrolCaldVinclds, mapWhitIdsCuadroMuestreo);



            }

            else{

                //significa que no hay ninguno vinculado
                // CustomAdapter.idsFormsVinucladosCntres);




                Log.i("hsyeyrr","actualiamos idOFfORMSCuadroMuestreo no es  mayor a cero y es  "+ mapWhitIdsCuadroMuestreo.size());




               idsFormsVinucladosCudorMuestreoString = null;
                Log.i("hakuna","el idsFormsVinucladosCudorMuestreoString value  es "+  idsFormsVinucladosCudorMuestreoString);

                updateNumReportsVinculadosALL(mapWhitIDScontrolCaldVinclds, mapWhitIdsCuadroMuestreo);

                // CustomAdapter.idOFfORMScontrolCaldVds =null;

            }


        }else{

            if(mapWhitIDScontrolCaldVinclds.size() >0){


                Log.i("hsyeyrr","actualiamos idOFfORMScontrolCaldVinclds si es  mayor a cero y es  "+ mapWhitIDScontrolCaldVinclds.size());
                Log.i("hsyeyrr","actualiamos idOFfORMScontrolCaldVinclds y el mapa de idOFfORMSCuadroMuestreo tien size : "+ mapWhitIdsCuadroMuestreo.size());


                StringJoiner joiner = new StringJoiner(",");

                //iteramos
                for(String value : mapWhitIDScontrolCaldVinclds.values()){
                    //  Log.i("datamapitkka","el string valu es  : "+value);
                    joiner.add(value);
                    idsFormsVinucladosControlCalidadString = joiner.toString(); // lo convertimos en una linea de string sperad por comas.. id01,id02,
                }

                Log.i("hakuna","el idsFormsVinucladosControlCalidadString value  es "+  idsFormsVinucladosControlCalidadString);


                updateNumReportsVinculadosALL(mapWhitIDScontrolCaldVinclds, mapWhitIdsCuadroMuestreo);


                Log.i("somere","el size de all rportes vunculados  es "+  Utils.numReportsVinculadsAll);
            }

            else{





                idsFormsVinucladosControlCalidadString = null;

                Log.i("hakuna","el idsFormsVinucladosControlCalidadString value  es "+  idsFormsVinucladosControlCalidadString);


                updateNumReportsVinculadosALL(mapWhitIDScontrolCaldVinclds, mapWhitIdsCuadroMuestreo);


                Log.i("somere","el size de all rportes vunculados  es "+  Utils.numReportsVinculadsAll);

                // CustomAdapter.idOFfORMScontrolCaldVds =null;

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
        CustomAdapter.clickListener = clickListener;


    }



    private void updateNumReportsVinculadosALL(HashMap<String,String> idOFfORMScontrolCaldVinclds
           , HashMap<String,String> idOFfORMSCuadroMuestreo){

        int valorMpa1;
        int valorMpa2;


        if(idOFfORMScontrolCaldVinclds==null){
            valorMpa1=0;
        }else{
            valorMpa1=idOFfORMScontrolCaldVinclds.size();
        }


        if(idOFfORMSCuadroMuestreo==null){
            valorMpa2=0;

        }else{
            valorMpa2=idOFfORMSCuadroMuestreo.size();
        }


        Utils.numReportsVinculadsAll=valorMpa1+valorMpa2;


        Log.i("somere","el size de all rportes vunculados  es "+  Utils.numReportsVinculadsAll);




    }

}