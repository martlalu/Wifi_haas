package com.example.otro_escaneo_wifi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    List<ScanResult> wifiList;

    public ListAdapter(Context context, List<ScanResult> wifiList) {
        this.context = context;
        this.wifiList = wifiList;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            }

    @Override
    public int getCount() {
        return wifiList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder1 holder1;
        View view = convertView;
        if(view==null){

            view = inflater.inflate(R.layout.list_item, null);
            holder1 = new Holder1();

            holder1.tvDetails = view.findViewById(R.id.txtWifiName);
            view.setTag(holder1);
                    }
        else{
            holder1 = (Holder1) view.getTag();

        }

        holder1.tvDetails.setText(wifiList.get(position).SSID);
        return view;


    }

    static class Holder1{
        TextView tvDetails;

    }

}
