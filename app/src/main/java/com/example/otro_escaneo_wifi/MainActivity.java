package com.example.otro_escaneo_wifi;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.net.wifi.WpsInfo;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.net.wifi.ScanResult;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    WifiManager wifiManager;
    WifiReceiver wifiReceiver;
    ListAdapter listAdapter;
    ListView wifiList;
    List<ScanResult> mywifiList;

    TextView WpsCallbakInfo;

    Button botonWPS;

    WifiManager.WpsCallback wpsListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main) ;

        wifiList = findViewById(R.id.myListView);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiReceiver= new WifiReceiver();
        WpsCallbakInfo = findViewById(R.id.WpsCallbakInfo);

        wpsListener = new WifiManager.WpsCallback() {
            @Override
            public void onStarted(String s) {
                WpsCallbakInfo.setText(s);
                Log.i("onStarted", "onStarted : " + s);
            }

            @Override
            public void onSucceeded() {
                Log.i("onSucceeded", "onSucceeded");
            }

            @Override
            public void onFailed(int i) {
                Log.i("onFailed", "onFailed : " + i);

            }
        };



        registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},0);

        }
        else{
            scanWifiList();
            
        }
        botonWPS = findViewById(R.id.buttonWPS);

        botonWPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScanWPS();
            }
        });


    }

    private void ScanWPS(){
        wifiManager.startWps(new WpsInfo(), wpsListener);
    }


    private void scanWifiList() {

        /*wifiManager.startScan();*/
        mywifiList = wifiManager.getScanResults();
        setAdapter();

    }

    private void setAdapter() {

        listAdapter = new ListAdapter(getApplicationContext(), mywifiList);
        wifiList.setAdapter(listAdapter);
    }


    static class WifiReceiver extends BroadcastReceiver{


        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }
}