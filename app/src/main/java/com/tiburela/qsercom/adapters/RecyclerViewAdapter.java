package com.tiburela.qsercom.adapters;
import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.storage.StorageReference;
import com.tiburela.qsercom.activities.ActivitySeeReports;
import com.tiburela.qsercom.activities.PreviewActivity;
import com.tiburela.qsercom.models.ImagenReport;

import java.io.InputStream;
import java.util.ArrayList;

import com.tiburela.qsercom.R;
import com.tiburela.qsercom.storage.StorageData;
import com.tiburela.qsercom.utils.Variables;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>  implements   View.OnClickListener  {

   // private static ClickListener clickListener;

    private View.OnClickListener listener;

    private static ClickListener clickListener;


    private ArrayList<ImagenReport> listImagenData;
    private Context mcontext;

    public RecyclerViewAdapter(ArrayList<ImagenReport> imagenReportArrayList, Context mcontext) {
        this.listImagenData = imagenReportArrayList;
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
        ImagenReport imagenReport = listImagenData.get(position);
        holder.textImputEditext.setText(imagenReport.getDescripcionImagen());

        holder.imvClose.setTag(imagenReport.getUniqueIdNamePic());
                  //si uri existe
              Uri uri=null;


        boolean existFileInPhone = false;


            try {
                 uri=Uri.parse(imagenReport.geturiImage());

                InputStream inputStream = PreviewActivity.context.getContentResolver().openInputStream(uri);
                inputStream.close();
                existFileInPhone = true;

            }

            catch (Exception e) {
             //   Log.i("miuris", "File corresponding to the uri does not exist " + uri.toString());


            }




          if(existFileInPhone){
//    private void dowloadImagesAndaddTag(String imgPath, RecyclerViewHolder holder,String tag){

            //  Uri myUri = Uri.parse(listImagenData.get(position).geturiImage());
              holder.imageview.setImageURI(uri);

            //  holder.imvClose.setTag(imagenReport.getUniqueIdNamePic());
              Log.i("ladtastor","existe "+imagenReport.getUniqueIdNamePic());

          }

          else

          {  //es el modo de selecionar imagenes y tomar fotos con la camara
              Log.i("ladtastor","lo descragamos  "+imagenReport.getUniqueIdNamePic());

              dowloadImagesAndaddTag(imagenReport.getUniqueIdNamePic(), holder,imagenReport.getUniqueIdNamePic());

          }



      //  holder.imageview.setImageResource(imagenReport.geturiImage());
        holder.textImputEditext.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {


            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

               // String textOftextImputEditext = holder.textImputEditext.getText().toString();

              //  imagenReport.listImagesData.get(position).setDescripcionImagen(textOftextImputEditext);

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

                    if(!Variables.isClickable)
                        return;

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





    private void dowloadImagesAndaddTag(String imgPath, RecyclerViewHolder holder,String tag){

        StorageReference storageRef = StorageData.rootStorageReference.child("imagenes_all_reports/"+imgPath);

        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                Log.i("ladtastor","el id es succes");

                Glide.with(ActivitySeeReports.context)
                        .load(uri)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)         //ALL or NONE as your requirement
                        //.thumbnail(Glide.with(OfertsAdminActivity.context).load(R.drawable.enviado_icon))
                       // .error(R.drawable.)
                        .into(holder.imageview);


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                Log.i("ladtastor","es un fallo y es "+exception.getMessage());

                try{

                 //   Glide.with(ActivitySeeReports.context)
                            //.load(R.drawable.acea2)
                           // .fitCenter()
                           // .into(holder.imgViewLogoGIFTc);


                } catch (Exception e) {
                    e.printStackTrace();
                }



            }
        });






    }


}
