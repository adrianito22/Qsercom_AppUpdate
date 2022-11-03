package com.tiburela.qsercom.models;

public class DefectsCantdad {


   public DefectsCantdad(int numeroDefectos, String nombreDefect) {
      this.numeroDefectos = numeroDefectos;
      this.nombreDefect = nombreDefect;
   }

   public int getNumeroDefectos() {
      return numeroDefectos;
   }

   public void setNumeroDefectos(int numeroDefectos) {
      this.numeroDefectos = numeroDefectos;
   }

   public String getNombreDefect() {
      return nombreDefect;
   }

   public void setNombreDefect(String nombreDefect) {
      this.nombreDefect = nombreDefect;
   }

   private    int numeroDefectos;
   private  String nombreDefect;


}
