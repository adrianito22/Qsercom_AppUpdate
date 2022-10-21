package com.tiburela.qsercom.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;

import java.util.ArrayList;

public class TableFifi {

    //
    private final int UNDER_OF=100;
    private final int LEF_OF=101;
    public static  final int  ANCHO_DOCUEMENTO=595;

    public static  final int  MATCHSIZE_DOCUEMENTO=595;



    //RECIBE UN OBJETO ANTERIOR...

    private int ultimaPOsicionXEnd=30; //EN ESTA SPOCIONES VA EMPEZAR LOS DIBUJOS
    private int ultimaPOsicionYEnd=100; //EN CASO QUE EMPEZCEMOS CON UNA TABLA..




    private ArrayList<Celda> list;

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

    public int getWhitModoTable() {
        return whitModoTable;
    }

    public void setWhitModoTable(int whitModoTable) {
        this.whitModoTable = whitModoTable;
    }

    public int getNumColumnas() {
        return numColumnas;
    }

    public void setNumColumnas(int numColumnas) {
        this.numColumnas = numColumnas;
    }

    public int getAnchoTable() {
        return anchoTable;
    }

    public void setAnchoTable(int anchoTable) {
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
    private int whitModoTable ;
    private int numColumnas;
    private int anchoTable;
    private int arrayWidtPercentColums [];
    private int numFilas;
    private int altoCelda;
    private Paint lines;

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

        this.list=  listCeldas;
        this.backgroundCeldFather = backgroundCeldFather; //la celda que contien las demas celdas, el cuadro grande
        this.whitModoTable=whitModoTable ;
        this.numColumnas=numColumnas;
        this.anchoTable=anchoTable;
        this.arrayWidtPercentColums=arrayWidtPercentColums;
        this.numFilas=numFilas;
        this.altoCelda=altoCelda;
        this.lines=lines;
        posicionxUlimoElementObj = 0;

    }


    //constructor crear tabla con Texto
    public TableFifi( int whitModoTable , int numColumnas, int anchoTable, int arrayWidtPercentColums [], int numFilas,
                      int altoCelda, Paint lines) {


        this.backgroundCeldFather = backgroundCeldFather; //la celda que contien las demas celdas, el cuadro grande
        this.whitModoTable=whitModoTable ;
        this.numColumnas=numColumnas;
        this.anchoTable=anchoTable;
        this.arrayWidtPercentColums=arrayWidtPercentColums;
        this.numFilas=numFilas;
        this.altoCelda=altoCelda;
        this.lines=lines;
        posicionxUlimoElementObj = 0;
        lisContenidos=new ArrayList<>();
        backgroundCeldFather=new Celda(0,0,0,0);
        list=new ArrayList<>();


    }





    public  void addTableDerechaDe(TableFifi tablaObject){ //el objeto donde o vamos a tomar como referncia para linear
        //este objeto ya debe estar en canvas y tener su propiedades..
        ultimaPOsicionXEnd=tablaObject.backgroundCeldFather.getEndX();

    }


    public  void addTableAbajoDe(TableFifi tablaObject){ //el objeto donde o vamos a tomar como referncia para linear
        ultimaPOsicionYEnd=tablaObject.backgroundCeldFather.getEndY();

    }




    public void DrawTableInCanvasaAndSetText (TableFifi tablaObject,Canvas canvas){/// ///EL largo puede ser default...


        int anchoOcuparaTabla=0;
        double operacion=0;


        /*** creating a variable for canvas */

        if(tablaObject.whitModoTable==1){ // ES 1


            anchoOcuparaTabla=ANCHO_DOCUEMENTO;

        }
        else if(tablaObject.whitModoTable==2) {

            operacion=ANCHO_DOCUEMENTO/2;

            anchoOcuparaTabla= (int) Math.floor(operacion);
        }

        else if(tablaObject.whitModoTable==3) {
            operacion=ANCHO_DOCUEMENTO/3;

            anchoOcuparaTabla= (int) Math.floor(operacion);


        }


        Celda celdabackgroundContainer=new Celda(ultimaPOsicionXEnd, anchoOcuparaTabla ,ultimaPOsicionYEnd,tablaObject.list.size()*tablaObject.altoCelda);


        Paint paintBacdkground= new Paint();
        paintBacdkground.setColor(Color.parseColor("#FFBB86FC"));


        //establcer una funciona para deterrminar el ancho position

        canvas.drawRect(ultimaPOsicionXEnd, ultimaPOsicionYEnd,ultimaPOsicionXEnd+ anchoOcuparaTabla, tablaObject.list.size()*tablaObject.altoCelda, paintBacdkground);


        //actualizamos el ancho que ocupara esta tabla.
        tablaObject.setBackgroundCeldFather(celdabackgroundContainer);




        ArrayList <Celda> celdasList= new ArrayList<>();



   /*
    for(int indice=0; indice<tablaObject.lisContenidos.size(); indice++){

        canvas.drawLine(ultimaPOsicionXEnd,ultimaPOsicionYEnd,ultimaPOsicionXEnd + anchoOcuparaTabla,0,tablaObject.lines);


        ///linea horizontal
        canvas.drawLine(0,0,150,0,tablaObject.lines);



        Celda celdabackgroundContainer2=new Celda(ultimaPOsicionXEnd, anchoOcuparaTabla ,ultimaPOsicionYEnd,tablaObject.list.size()*tablaObject.altoCelda);
        celdasList.add(celdabackgroundContainer2);


    }

    */


    }







