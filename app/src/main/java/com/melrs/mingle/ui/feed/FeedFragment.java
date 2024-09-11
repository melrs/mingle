package com.melrs.mingle.ui.feed;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.melrs.mingle.R;
import com.melrs.mingle.data.model.MingleUser;
import com.melrs.mingle.data.model.UserBalance;
import com.melrs.mingle.data.repositories.mingleItem.MingleItemRepositoryResolver;
import com.melrs.mingle.data.repositories.userBalance.UserBalanceRepositoryResolver;
import com.melrs.mingle.domain.balance.BalanceCalculator;
import com.melrs.mingle.domain.balance.events.BalanceEvent;
import com.melrs.mingle.list.MingleRecyclerViewAdapter;
import com.melrs.mingle.ui.mingleitem.AddManualMingleItemFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class FeedFragment extends Fragment {

    private static final String ARG_USER = "user";
    private MingleUser mingleUser;
    private TextView text_balance_amount;
    private TextView text_balance_cents;
    private ImageView user_profile_image;
    private TextView text_username;
    private TextView text_balance_currency;
    private RecyclerView recyclerView;
    private FloatingActionButton newMingleButton;
    private BalanceCalculator balanceCalculator;

    public static FeedFragment newInstance(MingleUser user) {
        FeedFragment fragment = new FeedFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mingleUser = (MingleUser) getArguments().getSerializable(ARG_USER);
        }
        EventBus.getDefault().register(this);
        balanceCalculator = BalanceCalculator.create(
                this.mingleUser,
                MingleItemRepositoryResolver.resolve(getContext()),
                UserBalanceRepositoryResolver.resolve(getContext())
        );
        balanceCalculator.register();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindUIComponents(view);
        setUpHeader();
        setUpButton();
        setUpMingleList();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBalanceUpdatedEvent(BalanceEvent event) {
        UserBalance updatedBalance = event.getBalance();
        updateBalanceUI(updatedBalance);
        Log.d("EventBus", Thread.currentThread().getName());
        Log.d("EventBus", "Balance updated in FeedFragment");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        balanceCalculator.unregister();
    }

    private void bindUIComponents(View view) {
        text_balance_amount = view.findViewById(R.id.userBalance);
        text_balance_cents = view.findViewById(R.id.cents);
        text_balance_currency = view.findViewById(R.id.rs);
        user_profile_image = view.findViewById(R.id.imageView);
        text_username = view.findViewById(R.id.userNameView);
        recyclerView = view.findViewById(R.id.mingleList);
        newMingleButton = view.findViewById(R.id.newMingleButton);
    }

    private void setUpButton() {
        newMingleButton.setOnClickListener(v -> {
            FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,  AddManualMingleItemFragment.newInstance(mingleUser.getUserId()));
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });
    }

    private void setUpHeader() {
        text_username.setText(this.mingleUser.getDisplayName());
        user_profile_image.setImageResource(R.drawable.ic_user_icon); // TODO: set user profile image
        updateBalanceUI(UserBalanceRepositoryResolver.resolve(getContext()).getUserBalanceByUserId(this.mingleUser.getUserId()));
    }

    private void updateBalanceUI(UserBalance userBalance) {
        if (userBalance == null) {
            return;
        }
        String[] balance = userBalance.getBalance().getNumber().toString().split("\\.");
        if (balance.length <= 1) {
            balance = new String[]{balance[0], "00"};
        }
        text_balance_amount.setText(balance[0]);
        text_balance_cents.setText(String.format(",%s", balance[1]));
        text_balance_currency.setText(userBalance.getBalance().getCurrency().getCurrencyCode());
    }

    private void setUpMingleList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(true);

        MingleRecyclerViewAdapter adapter = new MingleRecyclerViewAdapter(
                MingleItemRepositoryResolver.resolve(getContext()).getUserMingleItems(this.mingleUser.getUserId())
        );

        recyclerView.setAdapter(adapter);
    }

}