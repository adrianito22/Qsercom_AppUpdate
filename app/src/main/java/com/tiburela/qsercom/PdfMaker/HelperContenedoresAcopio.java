package com.tiburela.qsercom.PdfMaker;

import android.util.Log;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.tiburela.qsercom.models.DatosDeProceso;
import com.tiburela.qsercom.models.NameAndValue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class HelperContenedoresAcopio {

    public static final int TABLE_DATOS_CONTENEDORES_DE_ACOPIO=1;
    public static final int TABLE_DATOS_DEL_CONTENEDOR=2;
    public static final int TABLE_SELLOS_LLEGADA=3;
    public static final int TABLE_SELLOS_INSTALADOS=4;
    public static final int TABLE_DATOS_TRANSPORTISTA=5;
    public static final int TABLE_DATOS_DE_PROCESO=6;


   public static  DeviceRgb rgbColor= new DeviceRgb(219, 219, 219); //color verde claro

    public static  DeviceRgb rgbColorStrocke= new DeviceRgb(148, 148, 148); //color verde claro

    static Cell cell;
   static  Paragraph paragraph;

   static Table table;

    public static PdfFont font=null;
    public static void initFontx(){
        try{
            font = PdfFontFactory.createFont(StandardFonts.HELVETICA); //estane en courier

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

  public   static Table generaTableByID(ArrayList<NameAndValue> arrayList,int idHere){

        float sizeColumns[]= {190,285};  //establecemos el ewigth de cada columna de la tabla usando un array
        table=  new Table(sizeColumns); //le aplciamos este ewigth a cada columna
           if(idHere==TABLE_DATOS_CONTENEDORES_DE_ACOPIO){
                   int indice=0;

               for(NameAndValue objecNameValue: arrayList){




                   /***name*/
                   paragraph = new Paragraph(objecNameValue.getNameFields()).setFontSize(8.6f).setPaddingLeft(10f).setFont(font)
                           .setPaddingTop(0f).setPaddingBottom(0f).setBold();
                //   cell= new Cell().setHeight(11.7f).setPadding(0).add(paragraph);


                   cell= new Cell().add(paragraph).setPadding(0.1f);

                   cell.setBorder(new SolidBorder(Color.convertRgbToCmyk(rgbColorStrocke), 0.7f));


                   if(indice==0){
                       cell.setBackgroundColor(new DeviceRgb(255,217,102));
                   }

                   else if (indice==1 || indice==2){
                       cell.setBackgroundColor(new DeviceRgb(251,228,213));
                   }


                   table.addCell(cell);



                   /***value*/
                   paragraph = new Paragraph(objecNameValue.getValueContent()).setFontSize(8.6f).setPaddingLeft(10f).setFont(font)
                           .setPaddingTop(0f).setPaddingBottom(0f);
                   //cell= new Cell().setHeight(11.7f).setPadding(0).add(paragraph);
                   cell= new Cell().add(paragraph).setPadding(0.1f);

                   cell.setBorder(new SolidBorder(Color.convertRgbToCmyk(rgbColorStrocke), 0.7f));

                   if(indice==0){
                       cell.setBackgroundColor(new DeviceRgb(255,217,102));
                   }

                   else if (indice==1 || indice==2){
                       cell.setBackgroundColor(new DeviceRgb(251,228,213));
                   }

                   else if (indice==5){
                       cell.setBold();
                       cell.setBackgroundColor(new DeviceRgb(112,173,71));
                   }

                   table.addCell(cell);

                   indice++;

               }

           }else{ //normalmente

               for(NameAndValue objecNameValue: arrayList){


                   /***name*/
                   paragraph = new Paragraph(objecNameValue.getNameFields()).setFontSize(8.6f).setPaddingLeft(10f).setFont(font)//estsba 8
                           .setPaddingTop(0f).setPaddingBottom(0f).setBold();
                  // cell= new Cell().setHeight(11.7f).setPadding(0).add(paragraph);//etsab en 11.7

                   cell= new Cell().add(paragraph).setPadding(0.1f);

                   cell.setBorder(new SolidBorder(Color.convertRgbToCmyk(rgbColorStrocke), 0.7f));

                   table.addCell(cell);



                   /***value*/
                   paragraph = new Paragraph(objecNameValue.getValueContent()).setFontSize(8.6f).setPaddingLeft(10f).setFont(font)
                           .setPaddingTop(0f).setPaddingBottom(0f);
                   //cell= new Cell().setHeight(11.7f).setPadding(0).add(paragraph);

                   cell= new Cell().add(paragraph).setPadding(0.1f);

                   cell.setBorder(new SolidBorder(Color.convertRgbToCmyk(rgbColorStrocke), 0.7f));

                   table.addCell(cell);


               }


           }





        return table;

        // table1. configuramos el size de la tabla
       // HelperPdf.configTableMaringAndWidth(table,sizeTableAncho);



    }



    public static  ArrayList<NameAndValue> generateParValorList(int idTableGenerate){

        ArrayList<NameAndValue>list=new ArrayList<>();


        if(idTableGenerate==TABLE_DATOS_CONTENEDORES_DE_ACOPIO){
            list.add(new NameAndValue("FECHA INICIO "+"82/520/22","FECHA DE TÉRMINO: 13-10-2022 ")); //AQUI ACTUALIZAR NAMES

            list.add(new NameAndValue("EXPORTADORA SOLICITANTE","DATAHERE")); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("EXPORTADORA PROCESADA","DATAHERE")); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("PUERTO","DATAHERE")); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("ZONA","DATAHERE")); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("MARCA","DATAHERE")); //AQUI ACTUALIZAR NAMES

            list.add(new NameAndValue("HORA INICIO","DATAHERE")); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("HORA TERMINO","DATAHERE")); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("GUIA REMISION","DATAHERE")); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("TARJEA DE EMBARQUE","DATAHERE")); //AQUI ACTUALIZAR NAMES



        }else if(idTableGenerate==TABLE_DATOS_DEL_CONTENEDOR){


            list.add(new NameAndValue("DESTINO","DATAHERE")); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("VAPOR","DATAHERE")); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("NUMERACIÓN","DATAHERE")); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("HORA LLEGADA","DATAHERE")); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("HORA SALIDA","DATAHERE")); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("AGENCIA NAVIERA","DATAHERE")); //AQUI ACTUALIZAR NAMES


        }

        else if(idTableGenerate==TABLE_SELLOS_LLEGADA){

            list.add(new NameAndValue("PLÁSTICO PATIO NAVIERA","DATAHERE")); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue(" STICKER DE PATIO EN VENTOLERA EXTERNA 1","DATAHERE")); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("  NÚMERO DE SERIE DE FUNDA","DATAHERE")); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("CABLE DE RASTREO LLEGADA","DATAHERE")); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("BOOKING","DATAHERE")); //AQUI ACTUALIZAR
            list.add(new NameAndValue("MAX GROSS","DATAHERE")); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("TARE","DATAHERE")); //AQUI ACTUALIZAR NAMES


        }

        else if(idTableGenerate==TABLE_SELLOS_INSTALADOS){
            list.add(new NameAndValue("TERMÓGRAFO","DATAHERE")); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("SELLO NAVIERA","DATAHERE")); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("STICKER","DATAHERE")); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("OTROS CANDADOS 1","DATAHERE")); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("OTROS CANDADOS 2","DATAHERE")); //AQUI ACTUALIZAR NAMES
        }



        else if(idTableGenerate==TABLE_DATOS_TRANSPORTISTA){
            list.add(new NameAndValue(" COMPAÑÍA TRANSPORTISTA","DATAHERE")); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("NOMBRE CHOFER","DATAHERE")); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("CEDULA","DATAHERE")); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("PLACA","DATAHERE")); //AQUI ACTUALIZAR NAMES
           list.add(new NameAndValue("COLOR DE CABEZAL","DATAHERE")); //AQUI ACTUALIZAR NAMES

        }

        else if(idTableGenerate==TABLE_DATOS_DE_PROCESO){


        }

