package com.example.quantumuc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private Button addData;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance("https://quantumuc-683e5-default-rtdb.firebaseio.com/");
        myRef = database.getReference();

        addData = (Button) findViewById(R.id.post_button);
        addData.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                EditText msg = (EditText) findViewById(R.id.message_text);
                System.out.println(msg.getText().toString());
                myRef.child("AllMessages").child("08012021_Messages").setValue(msg.getText().toString());

                //JSON Tree should be something similar:
                /*
                AllMessages:
                {
                  $MessageID:
                  {
                    Neon:
                    {
                      message: "Hi everyone!"
                      time: 2021-08-10 12:00:30
                      replies:
                      {

                      }
                    }
                  }
                  $MessageID:
                  {
                    User01:
                    {
                      message: "Is this thing on?"
                      time: 2021-08-10 12:10:30
                      replies:
                      {

                      }
                    }
                  }
                  ...
                }


                Users:
                {
                  Neon:
                  {
                      message: "Hi everyone!"
                      time: 2021-08-10 12:00:30
                  }
                  User01:
                  {
                      message: "Is this thing on?"
                      time: 2021-08-10 12:10:30
                  }
                  ...
                }
                */

            }
        });

    }
}