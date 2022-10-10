package com.tiburela.qsercom.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tiburela.qsercom.R;

import java.util.ArrayList;
import java.util.Collections;

public class FormularioControlCalidad extends AppCompatActivity implements View.OnClickListener {
    // initialize variables

    TextView textView;
    boolean[] selectedLanguage;

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

    ArrayList<Integer> langList = new ArrayList<>();

    ArrayList<ArrayList<Boolean>> listOfLISTState = new ArrayList<>(); //serian unas dies listas...

    String[] langArray ;



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
        for (int i = 0; i <10; i++) {

            ArrayList<Boolean> listEmpty = new ArrayList<>(); //serian unas dies listas...

            listOfLISTState.add(listEmpty);

        }



       langArray  = getResources().getStringArray(R.array.array_defectos_fruta);
        // initialize selected language array
        selectedLanguage = new boolean[langArray.length];
    }
    //determinar que posicion pulso o si pusla este hacer esto

    private void findviewsIds() {

         imgSelecDefc1=findViewById(R.id.imgSelecDefc1);
         imgSelecDefc2=findViewById(R.id.imgSelecDefc2);
         imgSelecDefc3=findViewById(R.id.imgSelecDefc3);

        /*
         imgSelecDefc4=findViewById(R.id.imgSelecDefc4);
         imgSelecDefc5=findViewById(R.id.imgSelecDefc5);
         imgSelecDefc6=findViewById(R.id.imgSelecDefc6);
         imgSelecDefc7=findViewById(R.id.imgSelecDefc7);
         imgSelecDefc8=findViewById(R.id.imgSelecDefc8);
         imgSelecDefc9=findViewById(R.id.imgSelecDefc9);
         imgSelecDefc10=findViewById(R.id.imgSelecDefc10);



         */
    }


    private void addListnners(){

        imgSelecDefc1.setOnClickListener(this);
        imgSelecDefc2.setOnClickListener(this);
        imgSelecDefc3.setOnClickListener(this);


    }



    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.imgSelecDefc1:
                //  boolean [] estatesCurrentItem = new Boolean [listOfLISTState.get(0).size()];
                //resultArray = list.toArray(resultArray);
                // String[] strings = listOfLISTState.get(0).toArray(Boolean[]::new);

                boolean[] estates = {true, false, false, true, true};

                showDialogx(estates,0);

                break;


            case R.id.imgSelecDefc2:
              //  showDialogx(1);

                break;



            case R.id.imgSelecDefc3:
              //  showDialogx(2);

                break;
        }

    }


     void showDialogx(boolean[] selectedLanguage,int positiomItemOfListOFlistOflist) {

        // Initialize alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(FormularioControlCalidad.this);

        // set title
        builder.setTitle("Select Language");

        // set dialog non cancelable
        builder.setCancelable(false);

        builder.setMultiChoiceItems(langArray, selectedLanguage, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                // check condition
                if (b) {
                    // when checkbox selected
                    // Add position  in lang list
                    langList.add(i);
                    // Sort array list
                    Collections.sort(langList);
                } else {
                    // when checkbox unselected
                    // Remove position from langList
                    langList.remove(Integer.valueOf(i));
                }
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Initialize string builder
                StringBuilder stringBuilder = new StringBuilder();
                // use for loop


                try {
                    for (int j = 0; j < langList.size(); j++) {
                        // concat array value
                        stringBuilder.append(langArray[langList.get(j)]);
                        // check condition
                        if (j != langList.size() - 1) {
                            // When j value  not equal
                            // to lang list size - 1
                            // add comma
                            stringBuilder.append(", ");
                        }
                    }
                    // set text on textView
                    //textView.setText(stringBuilder.toString());
                }catch (Exception e) {


                }

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
                for (int j = 0; j < selectedLanguage.length; j++) {
                    // remove all selection
                    selectedLanguage[j] = false;
                    // clear language list
                    langList.clear();
                    // clear text view value
                  //  textView.setText("");
                }
            }
        });
        // show dialog
        builder.show();



    }



}