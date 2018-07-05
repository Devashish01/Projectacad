package com.example.android.time;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.time.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TimerListActivity extends AppCompatActivity {

    ArrayList<String> notes,notes1;

    ListView listView;

    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_list);
        listView=(ListView)findViewById(R.id.listview);

        getNotes();

    }

    void getNotes(){
        int j=0;

        notes= new ArrayList<String >();
        notes1= new ArrayList<String >();
        try{

            SQLiteDatabase db=this.openOrCreateDatabase("notesDB",MODE_PRIVATE,null);
            Cursor c=db.rawQuery("select * from list ",null);
            int noteIndex= c.getColumnIndex("notetext");
            int noteIndex1= c.getColumnIndex("notext");
            int noteIndex2= c.getColumnIndex("notext1");
            c.moveToFirst();
            while (c!=null){

                Log.i("Notes",c.getString(noteIndex));
                Log.i("Notes1",c.getString(noteIndex1));
              //  notes.add("c.getString(noteIndex");
                notes.add(c.getString(noteIndex)+" timer"+j+" "+c.getString(noteIndex2));
               // notes.add(date+" timer"+j+" "+c.getString(noteIndex1));
                c.moveToNext();
                j++;
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {

            arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,notes);
            listView.setAdapter(arrayAdapter);
          //  arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2,notes1);
            //listView.setAdapter(arrayAdapter);
        }

    }
}