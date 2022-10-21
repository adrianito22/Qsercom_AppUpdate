package com.tiburela.qsercom.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;

import java.util.ArrayList;

public class TableFifi {


    Paint paintLines;
    int posicionYEstarTable;
    int posicionYendTable;
    int marginLefTable;
    int marginRigthTable;
    int numFiles; //el size de la lista
    int numColumnas;
    String backGroundColorTable;
   int totalOcupa;  //puedes ser la mita o todoxx o cutoms cantidad
    Celda background;

   Canvas canvasTabla;


   public TableFifi(Canvas tableCanvas,ArrayList<Celda> list,Celda background){


   }

   //y las demas propiedades como ancho ...alto ,etc......
    // y un hasmpa con el conjunto de celdas; info..




    //un metodo para crear una tabla......
    ///queremos pasarle




public TableFifi(int poscionStarTableY,Paint paintLines, ArrayList<String> namesFieldsAndTotalColumna,int totalOcupaOmodo){  ///para header y table..






    //names fields.. si le pasa 3 fields....

    //podemos agregarle un header a la tabla....
}



//tambien deberia haber uno para solo crear tabla y hedader....


    public void addTAbleWhitHeader( TableFifi tableFifi){  ///le pasmoa un objeto table whit header......




    }

    //table whitHeader only color...

    //Table table = new table(); //patrametros...

    ////add table


    //table whit color column color...






    //table whit text..........



    //table whitouColor.



    //metodo agrega background color in celd.........
    ///entonces cuando quremos un header color usamos este metodo....


    //si es multicolor......



  //emetodo delete table...background  ....



    //vamos a dibujar una tabla........

    ////si le pasamos un objeto tab;a....


    public static   void geenraBitmap (int whitModoTable ,int numColumnas,int anchoTable, int arrayWidtPercentColums [], int numFilas, int altoCelda,Paint lines,Canvas objecPdfLienzo){/// ///EL largo puede ser default...
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


        Canvas canvas = myPage1.getCanvas();

        Celda celdabackgroundContainer=new Celda(0,anchoOcuparaTabla,0,numFilas*altoCelda);

        canvas.drawLine(0,0,anchoOcuparaTabla,0,lines);


        ArrayList <Celda> celdasList= new ArrayList<>();
        celdasList.add(celdabackgroundContainer);

        //   public TableFifi(Canvas tableCanvas,ArrayList<Celda> list,Celda background){
        TableFifi table=new TableFifi(canvas,celdasList,celdabackgroundContainer);


        Bitmap bmp = Bitmap.createBitmap(anchoOcuparaTabla, numFilas*altoCelda, Bitmap.Config.ARGB_8888);

         Celda.miBitmap=bmp;









//        Bitmap MIN=Bitmap.createBitmap(canvas);



        ///  canvasTabla.drawLine(0,0,anchoOcuparaTabla,0,paintLines);
         //despues de esto agregamos a otro canvas....


          //vamos a comprobar si asi funciona...si podemos refrenciar a un canvas y cambiarle la propiedad....



    //si en width modo table es 1 habra solo un ancho ...si 2 2 el maximo tamano sera hasta la mitad...

        //vamoas a establcer un ancho de canvas unicamnte con el ancho que tendrala tabla....


    //dibujamos una celda que conteien todo y sera el background...

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


        //   public TableFifi(Canvas tableCanvas,ArrayList<Celda> list,Celda background){
        TableFifi table=new TableFifi(canvas,celdasList,celdabackgroundContainer);

        Celda.micanvasBitmp=workingDrawing;
       // workingDrawing

        Celda.miBitmap=bmp;









//        Bitmap MIN=Bitmap.createBitmap(canvas);



        ///  canvasTabla.drawLine(0,0,anchoOcuparaTabla,0,paintLines);
        //despues de esto agregamos a otro canvas....


        //vamos a comprobar si asi funciona...si podemos refrenciar a un canvas y cambiarle la propiedad....



        //si en width modo table es 1 habra solo un ancho ...si 2 2 el maximo tamano sera hasta la mitad...

        //vamoas a establcer un ancho de canvas unicamnte con el ancho que tendrala tabla....


        //dibujamos una celda que conteien todo y sera el background...

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
