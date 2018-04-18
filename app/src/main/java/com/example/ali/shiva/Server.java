package com.example.ali.shiva;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class Server {
    Context context;
    String mac;
    int day, year, day2;
    SharedPreferences SP;
    DatabaseHandler db;


    public Server(Context context) {
        this.context = context;
    }

    public void sendToServer(final int emtiaz) {
        db = new DatabaseHandler(context);

        /////////////////MAC ADDRESS /////////////////////////////////////////////

        mac = db.get_mac();
        ////////////////////////////////CAl///////////
        Calendar calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_YEAR);
        year = calendar.get(Calendar.YEAR);
        ///////////////////CITY
        SP = PreferenceManager.getDefaultSharedPreferences(context);
        final String city = SP.getString("city", "31.89:54.36");
        /////////////////////////EMTAZ1???????????
        final String emtiaz1 = String.valueOf(db.get_emtiaz1());

        String url = "http://www.ali-zareshahi.ir/saveBidar.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                //////code recive inja
                Log.e("MSG", s);
                try {
                    JSONObject data = new JSONObject(s);
                    String status = data.getString("status");
                    String header = data.getString("header");
                    String msg = data.getString("msg");
                    String writer = data.getString("writer");
                    String view = data.getString("view");

                    if (status.equals("ok")) {
                        db.update_send("T");
                        if (!header.equals("")){
                            db.update_header(header);
                            db.update_msg(msg);
                            db.update_writer(writer);
                            db.update_view(view);
                            setNotifi(header, writer);
                        }



                    } else {
                        db.update_send("F");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                db.update_send("F");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();

                map.put("mac_address", mac);
                map.put("emtiaz", String.valueOf(emtiaz));
                map.put("city", city);
                map.put("day", String.valueOf(day));
                map.put("year", String.valueOf(year));
                map.put("emtiaz1", emtiaz1);
//
                Log.e("server@@@@@", "SEND TO SERVER");


                return map;

            }
        };
        RequestQueue myqu = Volley.newRequestQueue(context);
        myqu.add(request);


    }

    public void sendNagar(final String name, final String nagar, final float emtiaz) {

        String url = "http://www.ali-zareshahi.ir/save_nagarat.php";
        Calendar c = Calendar.getInstance();
        final int day = c.get(Calendar.DAY_OF_YEAR);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                //////code recive inja
                if (s.equals("ok")) {
                    Toast.makeText(context, "پیام با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context, "اشکال در تماس با اینترنت", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();

                map.put("name", name);
                map.put("nagar", nagar);
                map.put("star", String.valueOf(emtiaz));
                map.put("day", String.valueOf(day));

//
                Log.e("server@@@@@", "SEND TO SERVER image");


                return map;

            }
        };
        RequestQueue myqu = Volley.newRequestQueue(context);
        myqu.add(request);


    }




    public void getImage() {
        final DatabaseHandler db = new DatabaseHandler(context);


        String url = "http://www.ali-zareshahi.ir/get_image.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                //////code recive inja
                db.update_emtiaz2(s);
                Log.e("server@@@@@", "LOAD TO DB image");

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context, "Error in internet", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();

                map.put("req", "@ndroid_Image64_Al!");

//
                Log.e("server@@@@@", "SEND TO SERVER image");


                return map;

            }
        };
        RequestQueue myqu = Volley.newRequestQueue(context);
        myqu.add(request);


    }

    private void setNotifi(String msg, String wirter) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(context, ScrollingActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent1, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        builder.setTicker(msg + wirter);
        builder.setContentTitle("مطلب جدید...");
        builder.setContentText(msg);
        builder.setSmallIcon(R.drawable.mail);

        builder.setContentIntent(pendingIntent);

        Notification n = builder.build();

        // create the notification
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(uri);

        n.flags = Notification.FLAG_INSISTENT;
//        n.defaults |= Notification.DEFAULT_SOUND;
        n.flags |= Notification.FLAG_AUTO_CANCEL;
//        n.sound = Uri.parse("android.resource://com.example.ali/" + R.raw.alarmbuzzer);
//        n.vibrate = new long[]{150, 300, 150, 400};
        notificationManager.notify(R.drawable.mail, n);


    }

    public void getEmtiaz() {
        Calendar calendar = Calendar.getInstance();
        db = new DatabaseHandler(context);
        day2 = calendar.get(Calendar.DAY_OF_YEAR);
        day2 = day2 - 1;
        if (day2 == 0) {
            day2 = 365;
        }
        String url = "http://www.ali-zareshahi.ir/getEmtiaz.php";
        StringRequest request2 = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                //////code recive inja
                Log.e("GET EMTIAZ",s);
                try {
                    JSONObject data = new JSONObject(s);
                    String rotbeh = data.getString("emtiaz");
                    String total = data.getString("total");

                    if (!rotbeh.equals("0") && !total.equals("0")) {
                        db.update_rotbeh(rotbeh);
                        db.update_total(total);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                db.update_send("F");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();


                /////////////////MAC ADDRESS /////////////////////////////////////////////


                map.put("mac", db.get_mac());
                map.put("day", String.valueOf(day2));
                Log.e("GET EMTIAZ","send");
//
                return map;

            }

        };
        RequestQueue myqu = Volley.newRequestQueue(context);
        myqu.add(request2);
    }
}
