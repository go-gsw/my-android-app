package com.jsq.anew;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity implements View.OnClickListener,AdapterView.OnItemSelectedListener  {
    private static final String TAG = "main";
    private Button button;
    private TextView textView;
    private MediaPlayer mediaPlayer;
    private int position;
    private TextView textView2;
    private Spinner spinner;
    private List<String>list;
    private ArrayAdapter<String>adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "Mainactivity onCreate");
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);
        textView2 =(TextView)  findViewById(R.id.textview2);
        spinner =(Spinner)  findViewById(R.id.spinner);
        /*textView2.setText("您設置的城市是：");*/
        list =new ArrayList<String>();
        getList();
        adapter =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        if (savedInstanceState != null) {
            textView.setText(savedInstanceState.getString("name"));
        }
        button.setOnClickListener(this);
        spinner.setOnItemSelectedListener(this);
        mediaPlayer =MediaPlayer.create(this,R.raw.thelazysong_mp3);
        mediaPlayer.start();
    }
    public List<String> getList(){
        list.add("北京");
        list.add("上海");
        list.add("廣州");
        list.add("深圳");
        list.add("武漢");
        list.add("杭州");
        list.add("重慶");
        return list;
    }

     @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.i(TAG, "Mainactivity onSaveInstanceState");
        super.onSaveInstanceState(outState);
        outState.putString("name", getString(R.string.Save));
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, second.class);
        intent.putExtra("name","gsw");
        intent.putExtra("age",21);
        startActivity(intent);
        Log.i(TAG, "点击");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "Mainactivity onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "Mainactivity onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(position!=0){mediaPlayer.seekTo(position);
        mediaPlayer.start();}
        Log.i(TAG, "Mainactivity onResume");
    }



    @Override
    protected void onPause() {

        if(mediaPlayer.isPlaying()){mediaPlayer.pause();}
        position =mediaPlayer.getCurrentPosition();
        Log.i(TAG, "Mainactivity onPause");
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null){mediaPlayer.release();mediaPlayer=null;}
        Log.i(TAG, "Mainactivity onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "Mainactivity onStop");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String cityname=adapter.getItem(position);
        /*也可以是：String cityname=list.get(position);*/
        textView2.setText(getString(R.string.choose)+cityname);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
