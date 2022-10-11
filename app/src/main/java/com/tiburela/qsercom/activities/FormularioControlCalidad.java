package com.tiburela.qsercom.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.textfield.TextInputEditText;
import com.tiburela.qsercom.R;

import java.util.ArrayList;
import java.util.Calendar;

public class FormularioControlCalidad extends AppCompatActivity implements View.OnClickListener {
    // initialize variables

    TextView textView;
    boolean[] selectedLanguage;

    TextView textView48;


    //Imageviews defects
    ImageView imgSelecDefc1;
    ImageView imgSelecDefc2;
    ImageView imgSelecDefc3;
    ImageView imgSelecDefc4;
    ImageView imgSelecDefc5;
    ImageView imgSelecDefc6;
    ImageView imgSelecDefc7;
    ImageView imgSelecDefc8;
    ImageView imgSelecDefc9;
    ImageView imgSelecDefc10;




     TextInputEditText	edizz1	;
     TextInputEditText	edizz2	;
     TextInputEditText	edizz3	;
     TextInputEditText	edizz4	;
     TextInputEditText	edizz5	;
     TextInputEditText	edizz6	;
     TextInputEditText	edizz7	;
     TextInputEditText	edizz8	;
     TextInputEditText	edizz9	;
     TextInputEditText	edizz10	;
     TextInputEditText	edizz11	;
     TextInputEditText	edizz12	;
     TextInputEditText	edizz13	;
     TextInputEditText	edizz14	;
     TextInputEditText	edizz15	;
     TextInputEditText	edizz16	;
     TextInputEditText	edizz17	;
     TextInputEditText	edizz18	;
     TextInputEditText	edizz19	;
     TextInputEditText	edizz20	;
     TextInputEditText	edizz21	;
     TextInputEditText	edizz22	;
     TextInputEditText	edizz23	;
     TextInputEditText	edizz24	;
     TextInputEditText	edizz25	;
     TextInputEditText	edizz26	;
     TextInputEditText	edizz27	;
     TextInputEditText	edizz28	;
     TextInputEditText	edizz29	;
     TextInputEditText	edizz30	;
     TextInputEditText	edizz31	;
     TextInputEditText	edizz32	;
     TextInputEditText	edizz33	;
     TextInputEditText	edizz34	;
     TextInputEditText	edizz35	;
     TextInputEditText	edizz36	;
     TextInputEditText	edizz37	;
     TextInputEditText	edizz38	;
     TextInputEditText	edizz39	;
     TextInputEditText	edizz40	;



     //imgvEMPAQUES

    ImageView imvEmpaque1;
    ImageView imvEmpaque2;
    ImageView imvEmpaque3;
    ImageView imvEmpaque4;
    ImageView imvEmpaque5;
    ImageView imvEmpaque6;
    ImageView imvEmpaque7;
    ImageView imvEmpaque8;
    ImageView imvEmpaque9;
    ImageView imvEmpaque10;

    Spinner spinnerDef1;
    Spinner spinnerDef2;
    Spinner spinnerDef3;
    Spinner spinnerDef4;
    Spinner spinnerDef5;
    Spinner spinnerDef6;
    Spinner spinnerDef7;
    Spinner spinnerDef8;
    Spinner spinnerDef9;
    Spinner spinnerDef10;


    TextView txtTotal1;
    TextView txtTotal2;
    TextView txtTotal3;
    TextView txtTotal4;
    TextView txtTotal5;
    TextView txtTotal6;
    TextView txtTotal7;
    TextView txtTotal8;
    TextView txtTotal9;
    TextView txtTotal10;

    TextView txtTotal;




    ArrayList<Integer> langList = new ArrayList<>();

    ArrayList<ArrayList<Boolean>> listOfLISTState = new ArrayList<>(); //serian unas dies listas...

    ArrayList<ArrayList<Boolean>> listOfLISTState2 = new ArrayList<>(); //serian unas dies listas...


    String[] arrayDefect1;

