package com.app.testinterview.detail;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
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
    private ArrayMap<String, String> detailInfo;


    public static DetailFragment newInstance() {
        if (instance == null) {
            instance = new DetailFragment();
        }
        return instance;
    }

    public DetailFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initialData();
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
        adapter = new DetailAdapter(detailInfo);
        recyclerView.setAdapter(adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initialData(){
        Bundle bundle = getArguments();
        String title = bundle.getString("TITLE");
        setItemTitle(title);

        CoinDetail coinDetail = (CoinDetail) bundle.getSerializable("DETAIL");
        detailInfo = new ArrayMap<>();
        detailInfo.put("BuyDate", coinDetail.getBuyDate());
        detailInfo.put("No", String.valueOf(coinDetail.getNo()));
        detailInfo.put("TotalQuantity", String.valueOf(coinDetail.getTotalQuantity()));
        detailInfo.put("Quantity", String.valueOf(coinDetail.getQuantity()));
        detailInfo.put("CurrencyName", coinDetail.getCurrencyName());
        detailInfo.put("Country", coinDetail.getCountry());
        detailInfo.put("Currency", coinDetail.getCurrency());
        detailInfo.put("BankName", coinDetail.getBankName());
        detailInfo.put("BankBranch", coinDetail.getBankBranch());
        detailInfo.put("BankAddress", coinDetail.getBankAddress());
        detailInfo.put("AccountName", coinDetail.getAccountName());
        detailInfo.put("AccountNo", coinDetail.getAccountNo());
    }

    private void setItemTitle(String title){
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
    }

    private void showAlerDialog(){
        final View item = LayoutInflater.from(getActivity()).inflate(R.layout.item_layout, null);
        new AlertDialog.Builder(getContext())
                .setTitle("請輸入二級密碼")
                .setView(item)
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText editText = (EditText) item.findViewById(R.id.edit_text);
                        String name = editText.getText().toString();

                        ResultFragment resultFragment = ResultFragment.newInstance();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.contentPanel, resultFragment, ResultFragment.class.getSimpleName())
                                .commit();
                    }
                })
                .show();
    }
}
