package com.example.wd.flashlight;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

//public class MainActivity extends AppCompatActivity {
public class MainActivity extends Activity {

    private CameraManager manager;
    private Camera camera = null;
    private static boolean kaiguan = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //super.onCreate(savedInstanceState);

        manager = (CameraManager)getSystemService(Context.CAMERA_SERVICE);

        try{
            String [] cameraList = manager.getCameraIdList();
            for (String str:cameraList
                    ) {
                Log.d("List", str);
            }
        }catch (CameraAccessException e){
            Log.e("error",e.getMessage());
        }
        Button open_btn = (Button)findViewById(R.id.button);

        open_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    manager.setTorchMode("0",true);
                }catch(CameraAccessException e){
                    e.printStackTrace();
                }
            }
        });

        Button close_btn = (Button) findViewById(R.id.button);
        close_btn.setOnClickListener(closeOnClickListener);

        ToggleButton toggle_btn = (ToggleButton)findViewById(R.id.toggleButton);
        toggle_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            //@Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                try{
                    manager.setTorchMode("0",isChecked);
                }catch (CameraAccessException e){
                    e.printStackTrace();
                }
            }
        });
    }

    private View.OnClickListener closeOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try{
                manager.setTorchMode("0",false);
            }catch (CameraAccessException e){
                e.printStackTrace();
            }
        }
    };
}