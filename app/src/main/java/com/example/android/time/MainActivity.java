package com.example.android.time;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.spark.submitbutton.SubmitButton;

public class MainActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.prev)
        {

            Intent intent = new Intent(MainActivity.this, TimerListActivity.class);
            startActivity(intent);
        }
        else if (id==R.id.google)
        {
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.setClassName("com.google.android.googlequicksearchbox", "com.google.android.googlequicksearchbox.SearchActivity");
            startActivity(intent);
        }
        else if (id==R.id.call)
        {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:9779706779"));
            startActivity(intent);
        }

        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SubmitButton button = (SubmitButton)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ButtonState","Button Clicked");
                Intent intent = new Intent(MainActivity.this,TimerActivity.class);
                startActivity(intent);


            }
        });

        SubmitButton button1 = (SubmitButton)findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ButtonState","Button Clicked");
                Intent intent = new Intent(MainActivity.this, TimerListActivity.class);
                startActivity(intent);


            }
        });

        FloatingActionButton button1111 = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        button1111.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);

                intent.setClassName("com.google.android.googlequicksearchbox", "com.google.android.googlequicksearchbox.SearchActivity");
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                startActivity(intent);
            }
        });

//        SubmitButton button111 = (SubmitButton) findViewById(R.id.button311);
//        button111.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
//
//                intent.setClassName("com.google.android.googlequicksearchbox", "com.google.android.googlequicksearchbox.SearchActivity");
////                try {
////                    Thread.sleep(3000);
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
//        startActivity(intent);
//            }
//        });

    }

}
