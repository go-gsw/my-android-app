package com.jsq.anew;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 尚文 on 2017/1/14.
 */

public class second extends Activity implements View.OnClickListener {
    private TextView textView;
    private ProgressBar progressBar;
    private Button add;
    private Button reduce;
    private Button reset;
    private TextView text;
    private ProgressDialog progressDialog;
    private Button show;
    private Button web1;/*
    private int first =progressBar.getProgress();
    private int second =progressBar.getSecondaryProgress();
    private int max =progressBar.getMax();*/

    private static final String TAG = "main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_main);
        textView = (TextView) findViewById(R.id.textView2);
        Button button = (Button) findViewById(R.id.lll);
        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            int age = intent.getIntExtra("age", 0);
            textView.setText("name=" + name + "   age=" + age);
        }
        Log.i(TAG, "second onCreate");
        button.setOnClickListener(this);
        init();
    }


    private void init() {
        web1 = (Button) findViewById(R.id.web1);
        show = (Button) findViewById(R.id.show);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        add = (Button) findViewById(R.id.add1);
        reduce = (Button) findViewById(R.id.reduce);
        reset = (Button) findViewById(R.id.reset);
        text = (TextView) findViewById(R.id.text);
        int first = progressBar.getProgress();
        int second = progressBar.getSecondaryProgress();
        int max = progressBar.getMax();
        text.setText(getString(R.string.first_progress) + (int) (first / (float) max * 100) +
                getString(R.string.second_progress) + (int) (second / (float) max * 100) + "%");
        show.setOnClickListener(this);
        add.setOnClickListener(this);
        reduce.setOnClickListener(this);
        reset.setOnClickListener(this);
        web1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lll:
                Intent intent = new Intent(this, third.class);
                startActivity(intent);
                break;
            case R.id.add1:
                progressBar.incrementProgressBy(10);
                progressBar.incrementSecondaryProgressBy(10);
                break;
            case R.id.reduce:
                progressBar.incrementProgressBy(-10);
                progressBar.incrementSecondaryProgressBy(-10);
                break;
            case R.id.reset:
                progressBar.setProgress(50);
                progressBar.setSecondaryProgress(80);
                break;
            case R.id.show: {
                progressDialog = new ProgressDialog(this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setTitle(getString(R.string.welcome));
                progressDialog.setMessage(getString(R.string.thanks));
                progressDialog.setIcon(R.drawable.timg_03);
                progressDialog.setMax(100);
                progressDialog.incrementProgressBy(55);
                progressDialog.setIndeterminate(false);
                progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.queding),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(second.this, getString(R.string.huanying), Toast.LENGTH_SHORT).show();
                            }
                        });
                progressDialog.setCancelable(true);
                progressDialog.show();
                break;
            }
            case R.id.web1:
                Intent intent1 = new Intent(second.this, web.class);
                startActivity(intent1);
                break;
        }
        int first = progressBar.getProgress();
        int second = progressBar.getSecondaryProgress();
        int max = progressBar.getMax();
        text.setText(getString(R.string.first_progress) + (int) (first / (float) max * 100) +
                getString(R.string.second_progress) + (int) (second / (float) max * 100) + "%");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "second onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "second onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "second onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "second onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "second onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "Mainactivity onStop");
    }
}
