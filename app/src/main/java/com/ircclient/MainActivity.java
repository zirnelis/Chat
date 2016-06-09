package com.ircclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ircclient.activity.AddServerActivity;
import com.ircclient.activity.ConnectActivity;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements Serializable {
    public static final int REQUEST_CODE = 100;
    ServerList serverList = new ServerList();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1) {
            if(resultCode == RESULT_OK) {
                try {
                    serverList = (ServerList) getIntent().getSerializableExtra("serverList");
                } catch(NullPointerException ex) {
                    Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button moveBtn = (Button) findViewById(R.id.moveBtn);
        Button connBtn = (Button) findViewById(R.id.connBtn);

        try {
            serverList = (ServerList) getIntent().getSerializableExtra("serverList");
        } catch(NullPointerException ex) {
            return;
        }
        moveBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddServerActivity.class));
            }
        });

        connBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConnectActivity.class);
                try {
                    intent.putExtra("serverList", serverList);
                } catch(NullPointerException ex) {
                    return;
                }
                startActivity(intent);
            }
        });
    }
}

