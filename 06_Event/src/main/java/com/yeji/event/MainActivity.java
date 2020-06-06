package com.yeji.event;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    TextView bellTextView;
    TextView labelTextVeiw;
    CheckBox repeatCheckView;
    CheckBox vibrateCheckView;
    Switch switchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bellTextView = findViewById(R.id.bell_name);
        labelTextVeiw=findViewById(R.id.label);
        repeatCheckView=findViewById(R.id.repeatCheck);
        vibrateCheckView=findViewById(R.id.vibrate);
        switchView=findViewById(R.id.onOff);

        bellTextView.setOnClickListener(this);
        labelTextVeiw.setOnClickListener(this);
        repeatCheckView.setOnCheckedChangeListener(this);
        vibrateCheckView.setOnCheckedChangeListener(this);
        switchView.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==bellTextView) {
            showToast("bell text click event");
        }else if(v==labelTextVeiw){
            showToast("label text click event");
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(buttonView==repeatCheckView){
            showToast("repeat checkbox is " + isChecked);
        }else if(buttonView==vibrateCheckView){
            showToast("vibrate checkbox is " + isChecked);
        }else if(buttonView==switchView){
            showToast("switch checkbox is " + isChecked);
        }
    }

    private void showToast(String message){
        Toast toast=Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
