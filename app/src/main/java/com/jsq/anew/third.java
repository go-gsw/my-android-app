package com.jsq.anew;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by 尚文 on 2017/1/18.
 */

public class third extends Activity implements AdapterView.OnItemClickListener {
    private GridView gridView;
    private List<Map<String,Object>>dataList;
    private int[] icon={R.drawable.timg_03,R.drawable.timg_06,R.drawable.timg_25,
            R.drawable.timg_09 ,R.drawable.timg_13,R.drawable.timg_17,R.drawable.timg_21,R.
            drawable.timg_14};
    private String[] iconname={"通讯录","电话","短信","照相","音乐","游戏","时间","设置"};
    private SimpleAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third);
        gridView=(GridView)  findViewById(R.id.gridview);
        dataList =new ArrayList<Map<String,Object>>();
        adapter =new SimpleAdapter(this,getData(),R.layout.item,new String[]{"image","text"},
                new int[]{R.id.image,R.id.text});
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);

    }
    public List<Map<String,Object>> getData(){
        for(int i=0;i<icon.length;i++){
        Map<String,Object>map =new HashMap<String,Object>();
        map.put("image",icon[i]);
        map.put("text",iconname[i]);
        dataList.add(map);}
        return dataList;
    }
    

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,"我是"+iconname[position],Toast.LENGTH_SHORT).show();
    }
 }
