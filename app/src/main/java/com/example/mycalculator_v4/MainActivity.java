package com.example.mycalculator_v4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.File;

public class MainActivity extends AppCompatActivity {
    private TextView mResult;
    private double result;
    private String op;
    private boolean isUsual=true;
    public static ImageView im;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mResult=findViewById(R.id.result);
        im=findViewById(R.id.imageView);
        final View usual = findViewById(R.id.simpleCalc);
        final View unusual = findViewById(R.id.engineeringCalc);
        Button switchBtn = findViewById(R.id.btnSwitch);
        switchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isUsual){
                    usual.setVisibility(View.INVISIBLE);
                    unusual.setVisibility(View.VISIBLE);
                    mResult=findViewById(R.id.result_en);
                    isUsual=false;
                }else {
                    usual.setVisibility(View.VISIBLE);
                    unusual.setVisibility(View.INVISIBLE);
                    mResult=findViewById(R.id.result);
                    isUsual=true;
                }
            }
        });

        Button settingBtn=findViewById(R.id.btnSetting);
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
    }

    public static void LoadImg(String fileName){
        //досупность хранилища
        if(isExternalStorageReadable()){
            //Получаем ссылку на файл
            File pic= new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),fileName);
            //Загружаем картинку
            Bitmap bitmap= BitmapFactory.decodeFile(pic.getAbsolutePath());
            im.setImageBitmap(bitmap);

        }

    }

    public static boolean isExternalStorageReadable(){
        String state=Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)|| Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
            return true;
        }
        return false;
    }

    public void onNumberClick(View view){
        Button button = (Button)view;
        mResult.append(button.getText());
    }

    public void onOperationClick(View view){
        Button button = (Button)view;
        mResult.append(button.getText());
        op=button.getText().toString();
    }
    public void onNegativeNumClick(View view){
        //Button button = (Button)view;
        mResult.append("-");

    }

    public void onClearClick(View view){
        mResult.setText("");
    }

    public void onResultClick(View view) {
        Button button = (Button) view;
        mResult.append(button.getText());

        //String [] temp = mResult.getText().toString().split("^|\\+-|/|[-]+|=|\\+|\\*|%");
        String [] temp = mResult.getText().toString().split("^|/|-|=|\\+|\\*|%");
        Double num1=Double.parseDouble(temp[0]);
        if(temp.length>2) temp[1]="-"+temp[2];

        Double num2=Double.parseDouble(temp[1]);
        switch(op) {
            case "+":
                result = num1 + num2;
                mResult.append(Double.toString(result));
                break;
            case "-":
                result = num1 - num2;
                mResult.append(Double.toString(result));
                break;
            case "*":
                result = num1 * num2;
                mResult.append(Double.toString(result));
                break;
            case "/":
                result = num1 / num2;
                mResult.append(Double.toString(result));
                break;
            case "%":
                result = (num1/100) * num2;
                mResult.append(Double.toString(result));
                break;
        }

    }
}