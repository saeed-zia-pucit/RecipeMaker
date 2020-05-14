package com.example.myrecipemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.DropBoxManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AddNewRecipe extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{
CheckBox checkBox,checkBox2,checkBox3,checkBox4,checkBox5;
Button add;
EditText title,desc,inst,time;
Spinner spinner;
String recipe_type;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDbRef,mDbRef1;
    private String recipeId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_recipe);

        title=findViewById(R.id.title);
        desc=findViewById(R.id.desc);
        inst=findViewById(R.id.inst);
        time=findViewById(R.id.prep);

       checkBox=findViewById(R.id.checkBox);
        checkBox2=findViewById(R.id.checkBox2);
        checkBox3=findViewById(R.id.checkBox3);
        checkBox4=findViewById(R.id.checkBox4);
        checkBox5=findViewById(R.id.checkBox5);

        final ArrayList<CheckBox> boxes=new ArrayList<>();
        boxes.add(checkBox);
        boxes.add(checkBox2);
        boxes.add(checkBox3);
        boxes.add(checkBox4);
        boxes.add(checkBox5);



ArrayList<String> types=new ArrayList<String>();
types.add("Break fast");
types.add("Lunch");
types.add("Dinner");
        spinner = (Spinner) findViewById(R.id.type);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddNewRecipe.this,
                android.R.layout.simple_spinner_item, types);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                recipe_type= spinner.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    add=findViewById(R.id.add_recipe);
    add.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String text="";
//            ArrayList<String>ing=new ArrayList<>();
            for (CheckBox item : boxes){
                if(item.isChecked())
                    text=text+item.getText().toString()+",";
            }
//            DBhelper dBhelper=new DBhelper(getApplicationContext());
//            dBhelper.insertRecipe(new RecipeModel(title.getText().toString(),desc.getText().toString(),
//                    inst.getText().toString(),recipe_type,time.getText().toString(),text));
//


            mDatabase = FirebaseDatabase.getInstance();
            mDbRef = mDatabase.getReference("Recipe");
            recipeId = mDbRef.push().getKey();
            RecipeModel recipeModel=new RecipeModel(title.getText().toString(),desc.getText().toString(),
                    inst.getText().toString(),recipe_type,time.getText().toString(),text);
            mDbRef.child(recipeId).setValue(recipeModel);


        }
    });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
