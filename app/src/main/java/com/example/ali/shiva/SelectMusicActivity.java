package com.example.ali.shiva;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SelectMusicActivity extends Activity
{
    ImageButton btadd;
    Spinner spinner;
    CheckBox checkBox;
    RecyclerView recyclerView;
    SharedPreferences.Editor editor,editor2,editor3;
    int ACTIVITY_CHOOSE_FILE=122;
    MediaPlayer mp;
    Uri uri;
    public static DatabaseHandler db;
    String ahangs;
    AdapterRecyc adapterRecyc;
    SharedPreferences preferences3;
    TextView textviewinrec;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_music);
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        // permission is granted, open the camera
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        // check for permanent denial of permission
                        if (response.isPermanentlyDenied()) {
                            // navigate user to app settings
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();

        db = new DatabaseHandler(getApplicationContext());

        btadd=(ImageButton) findViewById(R.id.buttonsaveselect);
        textviewinrec=(TextView)findViewById(R.id.textviewinrec);

        spinner=(Spinner)findViewById(R.id.spinner);
        checkBox=(CheckBox)findViewById(R.id.checkBoxselect);
        recyclerView=(RecyclerView)findViewById(R.id.recycselect) ;

//        recyclerView.addOnItemTouchListener(
//                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
//                    @Override public void onItemClick(View view, int position) {
//                        List<String> list=getList(db.getMusic());
//                        String uri=list.get(position);
//                        play(uri);
//                    }
//                })
//        );

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences preferences2 = PreferenceManager.getDefaultSharedPreferences(this);
        preferences3 = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        editor2 = preferences2.edit();
        editor3 = preferences3.edit();
        ///////////// check box /////////////////////
        Log.e("selected",preferences2.getString("azan-first", ""));
        boolean select;
        if (preferences2.getString("azan-first", "").equals("1")){
            select=true;
        }else {
            select=false;
        }
        checkBox.setChecked(select);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    editor2.putString("azan-first","1");
                    Toast.makeText(SelectMusicActivity.this, "پخش اذان در آلارم اول", Toast.LENGTH_SHORT).show();
                }else {
                    editor2.putString("azan-first","0");
                    Toast.makeText(SelectMusicActivity.this, "عدم پخش اذان در آلارم اول", Toast.LENGTH_SHORT).show();
                }
                editor2.apply();
            }
        });

        /////////
        Log.e("selected",preferences.getString("type_ahang_man", ""));
        if (!preferences.getString("type_ahang_man", "").equals("")){
            spinner.setSelection(Integer.parseInt(preferences.getString("type_ahang_man", "")));
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        editor.putString("type_ahang_man","0");
                        Toast.makeText(SelectMusicActivity.this, "موزیک ملایم", Toast.LENGTH_SHORT).show();
                        recyclerView.setVisibility(View.INVISIBLE);
                        btadd.setVisibility(View.INVISIBLE);
                        textviewinrec.setVisibility(View.INVISIBLE);
                        btadd.setEnabled(false);
                        break;
                    case 1:
                        editor.putString("type_ahang_man","1");
                        Toast.makeText(SelectMusicActivity.this, "موزیک تند", Toast.LENGTH_SHORT).show();
                        recyclerView.setVisibility(View.INVISIBLE);
                        btadd.setVisibility(View.INVISIBLE);
                        textviewinrec.setVisibility(View.INVISIBLE);
                        btadd.setEnabled(false);
                        break;
                    case 2:
                                                          editor.putString("type_ahang_man","2");
//                        Toast.makeText(SelectMusicActivity.this, "با عرض پوزش این گزینه در حال حاضر غیرفعال می باشد.", Toast.LENGTH_SHORT).show();
                                                          recyclerView.setVisibility(View.VISIBLE);
                                                          btadd.setVisibility(View.VISIBLE);
                                                          textviewinrec.setVisibility(View.VISIBLE);
                                                          btadd.setEnabled(true);
                        break;
                }
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(SelectMusicActivity.this, "لطفا آهنگ خود را انتخاب کنید", Toast.LENGTH_SHORT).show();
                editor.putString("type_ahang","0");

            }
        });

        btadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.gc();
                Intent intent = new Intent(android.content.Intent.ACTION_GET_CONTENT);
                Uri data = Uri.parse("content:///sdcard");
                String type = "audio/*";
                intent.setDataAndType(data, type);
                startActivityForResult(intent, ACTIVITY_CHOOSE_FILE);
            }
        });
        setAdapter();

    }

    @Override
    public void onBackPressed() {
        if (mp!=null){
            if (mp.isPlaying()){
                mp.stop();
                mp.release();
            }
        }
        super.onBackPressed();
    }

    private void play(String uri) {
        Log.e("URI",uri);
        if (mp!=null){
            if (mp.isPlaying()){
                mp.stop();
                mp.release();
            }
        }
        try {
            String path=java.net.URLDecoder.decode(uri, "UTF-8");
            String[] pa=path.split(":");


            mp = new MediaPlayer();
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);

            if (uri != null) {
                File filePath = new File("/mnt/extSdCard/"+pa[2]);
                String[] mp2=pa[2].split("/");
                String mp3=mp2[1].substring(0,mp2[1].length()-4);

                mp.setDataSource(filePath.getPath());

                mp.prepare();
                mp.start();
                Toast.makeText(this, mp3, Toast.LENGTH_SHORT).show();

                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
            } else {
                Toast.makeText(SelectMusicActivity.this, "Data is null...", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            Log.e("EX",exception.getMessage());
            Toast.makeText(SelectMusicActivity.this, "Error", Toast.LENGTH_SHORT).show();
        }


    }


    private void setAdapter() {
        ahangs=preferences3.getString("ahang_list", "0");

        Log.e("db",ahangs);
        /////
        if (!ahangs.equals("0")){
            adapterRecyc=new AdapterRecyc(getList(ahangs));
            ///
//            Log.e("list","OKKK");
            RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
            recyclerView.setAdapter(adapterRecyc);
        }

    }

    private List<String> getList(String list){
        List<String> list1=new ArrayList<>();

        if (list==null || list.equals("")){
            return list1;
        }
        list1= Arrays.asList(list.split("#"));

        return list1;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        Log.e("selectmusic","first method ActivityResult");
        if (requestCode == ACTIVITY_CHOOSE_FILE) {
            if (data != null) {
                uri = data.getData();
                try {
                    if (uri != null) {
                        String ahang=uri.toString();
                        Log.e("Ahang",ahang);
//                    ahangs +="#"+ahang;
//                    db.setMusic("");
                        editor3.putString("ahang_list","");
                        editor3.apply();
                        String app;
                        if (ahangs.equals("0") || ahangs.equals("")){
                            app=ahang;
                            Log.e("app1",app);
                        }else {
                            app=ahangs+"#"+ahang;
                            Log.e("app2",app);
                        }

                        Log.e("AHANGS",app);
//                    db.setMusic(app);
                        editor3.putString("ahang_list",app);
                        editor3.apply();
                        setAdapter();
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                    Toast.makeText(SelectMusicActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private class AdapterRecyc extends RecyclerView.Adapter<AdapterRecyc.Holder>{
        private List<String> list;

        private AdapterRecyc(List<String> list) {
            this.list = list;
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle,parent,false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(Holder holder, final int position) {
            final String name=list.get(position);
            String[] n=name.split("/");
            int size=n.length;
            String name2=n[size-1];
            try {
                String m=java.net.URLDecoder.decode(name2, "UTF-8");

                String[] ff=m.split("/");
                holder.textmusic.setText(ff[1]);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }



            holder.imageViewrec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteItem(position);
                }
            });
            holder.play_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    play(name);
                }
            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class Holder extends RecyclerView.ViewHolder{
            TextView textmusic;
            ImageView imageViewrec,play_bt;
            public Holder(View view){
                super(view);
                textmusic=(TextView)view.findViewById(R.id.textViewrecycleview);
                imageViewrec=(ImageView)view.findViewById(R.id.imageButton_recyc);
                play_bt=(ImageView)view.findViewById(R.id.imageButton_rec_play);
            }
        }
    }

    private void deleteItem(int position) {
        if (mp!=null){
            if (mp.isPlaying()){
                mp.stop();
                mp.release();
            }
        }
        mp=new MediaPlayer();
//        String lis=db.getMusic();
        String lis=preferences3.getString("ahang_list","");
        List<String> list=getList(lis);
        String i=list.get(position);
        Log.e("deleth1",i);
        String item;
        if (position==0){
            if (list.size()==1){
                Log.e("deleth3","1");
                item=i;
            }else {
                Log.e("deleth3","2");
                item=i+"#";
            }
        }else {
            item="#"+i;
        }
//        String item="#"+i;
        String del_str=lis.replace(item,"");
//        db.setMusic("");
//        db.setMusic(del_str);
        editor3.putString("ahang_list",del_str);
        editor3.apply();
        setAdapter();

    }

    public static class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
        private OnItemClickListener mListener;

        public interface OnItemClickListener {
            public void onItemClick(View view, int position);
        }

        GestureDetector mGestureDetector;

        public RecyclerItemClickListener(Context context, OnItemClickListener listener) {
            mListener = listener;
            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
            View childView = view.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
                mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

}
