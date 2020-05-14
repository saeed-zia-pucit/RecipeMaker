package com.example.myrecipemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class Options extends AppCompatActivity {
Button CookBook,ViewRecipe,add_new;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

    CookBook=findViewById(R.id.cookbook);
   CookBook.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           Intent intent=new Intent(getApplicationContext(),MycookBook.class);
           startActivity(intent);
       }
   });

    ViewRecipe=findViewById(R.id.view);
    ViewRecipe.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(getApplicationContext(), YourRecipes.class);
            startActivity(intent);
        }
    });
add_new=findViewById(R.id.add_new);
add_new.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getApplicationContext(),AddNewRecipe.class);
        startActivity(intent);
    }
});
    }
}
