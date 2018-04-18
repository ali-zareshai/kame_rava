package com.example.ali.shiva;

/**
 * Created by Ali on 18/08/17.
 */

public class Contacts {
    int id;
    String azan;
    String tolo;
    String bidarshod;
    int martabeh;
    String emtiaz1;
    String emtiaz2;
    String send;
    String d0,d1,d2,d3,d4,d5,d6;

    public String getD6() {
        return d6;
    }

    public void setD6(String d6) {
        this.d6 = d6;
    }

    public String getD0() {
        return d0;
    }

    public void setD0(String d0) {
        this.d0 = d0;
    }

    public String getD1() {
        return d1;
    }

    public void setD1(String d1) {
        this.d1 = d1;
    }

    public String getD2() {
        return d2;
    }

    public void setD2(String d2) {
        this.d2 = d2;
    }

    public String getD3() {
        return d3;
    }

    public void setD3(String d3) {
        this.d3 = d3;
    }

    public String getD4() {
        return d4;
    }

    public void setD4(String d4) {
        this.d4 = d4;
    }

    public String getD5() {
        return d5;
    }

    public void setD5(String d5) {
        this.d5 = d5;
    }

    public Contacts(String azan, String tolo, String bidarshod, int martabeh, String emtiaz1, String emtiaz2, String send, String d0, String d1, String d2, String d3, String d4, String d5, String d6) {
        this.azan = azan;
        this.tolo = tolo;
        this.bidarshod = bidarshod;
        this.martabeh = martabeh;
        this.emtiaz1 = emtiaz1;
        this.emtiaz2 = emtiaz2;
        this.send = send;
        this.d0 = d0;
        this.d1 = d1;
        this.d2 = d2;
        this.d3 = d3;
        this.d4 = d4;
        this.d5 = d5;
        this.d6 = d6;
    }

    public Contacts(String send, String emtiaz2, String emtiaz1, String azan, String tolo, String bidarshod) {
        this.send = send;
        this.emtiaz2 = emtiaz2;
        this.emtiaz1 = emtiaz1;
        this.azan = azan;
        this.tolo = tolo;

        this.bidarshod = bidarshod;
    }

    public String getEmtiaz1() {
        return emtiaz1;
    }

    public void setEmtiaz1(String emtiaz1) {
        this.emtiaz1 = emtiaz1;
    }

    public String getEmtiaz2() {
        return emtiaz2;
    }

    public void setEmtiaz2(String emtiaz2) {
        this.emtiaz2 = emtiaz2;
    }

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }

    public Contacts(String azan, String tolo, String bidarshod, int martabeh) {
        this.azan = azan;
        this.tolo = tolo;
        this.bidarshod = bidarshod;
        this.martabeh = martabeh;
    }

    public String getAzan() {
        return azan;
    }

    public void setAzan(String azan) {
        this.azan = azan;
    }

    public String getTolo() {
        return tolo;
    }

    public void setTolo(String tolo) {
        this.tolo = tolo;
    }

    public String getBidarshod() {
        return bidarshod;
    }

    public void setBidarshod(String bidarshod) {
        this.bidarshod = bidarshod;
    }

    public int getMartabeh() {
        return martabeh;
    }

    public void setMartabeh(int martabeh) {
        this.martabeh = martabeh;
    }
}