    String[] arrayDefect2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_defectos);
        // assign variable
       // textView = findViewById(R.id.textView);
        findviewsIds();
        addListnners();


     //   String[] albums = getResources().getStringArray(R.array.array_defectos_fruta);
          //INICLIAMOS POSICIONES SON 10 LISTAS
      //  List<String> listDefectos = Arrays.asList();




       arrayDefect1 = getResources().getStringArray(R.array.array_defectos_fruta);
        arrayDefect2 = getResources().getStringArray(R.array.array_defectos_empaque2);



        for (int i = 0; i <10; i++) {

            ArrayList<Boolean> listItem = new ArrayList<>(); //serian unas dies listas...


            for (int j = 0; j < arrayDefect1.length; j++) {

                listItem.add(false);
                //agregamos valors a esta lista

            }

            listOfLISTState.add(listItem);


        }



        for (int i = 0; i <10; i++) {

            ArrayList<Boolean> listItem2 = new ArrayList<>(); //serian unas dies listas...


            for (int j = 0; j < arrayDefect2.length; j++) {

                listItem2.add(false);
                //agregamos valors a esta lista

            }

            listOfLISTState2.add(listItem2);


        }




        // initialize selected language array
        selectedLanguage = new boolean[arrayDefect1.length];
    }
    //determinar que posicion pulso o si pusla este hacer esto

    private void findviewsIds() {

        edizz1=findViewById(R.id.edizz1);
        edizz2=findViewById(R.id.edizz2);
        edizz3=findViewById(R.id.edizz3);
        edizz4=findViewById(R.id.edizz4);
        edizz5=findViewById(R.id.edizz5);
        edizz6=findViewById(R.id.edizz6);
        edizz7=findViewById(R.id.edizz7);
        edizz8=findViewById(R.id.edizz8);
        edizz9=findViewById(R.id.edizz9);
        edizz10=findViewById(R.id.edizz10);
        edizz11=findViewById(R.id.edizz11);
        edizz12=findViewById(R.id.edizz12);
        edizz13=findViewById(R.id.edizz13);
        edizz14=findViewById(R.id.edizz14);
        edizz15=findViewById(R.id.edizz15);
        edizz16=findViewById(R.id.edizz16);
        edizz17=findViewById(R.id.edizz17);
        edizz18=findViewById(R.id.edizz18);
        edizz19=findViewById(R.id.edizz19);
        edizz20=findViewById(R.id.edizz20);
        edizz21=findViewById(R.id.edizz21);
        edizz22=findViewById(R.id.edizz22);
        edizz23=findViewById(R.id.edizz23);
        edizz24=findViewById(R.id.edizz24);
        edizz25=findViewById(R.id.edizz25);
        edizz26=findViewById(R.id.edizz26);
        edizz27=findViewById(R.id.edizz27);
        edizz28=findViewById(R.id.edizz28);
        edizz29=findViewById(R.id.edizz29);
        edizz30=findViewById(R.id.edizz30);
        edizz31=findViewById(R.id.edizz31);
        edizz32=findViewById(R.id.edizz32);
        edizz33=findViewById(R.id.edizz33);
        edizz34=findViewById(R.id.edizz34);
        edizz35=findViewById(R.id.edizz35);
        edizz36=findViewById(R.id.edizz36);
        edizz37=findViewById(R.id.edizz37);
        edizz38=findViewById(R.id.edizz38);
        edizz39=findViewById(R.id.edizz39);
        edizz40=findViewById(R.id.edizz40);


        imgSelecDefc1=findViewById(R.id.imgSelecDefc1);
         imgSelecDefc2=findViewById(R.id.imgSelecDefc2);
         imgSelecDefc3=findViewById(R.id.imgSelecDefc3);
         imgSelecDefc4=findViewById(R.id.imgSelecDefc4);
         imgSelecDefc5=findViewById(R.id.imgSelecDefc5);
         imgSelecDefc6=findViewById(R.id.imgSelecDefc6);
         imgSelecDefc7=findViewById(R.id.imgSelecDefc7);
         imgSelecDefc8=findViewById(R.id.imgSelecDefc8);
         imgSelecDefc9=findViewById(R.id.imgSelecDefc9);
         imgSelecDefc10=findViewById(R.id.imgSelecDefc10);

         imvEmpaque1=findViewById(R.id.imvEmpaque1);
         imvEmpaque2=findViewById(R.id.imvEmpaque2);
         imvEmpaque3=findViewById(R.id.imvEmpaque3);
         imvEmpaque4=findViewById(R.id.imvEmpaque4);
         imvEmpaque5=findViewById(R.id.imvEmpaque5);
         imvEmpaque6=findViewById(R.id.imvEmpaque6);
         imvEmpaque7=findViewById(R.id.imvEmpaque7);
         imvEmpaque8=findViewById(R.id.imvEmpaque8);
         imvEmpaque9=findViewById(R.id.imvEmpaque9);
         imvEmpaque10=findViewById(R.id.imvEmpaque10);


        spinnerDef1=findViewById(R.id.spinnerDef1);
        spinnerDef1=findViewById(R.id.spinnerDef2);
        spinnerDef1=findViewById(R.id.spinnerDef3);
        spinnerDef1=findViewById(R.id.spinnerDef4);
        spinnerDef1=findViewById(R.id.spinnerDef5);
        spinnerDef1=findViewById(R.id.spinnerDef6);
        spinnerDef1=findViewById(R.id.spinnerDef7);
        spinnerDef1=findViewById(R.id.spinnerDef8);
        spinnerDef1=findViewById(R.id.spinnerDef9);
        spinnerDef1=findViewById(R.id.spinnerDef10);


        txtTotal1=findViewById(R.id.txtTotal1);
        txtTotal2=findViewById(R.id.txtTotal2);
        txtTotal3=findViewById(R.id.txtTotal3);
        txtTotal4=findViewById(R.id.txtTotal4);
        txtTotal5=findViewById(R.id.txtTotal5);
        txtTotal6=findViewById(R.id.txtTotal6);
        txtTotal7=findViewById(R.id.txtTotal7);
        txtTotal8=findViewById(R.id.txtTotal8);
        txtTotal9=findViewById(R.id.txtTotal9);
        txtTotal10=findViewById(R.id.txtTotal10);


        textView48=findViewById(R.id.textView48);

        txtTotal=findViewById(R.id.txttotal);

    }


    private void addListnners(){

        imgSelecDefc1.setOnClickListener(this);
        imgSelecDefc2.setOnClickListener(this);
        imgSelecDefc3.setOnClickListener(this);

        imgSelecDefc4.setOnClickListener(this);
        imgSelecDefc5.setOnClickListener(this);
        imgSelecDefc6.setOnClickListener(this);
        imgSelecDefc7.setOnClickListener(this);
        imgSelecDefc8.setOnClickListener(this);
        imgSelecDefc9.setOnClickListener(this);
        imgSelecDefc10.setOnClickListener(this);

        imvEmpaque1.setOnClickListener(this);
        imvEmpaque2.setOnClickListener(this);
        imvEmpaque3.setOnClickListener(this);
        imvEmpaque4.setOnClickListener(this);
        imvEmpaque5.setOnClickListener(this);
        imvEmpaque6.setOnClickListener(this);
        imvEmpaque7.setOnClickListener(this);
        imvEmpaque8.setOnClickListener(this);
        imvEmpaque9.setOnClickListener(this);
        imvEmpaque10.setOnClickListener(this);

        edizz1.setOnClickListener(this);
        edizz5.setOnClickListener(this);
        edizz9.setOnClickListener(this);
        edizz13.setOnClickListener(this);
        edizz17.setOnClickListener(this);
        edizz21.setOnClickListener(this);
        edizz25.setOnClickListener(this);
        edizz29.setOnClickListener(this);
        edizz33.setOnClickListener(this);
        edizz37.setOnClickListener(this);

        textView48.setOnClickListener(this);



    }



    void muestraResultado()  {

      ArrayList <Integer> result10 = new ArrayList<Integer>();

        int resultadoSuMA;

        for(int indice2=0; indice2<listOfLISTState.size(); indice2++){  //lista de listas
            resultadoSuMA=0;

            for(int indice=0; indice<listOfLISTState.get(indice2).size(); indice++){  //recorremos la lista actual


                if(listOfLISTState.get(indice2).get(indice)){
                    resultadoSuMA=resultadoSuMA+1;

                }




            }


            result10.add(resultadoSuMA);
            //aqui ya debemos tener el resultado


        }




int resultadotodo=0;
        for(int indice=0; indice<result10.size(); indice++){  //recorremos la lista actual


            resultadotodo=resultadotodo+result10.get(indice);


Log.i("sumarr","el valor es "+result10.get(indice));


        }

        txtTotal.setText(String.valueOf(resultadotodo));


        txtTotal1.setText(String.valueOf(result10.get(0)));
        txtTotal2.setText(String.valueOf(result10.get(1)));
        txtTotal3.setText(String.valueOf(result10.get(2)));
        txtTotal4.setText(String.valueOf(result10.get(3)));
        txtTotal5.setText(String.valueOf(result10.get(4)));
        txtTotal6.setText(String.valueOf(result10.get(5)));
        txtTotal7.setText(String.valueOf(result10.get(6)));
        txtTotal8.setText(String.valueOf(result10.get(7)));
        txtTotal9.setText(String.valueOf(result10.get(8)));
        txtTotal10.setText(String.valueOf(result10.get(9)));



    }





    @Override
    public void onClick(View view) {
        boolean[] estates;
        switch (view.getId()) {
            case R.id.imgSelecDefc1:
                //  boolean [] estatesCurrentItem = new Boolean [listOfLISTState.get(0).size()];
                //resultArray = list.toArray(resultArray);
                // String[] strings = listOfLISTState.get(0).toArray(Boolean[]::new);

                 estates =listToAarray(listOfLISTState.get(0));
                showDialogx(estates,0);

                break;


            case R.id.imgSelecDefc2:
              //  showDialogx(1);
                 estates =listToAarray(listOfLISTState.get(1));
                showDialogx(estates,1);
                break;



            case R.id.imgSelecDefc3:
              //  showDialogx(2);
                 estates =listToAarray(listOfLISTState.get(2));
                showDialogx(estates,2);
                break;


            case R.id.imgSelecDefc4:
                //  showDialogx(2);
                estates =listToAarray(listOfLISTState.get(3));
                showDialogx(estates,3);
                break;



            case R.id.imgSelecDefc5:
                //  showDialogx(2);
                estates =listToAarray(listOfLISTState.get(4));
                showDialogx(estates,4);
                break;


            case R.id.imgSelecDefc6:
                //  showDialogx(2);
                estates =listToAarray(listOfLISTState.get(5));
                showDialogx(estates,5);
                break;


            case R.id.imgSelecDefc7:
                //  showDialogx(2);
                estates =listToAarray(listOfLISTState.get(6));
                showDialogx(estates,6);
                break;


            case R.id.imgSelecDefc8:
                //  showDialogx(2);
                estates =listToAarray(listOfLISTState.get(7));
                showDialogx(estates,7);
                break;


            case R.id.imgSelecDefc9:
                //  showDialogx(2);
                estates =listToAarray(listOfLISTState.get(8));
                showDialogx(estates,8);
                break;


            case R.id.imgSelecDefc10:
                //  showDialogx(2);
                estates =listToAarray(listOfLISTState.get(9));
                showDialogx(estates,9);
                break;


              //para los otros defectos


            case R.id.imvEmpaque1:
                  estates =listToAarray(listOfLISTState2.get(0));
                showDialogx2(estates,0);


                break;
            case R.id.imvEmpaque2:
                estates =listToAarray(listOfLISTState2.get(1));
                showDialogx2(estates,1);

                break;

            case R.id.imvEmpaque3:
                estates =listToAarray(listOfLISTState2.get(2));
                showDialogx2(estates,2);

                break;

            case R.id.imvEmpaque4:
                estates =listToAarray(listOfLISTState2.get(3));
                showDialogx2(estates,3);

                break;


            case R.id.imvEmpaque5:
                estates =listToAarray(listOfLISTState2.get(4));
                showDialogx2(estates,4);

                break;


            case R.id.imvEmpaque6:
                estates =listToAarray(listOfLISTState2.get(5));
                showDialogx2(estates,5);

                break;

            case R.id.imvEmpaque7:
                estates =listToAarray(listOfLISTState2.get(6));
                showDialogx2(estates,6);

                break;

            case R.id.imvEmpaque8:
                estates =listToAarray(listOfLISTState2.get(7));
                showDialogx2(estates,7);

                break;

            case R.id.imvEmpaque9:
                estates =listToAarray(listOfLISTState2.get(8));
                showDialogx2(estates,8);

                break;


            case R.id.imvEmpaque10:
                estates =listToAarray(listOfLISTState2.get(9));
                showDialogx2(estates,9);

                break;





            case R.id.edizz1:

                showingTimePicker(view);
                break;


            case R.id.edizz5:

                showingTimePicker(view);
                break;


            case R.id.edizz9:

                showingTimePicker(view);
                break;



            case R.id.edizz13:

                showingTimePicker(view);
                break;



            case R.id.edizz17:

                showingTimePicker(view);
                break;


            case R.id.edizz21:

                showingTimePicker(view);
                break;



            case R.id.edizz25:

                showingTimePicker(view);
                break;



            case R.id.edizz29:

                showingTimePicker(view);
                break;


            case R.id.edizz33:

                showingTimePicker(view);
                break;


            case R.id.edizz37:

                showingTimePicker(view);
                break;



            case R.id.textView48:

                muestraResultado();

                break;
        }

    }


     void showDialogx(boolean[] selectedLanguage,int posicionListOfLIST) {

        // Initialize alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(FormularioControlCalidad.this);

        // set title
        builder.setTitle("Selecciones Defectos");

        // set dialog non cancelable
        builder.setCancelable(false);

        builder.setMultiChoiceItems(arrayDefect1, selectedLanguage, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                // check condition
                if (b) {
                    //cuando selecione une ...obtenemos la poisicion..
                    //

                    listOfLISTState.get(posicionListOfLIST).set(i,true);


                    // when checkbox selected
                    // Add position  in lang list
                  //  langList.add(i);
                    // Sort array list
                 //   Collections.sort(langList);
                } else {  //CUANDO LO DESELECIONA
                    // when checkbox unselected
                    // Remove position from langList

                    listOfLISTState.get(posicionListOfLIST).set(i,false);

                  //  langList.remove(Integer.valueOf(i));
                }
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Initialize string builder
                dialogInterface.dismiss();

            }
        });



        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // dismiss dialog
                dialogInterface.dismiss();
            }
        });


        builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // use for loop

                for (int j = 0; j < listOfLISTState.get(posicionListOfLIST).size(); j++) {

                    listOfLISTState.get(posicionListOfLIST).set(j,false);

                }




            }
        });
        // show dialog
        builder.show();



    }


    void showDialogx2(boolean[] selectedLanguage,int posicionListOfLIST) {

        // Initialize alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(FormularioControlCalidad.this);

        // set title
        builder.setTitle("Seleccione Defectos");

        // set dialog non cancelable
        builder.setCancelable(false);

        builder.setMultiChoiceItems(arrayDefect2, selectedLanguage, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                // check condition
                if (b) {
                    //cuando selecione une ...obtenemos la poisicion..
                    //

                    listOfLISTState2.get(posicionListOfLIST).set(i,true);


                    // when checkbox selected
                    // Add position  in lang list
                    //  langList.add(i);
                    // Sort array list
                    //   Collections.sort(langList);
                } else {  //CUANDO LO DESELECIONA
                    // when checkbox unselected
                    // Remove position from langList

                    listOfLISTState2.get(posicionListOfLIST).set(i,false);

                    //  langList.remove(Integer.valueOf(i));
                }
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Initialize string builder
                dialogInterface.dismiss();

            }
        });



        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // dismiss dialog
                dialogInterface.dismiss();
            }
        });


        builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // use for loop

                for (int j = 0; j < listOfLISTState2.get(posicionListOfLIST).size(); j++) {

                    listOfLISTState2.get(posicionListOfLIST).set(j,false);

                }




            }
        });
        // show dialog
        builder.show();



    }


    private boolean [] listToAarray(ArrayList<Boolean>list){

        boolean array[]= new boolean[list.size()];


        for(int indice=0; indice< list.size(); indice++){

            array[indice]=list.get(indice);


        }


        return  array;


    }






    void showingTimePicker( View vista){

        final Calendar cldr = Calendar.getInstance();
        int hour = cldr.get(Calendar.HOUR_OF_DAY);
        int minutes = cldr.get(Calendar.MINUTE);
        // time picker dialog
        TimePickerDialog picker = new TimePickerDialog(FormularioControlCalidad.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                        if(vista.getId()==R.id.edizz1) {
                            edizz1.setText(sHour + ":" + sMinute);


                        }


                        else if (vista.getId()== R.id.edizz5) {
                            edizz5.setText(sHour + ":" + sMinute);


                        }



                        else if (vista.getId()== R.id.edizz9) {
                            edizz9.setText(sHour + ":" + sMinute);


                        }



                        else if (vista.getId()== R.id.edizz13) {
                            edizz13.setText(sHour + ":" + sMinute);


                        }


                        else if (vista.getId()== R.id.edizz17) {
                            edizz17.setText(sHour + ":" + sMinute);


                        }



                        else if (vista.getId()== R.id.edizz21) {
                            edizz21.setText(sHour + ":" + sMinute);


                        }

                        else if (vista.getId()== R.id.edizz25) {
                            edizz25.setText(sHour + ":" + sMinute);


                        }


                        else if (vista.getId()== R.id.edizz29) {
                            edizz29.setText(sHour + ":" + sMinute);


                        }




                        else if (vista.getId()== R.id.edizz33) {
                            edizz33.setText(sHour + ":" + sMinute);


                        }



                        else if (vista.getId()== R.id.edizz37) {
                            edizz37.setText(sHour + ":" + sMinute);


                        }





                    }
                }, hour, minutes, true);

        picker.setButton(DialogInterface.BUTTON_POSITIVE, "OK", picker);
        picker.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar", picker);

        picker.show();
    }



}