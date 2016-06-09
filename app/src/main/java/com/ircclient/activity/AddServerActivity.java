package com.ircclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ircclient.MainActivity;
import com.ircclient.R;
import com.ircclient.Server;
import com.ircclient.ServerList;

import java.util.ArrayList;

public class AddServerActivity extends AppCompatActivity {

    ServerList serverList = new ServerList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_server);
        Button saveBtn = (Button) findViewById(R.id.saveBtn);
        EditText nameField = (EditText) findViewById(R.id.nameField);
        EditText address = (EditText) findViewById(R.id.addressField);
        EditText port = (EditText) findViewById(R.id.portField);
        EditText nice = (EditText) findViewById(R.id.nickField);
        EditText usrName = (EditText) findViewById(R.id.usernameField);
        EditText realName = (EditText) findViewById(R.id.realNameField);
        final ArrayList<EditText> lol = new ArrayList<>();
        lol.add(nameField);
        lol.add(address);
        lol.add(port);
        lol.add(nice);
        lol.add(usrName);
        lol.add(realName);
        Server sv = createServer(lol);
        serverList.addServer(sv);
        try {
            serverList = (ServerList) getIntent().getSerializableExtra("serverList");
        } catch(NullPointerException ex) {
        }

        if(serverList == null) {
            serverList = new ServerList();
        }

        EditText.OnKeyListener myListener = new EditText.OnKeyListener(){
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == 66){
                    for(int i = 0; i < lol.size(); i++) {
                        if(lol.get(i).isFocused()) {
                            lol.get(i + 1).requestFocus();
                        }
                    }
                }
                return false;
            }
        };

        for(int i = 0; i < lol.size(); i++) {
            lol.get(i).setOnKeyListener(myListener);
        }

        saveBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AddServerActivity.this, MainActivity.class);
                try {
                    Server sv = createServer(lol);
                    serverList.addServer(sv);
                    intent.putExtra("serverList", serverList);
                } catch(NullPointerException ex) {
                    Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_LONG).show();
                }
                startActivity(intent);
            }
        });
    }

    public Server createServer(ArrayList<EditText> edit) {

        String name = "Random Server";
        String address = "address.address.com";
        int port = 6667;
        String nick = "cool nick";
        String username = "cool username";
        String realName = "Valdis Kusa";
        String portTmp = "";
        try {
            if(edit.get(0).getText().toString().length() > 0)
                name = edit.get(0).getText().toString();
            if(edit.get(1).getText().toString().length() > 0)
                address = edit.get(1).getText().toString();
            if(edit.get(2).getText().toString().length() > 0)
                portTmp = edit.get(2).getText().toString();
            if(!portTmp.isEmpty()) {
                port = Integer.parseInt(portTmp);
            }
            if(edit.get(3).getText().toString().length() > 0)
                nick = edit.get(3).getText().toString();
            if(edit.get(4).getText().toString().length() > 0)
                username = edit.get(4).getText().toString();
            if(edit.get(5).getText().toString().length() > 0)
                realName = edit.get(5).getText().toString();
        } catch(NullPointerException ex) {
            ex.toString();
        }
        Server sv = new Server(name, address, port, nick, username, realName);

        return sv;

    }
}


