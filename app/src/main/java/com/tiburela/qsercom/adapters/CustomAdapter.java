package com.tiburela.qsercom.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.tiburela.qsercom.R;
import com.tiburela.qsercom.models.ControlCalidad;

import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    public static ArrayList<ControlCalidad> listControLcalidad;
    private Context ctx;

    public CustomAdapter(Context ctx, ArrayList<ControlCalidad> listControLcalidad) {

        inflater = LayoutInflater.from(ctx);
        this.listControLcalidad = listControLcalidad;
        this.ctx = ctx;
   
    }

    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_control_cald_checkbx, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final CustomAdapter.MyViewHolder holder, int position) {

        holder.checkBx.setText("Control Calidad ");

        holder.checkBx.setChecked(listControLcalidad.get(position).isEstaCheckeed());

        holder.txtDataFirst.setText("FECHA:  "+listControLcalidad.get(position).getSimpleDate());

        holder.txtDataSecond.setText("HDA "+listControLcalidad.get(position).getHacienda());


        // holder.checkBx.setTag(R.integer.btnplusview, convertView);
        holder.checkBx.setTag(position);


        holder.checkBx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer pos = (Integer) holder.checkBx.getTag();
              //  Toast.makeText(ctx, listControLcalidad.get(pos).getAnimal() + " clicked!", Toast.LENGTH_SHORT).show();

                if (listControLcalidad.get(pos).isEstaCheckeed()) {
                    listControLcalidad.get(pos).setEstaCheckeed(false);
                } else {
                    listControLcalidad.get(pos).setEstaCheckeed(true);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return listControLcalidad.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        protected CheckBox checkBx;
        private TextView txtDataFirst;
        private TextView txtDataSecond;
        private ImageView imgSee;
        
        
        public MyViewHolder(View itemView) {
            super(itemView);

            checkBx = (CheckBox) itemView.findViewById(R.id.checkBx);
            txtDataFirst = (TextView) itemView.findViewById(R.id.txtDataFirst);
            txtDataSecond = (TextView) itemView.findViewById(R.id.txtDataSecond);
            imgSee = (ImageView) itemView.findViewById(R.id.imgSee);


        }

    } 



}