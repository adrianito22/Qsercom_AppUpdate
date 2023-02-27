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
import com.tiburela.qsercom.utils.Variables;

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


        //        addDataEnFields(Variables.CurrenReportContensEnACp);
        if(idTableGenerate==TABLE_DATOS_CONTENEDORES_DE_ACOPIO){
            list.add(new NameAndValue("FECHA DE INICIO: "+ Variables.CurrenReportContensEnACp.getFechaInicio(),
                    "FECHA DE TÉRMINO: "+Variables.CurrenReportContensEnACp.getFechadeTermino())); //AQUI ACTUALIZAR NAMES

            list.add(new NameAndValue("EXPORTADORA SOLICITANTE",Variables.CurrenReportContensEnACp.getExportSolicitante())); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("EXPORTADORA PROCESADA",Variables.CurrenReportContensEnACp.getExportProcesada())); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("PUERTO",Variables.CurrenReportContensEnACp.getPuerto())); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("ZONA",Variables.CurrenReportContensEnACp.getZona())); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("MARCA",Variables.CurrenReportContensEnACp.getMarca())); //AQUI ACTUALIZAR NAMES



            String [] arrayTime=Variables.CurrenReportContensEnACp.getHoraInicio().split(":");

            if(arrayTime[0].length()==1){
                list.add(new NameAndValue("HORA INICIO","0"+Variables.CurrenReportContensEnACp.getHoraInicio())); //AQUI ACTUALIZAR NAMES
            }

            else{
                list.add(new NameAndValue("HORA INICIO",Variables.CurrenReportContensEnACp.getHoraInicio())); //AQUI ACTUALIZAR NAMES
            }




            arrayTime=Variables.CurrenReportContensEnACp.getHoraDetermino().split(":");

            if(arrayTime[0].length()==1){
                list.add(new NameAndValue("HORA TERMINO","0"+Variables.CurrenReportContensEnACp.getHoraDetermino())); //AQUI ACTUALIZAR NAMES
            }

            else{
                list.add(new NameAndValue("HORA TERMINO",Variables.CurrenReportContensEnACp.getHoraDetermino())); //AQUI ACTUALIZAR NAMES
            }





            list.add(new NameAndValue("GUÍA REMISIÓN",Variables.CurrenReportContensEnACp.getGuiaDeRemision())); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("TARJETA DE EMBARQUE",Variables.CurrenReportContensEnACp.getTarjaDeEmbarque())); //AQUI ACTUALIZAR NAMES



        }else if(idTableGenerate==TABLE_DATOS_DEL_CONTENEDOR){


            list.add(new NameAndValue("DESTINO",Variables.CurrenReportContensEnACp.getDestino())); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("VAPOR",Variables.CurrenReportContensEnACp.getVapor())); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("NUMERACIÓN",Variables.CurrenReportContensEnACp.getNumContenedor())); //AQUI ACTUALIZAR NAMES



            String [] arrayTime=Variables.CurrenReportContensEnACp.getHoraDeLlegada().split(":");
            if(arrayTime[0].length()==1){
                list.add(new NameAndValue("HORA LLEGADA","0"+Variables.CurrenReportContensEnACp.getHoraDeLlegada())); //AQUI ACTUALIZAR NAMES
            }

            else{
                list.add(new NameAndValue("HORA LLEGADA",Variables.CurrenReportContensEnACp.getHoraDeLlegada())); //AQUI ACTUALIZAR NAMES
            }




             arrayTime=Variables.CurrenReportContensEnACp.getHoraDeSalida().split(":");
            if(arrayTime[0].length()==1){
                list.add(new NameAndValue("HORA SALIDA","0"+Variables.CurrenReportContensEnACp.getHoraDeSalida())); //AQUI ACTUALIZAR NAMES
            }

            else{
                list.add(new NameAndValue("HORA SALIDA",Variables.CurrenReportContensEnACp.getHoraDeSalida())); //AQUI ACTUALIZAR NAMES
            }





            list.add(new NameAndValue("AGENCIA NAVIERA",Variables.CurrenReportContensEnACp.getAgenciaNaviera())); //AQUI ACTUALIZAR NAMES


        }

        else if(idTableGenerate==TABLE_SELLOS_LLEGADA){

            list.add(new NameAndValue("PLÁSTICO PATIO NAVIERA",Variables.CurrenReportContensEnACp.getSellosPlasticoNaviera())); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("STICKER DE PATIO EN VENTOLERA EXTERNA 1",Variables.CurrenReportContensEnACp.getStickerDeVentolExternn1())); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("NÚMERO DE SERIE DE FUNDA",Variables.CurrenReportContensEnACp.getNumSerieFunda())); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("CABLE DE RASTREO LLEGADA",Variables.CurrenReportContensEnACp.getCableRastreoLlegada())); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("BOOKING",Variables.CurrenReportContensEnACp.getBooking())); //AQUI ACTUALIZAR
            list.add(new NameAndValue("MAX GROSS",Variables.CurrenReportContensEnACp.getMaxGross())); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("TARE",Variables.CurrenReportContensEnACp.getTare())); //AQUI ACTUALIZAR NAMES


        }

        else if(idTableGenerate==TABLE_SELLOS_INSTALADOS){
            list.add(new NameAndValue("TERMÓGRAFO",Variables.CurrenReportContensEnACp.getTermografoN1())); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("SELLO NAVIERA",Variables.CurrenReportContensEnACp.getSelloDeNaviera())); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("STICKER",Variables.CurrenReportContensEnACp.getStickerDeVentolExternn1())); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("OTROS CANDADOS 1",Variables.CurrenReportContensEnACp.getOtrosCandados())); //AQUI ACTUALIZAR NAMES
         //   list.add(new NameAndValue("OTROS CANDADOS 2",Variables.CurrenReportContensEnACp.setOtrosCandados())); //AQUI ACTUALIZAR NAMES
        }



        else if(idTableGenerate==TABLE_DATOS_TRANSPORTISTA){
            list.add(new NameAndValue("COMPAÑÍA TRANSPORTISTA",Variables.CurrenReportContensEnACp.getCompaniaTranportista())); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("NOMBRE CHOFER",Variables.CurrenReportContensEnACp.getNombredeChofer())); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("CEDULA",Variables.CurrenReportContensEnACp.getCedula())); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("CÉLULAR",Variables.CurrenReportContensEnACp.getCelular())); //AQUI ACTUALIZAR NAMES
            list.add(new NameAndValue("PLACA",Variables.CurrenReportContensEnACp.getPlaca())); //AQUI ACTUALIZAR NAMES
           list.add(new NameAndValue("COLOR DE CABEZAL",Variables.CurrenReportContensEnACp.getColorCabezal())); //AQUI ACTUALIZAR NAMES

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

        Log.i("mispaps","el size de map es "+map.size());



        float sizeColumns[]= {1,1,1,1};  //establecemos el ewigth de cada columna de la tabla usando un array
        table=  new Table(sizeColumns); //le aplciamos este ewigth a cada columna

        //AQUI CREMOAS UNO MAS
        paragraph = new Paragraph("CAJAS PROCESADAS DESPACHADAS: ").setFontSize(9.6f).setPaddingLeft(10f).setFont(font)//estsba 8
                .setPaddingTop(0f).setPaddingBottom(0f).setBold();
        cell= new Cell().add(paragraph).setPadding(0.1f);
        cell.setBorder(new SolidBorder(Color.convertRgbToCmyk(rgbColorStrocke), 0.7f));
        table.addCell(cell);



        paragraph = new Paragraph(String.valueOf(Variables.CurrenReportContensEnACp.getCajasProcesadasDespachadas())).setFontSize(10.6f).setPaddingLeft(10f).setFont(font)
                .setPaddingTop(0f).setPaddingBottom(0f);
        //cell= new Cell().setHeight(11.7f).setPadding(0).add(paragraph);

        cell= new Cell(1,3).add(paragraph).setPadding(0.1f);

        cell.setBorder(new SolidBorder(Color.convertRgbToCmyk(rgbColorStrocke), 0.7f));

        table.addCell(cell);




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
