package com.example.realtimedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText edittext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edittext = findViewById(R.id.edittext);
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Owner");
        Log.d("owner", myRef.toString());
//        myRef.setValue("Rishabh Srivastava");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String value = snapshot.getValue(String.class);
                    Log.d("Avalue is : ", value);
                    edittext.setText(value);
                } else {
                    Log.d("tag","1");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("FAiled to read value", error.toString());
            }
        });


        EditText name = findViewById(R.id.name);
        EditText address = findViewById(R.id.address);
        EditText mobile = findViewById(R.id.mobile);

        Button button = findViewById(R.id.button);
        DatabaseReference myData = FirebaseDatabase.getInstance().getReference("Owner1");
        String myDataId = "profile";
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = name.getText().toString();
                String address1 = address.getText().toString();
                String mobile1 = mobile.getText().toString();

                DataModel data = new DataModel(name1, address1, mobile1);
                myData.child(myDataId).setValue(data);
            }
        });
        Log.d("tag","1");

        Log.d("tag myData",myData.toString());
        Log.d("tag myDataId",myDataId);
        myData.child(myDataId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("tag","2");
                if(snapshot.exists())
                {
                    Log.d("tag","3");
                    DataModel data = snapshot.getValue(DataModel.class);

                    Log.d("tag","4");
                    name.setText(data.getName());
                    address.setText(data.getAddress());
                    mobile.setText(data.getMobile());
                }
                Log.d("tag","5");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("tag","6");
            }
        });
        Log.d("tag","7");
    }
}