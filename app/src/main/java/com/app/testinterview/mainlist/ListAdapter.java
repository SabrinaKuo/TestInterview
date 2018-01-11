package com.app.testinterview.mainlist;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.app.testinterview.object.Coin;
import com.app.testinterview.R;
import com.app.testinterview.object.CoinDetail;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by kuosabrina on 2018/1/10.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{

    private ArrayList<Coin.BonusArrayBean> mList;
    private ClickCallback mCallback;
    private int oldselected = -1;
    private RecyclerView mRv;
    private CoinDetail detail;

    public ListAdapter(ArrayList<Coin.BonusArrayBean> list, RecyclerView rv, ClickCallback callback) {
        this.mList = list;
        this.mCallback = callback;
        this.mRv = rv;
        initalDetailData();
    }

    private void initalDetailData(){
        String json = "{\n" +
                "  \"BuyDate\": \"2016-12-08T18:16:04.563Z\",\n" +
                "  \"No\": 10018,\n" +
                "  \"TotalQuantity\": 2012,\n" +
                "  \"Quantity\": 300,\n" +
                "  \"CurrencyName\": \"USD\",\n" +
                "  \"Country\": \"中國\",\n" +
                "  \"Currency\": \"人民幣\",\n" +
                "  \"BankName\": \"中國人民銀行\",\n" +
                "  \"BankBranch\": \"上海分行\",\n" +
                "  \"BankAddress\": \"上海市虹橋區虹橋路18號\",\n" +
                "  \"AccountName\": \"李大寶\",\n" +
                "  \"AccountNo\": \"1234123412341234\"\n" +
                "}";
        Gson gson = new Gson();
        detail = gson.fromJson(json, CoinDetail.class);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tv_title.setText(mList.get(position).getTitle());
        holder.tv_value.setText("(" + String.valueOf(mList.get(position).getValue()) + ")");
        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewHolder viewHolder = (ViewHolder) mRv.findViewHolderForLayoutPosition(oldselected);
                if (viewHolder != null){
                    viewHolder.radioButton.setChecked(false);
                } else {
                    notifyItemChanged(oldselected);
                }
                oldselected = position;
                holder.radioButton.setChecked(true);
                mCallback.onItemClick(mList.get(position), detail);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        RadioButton radioButton;
        TextView tv_title;
        TextView tv_value;

        public ViewHolder(View itemView) {
            super(itemView);
            radioButton = itemView.findViewById(R.id.radio_button);
            tv_title = itemView.findViewById(R.id.list_item_title);
            tv_value = itemView.findViewById(R.id.list_item_value);
        }
    }

    public interface ClickCallback {
        void onItemClick(Coin.BonusArrayBean coinBean, CoinDetail coinDetail);
    }
}
