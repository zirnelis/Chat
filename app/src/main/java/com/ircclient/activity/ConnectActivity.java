
package com.ircclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.net.Socket;
import com.ircclient.MainActivity;
import com.ircclient.R;
import com.ircclient.ServerList;
import com.ircclient.adapter.ServerAdapter;
import java.io.*;
import java.lang.String;
import java.io.InputStreamReader;


public class ConnectActivity extends AppCompatActivity {

    ServerList serverList = new ServerList();

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ConnectActivity.this, MainActivity.class);
        intent.putExtra("serverList", serverList);
        setResult(RESULT_OK, intent);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
        try {
            serverList = (ServerList) getIntent().getSerializableExtra("serverList");
        } catch(NullPointerException ex) {
        }
        if(serverList == null) {
            serverList = new ServerList();
        }

        final ListView servList = (ListView) findViewById(R.id.servList_);
        final ServerAdapter adapter = new ServerAdapter(serverList, getApplicationContext());
        try {
            servList.setAdapter(adapter);
        } catch(NullPointerException ex) {
            Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_LONG).show();
        }

        servList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                serverList.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

        /*servList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ConnectActivity.this, ChatActivity.class);
                Bundle extras = new Bundle();
                extras.putSerializable("serverList", serverList);
                extras.putInt("position", position);
                intent.putExtra("extras", extras);
                startActivity(intent);
            }
        });*/

        servList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ConnectActivity.this, ServerSelectActivity.class);
//                Bundle extras = new Bundle();
//                extras.putSerializable("serverList", serverList);
//                extras.putInt("position", position);
//                intent.putExtra("extras", extras);
                startActivity(intent);

            }
        });

    }
}
