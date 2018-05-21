package com.afordev.whentodo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RcvAdapter extends RecyclerView.Adapter<RcvAdapter.ViewHolder> {

    private Context mContext;
    private DBManager dbManager;
    private ArrayList<DataWhen> dataList;
    // 예시에서는 DataForm Class에 Data가 담겨있음을 전제로 한다. DataForm을 원하는 클래스로 바꿔주자.

    public RcvAdapter(Context mContext, DBManager dbManager) {
        this.mContext = mContext;
        this.dbManager = dbManager;
        this.dataList = dbManager.getTodoWhenList();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_when, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle, tvDday, tvDate;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.item_when_tv_title);
            tvDday = itemView.findViewById(R.id.item_when_tv_dday);
            tvDate = itemView.findViewById(R.id.item_when_tv_date);
        }

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        DataWhen temp = dataList.get(position);
        holder.tvTitle.setText(temp.getTitle());
        holder.tvDday.setText("3");
        holder.tvDate.setText("Add: " + temp.getAddDate() + "\nRecent: " + temp.getRecentDate());
    }

    private void removeItem(int position) {
        dataList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, dataList.size());
    }

    public void onRefresh() {
        this.dataList = dbManager.getTodoWhenList();
        notifyDataSetChanged();
    }
}