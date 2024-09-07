package com.melrs.mingle.ui.feed;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.melrs.mingle.R;
import com.melrs.mingle.data.model.MingleItem;
import com.melrs.mingle.data.model.MingleUser;
import com.melrs.mingle.data.model.UserBalance;
import com.melrs.mingle.data.repositories.mingleItem.MingleItemRepositoryResolver;
import com.melrs.mingle.list.MingleRecyclerViewAdapter;

public class FeedFragment extends Fragment {

    private final MingleUser user;
    private final UserBalance userBalance;

    public FeedFragment(MingleUser user, UserBalance userBalance) {
        this.user = user;
        this.userBalance = userBalance;
    }

    public FeedFragment() {
        this.user = MingleUser.empty();
        this.userBalance = UserBalance.create("1", "100.86", "USD");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        setUpHeader(view);
        setUpMingleList(view);
        return view;
    }

    private void setUpHeader(View view) {
        TextView text_username = view.findViewById(R.id.userNameView);
        text_username.setText(this.user.getDisplayName());

        TextView text_balance_amount = view.findViewById(R.id.userBalance);
        String[] balance = this.userBalance.getBalance().getNumber().toString().split("\\.");

        text_balance_amount.setText(balance[0]);

        TextView text_balance_currency = view.findViewById(R.id.rs);
        text_balance_currency.setText(this.userBalance.getBalance().getCurrency().getCurrencyCode());

        ImageView user_profile_image = view.findViewById(R.id.imageView);
        user_profile_image.setImageResource(R.drawable.ic_user_icon);

        TextView text_balance_cents = view.findViewById(R.id.cents);
        text_balance_cents.setText(String.format(",%s", balance[1]));
    }

    private void setUpMingleList(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.mingle_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mockItems();
        }

        MingleRecyclerViewAdapter adapter = new MingleRecyclerViewAdapter(
                MingleItemRepositoryResolver.resolve(getContext()).getUserMingleItems(this.user.getUserId())
        );
        recyclerView.setAdapter(adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void mockItems() {
        MingleItem item = MingleItem.create(
                1,
                "1",
                "2",
                "25.00",
                "BRL",
                "2021-09-01 00:00:00",
                "Just a mingle",
                "MI",
                "PA",
                "2021-09-01 08:00:00"
        );
        MingleItem item2 = MingleItem.create(
                2,
                "1",
                "2",
                "25.00",
                "BRL",
                "2021-09-01 00:00:00",
                "Other a mingle",
                "MO",
                "CA",
                "2021-09-01 08:00:00"
        );
        MingleItemRepositoryResolver.resolve(getContext()).saveMingleItem(item);
        MingleItemRepositoryResolver.resolve(getContext()).saveMingleItem(item2);
        MingleItemRepositoryResolver.resolve(getContext()).saveMingleItem(item);
        MingleItemRepositoryResolver.resolve(getContext()).saveMingleItem(item2);
        MingleItemRepositoryResolver.resolve(getContext()).saveMingleItem(item);
        MingleItemRepositoryResolver.resolve(getContext()).saveMingleItem(item);
        MingleItemRepositoryResolver.resolve(getContext()).saveMingleItem(item2);
        MingleItemRepositoryResolver.resolve(getContext()).saveMingleItem(item2);
    }
}