return  list;

    }

    public static void configTableMaringAndWidth(Table table1, float sizeTable){

        table1.setWidth(sizeTable);
        table1.setHorizontalAlignment(HorizontalAlignment.CENTER);
        table1.setMarginLeft(20f);
        table1.setMarginRight(20f);
        Log.i("mitables","el table es  here es es "+sizeTable);


    }


    public   static Table generaTableDatosProceso(HashMap<String,DatosDeProceso> map){

        float sizeColumns[]= {1,1,1,1};  //establecemos el ewigth de cada columna de la tabla usando un array
        table=  new Table(sizeColumns); //le aplciamos este ewigth a cada columna

        //AQUI CREMOAS UNO MAS


        int contador=1;
        for(DatosDeProceso objecNameValue: map.values()){


            /***nombre productor*/
            paragraph = new Paragraph("NOMBRE DE PROD "+contador+": "+objecNameValue.getNombreProd()).setFontSize(8.6f).setPaddingLeft(10f).setFont(font)//estsba 8
                    .setPaddingTop(0f).setPaddingBottom(0f).setBold();
            // cell= new Cell().setHeight(11.7f).setPadding(0).add(paragraph);//etsab en 11.7

            cell= new Cell().add(paragraph).setPadding(0.1f);

            cell.setBorder(new SolidBorder(Color.convertRgbToCmyk(rgbColorStrocke), 0.7f));

            table.addCell(cell);



            /***tipo de empaque*/
            paragraph = new Paragraph("TIPO DE EMPAQUE: "+objecNameValue.getTipoEmpaque()).setFontSize(8.6f).setPaddingLeft(10f).setFont(font)
                    .setPaddingTop(0f).setPaddingBottom(0f);
            //cell= new Cell().setHeight(11.7f).setPadding(0).add(paragraph);

            cell= new Cell().add(paragraph).setPadding(0.1f);

            cell.setBorder(new SolidBorder(Color.convertRgbToCmyk(rgbColorStrocke), 0.7f));

            table.addCell(cell);



            /***codigo*/
            paragraph = new Paragraph("COD."+contador+": "+objecNameValue.getCod()).setFontSize(8.6f).setPaddingLeft(10f).setFont(font)
                    .setPaddingTop(0f).setPaddingBottom(0f);
            //cell= new Cell().setHeight(11.7f).setPadding(0).add(paragraph);

            cell= new Cell().add(paragraph).setPadding(0.1f);

            cell.setBorder(new SolidBorder(Color.convertRgbToCmyk(rgbColorStrocke), 0.7f));

            table.addCell(cell);




            /***# cajas*/
            paragraph = new Paragraph("#CAJAS:"+objecNameValue.getNumeroCajas()).setFontSize(8.6f).setPaddingLeft(10f).setFont(font)
                    .setPaddingTop(0f).setPaddingBottom(0f);
            //cell= new Cell().setHeight(11.7f).setPadding(0).add(paragraph);

            cell= new Cell().add(paragraph).setPadding(0.1f);

            cell.setBorder(new SolidBorder(Color.convertRgbToCmyk(rgbColorStrocke), 0.7f));

            table.addCell(cell);

            contador++;

        }



        /**creamos datos faltantes emptys*/
        for(int indice=0; indice<10-map.size(); indice++){

            /***nombre productor*/
            paragraph = new Paragraph("NOMBRE DE PROD "+contador+":").setFontSize(8.6f).setPaddingLeft(10f).setFont(font)//estsba 8
                    .setPaddingTop(0f).setPaddingBottom(0f).setBold();
            // cell= new Cell().setHeight(11.7f).setPadding(0).add(paragraph);//etsab en 11.7

            cell= new Cell().add(paragraph).setPadding(0.1f);

            cell.setBorder(new SolidBorder(Color.convertRgbToCmyk(rgbColorStrocke), 0.7f));

            table.addCell(cell);



            /***tipo de empaque*/
            paragraph = new Paragraph("TIPO DE EMPAQUE: ").setFontSize(8.6f).setPaddingLeft(10f).setFont(font)
                    .setPaddingTop(0f).setPaddingBottom(0f);
            //cell= new Cell().setHeight(11.7f).setPadding(0).add(paragraph);

            cell= new Cell().add(paragraph).setPadding(0.1f);

            cell.setBorder(new SolidBorder(Color.convertRgbToCmyk(rgbColorStrocke), 0.7f));

            table.addCell(cell);



            /***codigo*/
            paragraph = new Paragraph("COD."+contador+":").setFontSize(8.6f).setPaddingLeft(10f).setFont(font)
                    .setPaddingTop(0f).setPaddingBottom(0f);
            //cell= new Cell().setHeight(11.7f).setPadding(0).add(paragraph);

            cell= new Cell().add(paragraph).setPadding(0.1f);

            cell.setBorder(new SolidBorder(Color.convertRgbToCmyk(rgbColorStrocke), 0.7f));

            table.addCell(cell);




            /***# cajas*/
            paragraph = new Paragraph("#CAJAS:").setFontSize(8.6f).setPaddingLeft(10f).setFont(font)
                    .setPaddingTop(0f).setPaddingBottom(0f);
            //cell= new Cell().setHeight(11.7f).setPadding(0).add(paragraph);

            cell= new Cell().add(paragraph).setPadding(0.1f);

            cell.setBorder(new SolidBorder(Color.convertRgbToCmyk(rgbColorStrocke), 0.7f));

            table.addCell(cell);

            contador++;

        }



        return table;

        // table1. configuramos el size de la tabla
        // HelperPdf.configTableMaringAndWidth(table,sizeTableAncho);



    }

}
