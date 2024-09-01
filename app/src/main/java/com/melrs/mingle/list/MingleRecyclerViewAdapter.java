// MingleRecyclerViewAdapter.java
package com.melrs.mingle.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.melrs.mingle.R;
import com.melrs.mingle.data.MingleStatus;
import com.melrs.mingle.data.MingleType;

import java.util.List;

public class MingleRecyclerViewAdapter extends RecyclerView.Adapter<MingleRecyclerViewAdapter.MingleViewHolder> {

    private List<MingleItem> mingleItems;

    public MingleRecyclerViewAdapter(List<MingleItem> mingleItems) {
        this.mingleItems = mingleItems;
    }

    @NonNull
    @Override
    public MingleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.mingle_item, parent, false);
        return new MingleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MingleViewHolder holder, int position) {
        holder.friends_name.setText(mingleItems.get(position).getName());
        holder.amount.setText(mingleItems.get(position).getAmount());
        holder.status_icon.setImageResource(mingleItems.get(position).getStatus() == MingleStatus.PA ? R.drawable.ic_done_all : R.drawable.ic_friends);
        holder.mingle_status.setText(mingleItems.get(position).getStatus() == MingleStatus.PA ? "Pago" : "A pagar");
        holder.activity_icon.setImageResource(mingleItems.get(position).getType() == MingleType.MI ? R.drawable.ic_mingle_in : R.drawable.ic_mingle_out);
    }

    @Override
    public int getItemCount() {
        return mingleItems.size();
    }

    public static class MingleViewHolder extends RecyclerView.ViewHolder {

        public TextView friends_name = itemView.findViewById(R.id.mingle_friends_name);
        public TextView amount = itemView.findViewById(R.id.mingle_activity_amount);
        public ImageView status_icon = itemView.findViewById(R.id.status_icon);
        public TextView mingle_status = itemView.findViewById(R.id.mingle_status);
        public ImageView activity_icon = itemView.findViewById(R.id.mingle_activity_icon);

        public MingleViewHolder(View v) {
            super(v);
        }
    }
}