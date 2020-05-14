package com.example.myrecipemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.SearchView.OnQueryTextListener;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class YourRecipes extends AppCompatActivity {

    private static final String TAG ="TAG" ;
    SearchView searchView;
    ListView listView;
   ArrayList<RecipeModel> recipeList;
    ArrayList<String> title_list;

    ArrayAdapter<String > adapter;
    private FirebaseDatabase mDatabase1;
    private DatabaseReference mDbRef1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_recipes);
        title_list=new ArrayList<>();
        recipeList=new ArrayList<>();
        //

        mDatabase1 = FirebaseDatabase.getInstance();
        mDbRef1 = mDatabase1.getReference("Recipe");
        fetchData();


        searchView = (SearchView) findViewById(R.id.searchFilter);
        listView = (ListView) findViewById(R.id.list_view);



        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,title_list);
        listView.setAdapter(adapter);

listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String s=parent.getItemAtPosition(position).toString();

        Intent intent=new Intent(getApplicationContext(),RecipeDetail.class);
       for(RecipeModel recipeModel: recipeList){
           if(recipeModel.getTitle().equals(s)){
               intent.putExtra("object",recipeModel);
               intent.putExtra("database","firebase");
               startActivity(intent);
           }
       }




    }
});

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(title_list.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(getApplicationContext(), "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(),AddNewRecipe.class);
                startActivity(intent);


            }
        });

    }

    public  void fetchData(){
        mDbRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                title_list.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    RecipeModel recipeModel = postSnapshot.getValue(RecipeModel.class);
                    recipeList.add(recipeModel);
                    title_list.add(recipeModel.getTitle().toString());
                }
                    adapter.notifyDataSetChanged();
                //Log.d(TAG, "User name: " + user.getName() + ", email " + user.getPassword());
                // Toast.makeText(getApplicationContext(),user.getName(),Toast.LENGTH_SHORT);
                // adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
// Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}




