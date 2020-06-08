package com.yeji.dbms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText titleView;
    EditText contentView;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleView = findViewById(R.id.add_title);
        contentView = findViewById(R.id.add_content);
        addBtn = findViewById(R.id.add_btn);

        addBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final String title = titleView.getText().toString();
        final String content = contentView.getText().toString();

        /* DB Helper Example
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("insert into tb_memo (title, content) values (?, ?)",
                new String[]{title, content});
        db.close();

        Intent intent = new Intent(this, ReadDBActivity.class);
        startActivity(intent);
         */

        Realm.init(this);
        Realm mRealm = Realm.getDefaultInstance();
        mRealm.executeTransaction(new Realm.Transaction(){

            @Override
            public void execute(Realm realm) {
                MemoVO vo = realm.createObject(MemoVO.class);
                vo.title = title;
                vo.content = content;
            }
        });

        Intent intent = new Intent(this, RealmReadActivity.class);
        intent.putExtra("title", title);
        startActivity(intent);
    }
}
