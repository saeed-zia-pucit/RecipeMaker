package com.example.myrecipemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RecipeDetail extends AppCompatActivity {
    private static final String TAG ="tag" ;
    TextView title,desc,inst,time,ingr,type;
Button add_to_cookbook,share_recipe;
    private FirebaseDatabase mDatabase1;
    private DatabaseReference mDbRef1;
    ValueEventListener valueEventListener;
    RecipeModel recipeModel;
    String title1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        mDatabase1 = FirebaseDatabase.getInstance();
        mDbRef1 = mDatabase1.getReference("Recipe");

        recipeModel=new RecipeModel();
        Intent intent=getIntent();
        recipeModel=(RecipeModel) intent.getSerializableExtra("object");
        String database=intent.getStringExtra("database");
        title1=intent.getStringExtra("title");
        if(database.equals("firebase")){
          //  getFromFirebase();

        }
        else{
            DBhelper dBhelper=new DBhelper(getApplicationContext());
            recipeModel= dBhelper.getDetail(title1);

        }

        share_recipe=findViewById(R.id.ShareRecipe);
        title=findViewById(R.id.title);
        desc=findViewById(R.id.desc);
        inst=findViewById(R.id.inst);
        time=findViewById(R.id.time);
        type=findViewById(R.id.type);
        ingr=findViewById(R.id.ingr);

      title.setText(recipeModel.getTitle());
      desc.setText(recipeModel.getDescrption());
      inst.setText(recipeModel.getInstruction());
      time.setText(recipeModel.getPrepare_time());
      ingr.setText(recipeModel.getIngrediens());
      type.setText(recipeModel.getType());

add_to_cookbook=findViewById(R.id.add_to_cook);
add_to_cookbook.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        DBhelper dBhelper=new DBhelper(getApplicationContext());
        dBhelper.insertRecipe(new RecipeModel(title.getText().toString(),desc.getText().toString(),
                inst.getText().toString(),type.getText().toString(),time.getText().toString(),ingr.getText().toString()));
    }
});
share_recipe.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        String message = recipeModel.getTitle()+recipeModel.getDescrption()+recipeModel.getIngrediens();
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, message);

        startActivity(Intent.createChooser(share, "Title of the dialog the system will open"));

    }
});
    }
    public  void getFromFirebase(){
       valueEventListener= mDbRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    recipeModel = postSnapshot.getValue(RecipeModel.class);
                    if(recipeModel.getTitle().equals(title1)){
                      break;
                    }
                }
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
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //mDbRef1.removeEventListener(valueEventListener);
    }

}
