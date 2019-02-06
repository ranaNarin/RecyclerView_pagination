package com.service;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


public class RecyclerView_Adapter extends RecyclerView.Adapter< RecyclerView.ViewHolder> {

    private final Activity activity;
    private List<String> data;
    private static final int FOOTER_VIEW = 1;



    public RecyclerView_Adapter(Activity activity, List<String> moviesList, int i) {
        this.activity=activity;
        this.data = moviesList;
    }

    @Override
    public  RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == FOOTER_VIEW) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_footer, parent, false);
            FooterViewHolder vh = new FooterViewHolder(view);
            return vh;
        }
        else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recycler_view, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        // View view = inflater.inflate(R.layout.child_list_adapter, parent, false);

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder  viewHolder, int position) {

        if (viewHolder instanceof MyViewHolder) {
            final MyViewHolder holder = (MyViewHolder) viewHolder;
            holder.text.setText("hello Hello "+position);
        }
        else if(viewHolder instanceof FooterViewHolder) {
            FooterViewHolder vh = (FooterViewHolder) viewHolder;
            vh.mainLayout.setVisibility(View.VISIBLE);

        }


    }

    @Override
    public int getItemCount() {

       /* if (data == null) {
            return 0;
        }
        if (data.size() == 0) {
            return data.size();
        }
        try {
            if(data.size()<21)
            {
                return data.size();
            }

        }catch (Exception ex)
        {
            ex.printStackTrace();
        }


        return data.size() + 1;*/
       int size=data.size()+1;
       return size;
    }
    @Override
    public int getItemViewType(int position) {
        if (position == data.size()) {
            return FOOTER_VIEW;
        }

        return super.getItemViewType(position);
    }


    public class FooterViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mainLayout;

        public FooterViewHolder(View itemView) {
            super(itemView);

            mainLayout=(LinearLayout)itemView.findViewById(R.id.mainLayout);

        }
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView text;

        public MyViewHolder(View view) {
            super(view);
            text=(TextView)view.findViewById(R.id.text);
        }
    }

}