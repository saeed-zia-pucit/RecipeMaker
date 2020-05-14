package com.example.myrecipemaker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myrecipemaker.R;
import com.example.myrecipemaker.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="tag" ;
    Button Login,Register;
EditText Name,Password;
String name,password;
boolean status=false;
ArrayList<UserInfo> userInfoList;
    DatabaseReference rootRef,demoRef;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDbRef;
    String userId;
    ValueEventListener valueEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        userInfoList = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance();
        mDbRef = mDatabase.getReference("User");
        fetchData();

        Name = findViewById(R.id.name);
        Password = findViewById(R.id.password);
        Login = findViewById(R.id.login);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = Name.getText().toString();
                password = Password.getText().toString();
                for(UserInfo userInfo: userInfoList){
                    if(userInfo.getName().equals(name) && userInfo.getPassword().equals(password)){
                        status=true;
                        Intent intent=new Intent(getApplicationContext(),Options.class);
                        startActivity(intent);

                    }
                }
                if(!status) {
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Invalid Name or Password,try another");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }

            }
        });

        Register = findViewById(R.id.register);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = Name.getText().toString();
                password = Password.getText().toString();
        for (UserInfo userInfo: userInfoList){

            if(userInfo.getName().equals(name)){
                status=true;
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("This name already exist,try another");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                status=true;
                break;
            }
        }
        if(!status){

                userId = mDbRef.push().getKey();
                UserInfo user = new UserInfo(name, password);
                mDbRef.child(userId).setValue(user);
                Intent intent=new Intent(getApplicationContext(),Options.class);
                startActivity(intent);
            }

//database reference pointing to demo node

//Setting firebase unique key for Hashmap list

            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();

        }
public  void fetchData(){
   valueEventListener= mDbRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {


            userInfoList.clear();
            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                //getting artist
                UserInfo userInfo = postSnapshot.getValue(UserInfo.class);
                //adding artist to the list
                userInfoList.add(userInfo);
            }

            //Log.d(TAG, "User name: " + user.getName() + ", email " + user.getPassword());
            // Toast.makeText(getApplicationContext(),user.getName(),Toast.LENGTH_SHORT);

        }

        @Override
        public void onCancelled(DatabaseError error) {
// Failed to read value
            Log.w(TAG, "Failed to read value.", error.toException());
        }
    });

}

@Override
   public void onDestroy(){
        super.onDestroy();
        mDbRef.removeEventListener(valueEventListener);
}
}
