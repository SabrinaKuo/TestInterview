package com.app.testinterview.mainlist;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.testinterview.object.Coin;
import com.app.testinterview.detail.DetailFragment;
import com.app.testinterview.R;
import com.google.gson.Gson;

import java.util.ArrayList;


/**
 * Show List
 */
public class ListFragment extends Fragment {

    private static ListFragment instance = null;
    private RecyclerView recyclerView;
    private ArrayList<Coin.BonusArrayBean> list;
    private ListAdapter adapter;
    private Button nextButton;
    private String selectTitle;


    public static ListFragment newInstance() {
        if (instance == null){
            instance = new ListFragment();
        }
        return instance;
    }

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String json = "{\n" +
                "  \"BonusArray\": [\n" +
                "    {\n" +
                "      \"Title\": \"FB\",\n" +
                "      \"Value\": 18.09\n" +
                "    },\n" +
                "    {\n" +
                "      \"Title\": \"Register\",\n" +
                "      \"Value\": 5701\n" +
                "    },\n" +
                "    {\n" +
                "      \"Title\": \"AP\",\n" +
                "      \"Value\": 230\n" +
                "    },\n" +
                "    {\n" +
                "      \"Title\": \"TP1\",\n" +
                "      \"Value\": 13085.58\n" +
                "    },\n" +
                "    {\n" +
                "      \"Title\": \"MP\",\n" +
                "      \"Value\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"Title\": \"Red\",\n" +
                "      \"Value\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"Title\": \"Black\",\n" +
                "      \"Value\": 9900\n" +
                "    },\n" +
                "    {\n" +
                "      \"Title\": \"DRAmount\",\n" +
                "      \"Value\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"Title\": \"CRAmount\",\n" +
                "      \"Value\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"Title\": \"DMAmount\",\n" +
                "      \"Value\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"Title\": \"BonusAmount\",\n" +
                "      \"Value\": 0\n" +
                "    }\n" +
                "  ],\n" +
                "  \"ResultCode\": \"0000\",\n" +
                "  \"ResultMessage\": \"成功\"\n" +
                "}";

        Gson gson = new Gson();
        Coin coin = gson.fromJson(json, Coin.class);
        list = coin.getBonusArray();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_list);
        nextButton = view.findViewById(R.id.next);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nextButton.setVisibility(View.GONE);
                DetailFragment detailFragment = DetailFragment.newInstance();

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.addToBackStack(null);
                transaction.replace(R.id.panel, detailFragment, DetailFragment.class.getSimpleName());
                transaction.commit();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new ListAdapter(list, recyclerView,  new ListAdapter.ClickCallback() {
            @Override
            public void onItemClick(Coin.BonusArrayBean coinBean) {
                Log.i("Sabrina", "ListFragment- Title : " + coinBean.getTitle());
                selectTitle = coinBean.getTitle();
                nextButton.setVisibility(View.VISIBLE);
            }
        });
        recyclerView.setAdapter(adapter);
    }



}
