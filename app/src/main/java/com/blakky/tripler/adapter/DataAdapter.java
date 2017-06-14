package com.blakky.tripler.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blakky.tripler.R;
import com.blakky.tripler.model.Android;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sangrambankar on 6/13/17.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>{

    private ArrayList<Android> mAndroidList;

    public DataAdapter(ArrayList<Android> mAndroidList) {
        this.mAndroidList = mAndroidList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTVVersion.setText(mAndroidList.get(position).getVersion());
        holder.mTVName.setText(mAndroidList.get(position).getName());
        holder.mTVApiLevel.setText(mAndroidList.get(position).getApi());
    }

    @Override
    public int getItemCount() {
        return mAndroidList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_version)
        TextView mTVVersion;

        @BindView(R.id.tv_name)
        TextView mTVName;

        @BindView(R.id.tv_api_level)
        TextView mTVApiLevel;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }

}
