package com.example.myrecipemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;

public class MycookBook extends AppCompatActivity {

    private RecipeAdapter adapter;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_recipes);

        ArrayList<String> ing=new ArrayList<>();


        //
        ArrayList<String>title=new ArrayList<String>();
        DBhelper dBhelper=new DBhelper(getApplicationContext());
      title=dBhelper.getTitleList();

        adapter = new RecipeAdapter(this, title, R.layout.title_list);


        list=(ListView)findViewById(R.id.list_view);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub


//
                Intent intent=new Intent(getApplicationContext(), RecipeDetail.class);
                String s=parent.getItemAtPosition(position).toString();
                intent.putExtra("database","sqlite");
                intent.putExtra("title",s);
                startActivity(intent);

                //                final TextView tv_id = (TextView) view.findViewById(R.id.employe_phone);
//                String phone = tv_id.getText().toString();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();

//
//


            }
        });


    }
}
