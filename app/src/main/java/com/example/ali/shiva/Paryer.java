package com.example.ali.shiva;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Ali on 10/08/17.
 */

public class Paryer {
    private double month, day, longitude, latitude, XX, YY;
    private static Double PI = 3.14159265358979;
    private int h1;
    private int m1;

    public int getH1() {
        return h1;
    }

    public int getM1() {
        return m1;
    }

    public void SetGeo(double Longitude, double Latitude, double Month, double Day)
    {
        latitude = Latitude;
        longitude = Longitude;
        day = Day;
        month = Month;
    }

    public Paryer()
    {
    }

    private double loc2hor(double z, double d, double p)
    {
        return acosd((cosd(z) - sind(d) * sind(p)) / cosd(d) / cosd(p)) / 15;
    }

    private double rRound(double X, double a)
    {
        double tmp;
        tmp = mod2(X, a);
        if (tmp < 0) tmp = tmp + a;
        return tmp;
    }


    private String hms(double X)
    {
        double h, mp, m, ss;
        String s = "";
        TimeZone tz=Calendar.getInstance().getTimeZone();

        X = Floor(3600 * X);
        h = Floor(X / 3600);
        mp = X - 3600 * h;
        m = Floor(mp / 60);
        ss = Floor(mp - 60 * m);
        if (m < 0) { h = h - 1; m = 60 + m; }
        if (ss < 0) { m = m - 1; ss = 60 + ss; }

        if (tz.inDaylightTime(new Date())) h+=1;


        if (h < 10) s = "0";
        else  s = "";
        h1=(int)h;

        s = s + Integer.toString(h1) + ":";

        if (m < 0) m = 0;
        if (m < 10) s = s + "0";
        m1=(int)m;
        s = s + Integer.toString(m1);

        //if (ss < 10) s = s + "0";
        // s = s + ss.ToString();


        return s;
    }

    private double sind(double X)
    {
        return Math.sin(PI / 180 * X);
    }
    private double cosd(double X)
    {
        return Math.cos(PI / 180 * X);
    }
    private double tand(double X)
    {
        return Math.tan(PI / 180 * X);
    }
    private double atand(double X)
    {
        return Math.atan(X) * 180 / PI;
    }
    private double asind(double X)
    {
        return Math.asin(X) * 180 / PI;
    }
    private double acosd(double X)
    {
        return Math.acos(X) * 180 / PI;
    }
    private long Floor(double X)
    {
        return (int)X;
    }
    private double ASin(double X)
    {
        return Math.atan(X / Math.sqrt(-X * X + 1.01));
    }
    private double ACos(double X)
    {
        return Math.atan(-X / Math.sqrt(-X * X + 1.01)) + 2 * Math.atan(1);
    }

    private double ATan2(double X, double Y)
    {

        if (X == 0)

            if (Y == 0)
                return 0;
            else if (Y > 0)
                return PI / 2;
            else
                return -PI / 2;

        else if (X > 0)
            if (Y == 0)
                return 0;
            else
                return Math.atan(Y / X);

        else
        if (Y == 0)
            return PI;
        else
            return (PI - Math.atan(Math.abs(Y) / Math.abs(X))) * Math.signum(Y);

    }
    private double mod2(double a, double b)
    {
        return a - (b * (int)(a / b));
    }
    private void sun(double m1, double d1, double h, double lo)
    {
        double m2, l, lst, e, omega, ep, ed, u, v, theta, delta, alpha, ha, zr;
        long i;
        if (m1 < 7) d1 = 31 * (m1 - 1) + d1 + h / 24;
        else if (m1 >= 7) d1 = 6 + 30 * (m1 - 1) + d1 + h / 24;
        m2 = 74.2023 + 0.98560026 * d1;
        l = -2.75043 + 0.98564735 * d1;
        lst = 8.3162159 + 0.065709824 * Floor(d1) + 1.00273791 * 24 * mod2(d1, 1) + lo / 15;
        e = 0.0167065;
        omega = 4.85131 - 0.052954 * d1;
        ep = 23.4384717 + 0.00256 * cosd(omega);
        ed = 180 / PI * e;
        u = m2;
        for (i = 1; i <= 4; i++)
            u = u - (u - ed * sind(u) - m2) / (1 - e * cosd(u));

        v = 2 * atand(tand(u / 2) * Math.sqrt((1 + e) / (1 - e)));
        theta = l + v - m2 - 0.00569 - 0.00479 * sind(omega);
        delta = asind(sind(ep) * sind(theta));

        alpha = 180 / PI * ATan2(cosd(theta), cosd(ep) * sind(theta));

        if (alpha >= 360) alpha = alpha - 360;
        ha = lst - alpha / 15;
        zr = rRound(h - ha, 24);

        XX = zr;
        YY = delta;

    }

