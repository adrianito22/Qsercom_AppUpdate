package com.tiburela.qsercom.models;

import static android.view.Gravity.CENTER;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;

import com.tiburela.qsercom.utils.Utils;

import java.util.ArrayList;

public class TableFifi {
   //ANCHO TABLA
   public static int     SIZE_1_TERCIO=20;
    public static int    SIZE_2_TERCIO=21;



   //ALINEACIONES DE TABLA
    public static int   ALIGN_TABLE_CENTER=11;
    public static int   ALIGN_TABLE_LEFT=12;
    public static int   ALIGN_TABLE_RIGTH=13;

    public static final int   CENTER_ALIGN=200;
    private final int   LEFT_ALIGN  =201;



    public  final int   NOT_MARGIN=0;
    public  final int   LOW_MARGIN=2;
    public  final int   MIDLE_MARGIN=5;
    public  final int   HIGTH_MARGIN=10;


    private   int marginLeft;
    private   int marginRigth;


    //le cambairemos el size usando marging

    public  int getMarginLeft() {
        return marginLeft;
    }

    public  void setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
    }

    public  int getMarginRigth() {
        return marginRigth;
    }

    public  void setMarginRigth(int marginRigth) {
            this.marginRigth = marginRigth;
    }




    private final int UNDER_OF=100;
    private final int LEF_OF=101;
  //  public static  final int  ANCHO_DOCUEMENTO=1000;  ///esta es la correcta

   public static  final int  ANCHO_DOCUEMENTO=1080;  ///esta es la correcta595
    public static  final int  MATCHSIZE_DOCUEMENTO=1080;  //595

    //RECIBE UN OBJETO ANTERIOR...

    public static float ultimaPOsicionXEnd=0; //EN ESTA SPOCIONES VA EMPEZAR LOS DIBUJOS
    public static float ultimaPOsicionYEnd=220; //EN CASO QUE EMPEZCEMOS CON UNA TABLA..


    public ArrayList<Celda> getListCeldas() {
        return listCeldas;
    }

    public void setListCeldas(ArrayList<Celda> listCeldas) {
        this.listCeldas = listCeldas;
    }

    private ArrayList<Celda> listCeldas;

    public ArrayList<String> getLisContenidos() {
        return lisContenidos;
    }

    public void setLisContenidos(ArrayList<String> lisContenidos) {
        this.lisContenidos = lisContenidos;
    }

    private  ArrayList<String> lisContenidos;

    public Celda getBackgroundCeldFather() {
        return backgroundCeldFather;
    }

    public void setBackgroundCeldFather(Celda backgroundCeldFather) {
        this.backgroundCeldFather = backgroundCeldFather;
    }




    public int getNumColumnas() {
        return numColumnas;
    }

    public void setNumColumnas(int numColumnas) {
        this.numColumnas = numColumnas;
    }

    public float getAnchoTable() {
        return anchoTable;
    }

    public void setAnchoTable(float anchoTable) {
        this.anchoTable = anchoTable;
    }

    public int[] getArrayWidtPercentColums() {
        return arrayWidtPercentColums;
    }

    public void setArrayWidtPercentColums(int[] arrayWidtPercentColums) {
        this.arrayWidtPercentColums = arrayWidtPercentColums;
    }

    public int getNumFilas() {
        return numFilas;
    }

    public void setNumFilas(int numFilas) {
        this.numFilas = numFilas;
    }

    public int getAltoCelda() {
        return altoCelda;
    }

    public void setAltoCelda(int altoCelda) {
        this.altoCelda = altoCelda;
    }

    public Paint getLines() {
        return lines;
    }

    public void setLines(Paint lines) {
        this.lines = lines;
    }

    private Celda backgroundCeldFather;
    private float anchoTable;
    private int numColumnas;
    private int arrayWidtPercentColums [];
    private int numFilas;
    private int altoCelda;
    private Paint lines;

    public int getAling() {
        return aling;
    }

    public void setAling(int aling) {
        this.aling = aling;
    }

    private int aling;


    public int getPosicionxUlimoElementObj() {
        return posicionxUlimoElementObj;
    }

    public void setPosicionxUlimoElementObj(int posicionxUlimoElementObj) {
        this.posicionxUlimoElementObj = posicionxUlimoElementObj;
    }

    private int posicionxUlimoElementObj;
    private int posicionyUlimoElementObj;



    public TableFifi(ArrayList<Celda> listCeldas, Celda backgroundCeldFather, int whitModoTable ,
                     int numColumnas, int anchoTable, int arrayWidtPercentColums [], int numFilas,
                     int altoCelda, Paint lines) {

        this.listCeldas =  listCeldas;
        this.backgroundCeldFather = backgroundCeldFather; //la celda que contien las demas celdas, el cuadro grande
        this.anchoTable =whitModoTable ;
        this.numColumnas=numColumnas;
        this.anchoTable=anchoTable;
        this.arrayWidtPercentColums=arrayWidtPercentColums;
        this.numFilas=numFilas;
        this.altoCelda=altoCelda;
        this.lines=lines;
        posicionxUlimoElementObj = 0;

    }


    /***CREA TABLA CON TEXTO*/

    public TableFifi(int anchoTable, int numColumnas, int marginRigth, int marginLeft,
                     int altoCelda,int aling, Paint lines, ArrayList <String>lisContenidos) {

        this.backgroundCeldFather = backgroundCeldFather; //la celda que contien las demas celdas, el cuadro grande
        this.anchoTable = anchoTable;
        this.numColumnas=numColumnas;
        this.marginRigth=marginRigth;
        this.marginLeft=marginLeft;
        this.altoCelda=altoCelda;
        this.lines=lines;
        this.aling=aling;
        posicionxUlimoElementObj = 0;
        backgroundCeldFather=new Celda(0,0,0,0);
        listCeldas =new ArrayList<>();
        this.lisContenidos=lisContenidos;

    }



    /***CREA TABLA VACIA**/

    public TableFifi(int anchoTable,int numColumnas, int marginRigth, int marginLeft, int numFilas,
                     int altoCelda, Paint lines) {

        this.backgroundCeldFather = backgroundCeldFather; //la celda que contien las demas celdas, el cuadro grande
        this.anchoTable = anchoTable;
        this.numColumnas=numColumnas;
        this.marginRigth=marginRigth;
        this.marginLeft=marginLeft;
        this.numFilas=numFilas;
        this.altoCelda=altoCelda;
        this.lines=lines;
        posicionxUlimoElementObj = 0;
        backgroundCeldFather=new Celda(0,0,0,0);
        listCeldas =new ArrayList<>();

    }






    public  void addTableDerechaDe(TableFifi tablaObject,int margingLeft){ //el objeto donde o vamos a tomar como referncia para linear
        //este objeto ya debe estar en canvas y tener su propiedades..
        ultimaPOsicionXEnd=tablaObject.backgroundCeldFather.getEndX()+margingLeft;

    }


    public  void addTableAbajoDe(TableFifi tablaObject,int marginTop){ //el objeto donde o vamos a tomar como referncia para linear
        ultimaPOsicionYEnd=tablaObject.backgroundCeldFather.getEndY()+marginTop;

    }
    public  void alinearTablaEn(TableFifi tablaObject,int alineacion){

        // ultimaPOsicionYEnd=tablaObject.backgroundCeldFather.getEndY()+marginTop;



        if(alineacion==ALIGN_TABLE_CENTER){

           // float xPosZ =celdaWhereEstaraText.getAnchoSize()/2 + celdaWhereEstaraText.getStartX() - (int)(painCentterna.measureText(text)/2);


            float  anchoTotal_menos_anchoTabla= ANCHO_DOCUEMENTO-tablaObject.anchoTable;

            ultimaPOsicionXEnd= anchoTotal_menos_anchoTabla/2;
            Log.i("alineamos","elanchoTotal_menos_anchoTabla es : "+anchoTotal_menos_anchoTabla);


           Log.i("alineamos","el x posicion es  : "+ultimaPOsicionXEnd);

          //  ultimaPOsicionYEnd=ANCHO_DOCUEMENTO-tablaObject.anchoTable/2;



        }
        /*
        else if(alineacion==ALIGN_TABLE_LEFT)  { //esta ya esta por defecto

            ultimaPOsicionXEnd=ANCHO_DOCUEMENTO-tablaObject.anchoTable;


        }
*/
        else if (alineacion==ALIGN_TABLE_RIGTH) {



            ultimaPOsicionXEnd=ANCHO_DOCUEMENTO-tablaObject.anchoTable-tablaObject.marginRigth;

        }


       // ultimaPOsicionYEnd=tablaObject.backgroundCeldFather.getEndY()+marginTop;

    }



    public  void alinearTablaCustom(TableFifi tablaObject,int alineacionCustom){ //el objeto donde o vamos a tomar como referncia para linear



       // ultimaPOsicionYEnd=tablaObject.backgroundCeldFather.getEndY()+marginTop;

    }




    public void DrawTableInCanvasaAndSetText (TableFifi tablaObject,Canvas canvas,int marginLeft,int marginRigth){/// ///EL largo puede ser default...
        float anchoOcuparaTabla=0;


        ultimaPOsicionXEnd=ultimaPOsicionXEnd+marginLeft;

        if(tablaObject.anchoTable ==TableFifi.ANCHO_DOCUEMENTO){ // ES 1 ///la tabla ucpara todo el ancho..
            anchoOcuparaTabla=ANCHO_DOCUEMENTO-marginRigth-marginLeft;
        }



        else if(tablaObject.anchoTable ==TableFifi.SIZE_2_TERCIO) { //si es dos ocupara solo la mita

            anchoOcuparaTabla=ANCHO_DOCUEMENTO/2;

        }

        else if(tablaObject.anchoTable ==TableFifi.SIZE_1_TERCIO) {

            anchoOcuparaTabla=ANCHO_DOCUEMENTO/3;



        }else{   //es custom size

            anchoOcuparaTabla=tablaObject.anchoTable;

        }


        tablaObject.setAnchoTable(anchoOcuparaTabla);
       alinearTablaEn(tablaObject,tablaObject.aling); //alineamos la tabla


        int numeroFilas=tablaObject.lisContenidos.size()/tablaObject.numColumnas;

         Log.i("debugMaximo","el numero de filas es: "+numeroFilas);

          //el seize de esta tabla...//el tercer paraemtro estaba en

        Celda celdabackgroundContainer=new Celda(ultimaPOsicionXEnd,ultimaPOsicionXEnd+ anchoOcuparaTabla ,ultimaPOsicionYEnd,ultimaPOsicionYEnd+ (numeroFilas*tablaObject.altoCelda));
        Paint paintBacdkground= new Paint();
        paintBacdkground.setColor(Color.parseColor("#FFBB86FC"));
        //actualizamos el ancho que ocupara esta tabla.
        tablaObject.setBackgroundCeldFather(celdabackgroundContainer);



    for(int indice=0; indice<numeroFilas+1; indice++){ ///cre lines horizintales

        Log.i("debugMaximo","crear lineas horiziontales se ejecuto : "+indice+" veces");

        canvas.drawLine(ultimaPOsicionXEnd,ultimaPOsicionYEnd,ultimaPOsicionXEnd+anchoOcuparaTabla,ultimaPOsicionYEnd,tablaObject.lines);

        ultimaPOsicionYEnd=ultimaPOsicionYEnd+tablaObject.getAltoCelda(); ///con esto basta

    }


        for(int indice=0; indice<tablaObject.numColumnas+1; indice++){ ///crea lineas vertiacles o clumnas

            canvas.drawLine(ultimaPOsicionXEnd,tablaObject.backgroundCeldFather.getStartY(), ultimaPOsicionXEnd,celdabackgroundContainer.getEndY(),tablaObject.lines);

            ultimaPOsicionXEnd=ultimaPOsicionXEnd + (anchoOcuparaTabla/numColumnas);

            Log.i("debugMaximo","la ultima posicion x es  "+ultimaPOsicionXEnd);

        }




        ultimaPOsicionXEnd=celdabackgroundContainer.getStartX();
        ultimaPOsicionYEnd=celdabackgroundContainer.getStartY();

        ArrayList<Celda>listCeldaCreate=new ArrayList<>();

        int indiceX=1;

        for(int indice=0; indice<tablaObject.numColumnas*numeroFilas; indice++){ ///crea values of celdas data..

            Celda celdaObject= new Celda(ultimaPOsicionXEnd,ultimaPOsicionXEnd+(anchoOcuparaTabla/numColumnas),ultimaPOsicionYEnd,ultimaPOsicionYEnd+altoCelda);


            Log.i("coquerop","el SIZE DE ESTE LEEMNTO ES "+celdaObject.getAltoSize());


            listCeldaCreate.add(celdaObject);
            tablaObject.listCeldas=listCeldaCreate;

            addTextInCanvas(tablaObject.lisContenidos.get(indice),canvas,lines,celdaObject,CENTER_ALIGN);

            ultimaPOsicionXEnd=ultimaPOsicionXEnd+(anchoOcuparaTabla/numColumnas);


            if(indiceX % numColumnas==0 &&  indice!=0){ //se debe ejecutar al menos 3 veces


                ultimaPOsicionXEnd=celdabackgroundContainer.getStartX();  //x regres a la osicion inicial
                ultimaPOsicionYEnd=ultimaPOsicionYEnd+tablaObject.altoCelda; //y le sumamos el aalto

                Log.i("coquerop","es multiplo el valor de y es  "+ultimaPOsicionYEnd);


            }
            indiceX++;

            Log.i("sabCELDEBUms","ultima posicicion x es  "+ultimaPOsicionXEnd);

        }





        for(int indice=0; indice<listCeldas.size(); indice++){


            Log.i("haberta","el contenido es "+listCeldas.get(indice).getStartX());

        }



        ///ACTUALIZAMOS VALOR DE BACGROUND=AUNQUE YA ESTA ECHO ARRIBA



    }




    public void DrawTableAdpatoSizeInCanvasaAndSetText (TableFifi tablaObject,Canvas canvas,int marginLeft,int marginRigth){/// ///EL largo puede ser default...
        float anchoOcuparaTabla=0;


        ultimaPOsicionXEnd=ultimaPOsicionXEnd+marginLeft;

        if(tablaObject.anchoTable ==TableFifi.ANCHO_DOCUEMENTO){ // ES 1 ///la tabla ucpara todo el ancho..
            anchoOcuparaTabla=ANCHO_DOCUEMENTO-marginRigth-marginLeft;
        }



        else if(tablaObject.anchoTable ==TableFifi.SIZE_2_TERCIO) { //si es dos ocupara solo la mita

            anchoOcuparaTabla=ANCHO_DOCUEMENTO/2;

        }

        else if(tablaObject.anchoTable ==TableFifi.SIZE_1_TERCIO) {

            anchoOcuparaTabla=ANCHO_DOCUEMENTO/3;



        }else{   //es custom size

            anchoOcuparaTabla=tablaObject.anchoTable;

        }


        tablaObject.setAnchoTable(anchoOcuparaTabla);
        alinearTablaEn(tablaObject,tablaObject.aling); //alineamos la tabla


        int numeroFilas=tablaObject.lisContenidos.size()/tablaObject.numColumnas;

        Log.i("debugMaximo","el numero de filas es: "+numeroFilas);

        //el seize de esta tabla...//el tercer paraemtro estaba en

        ArrayList<Float>alturaContendraCadaFila=Utils.generaAlturaDeFIlaByText(tablaObject,lines,50);
         float altoTabla= Utils.generaAlturaDeTabla(alturaContendraCadaFila);
        Celda celdabackgroundContainer=new Celda(ultimaPOsicionXEnd,ultimaPOsicionXEnd+ anchoOcuparaTabla ,ultimaPOsicionYEnd,ultimaPOsicionYEnd+altoTabla);

        Paint paintBacdkground= new Paint();
        paintBacdkground.setColor(Color.parseColor("#FFBB86FC"));
        //actualizamos el ancho que ocupara esta tabla.
        tablaObject.setBackgroundCeldFather(celdabackgroundContainer);



        for(int indice=0; indice<numeroFilas+1; indice++){ ///cdibujamos las lineas

            Log.i("debugMaximo","crear lineas horiziontales se ejecuto : "+indice+" veces");

            canvas.drawLine(ultimaPOsicionXEnd,ultimaPOsicionYEnd,ultimaPOsicionXEnd+anchoOcuparaTabla,ultimaPOsicionYEnd,tablaObject.lines);


            if(indice==numeroFilas){

                break;
            }

            ultimaPOsicionYEnd=ultimaPOsicionYEnd+alturaContendraCadaFila.get(indice); ///con esto basta

        }


        for(int indice=0; indice<tablaObject.numColumnas+1; indice++){ ///crea lineas vertiacles o clumnas

            canvas.drawLine(ultimaPOsicionXEnd,tablaObject.backgroundCeldFather.getStartY(), ultimaPOsicionXEnd,celdabackgroundContainer.getEndY(),tablaObject.lines);

            ultimaPOsicionXEnd=ultimaPOsicionXEnd + (anchoOcuparaTabla/numColumnas);

            Log.i("debugMaximo","la ultima posicion x es  "+ultimaPOsicionXEnd);

        }




        ultimaPOsicionXEnd=celdabackgroundContainer.getStartX();
        ultimaPOsicionYEnd=celdabackgroundContainer.getStartY();

        ArrayList<Celda>listCeldaCreate=new ArrayList<>();

        int indiceX=1;




        int  indiceAltoCeldaCurrentFila=0;

        for(int indice=0; indice<tablaObject.numColumnas*numeroFilas; indice++){ ///crea values of celdas data..


            Celda celdaObject= new Celda(ultimaPOsicionXEnd,ultimaPOsicionXEnd+(anchoOcuparaTabla/numColumnas),
                    ultimaPOsicionYEnd,ultimaPOsicionYEnd+alturaContendraCadaFila.get(indiceAltoCeldaCurrentFila));

            Log.i("coquerop","el SIZE DE ESTE LEEMNTO ES "+celdaObject.getAltoSize());


            listCeldaCreate.add(celdaObject);
            tablaObject.listCeldas=listCeldaCreate;


            Paint paintText= new Paint();
            paintText.setTextSize(30);

            addTextInCanvas(tablaObject.lisContenidos.get(indice),canvas,paintText,celdaObject,CENTER_ALIGN);

            ultimaPOsicionXEnd=ultimaPOsicionXEnd+(anchoOcuparaTabla/numColumnas);


            if(indiceX % numColumnas==0 &&  indice!=0){ //se debe ejecutar al menos 3 veces
                ultimaPOsicionXEnd=celdabackgroundContainer.getStartX();  //x regres a la osicion inicial
                ultimaPOsicionYEnd=ultimaPOsicionYEnd+alturaContendraCadaFila.get(indiceAltoCeldaCurrentFila); //y le sumamos el aalto
                indiceAltoCeldaCurrentFila++;

                Log.i("coquerop","es multiplo el valor de y es  "+ultimaPOsicionYEnd);


            }
            indiceX++;

            Log.i("sabCELDEBUms","ultima posicicion x es  "+ultimaPOsicionXEnd);

        }





        for(int indice=0; indice<listCeldas.size(); indice++){


            Log.i("haberta","el contenido es "+listCeldas.get(indice).getStartX());

        }



        ///ACTUALIZAMOS VALOR DE BACGROUND=AUNQUE YA ESTA ECHO ARRIBA



    }


    public void DrawTableGeneaAnchoAutomaticoyTABLAYAltoInCanvasaAndSetText (TableFifi tablaObject,Canvas canvas,int marginLeft,int marginRigth){/// ///EL largo puede ser default...

       ///hacemos un calculo para definir el ancho de la tabla

        //dpendiendo del numero de columna




        float anchoOcuparaTabla=0;


        ultimaPOsicionXEnd=ultimaPOsicionXEnd+marginLeft;

        if(tablaObject.anchoTable ==TableFifi.ANCHO_DOCUEMENTO){ // ES 1 ///la tabla ucpara todo el ancho..
            anchoOcuparaTabla=ANCHO_DOCUEMENTO-marginRigth-marginLeft;
        }



        else if(tablaObject.anchoTable ==TableFifi.SIZE_2_TERCIO) { //si es dos ocupara solo la mita

            anchoOcuparaTabla=ANCHO_DOCUEMENTO/2;

        }

        else if(tablaObject.anchoTable ==TableFifi.SIZE_1_TERCIO) {

            anchoOcuparaTabla=ANCHO_DOCUEMENTO/3;



        }else{   //es custom size

            anchoOcuparaTabla=tablaObject.anchoTable;

        }


        tablaObject.setAnchoTable(anchoOcuparaTabla);
        alinearTablaEn(tablaObject,tablaObject.aling); //alineamos la tabla


        int numeroFilas=tablaObject.lisContenidos.size()/tablaObject.numColumnas;

        Log.i("debugMaximo","el numero de filas es: "+numeroFilas);

        //el seize de esta tabla...//el tercer paraemtro estaba en

        ArrayList<Float>alturaContendraCadaFila=Utils.generaAlturaDeFIlaByText(tablaObject,lines,50);
        float altoTabla= Utils.generaAlturaDeTabla(alturaContendraCadaFila);
        Celda celdabackgroundContainer=new Celda(ultimaPOsicionXEnd,ultimaPOsicionXEnd+ anchoOcuparaTabla ,ultimaPOsicionYEnd,ultimaPOsicionYEnd+altoTabla);

        Paint paintBacdkground= new Paint();
        paintBacdkground.setColor(Color.parseColor("#FFBB86FC"));
        //actualizamos el ancho que ocupara esta tabla.
        tablaObject.setBackgroundCeldFather(celdabackgroundContainer);



        for(int indice=0; indice<numeroFilas+1; indice++){ ///cdibujamos las lineas

            Log.i("debugMaximo","crear lineas horiziontales se ejecuto : "+indice+" veces");

            canvas.drawLine(ultimaPOsicionXEnd,ultimaPOsicionYEnd,ultimaPOsicionXEnd+anchoOcuparaTabla,ultimaPOsicionYEnd,tablaObject.lines);


            if(indice==numeroFilas){

                break;
            }

            ultimaPOsicionYEnd=ultimaPOsicionYEnd+alturaContendraCadaFila.get(indice); ///con esto basta

        }


        for(int indice=0; indice<tablaObject.numColumnas+1; indice++){ ///crea lineas vertiacles o clumnas

            canvas.drawLine(ultimaPOsicionXEnd,tablaObject.backgroundCeldFather.getStartY(), ultimaPOsicionXEnd,celdabackgroundContainer.getEndY(),tablaObject.lines);

            ultimaPOsicionXEnd=ultimaPOsicionXEnd + (anchoOcuparaTabla/numColumnas);

            Log.i("debugMaximo","la ultima posicion x es  "+ultimaPOsicionXEnd);

        }




        ultimaPOsicionXEnd=celdabackgroundContainer.getStartX();
        ultimaPOsicionYEnd=celdabackgroundContainer.getStartY();

        ArrayList<Celda>listCeldaCreate=new ArrayList<>();

        int indiceX=1;




        int  indiceAltoCeldaCurrentFila=0;

        for(int indice=0; indice<tablaObject.numColumnas*numeroFilas; indice++){ ///crea values of celdas data..


            Celda celdaObject= new Celda(ultimaPOsicionXEnd,ultimaPOsicionXEnd+(anchoOcuparaTabla/numColumnas),
                    ultimaPOsicionYEnd,ultimaPOsicionYEnd+alturaContendraCadaFila.get(indiceAltoCeldaCurrentFila));

            Log.i("coquerop","el SIZE DE ESTE LEEMNTO ES "+celdaObject.getAltoSize());


            listCeldaCreate.add(celdaObject);
            tablaObject.listCeldas=listCeldaCreate;


            Paint paintText= new Paint();
            paintText.setTextSize(30);

            addTextInCanvas(tablaObject.lisContenidos.get(indice),canvas,paintText,celdaObject,CENTER_ALIGN);

            ultimaPOsicionXEnd=ultimaPOsicionXEnd+(anchoOcuparaTabla/numColumnas);


            if(indiceX % numColumnas==0 &&  indice!=0){ //se debe ejecutar al menos 3 veces
                ultimaPOsicionXEnd=celdabackgroundContainer.getStartX();  //x regres a la osicion inicial
                ultimaPOsicionYEnd=ultimaPOsicionYEnd+alturaContendraCadaFila.get(indiceAltoCeldaCurrentFila); //y le sumamos el aalto
                indiceAltoCeldaCurrentFila++;

                Log.i("coquerop","es multiplo el valor de y es  "+ultimaPOsicionYEnd);


            }
            indiceX++;

            Log.i("sabCELDEBUms","ultima posicicion x es  "+ultimaPOsicionXEnd);

        }





        for(int indice=0; indice<listCeldas.size(); indice++){


            Log.i("haberta","el contenido es "+listCeldas.get(indice).getStartX());

        }



        ///ACTUALIZAMOS VALOR DE BACGROUND=AUNQUE YA ESTA ECHO ARRIBA



    }



    private void addTextInCanvas(String text,Canvas canvas, Paint paintText,Celda celdaWhereEstaraText ,int tipoAlineacionText) {

        Rect bounds = new Rect();

        float sizeAltoCelda = celdaWhereEstaraText.getAltoSize();
        int text_height = 0;
        int text_width = 0;

        // paint.setTypeface(Typeface.DEFAULT);// your preference here
        // paint.setTextSize(25);// have this the same as your text size

        paintText.getTextBounds(text, 0, text.length(), bounds);

        text_height = bounds.height();
        text_width = bounds.width();

        // Paint painCentterna = new Paint();
        paintText.setTextAlign(Paint.Align.LEFT);

        int xPos = (canvas.getWidth() / 2);
        int yPos = (int) ((canvas.getHeight() / 2) - ((paintText.descent() + paintText.ascent()) / 2));
        //((textPaint.descent() + textPaint.ascent()) / 2) is the distance from the baseline to the center.


        if (tipoAlineacionText == CENTER_ALIGN) {
            float xPosZ = celdaWhereEstaraText.getAnchoSize() / 2 + celdaWhereEstaraText.getStartX() - (int) (paintText.measureText(text) / 2);



            float yPosZ = celdaWhereEstaraText.getAltoSize() / 2 + celdaWhereEstaraText.getStartY() + 3;

              if((paintText.measureText(text) + 10 < celdaWhereEstaraText.getAnchoSize())){

                  canvas.drawText(text, xPosZ, yPosZ, paintText);
                  Log.i("dominguillo", "el measure de "+ text +" es menor");


              }


            else if (paintText.measureText(text) + 10 > celdaWhereEstaraText.getAnchoSize()) {

                  Log.i("dominguillo", "sobrepaasa esl siguient text"+ text );


                  String[] textArray = text.split(" ");

                if (textArray.length == 2) { ////EL SIZE SERA menor...al size que ya esta le restamos -4


                    float  xPos1 = celdaWhereEstaraText.getAnchoSize() / 2 + celdaWhereEstaraText.getStartX() - (int) (paintText.measureText(textArray[0]) / 2);
                    float  xPos2 = celdaWhereEstaraText.getAnchoSize() / 2 + celdaWhereEstaraText.getStartX() - (int) (paintText.measureText(textArray[1]) / 2);

                    canvas.drawText(textArray[0], xPos1, yPosZ, paintText);
                     canvas.drawText(textArray[1], xPos2, yPosZ+6, paintText);


                }


                else if (textArray.length == 3) { //si hay dos paabras -6 ejempo de formula al reucior text size
                    if(paintText.measureText(textArray[0]+" "+textArray[1]) -20> celdaWhereEstaraText.getAnchoSize()){
                        ///entonces size mas pequeno y una en cada fila..

                        float  xPos1 = celdaWhereEstaraText.getAnchoSize() / 2 + celdaWhereEstaraText.getStartX() - (int) (paintText.measureText(textArray[0]+" "+textArray[1]) / 2);
                        float  xPos2 = celdaWhereEstaraText.getAnchoSize() / 2 + celdaWhereEstaraText.getStartX() - (int) (paintText.measureText(textArray[2]) / 2);

                       // xPosZ = celdaWhereEstaraText.getAnchoSize() / 2 + celdaWhereEstaraText.getStartX() - (int) (paintText.measureText(text) / 2);
                        canvas.drawText(textArray[0] +" "+textArray[1], xPos1, yPosZ, paintText);
                        canvas.drawText(textArray[1], xPos2, yPosZ+6, paintText);







                    }else{  //las 3 letras una en cada fila



                       float  xPos1 = celdaWhereEstaraText.getAnchoSize() / 2 + celdaWhereEstaraText.getStartX() - (int) (paintText.measureText(textArray[0]) / 2);
                       float  xPos2 = celdaWhereEstaraText.getAnchoSize() / 2 + celdaWhereEstaraText.getStartX() - (int) (paintText.measureText(textArray[1]) / 2);
                       float  xPos3 = celdaWhereEstaraText.getAnchoSize() / 2 + celdaWhereEstaraText.getStartX() - (int) (paintText.measureText(textArray[2]) / 2);

                        canvas.drawText(textArray[0], xPos1, yPosZ-20, paintText);
                        canvas.drawText(textArray[1], xPos2, yPosZ+7, paintText);
                        canvas.drawText(textArray[2], xPos3, yPosZ+35, paintText);


                    }


                }


                else{
                    Log.i("dominguillo", "2 no coincide ");


                }

            }


        }



        //1. MEDIR EL TAMANO DEL SIZE ...... DEL TEXTO .....
        //2. DIVIDIRLO EN 3 O 2..

        //SI TEXTO SOBREPASA.....
        //4..


        else if (tipoAlineacionText == CENTER) { ///falta arrgelaro esto con lo de arriba

            if((paintText.measureText(text) - 10 < celdaWhereEstaraText.getAnchoSize())){

                canvas.drawText(text, celdaWhereEstaraText.getStartX() + 10, celdaWhereEstaraText.getStartY() + text_height + (celdaWhereEstaraText.getAltoSize() / 4), paintText);


            }

            else if (paintText.measureText(text) - 10 > celdaWhereEstaraText.getAnchoSize()) {
                String[] textArray = text.split(" ");

                if (textArray.length == 2) { ////EL SIZE SERA menor...al size que ya esta le restamos -4

                    paintText.setTextSize(10);

                    canvas.drawText(textArray[0], celdaWhereEstaraText.getStartX() + 10, celdaWhereEstaraText.getStartY() + text_height + (celdaWhereEstaraText.getAltoSize() / 4), paintText);

                    canvas.drawText(textArray[1], celdaWhereEstaraText.getStartX() + 10, 6+celdaWhereEstaraText.getStartY() + text_height + (celdaWhereEstaraText.getAltoSize() / 4), paintText);




                }


                if (textArray.length == 3) { //si hay dos paabras -6
                    if(paintText.measureText(textArray[0]+" "+textArray[1]) -10> celdaWhereEstaraText.getAnchoSize()){
                        ///entonces size mas pequeno y una en cada fila..
                        paintText.setTextSize(12); ///crrr ofmrula


                        canvas.drawText(textArray[0]+" "+textArray[1], celdaWhereEstaraText.getStartX() + 10, celdaWhereEstaraText.getStartY() + text_height + (celdaWhereEstaraText.getAltoSize() / 4), paintText);

                        canvas.drawText(textArray[2], celdaWhereEstaraText.getStartX() + 10, 6+celdaWhereEstaraText.getStartY() + text_height + (celdaWhereEstaraText.getAltoSize() / 4), paintText);




                    }else{
                        paintText.setTextSize(10); ///crrr ofmrula  //3 uno debajo de otro

                      //  canvas.drawText(textArray[1], xPosZ, yPosZ+10, paintText);
                       // canvas.drawText(textArray[2], xPosZ, yPosZ+20, paintText);


                        canvas.drawText(textArray[0], celdaWhereEstaraText.getStartX() + 10, 6+celdaWhereEstaraText.getStartY() + text_height + (celdaWhereEstaraText.getAltoSize() / 4), paintText);
                        canvas.drawText(textArray[1], celdaWhereEstaraText.getStartX() + 10, 12+celdaWhereEstaraText.getStartY() + text_height + (celdaWhereEstaraText.getAltoSize() / 4), paintText);
                        canvas.drawText(textArray[2], celdaWhereEstaraText.getStartX() + 10, 18+celdaWhereEstaraText.getStartY() + text_height + (celdaWhereEstaraText.getAltoSize() / 4), paintText);


                    }


                }


            }





        }


        //Log.i("saeeer","el heigth celda es "+sizeAltoCelda);

        //   Log.i("saeeer","textHeigth "+text_height);

        // Log.i("saeeer","text_width "+text_width);


    }


    public void DrawTableInCanvas (TableFifi tablaObject,Canvas canvas){/// ///EL largo puede ser default...
        int anchoOcuparaTabla=0;
        double operacion=0;



        /*** creating a variable for canvas */

        if(tablaObject.anchoTable ==1){ //remplzar por nombre de variables


            anchoOcuparaTabla=ANCHO_DOCUEMENTO;

        }else if(tablaObject.anchoTable ==2) {

            operacion=ANCHO_DOCUEMENTO/2;

            anchoOcuparaTabla= (int) Math.floor(operacion);

        }else if(tablaObject.anchoTable ==3) {
            operacion=ANCHO_DOCUEMENTO/3;

            anchoOcuparaTabla= (int) Math.floor(operacion);


        }


        Celda celdabackgroundContainer=new Celda(ultimaPOsicionXEnd,anchoOcuparaTabla,ultimaPOsicionYEnd,tablaObject.numFilas*tablaObject.altoCelda);
        tablaObject.setBackgroundCeldFather(celdabackgroundContainer);



        //donde empezamos posicion x.start...si un opaRAMETRO CON LA POSICION DONDE EMPEZAMOS.,...





        ArrayList <Celda> celdasList= new ArrayList<>();



        for(int indice=0; indice<tablaObject.numFilas; indice++){





            canvas.drawLine(0,0,anchoOcuparaTabla,0,tablaObject.lines);

            celdasList.add(celdabackgroundContainer);



        }



        //por ultimo creamos el objeto table fifi con todas las pripiedas como canvas bacground y el hasmpa que cotneinee las celdas....




        //si podecir que un objeto tabla contiene un Canvas.....y ademas los otros atibutos...

//esto generar un obejto tabla......con todo ready....
//no la posicionara ni nada....solo crea un objeto tabla......


    }



/****
*
*
* */




}
