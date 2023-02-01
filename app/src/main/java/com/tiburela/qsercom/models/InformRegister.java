package com.tiburela.qsercom.models;

import com.google.firebase.database.Exclude;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InformRegister {


   private String codeInformtoVinculacion;
   private String informUniqueIdPertenece;
   private long dateUploadByinspCampoIformeMillisecond;
   private String simpleDateForm;
   private int  TypeInform;
   private boolean seRevisoForm;
   private boolean seSubioinDriveClienteAndBackup;
   private String nombreQUienSubio;
   private String idQuienSUbioForm; //el inspector de campo
   private String nombreQUienRevisoForm;
   private String idQUienRevisoForm;
   private boolean seSubioFormAlinea;


   private String keyLoactionThisForm;


   public String getTypeReportString() {
      return typeReportString;
   }

   public void setTypeReportString(String typeReportString) {
      this.typeReportString = typeReportString;
   }

   private String typeReportString;

   private long dateUltimaRevision;



   public String getCodeInformtoVinculacion() {
      return codeInformtoVinculacion;
   }

   public void setCodeInformtoVinculacion(String codeInformtoVinculacion) {
      this.codeInformtoVinculacion = codeInformtoVinculacion;
   }

   public String getInformUniqueIdPertenece() {
      return informUniqueIdPertenece;
   }

   public void setInformUniqueIdPertenece(String informUniqueIdPertenece) {
      this.informUniqueIdPertenece = informUniqueIdPertenece;
   }

   public long getDateUploadByinspCampoIformeMillisecond() {
      return dateUploadByinspCampoIformeMillisecond;
   }

   public void setDateUploadByinspCampoIformeMillisecond(long dateUploadByinspCampoIformeMillisecond) {
      this.dateUploadByinspCampoIformeMillisecond = dateUploadByinspCampoIformeMillisecond;
   }

   public String getSimpleDateForm() {
      return simpleDateForm;
   }

   public void setSimpleDateForm(String simpleDateForm) {
      this.simpleDateForm = simpleDateForm;
   }

   public int getTypeInform() {
      return TypeInform;
   }

   public void setTypeInform(int typeInform) {
      TypeInform = typeInform;
   }

   public boolean isSeRevisoForm() {
      return seRevisoForm;
   }

   public void setSeRevisoForm(boolean seRevisoForm) {
      this.seRevisoForm = seRevisoForm;
   }

   public boolean isSeSubioinDriveClienteAndBackup() {
      return seSubioinDriveClienteAndBackup;
   }

   public void setSeSubioinDriveClienteAndBackup(boolean seSubioinDriveClienteAndBackup) {
      this.seSubioinDriveClienteAndBackup = seSubioinDriveClienteAndBackup;
   }

   public String getNombreQUienSubio() {
      return nombreQUienSubio;
   }

   public void setNombreQUienSubio(String nombreQUienSubio) {
      this.nombreQUienSubio = nombreQUienSubio;
   }

   public String getIdQuienSUbioForm() {
      return idQuienSUbioForm;
   }

   public void setIdQuienSUbioForm(String idQuienSUbioForm) {
      this.idQuienSUbioForm = idQuienSUbioForm;
   }

   public String getNombreQUienRevisoForm() {
      return nombreQUienRevisoForm;
   }

   public void setNombreQUienRevisoForm(String nombreQUienRevisoForm) {
      this.nombreQUienRevisoForm = nombreQUienRevisoForm;
   }

   public String getIdQUienRevisoForm() {
      return idQUienRevisoForm;
   }

   public void setIdQUienRevisoForm(String idQUienRevisoForm) {
      this.idQUienRevisoForm = idQUienRevisoForm;
   }

   public long getDateUltimaRevision() {
      return dateUltimaRevision;
   }

   public void setDateUltimaRevision(long dateUltimaRevision) {
      this.dateUltimaRevision = dateUltimaRevision;
   }


   public String getKeyLoactionThisForm() {
      return keyLoactionThisForm;
   }


   public void setKeyLoactionThisForm(String keyLoactionThisForm) {
      this.keyLoactionThisForm = keyLoactionThisForm;
   }

   public boolean isSeSubioFormAlinea() {
      return seSubioFormAlinea;
   }

   public void setSeSubioFormAlinea(boolean seSubioFormAlinea) {
      this.seSubioFormAlinea = seSubioFormAlinea;
   }

   public InformRegister(String informUniqueIdPertenece, int typeInform,
                         String nombreQUienSubio, String idQuienSUbioForm,
                         String typeReportString)
   {


     codeInformtoVinculacion = "";
      this.informUniqueIdPertenece = informUniqueIdPertenece;


      Date date= new Date();
      dateUploadByinspCampoIformeMillisecond =date.getTime();
      Format formatter = new SimpleDateFormat("dd-MM-yyyy");

        simpleDateForm = formatter.format(dateUploadByinspCampoIformeMillisecond);

      TypeInform = typeInform;
      seRevisoForm = false;
     seSubioinDriveClienteAndBackup = false;
      this.nombreQUienSubio = nombreQUienSubio;
      this.idQuienSUbioForm = idQuienSUbioForm;
      nombreQUienRevisoForm = "";
      idQUienRevisoForm = "";
       dateUltimaRevision = 0;
      this.typeReportString=typeReportString;

     keyLoactionThisForm="";

      seSubioFormAlinea=false;
   }



public InformRegister(){


}


   @Exclude
   public Map<String, Object> toMap() {
      HashMap<String, Object> result = new HashMap<>();

      result.put("codeInformtoVinculacion", codeInformtoVinculacion);
      result.put("informUniqueIdPertenece", informUniqueIdPertenece);
      result.put("dateUploadByinspCampoIformeMillisecond", dateUploadByinspCampoIformeMillisecond);


      result.put("simpleDateForm", simpleDateForm);
      result.put("TypeInform", TypeInform);
      result.put("seRevisoForm", seRevisoForm);
      result.put("seSubioinDriveClienteAndBackup", seSubioinDriveClienteAndBackup);
      result.put("nombreQUienSubio", nombreQUienSubio);
      result.put("idQuienSUbioForm", idQuienSUbioForm);
      result.put("nombreQUienRevisoForm", nombreQUienRevisoForm);
      result.put("idQUienRevisoForm", idQUienRevisoForm);

      result.put("dateUltimaRevision", dateUltimaRevision);
      result.put("typeReportString", typeReportString);
      result.put("keyLoactionThisForm", keyLoactionThisForm);



      return result;

   }


}
