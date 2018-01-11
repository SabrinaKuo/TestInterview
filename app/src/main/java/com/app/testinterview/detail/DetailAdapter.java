package com.app.testinterview.detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.testinterview.R;

import java.util.ArrayList;

/**
 * Created by kuosabrina on 2018/1/10.
 */

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder>{

    private ArrayList<String> mLabel;
    private ArrayList<String> mContent;

    public DetailAdapter(ArrayList<String> label, ArrayList<String> content) {
        this.mLabel = label;
        this.mContent = content;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_label.setText(mLabel.get(position));
        holder.tv_content.setText(mContent.get(position));
    }

    @Override
    public int getItemCount() {
        return mLabel.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_label;
        TextView tv_content;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_label = itemView.findViewById(R.id.detail_label);
            tv_content = itemView.findViewById(R.id.detail_content);
        }
    }
}