    public String GetAzanSobh()
    {
        double m1 = month, d1 = day, lo = longitude, la = latitude, XX1, YY1, zr, delta, ha, t1;
        sun(m1, d1, 4, lo);
        XX1 = XX;
        YY1 = YY;
        zr = XX1;
        delta = YY1;
        ha = loc2hor(108, delta, la);
        t1 = rRound(zr - ha, 24);
        sun(m1, d1, t1, lo);
        zr = XX1;
        delta = YY1;
        ha = loc2hor(108, delta, la);
        t1 = rRound(zr - ha, 24);
        return hms(t1);
    }

    public String GetTolue()
    {
        double m1 = month, d1 = day, lo = longitude, la = latitude, XX1, YY1, zr, delta, ha, t2;
        sun(m1, d1, 6, lo);
        XX1 = XX;
        YY1 = YY;
        zr = XX1;
        delta =YY1;
        ha = loc2hor(90.833, delta, la);
        t2 = rRound(zr - ha, 24);
        sun(m1, d1, t2, lo);
        zr = XX1;
        delta = YY1;
        ha = loc2hor(90.833, delta, la);
        t2 = rRound(zr - ha, 24);
        return   hms(t2);
    }

    public String GetAzanZohr()
    {
        double m1 = month, d1 = day, lo = longitude, la = latitude, XX1 = XX, zr;
        sun(m1, d1, 12, lo);
        sun(m1, d1, XX1, lo);
        zr = XX1;
        return hms(zr);
    }

    public String GetGhorub()
    {
        double m1 = month, d1 = day, lo = longitude, la = latitude, XX1=XX, YY1=YY, zr, delta, ha, t3;
        sun(m1, d1, 18, lo);
        zr = XX1;
        delta = YY1;
        ha = loc2hor(90.833, delta, la);
        t3 = rRound(zr + ha, 24);
        sun(m1, d1, t3, lo);
        zr = XX1;
        delta = YY1;
        ha = loc2hor(90.833, delta, la);
        t3 = rRound(zr + ha, 24);
        return hms(t3);
    }

    public String GetAzanMaghreb()
    {
        double m1 = month, d1 = day, lo = longitude, la = latitude, XX1 = XX, YY1 = YY, zr, delta, ha, t4;
        sun(m1, d1, 18.5, lo);
        zr = XX1;
        delta = YY1;
        ha = loc2hor(94.3, delta, la);
        t4 = rRound(zr + ha, 24);
        sun(m1, d1, t4, lo);
        zr = XX1;
        delta = YY1;
        ha = loc2hor(94.3, delta, la);
        t4 = rRound(zr + ha, 24);
        return hms(t4);
    }

    public String GetNimehShab()
    {
        TimeZone tz = Calendar.getInstance().getTimeZone();
        int m1 = (int) month, d1 = (int) day, lo = (int) longitude, la = (int) latitude, XX1 = (int) XX, zr;
        sun(m1, d1, 12, lo);
        sun(m1, d1, XX1, lo);
        zr = XX1;

        double h, mp, m, ss;
        String s = "";
        zr = (int) Floor(3600 * zr);
        h = Floor(zr / 3600);
        mp = zr - 3600 * h;

        m = Floor(mp / 60);

        ss = Floor(mp - 60 * m);
        if (m < 0) { h = h - 1; m = 60 + m; }
        if (ss < 0) { m = m - 1; ss = 60 + ss; }
        if (m < 45) m += 15; else { h++; m = (m + 15) - 60; }

        if (tz.inDaylightTime(new Date())) h += 1;

        if ((h + 11) >= 24) s = "00:";
        else
        {
            if (h < 10) s = "0";
            else s = "";
//            s = s + ""(h + 11).toString() + ":";
            s = s + Integer.toString((int) (h + 11)) + ":";
        }
        if (m < 10) s = s + "0";
//        s = s + m.ToString();
        s = s + Integer.toString((int) m);


        //if (ss < 10) s = s + "0";
        // s = s + ss.ToString();
        return s;

    }
}
