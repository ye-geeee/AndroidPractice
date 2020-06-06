package com.example.alarmexample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button vibrationBtn;
    Button sysBeepBtn;
    Button customBeepBtn;

    Button alertBtn;
    Button listBtn;
    Button dateBtn;
    Button timeBtn;
    Button customDialogBtn;

    AlertDialog customDialog;
    AlertDialog listDialog;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vibrationBtn = findViewById(R.id.btn_vibration);
        sysBeepBtn = findViewById(R.id.btn_system_beep);
        customBeepBtn = findViewById(R.id.btn_custom_sound);

        alertBtn = findViewById(R.id.btn_alert);
        listBtn = findViewById(R.id.btn_list);
        dateBtn = findViewById(R.id.btn_date);
        timeBtn = findViewById(R.id.btn_time);
        customDialogBtn = findViewById(R.id.btn_custom);

        vibrationBtn.setOnClickListener(this);
        sysBeepBtn.setOnClickListener(this);
        customBeepBtn.setOnClickListener(this);

        alertBtn.setOnClickListener(this);
        listBtn.setOnClickListener(this);
        dateBtn.setOnClickListener(this);
        timeBtn.setOnClickListener(this);
        customDialogBtn.setOnClickListener(this);

    }

    private void showToast(String msg){
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener(){

        @Override
        public void onClick(DialogInterface dialog, int which) {
            if(dialog==customDialog && which==DialogInterface.BUTTON_POSITIVE){
                showToast("custom dialog clicked");
            }
            else if(dialog==listDialog){
                String[] datas=getResources().getStringArray(R.array.dialog_array);
                showToast(datas[which]+"clicked");
            }
            else if(dialog==alertDialog && which==DialogInterface.BUTTON_POSITIVE){
                showToast("alert dialog clicked");
            }
        }
    };

    @Override
    public void onClick(View v){

        if(v == vibrationBtn){
            Vibrator vib=(Vibrator)getSystemService(VIBRATOR_SERVICE);
            vib.vibrate(1000);
        }

        else if(v == sysBeepBtn){
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), notification);
            ringtone.play();
        }

        else if(v == customBeepBtn){
            MediaPlayer player = MediaPlayer.create(this, R.raw.bulletin);
            player.start();
        }

        else if(v == alertBtn){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setTitle("알림");
            builder.setMessage("정말로 종료하시겠습니까?");
            builder.setPositiveButton("OK", dialogListener);
            builder.setNegativeButton("NO", null);

            alertDialog=builder.create();
            alertDialog.show();
        }

        else if(v == listBtn){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("알람 벨소리");
            builder.setSingleChoiceItems(R.array.dialog_array, 0, dialogListener);
            builder.setPositiveButton("OK", null);
            builder.setNegativeButton("Canccel", null);

            listDialog = builder.create();
            listDialog.show();
        }

        else if(v == dateBtn){
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    showToast(year + ":" + (month+1) + ":" + dayOfMonth);
                }
            }, year, month, day);
            dateDialog.show();
        }

        else if(v == timeBtn){
            Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            TimePickerDialog timeDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    showToast(hourOfDay + ":" + minute);
                }
            }, hour, minute, false);
            timeDialog.show();
        }

        else if(v == customDialogBtn){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.usbdialog_layout, null);
            builder.setView(view);
            builder.setPositiveButton("OK", dialogListener);
            builder.setNegativeButton("Cancel", null);

            customDialog=builder.create();
            customDialog.show();
        }
    }
}
