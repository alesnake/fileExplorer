package com.example.ale.alfileexplorer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1 ;
    ListView lista;
    String directorioRaiz= Environment.getExternalStorageDirectory().getPath();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);

            }
        }

        lista=(ListView) findViewById(R.id.lista);

        Button toPrincipal=(Button)findViewById(R.id.directorioPrincipal);

        toPrincipal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mostrarCarpetas(directorioRaiz,directorioRaiz,lista);
            }
        });

        mostrarCarpetas(directorioRaiz,directorioRaiz,lista);

        lista.setClickable(true);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object o = lista.getItemAtPosition(i);

                File f=(File)o;

                if(f.isFile()){
                    Toast.makeText(getBaseContext(),"Has accededido a: " +
                            ""+f.getName(),Toast.LENGTH_SHORT).show();
                }else if(f.isDirectory()){

                    f=new File(f.getAbsolutePath());
                    /**
                    File[]listaArchivos =f.listFiles();
                    Toast.makeText(getBaseContext(),f.getPath(),Toast.LENGTH_SHORT).show();
                    */

                    mostrarCarpetas(f.getPath(),directorioRaiz,lista);
                }
            }
        });


    }

    public void mostrarCarpetas(String directorioActual, String directorioRaiz, ListView lista){

        File now=new File(directorioActual);

        TextView actual=(TextView)findViewById(R.id.pathActual);
        actual.setText(now.getPath());

        File[]listaArchivos =now.listFiles();

        if(listaArchivos.length==0){
            Toast.makeText(getBaseContext(),"Esta carpeta esta vacia",Toast.LENGTH_SHORT).show();
        }

        adapter ad=new adapter(this,listaArchivos);

        lista.setAdapter(ad);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                if (grantResults.length > 1
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                return;
            }
        }

    }
}