    public void DrawTableInCanvas (TableFifi tablaObject,Canvas canvas){/// ///EL largo puede ser default...
        int anchoOcuparaTabla=0;
        double operacion=0;



        /*** creating a variable for canvas */

        if(tablaObject.whitModoTable==1){ //remplzar por nombre de variables


            anchoOcuparaTabla=ANCHO_DOCUEMENTO;

        }else if(tablaObject.whitModoTable==2) {

            operacion=ANCHO_DOCUEMENTO/2;

            anchoOcuparaTabla= (int) Math.floor(operacion);

        }else if(tablaObject.whitModoTable==3) {
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



    public static   void geenraBitmapTest (int whitModoTable , int numFilas, int altoCelda,Paint lines){/// ///EL largo puede ser default...
        final int  ANCHO_DOCUEMENTO=595;
        int anchoOcuparaTabla=0;
        double operacion=0;



        /*** creating a variable for canvas */

        if(whitModoTable==1){ //remplzar por nombre de variables


            anchoOcuparaTabla=ANCHO_DOCUEMENTO;

        }else if(whitModoTable==2) {

            operacion=ANCHO_DOCUEMENTO/2;

            anchoOcuparaTabla= (int) Math.floor(operacion);

        }else if(whitModoTable==3) {
            operacion=ANCHO_DOCUEMENTO/3;

            anchoOcuparaTabla= (int) Math.floor(operacion);


        }





        PdfDocument pdfDocument = new PdfDocument();
        PdfDocument.PageInfo mypageInfo1 = new PdfDocument.PageInfo.Builder(anchoOcuparaTabla, numFilas*altoCelda, 1).create();
        PdfDocument.Page myPage1 = pdfDocument.startPage(mypageInfo1);


        Bitmap bmp = Bitmap.createBitmap(anchoOcuparaTabla, numFilas*altoCelda, Bitmap.Config.ARGB_8888);
        Canvas workingDrawing = new Canvas(bmp);


        Canvas canvas = myPage1.getCanvas();

        Celda celdabackgroundContainer=new Celda(0,anchoOcuparaTabla,0,numFilas*altoCelda);

        canvas.drawLine(0,0,anchoOcuparaTabla,0,lines);
        canvas.drawText("HOLAHJSGDJHDF",0,20,lines);

        workingDrawing.drawLine(0,0,anchoOcuparaTabla,0,lines);
        workingDrawing.drawText("HOLAHJSGDJHDF",0,20,lines);

        ArrayList <Celda> celdasList= new ArrayList<>();
        celdasList.add(celdabackgroundContainer);


        //   public TableFifi(Canvas tableCanvas,ArrayList<Celda> list,Celda backgroundCeldFather){











//        Bitmap MIN=Bitmap.createBitmap(canvas);



        ///  canvasTabla.drawLine(0,0,anchoOcuparaTabla,0,paintLines);
        //despues de esto agregamos a otro canvas....


        //vamos a comprobar si asi funciona...si podemos refrenciar a un canvas y cambiarle la propiedad....



        //si en width modo table es 1 habra solo un ancho ...si 2 2 el maximo tamano sera hasta la mitad...

        //vamoas a establcer un ancho de canvas unicamnte con el ancho que tendrala tabla....


        //dibujamos una celda que conteien todo y sera el backgroundCeldFather...

        //size para empezar...//ancho table pueder custom o por defect matchpaarent....
        //donde empezar... necesitamos obtener el hancho y restarle el padding

        //si whidtdocumento es 1... oucoara toda la pantalla...





        for(int indice=0; indice<numFilas; indice++){




        }



        //por ultimo creamos el objeto table fifi con todas las pripiedas como canvas bacground y el hasmpa que cotneinee las celdas....




        //si podecir que un objeto tabla contiene un Canvas.....y ademas los otros atibutos...

//esto generar un obejto tabla......con todo ready....
//no la posicionara ni nada....solo crea un objeto tabla......


    }
    public static  Bitmap getBitmap(int drawableRes, Context context,Canvas canvas) {
        Drawable drawable = context.getResources().getDrawable(drawableRes);

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    private void crearTablaWhitText(){



    }






}
