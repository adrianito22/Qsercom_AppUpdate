package com.tiburela.qsercom.models;

import java.util.UUID;

public class ColorCintasSemns {

  private int semanNum;
  private int columFieldNUm14;
  private int columFieldNUm13;
  private int columFieldNUm12;
  private int columFieldNUm11;
  private int columFieldNUm10;
  private int columFieldNUm9;
  private String uniqueId ;


  public int getSemanNum() {
    return semanNum;
  }

  public void setSemanNum(int semanNum) {
    this.semanNum = semanNum;
  }

  public int getColumFieldNUm14() {
    return columFieldNUm14;
  }

  public void setColumFieldNUm14(int columFieldNUm14) {
    this.columFieldNUm14 = columFieldNUm14;
  }

  public int getColumFieldNUm13() {
    return columFieldNUm13;
  }

  public void setColumFieldNUm13(int columFieldNUm13) {
    this.columFieldNUm13 = columFieldNUm13;
  }

  public int getColumFieldNUm12() {
    return columFieldNUm12;
  }

  public void setColumFieldNUm12(int columFieldNUm12) {
    this.columFieldNUm12 = columFieldNUm12;
  }

  public int getColumFieldNUm11() {
    return columFieldNUm11;
  }

  public void setColumFieldNUm11(int columFieldNUm11) {
    this.columFieldNUm11 = columFieldNUm11;
  }

  public int getColumFieldNUm10() {
    return columFieldNUm10;
  }

  public void setColumFieldNUm10(int columFieldNUm10) {
    this.columFieldNUm10 = columFieldNUm10;
  }

  public int getColumFieldNUm9() {
    return columFieldNUm9;
  }

  public void setColumFieldNUm9(int columFieldNUm9) {
    this.columFieldNUm9 = columFieldNUm9;
  }


  public String getUniqueId() {
    return uniqueId;
  }

  public void setUniqueId(String uniqueId) {
    this.uniqueId = uniqueId;
  }


  public ColorCintasSemns() {


  }


  public ColorCintasSemns(int semanNum, int columFieldNUm14, int columFieldNUm13,
                          int columFieldNUm12, int columFieldNUm11, int columFieldNUm10,
                          int columFieldNUm9) {


    this.semanNum = semanNum;
    this.columFieldNUm14 = columFieldNUm14;
    this.columFieldNUm13 = columFieldNUm13;
    this.columFieldNUm12 = columFieldNUm12;
    this.columFieldNUm11 = columFieldNUm11;
    this.columFieldNUm10 = columFieldNUm10;
    this.columFieldNUm9 = columFieldNUm9;
    uniqueId= UUID.randomUUID().toString();


  }



}
