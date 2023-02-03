package com.tiburela.qsercom.adapters;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
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
import com.tiburela.qsercom.activities.formularios.ActivityContersEnAcopio;
import com.tiburela.qsercom.activities.formularios.ActivityContenedores;
import com.tiburela.qsercom.activities.formulariosPrev.ActivityContenedoresPrev;
import com.tiburela.qsercom.activities.formulariosPrev.PreviewCalidadCamionesyCarretas;
import com.tiburela.qsercom.activities.formulariosPrev.PreviewsFormDatSContersEnAc;
import com.tiburela.qsercom.activities.formularios.ActivityReporteCalidadCamionesyCarretas;
import com.tiburela.qsercom.models.ImagenReport;

import java.io.InputStream;
import java.util.ArrayList;

import com.tiburela.qsercom.R;
import com.tiburela.qsercom.storage.StorageData;
import com.tiburela.qsercom.utils.Utils;
import com.tiburela.qsercom.utils.Variables;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>  implements   View.OnClickListener  {

    private View.OnClickListener listener;
    private static ClickListener clickListener;
    Bitmap currentBitmP;

    StorageReference storageRef;

    private ArrayList<ImagenReport> listImagenData;
    private Context mcontext;

    public RecyclerViewAdapter(ArrayList<ImagenReport> imagenReportArrayList, Context mcontext) { //si no fuunciona este contexto lo obtenmos con getaplicarion context
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
        holder.textImputEditext.setTag(imagenReport.getUniqueIdNamePic());

        holder.imvClose.setTag(imagenReport.getUniqueIdNamePic());

        Log.i("mispiggi","el size de la  lists  hashMapImagesData HERE SARECICLER  es  es "+ ImagenReport.hashMapImagesData.size());



        //si hay descripcion en las imagenes le agregamos..
        if(imagenReport.getDescripcionImagen().length()>1){
            holder.textImputEditext.setText(imagenReport.getDescripcionImagen());
        }

         Log.i("mispiggi","el tag es "+imagenReport.getUniqueIdNamePic());

                  //si uri existe
              Uri uri=null;


        boolean existFileInPhone = false;


            try {
                 uri=Uri.parse(imagenReport.geturiImage());
                InputStream inputStream=null;


                if(Variables.activityCurrent==Variables.FormatDatsContAcopi){
                    inputStream = ActivityContersEnAcopio.context.getContentResolver().openInputStream(uri);

                }



                else if(Variables.activityCurrent==Variables.FormContenedores) { //si es CONTENEDORES

                    Log.i("sabumaa","el uri de contendores es  "+uri);

                    inputStream = ActivityContenedores.context.getContentResolver().openInputStream(uri);


                    Log.i("sabumaa","nnnnmmm dkdkd");


                }


                else if(Variables.activityCurrent==Variables.FormPreviewContenedores){

                    inputStream = ActivityContenedoresPrev.context.getContentResolver().openInputStream(uri);

                }

                else if(Variables.activityCurrent==Variables.FormatDatsContAcopiPREVIEW){

                    inputStream = PreviewsFormDatSContersEnAc.context.getContentResolver().openInputStream(uri);

                }



                else if(Variables.activityCurrent==Variables.FormCamionesyCarretasActivity){

                    inputStream = ActivityReporteCalidadCamionesyCarretas.context.getContentResolver().openInputStream(uri);

                }



                else if(Variables.activityCurrent==Variables.FormCamionesyCarretasActivityPreview){

                    inputStream = PreviewCalidadCamionesyCarretas.context.getContentResolver().openInputStream(uri);

                }


                Log.i("sabumaa","por aqui lo ponemos en true");


                inputStream.close();
                existFileInPhone = true;

            }

            catch (Exception e) {
             //   Log.i("miuris", "File corresponding to the uri does not exist " + uri.toString());


            }




          if(existFileInPhone){

              //    private void dowloadImagesAndaddTag(String imgPath, RecyclerViewHolder holder,String tag){

              Log.i("cancionx","existe file in the phone");


              //  Uri myUri = Uri.parse(listImagenData.get(position).geturiImage());
              holder.imageview.setImageURI(uri);

            //  holder.imvClose.setTag(imagenReport.getUniqueIdNamePic());
              Log.i("ladtastor","existe "+imagenReport.getUniqueIdNamePic());

          }

          else {

              Log.i("cancionx","se ejecto el else ");

                   //File imgFile = new  File(imagenReport.getImagenPathNow());


                  // Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

              if(Variables.activityCurrent==Variables.FormPreviewContenedores ||  //es descrgada de de la base de datos...
                      Variables.activityCurrent==Variables.FormatDatsContAcopiPREVIEW||
                      Variables.activityCurrent==Variables.FormCamionesyCarretasActivityPreview){//

                  Log.i("cancionx","se ejecto este iff aqui ");


                  if(ImagenReport.hashMapImagesData.containsKey(imagenReport.getUniqueIdNamePic())) {

                      dowloadAndSetImg(imagenReport, holder.imageview,mcontext);

                      //  holder.imageview.setImageBitmap(  HelperImage.generateBitmapTumbail(currentBitmP));

                  }

              }else{  //es gaurdada localemente

                  Log.i("cancionx","se ejecto este else al final  ");


                  try{

                         holder.imageview.setImageURI(uri);

                  } catch (Exception e) {

                      e.printStackTrace();


                  }
              }




          }





          holder.textImputEditext.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {


            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {


                /***en edicion arreglar que remplzamos el correcto*/

                String key= holder.textImputEditext.getTag().toString();
                ImagenReport.hashMapImagesData.get(key).setDescripcionImagen( holder.textImputEditext.getText().toString());

                Log.i("zaaample","el texto del current edi es "+  holder.textImputEditext.getText().toString());

                Log.i("zaaample","el nombre de esta foto o key es "+  ImagenReport.hashMapImagesData.get(key).getUniqueIdNamePic());
                ///editamos la propiedad descripcion del objeto ImagenRerpot mapa..


                if(!holder.textImputEditext.getText().toString().isEmpty()){ //si contiene al menos un caratcer

                    Utils.objsIdsDecripcionImgsMOreDescripc.add(ImagenReport.hashMapImagesData.get(key).getUniqueIdNamePic()+"@"+holder.textImputEditext.getText().toString());
                }

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

private void checkExistFile(){

     /*
        if(Activi){


        }
*/


}

    private void dowloadAndSetImg(ImagenReport imagenReport, ImageView holder,Context context){

        Log.i("ladtastor","ladtastor es "+imagenReport.getUniqueIdNamePic());


        storageRef  = StorageData.rootStorageReference.child("imagenes_all_reports/"+imagenReport.getUniqueIdNamePic());

        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {



                Glide.with(context)
                        .load(uri)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.DATA)  //ESTABA EN ALL         //ALL or NONE as your requirementDiskCacheStrategy.DATA
                        //.thumbnail(Glide.with(OfertsAdminActivity.context).load(R.drawable.enviado_icon))
                       //.error(R.drawable.)
                        //aqi cargamos una version lower

                        .into(holder);



              //  imagenReport.setUrlStoragePic(uri.toString());

                  //  ImagenReport.hashMapImagesData.put(imagenReport.getUniqueIdNamePic(),imagenReport);


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
