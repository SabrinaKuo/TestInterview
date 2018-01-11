package com.app.testinterview.detail;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.app.testinterview.object.CoinDetail;
import com.app.testinterview.R;
import com.app.testinterview.result.ResultFragment;
import com.google.gson.Gson;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {


    private static DetailFragment instance = null;
    private ArrayList<String> label = new ArrayList<>();
    private ArrayList<String> content = new ArrayList<>();
    private RecyclerView recyclerView;
    private DetailAdapter adapter;


    public static DetailFragment newInstance() {
        if (instance == null) {
            instance = new DetailFragment();
        }
        return instance;
    }

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        CoinDetail coinDetail = gson.fromJson(json, CoinDetail.class);
        label.add("BuyDate");
        label.add("No");
        label.add("TotalQuantity");
        label.add("Quantity");
        label.add("CurrencyName");
        label.add("Country");
        label.add("Currency");
        label.add("BankName");
        label.add("BankBranch");
        label.add("BankAddress");
        label.add("AccountName");
        label.add("AccountNo");

        content.add(coinDetail.getBuyDate());
        content.add(String.valueOf(coinDetail.getNo()));
        content.add(String.valueOf(coinDetail.getTotalQuantity()));
        content.add(String.valueOf(coinDetail.getQuantity()));
        content.add(coinDetail.getCurrencyName());
        content.add(coinDetail.getCountry());
        content.add(coinDetail.getCurrency());
        content.add(coinDetail.getBankName());
        content.add(coinDetail.getBankBranch());
        content.add(coinDetail.getBankAddress());
        content.add(coinDetail.getAccountName());
        content.add(coinDetail.getAccountNo());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_detail);
        Button button = view.findViewById(R.id.confirm);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlerDialog();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new DetailAdapter(label, content);
        recyclerView.setAdapter(adapter);
    }

    public void showAlerDialog(){
        final View item = LayoutInflater.from(getActivity()).inflate(R.layout.item_layout, null);
        new AlertDialog.Builder(getContext())
                .setTitle("請輸入二級密碼")
                .setView(item)
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText editText = (EditText) item.findViewById(R.id.edit_text);
                        String name = editText.getText().toString();

                        ResultFragment resultFragment = new ResultFragment();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.panel, resultFragment)
                                .commit();
                    }
                })
                .show();
    }
}
