package com.example.ali.shiva;



/**
 * Created by Ali on 11/08/17.
 */

public  class Convert_Date {
    int month;
    int day;
    String s,w;
    int jy;



    public String getDayWeek(){
        return w;
    }
    public String getMonthFarsi(){
        return  s;
    }
    public String getYearFarsi(){
        return String.valueOf(jy);
    }


    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public  String gregorian_to_jalali(int gy1, int gm1, int gd1, int week )
    {

        ////////
        int g_days_in_month[]=new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int j_days_in_month[]=new int[]{31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 29};
        int gy=gy1-1600;
        int gm=gm1-1;
        int gd=gd1-1;
        int g_day_no =
                365*gy+doubleToInt((gy+3)/4)-doubleToInt((gy+99)/100)+
                        doubleToInt((gy+399)/400);
        int i;
        for (i=0;i<gm;++i)
            g_day_no += g_days_in_month[i];
        if (gm>1 && ((gy%4==0 && gy%100!=0) || (gy%400==0)))
            g_day_no++;
        g_day_no += gd;
        int j_day_no = g_day_no-79;
        int j_np = doubleToInt(j_day_no/12053);
        j_day_no = j_day_no % 12053;
        jy = 979+33*j_np+4*doubleToInt(j_day_no/1461);
        j_day_no %= 1461;
        if (j_day_no >= 366)
        {
            jy += doubleToInt((j_day_no-1)/365);
            j_day_no = (j_day_no-1)%365;
        }
        for (i = 0; i < 11 && j_day_no >= j_days_in_month[i]; ++i)
            j_day_no -= j_days_in_month[i];
        int jm = i+1;
        s = null;
        w = null;
        switch(week) {
            case 0:
                w = "يکشنبه";
                break;
            case 1:
                w = "دوشنبه";
                break;
            case 2:
                w = "سه شنبه";
                break;
            case 3:
                w = "چهارشنبه";
                break;
            case 4:
                w = "پنج شنبه";
                break;
            case 5:
                w = "جمعه";
                break;
            case 6:
                w = "شنبه";
                break;
        }
        month=jm;
        switch(jm) {
            case 1:
                s = "فروردين";
                break;
            case 2:
                s = "ارديبهشت";
                break;
            case 3:
                s = "خرداد";
                break;
            case 4:
                s = "تير";
                break;
            case 5:
                s = "مرداد";
                break;
            case 6:
                s = "شهريور";
                break;
            case 7:
                s = "مهر";
                break;
            case 8:
                s = "آبان";
                break;
            case 9:
                s = "آذر";
                break;
            case 10:
                s = "دي";
                break;
            case 11:
                s = "بهمن";
                break;
            case 12:
                s = "اسفند";
                break;
        }

        String d = null;
        d = "";
        d += w + " ";
        day=j_day_no;
        d += Integer.toString(j_day_no+1) + " ";
        d += s + " ";
        d += Integer.toString(jy);

        return d;
    };


    private  int doubleToInt(double f)
    {
        Double fint=new Double(f);
        return fint.intValue();
    };
    public String getDayMonth(int gy1, int gm1, int gd1 ){
        int g_days_in_month[]=new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int j_days_in_month[]=new int[]{31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 29};
        int gy=gy1-1600;
        int gm=gm1-1;
        int gd=gd1-1;
        int g_day_no =
                365*gy+doubleToInt((gy+3)/4)-doubleToInt((gy+99)/100)+
                        doubleToInt((gy+399)/400);
        int i;
        for (i=0;i<gm;++i)
            g_day_no += g_days_in_month[i];
        if (gm>1 && ((gy%4==0 && gy%100!=0) || (gy%400==0)))
            g_day_no++;
        g_day_no += gd;
        int j_day_no = g_day_no-79;
        int j_np = doubleToInt(j_day_no/12053);
        j_day_no = j_day_no % 12053;
        int jy = 979+33*j_np+4*doubleToInt(j_day_no/1461);
        j_day_no %= 1461;
        if (j_day_no >= 366)
        {
            jy += doubleToInt((j_day_no-1)/365);
            j_day_no = (j_day_no-1)%365;
        }
        for (i = 0; i < 11 && j_day_no >= j_days_in_month[i]; ++i)
            j_day_no -= j_days_in_month[i];
        int jm = i+1;



        return Integer.toString(j_day_no+1)+"/"+jm;
    }

}

