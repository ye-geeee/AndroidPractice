package com.yeji.dbms;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import io.realm.Realm;

public class RealmReadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_read);

        TextView titleView = findViewById(R.id.realm_read_title);
        TextView contentView = findViewById(R.id.realm_read_content);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");

        Realm mRealm = Realm.getDefaultInstance();
        MemoVO vo = mRealm.where(MemoVO.class).equalTo("title", title).findFirst();
        titleView.setText(vo.title);
        contentView.setText(vo.content);
    }

}
