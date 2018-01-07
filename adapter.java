package com.example.ale.alfileexplorer;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by ale on 25/12/17.
 */

public class adapter extends BaseAdapter {
    protected Activity activity;
    File[]listaArchivos;

    public adapter (Activity activity, File[]asd) {
        this.activity = activity;
        this.listaArchivos = asd;
    }

    @Override
    public int getCount() {
        return listaArchivos.length;
    }

    @Override
    public Object getItem(int i) {
        return listaArchivos[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = view;

        if (view == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.lista, null);
        }

        File f=listaArchivos[i];


        TextView tv=(TextView)v.findViewById(R.id.txt);
        tv.setText(f.getName());

        ImageView img=(ImageView)v.findViewById(R.id.image);

        if(f.isDirectory()){
            img.setImageResource(R.drawable.carpeta_icon);
        }else if(f.isFile()){
            img.setImageResource(R.drawable.file_icon);
        }

        return v;
    }
}
