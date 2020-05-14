package com.example.myrecipemaker;



import android.app.Activity;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class RecipeAdapter extends ArrayAdapter<String> implements Filterable {

    private final Activity context;
    private final ArrayList<String> titles;

    public RecipeAdapter(Activity context, ArrayList<String> titles,int layout) {
        super(context, layout,titles);
        // TODO Auto-generated constructor stub
        this.context=context;
        this.titles=titles;



    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.title_list, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);


        titleText.setText(titles.get(position));

//        detail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final TextView tv_id = (TextView) v.findViewById(R.id.trip_name);
//                String name= trip_names.get(position).toString();
//
//
//                Intent intent=new Intent(context,TripDetail.class);
//                intent.putExtra("trip_name",name);
//                context.startActivity(intent);
//            }
//        });


        return rowView;

    };


    public  void addnewTrip(String s){
        //trip_names.add(s);
        //notifyDataSetChanged();
    }
}
