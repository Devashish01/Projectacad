
package com.example.android.time;

        import android.content.Intent;
        import android.database.sqlite.SQLiteDatabase;
        import android.media.MediaPlayer;
        import android.net.Uri;
        import android.os.CountDownTimer;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.*;

        import java.text.SimpleDateFormat;
        import java.util.Date;

public class TimerActivity extends AppCompatActivity {
    String age;
    SeekBar timerSeekBar;
    TextView timerTextView;
    Button button;
    Boolean counterActive = false;
    CountDownTimer countDownTimer;
    int ab;
    String ab1,ab2;
    int sec1=0,sec2=0;
    int h1,h2,s1,s2;
    int a,b,c,d,count=0;
    int minutes,seconds,p3,p1,p2;
    public void reset(){
        try {

         /*d=p3-minutes;
          c=p1-seconds;
          if(c>10) {
              ab2 = Integer.toString(d) + ":" + Integer.toString(c);
          }
          else
              ab2 = Integer.toString(d) + ":0" + Integer.toString(c);
*/

            //cin>>h1>>s1;
            //cin>>h2>>s2;


            sec1=s1+(h1*60);
            sec2=s2+(h2*60);
            int sub=0;
            int m=0,ss=0;
            sub=sec1-sec2;
            if(sub>60)
            {
                m = sub/60;
                ss = sub%60;
                if(ss<10) {
                    ab2 = Integer.toString(m) + ":0" + Integer.toString(ss);
                }
                    else {
                    ab2 = Integer.toString(m) + ":" + Integer.toString(ss);
                    // cout<<m<<":"<<ss;
                }
            }
            else if(sub<60)
            {
                ab2 ="0:" + Integer.toString(sub);
                //cout<<"0:"<<sub;
            }





            SQLiteDatabase db = this.openOrCreateDatabase("notesDB", MODE_PRIVATE, null);
            db.execSQL("create table if not exists list (notetext VARCHAR,notext VARCHAR,notext1 VARCHAR)");
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            db.execSQL("insert into list values('" + date + "','" + age + "','" + ab2 + "')");
            Log.i(age, "uccess");
            Log.i("notes", "Success");
            Toast.makeText(getApplicationContext(), "Notes added successfully", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            e.printStackTrace();
        }
        timerTextView.setText("0:30");
        timerSeekBar.setProgress(30);
        countDownTimer.cancel();
        button.setText("Go!!");
        timerSeekBar.setEnabled(true);
        counterActive = false;
    }

    public void updateme(int secondsLeft){

       minutes = (int) secondsLeft / 60;
         seconds = secondsLeft - minutes * 60;
            count++;
        String secondString = Integer.toString(seconds);

        if(seconds <= 9){
            secondString = "0" + secondString;
        }
        age= Integer.toString(minutes) + ":" + secondString;

        timerTextView.setText(Integer.toString(minutes) + ":" + secondString);
        h2=minutes;
        s2=seconds;
    }

    public void controlTimer(View view){

        if(counterActive == false){
            counterActive = true;
            timerSeekBar.setEnabled(false);
            button.setText("Stop!");

            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000){

                @Override
                public void onTick(long millisUntilFinished){

                    updateme((int) millisUntilFinished/1000);

                }

                @Override
                public void onFinish() {

                    reset();
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.horn);
                    mplayer.start();

                }

            }.start();
        }else{
            reset();
        }
    }

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

            Intent intent = new Intent(TimerActivity.this, TimerListActivity.class);
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
            intent.setData(Uri.parse("tel:8427180924"));
            startActivity(intent);
        }

        return true;
    }

    Button view,add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);


        timerSeekBar = (SeekBar)findViewById(R.id.timer_seekbar);
        timerTextView = (TextView)findViewById(R.id.time_text_view);
        button = (Button)findViewById(R.id.start_stop_button);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);
        a= timerSeekBar.getProgress();
        p1 = a % 60;
        p2 = a / 60;
        p3 = p2 % 60;
        p2 = p2 / 60;
        h1=p3;
        s1=p1;
        ab1=Integer.toString(a);
//        if(a<60)
//        {
//            ab1="0"+":"+Integer.toString(a);
//        }
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                a= timerSeekBar.getProgress();
               // a=a/60;
                 p1 = a % 60;
                 p2 = a / 60;
                 p3 = p2 % 60;
                p2 = p2 / 60;
                updateme(progress);
                ab1=Integer.toString(p3)+":"+Integer.toString(p1);
               h1=p3;
               s1=p1;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}