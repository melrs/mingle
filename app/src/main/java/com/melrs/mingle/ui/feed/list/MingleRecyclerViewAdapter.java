package com.melrs.mingle.ui.feed.list;

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
import com.melrs.mingle.data.model.MingleItem;

import java.util.List;

public class MingleRecyclerViewAdapter extends RecyclerView.Adapter<MingleRecyclerViewAdapter.MingleViewHolder> {

    private final List<MingleItem> items;

    public MingleRecyclerViewAdapter(List<MingleItem> mingleItems) {
        this.items = mingleItems;
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
        holder.friends_name.setText(items.get(position).getDescription());
        holder.amount.setText(items.get(position).getAmount().toString());
        holder.status_icon.setImageResource(items.get(position).getStatus() == MingleStatus.PA ? R.drawable.ic_done_all : R.drawable.ic_friends);
        holder.mingle_status.setText(items.get(position).getStatus() == MingleStatus.PA ? "Pago" : "A pagar");
        holder.activity_icon.setImageResource(items.get(position).getType() == MingleType.MI ? R.drawable.ic_mingle_in : R.drawable.ic_mingle_out);
    }

    @Override
    public int getItemCount() {
        return items.size();
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