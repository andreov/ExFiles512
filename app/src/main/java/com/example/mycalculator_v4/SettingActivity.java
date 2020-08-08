package com.example.mycalculator_v4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import static com.example.mycalculator_v4.MainActivity.LoadImg;

public class SettingActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_PERMISSION = 11;
    public String fileName;
    public EditText textSt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        textSt = findViewById(R.id.editTextSt);
        fileName= textSt.getText().toString();
        Button buttonSt=findViewById(R.id.buttonSt);

        buttonSt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //проверяем разрешение
                int permissionStatus= ContextCompat.checkSelfPermission(SettingActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
                if(permissionStatus== PackageManager.PERMISSION_GRANTED){
                    //Toast.makeText(SettingActivity.this,fileName,Toast.LENGTH_LONG).show();
                    LoadImg(fileName);
                    // если нет разрешения запрашиваем
                }else {
                    //вызываем окошко на разрешение
                    ActivityCompat.requestPermissions(SettingActivity.this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE_PERMISSION);

                }
                finish();
            }
        });
    }

    //что ответил пользователь
    @Override
    public void onRequestPermissionsResult(int requestCode, String[]permissions,int[]grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        switch (requestCode){
            case REQUEST_CODE_PERMISSION:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    LoadImg(fileName);
                }else {
                    Toast.makeText(SettingActivity.this,"У вас нет разрешения на чтение",Toast.LENGTH_LONG).show();
                    finish();
                }
        }
    }
}