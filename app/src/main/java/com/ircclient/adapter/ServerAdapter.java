package com.ircclient.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ircclient.R;
import com.ircclient.ServerList;

/**
 * Created by pfff on 15.05.2016.
 */
public class ServerAdapter extends BaseAdapter {

    private Context mContext;
    private ServerList mServerList;

    public ServerAdapter(ServerList mServerList, Context mContext) {
        this.mServerList = mServerList;
        this.mContext = mContext;
    }



    @Override
    public int getCount() {
        return mServerList.getList().size();
    }

    @Override
    public Object getItem(int position) {
        return mServerList.getList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.item_server_list, null);
        TextView serverName = (TextView) v.findViewById(R.id.server_name);
        TextView serverHostname = (TextView) v.findViewById(R.id.server_hostname);
        serverName.setText(mServerList.getList().get(position).getServerName());
        serverHostname.setText(mServerList.getList().get(position).getServerHostname());

        v.setTag(mServerList.getList().get(position));
        return v;
    }
}
