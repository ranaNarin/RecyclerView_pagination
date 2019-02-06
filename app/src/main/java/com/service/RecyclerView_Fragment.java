package com.service;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class RecyclerView_Fragment extends android.support.v4.app.Fragment  {


    private RecyclerView_Adapter mAdapter;
    private RecyclerView recyclerView;
    private int visibleItemCount;
    private int totalItemCount;
    private int pastVisiblesItems;
    List<String> data=new ArrayList<>();
    private boolean loading;
    private int offset;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        init(view);
        callApi(0);
        return view;
    }

    private void init(View view) {


        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);

        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new RecyclerView_Adapter(getActivity(), data, 100);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                try {
                    if (dy > 0) //check for scroll down
                    {
                        visibleItemCount = mLayoutManager.getChildCount();
                        totalItemCount = mLayoutManager.getItemCount();
                        pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                        if (loading) {
                            if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                                loading = false;
                                offset = data.size();//DataTemp.LEAD_OFFSET;
                                callApi(offset);
                            }
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });
    }

    private void callApi(final int offset) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                Log.e("TAG", "Call  Api .... offset ..."+offset +"   data.size.. "+data.size());
                for(int i=0; i<20; i++){
                    data.add(new String());
                }

                loading=true;
                Log.e("TAG", "Call  Api 2.... offset ..."+offset +"   data.size.. "+data.size());
                mAdapter.notifyItemRangeInserted(offset+1, data.size());
                ;

            }
        }, 1000);



    }

}
