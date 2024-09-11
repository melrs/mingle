package com.melrs.mingle.domain.balance;

import android.util.Log;

import com.melrs.mingle.data.MingleStatus;
import com.melrs.mingle.data.model.MingleItem;
import com.melrs.mingle.data.model.MingleUser;
import com.melrs.mingle.data.model.UserBalance;
import com.melrs.mingle.data.repositories.mingleItem.MingleItemRepository;
import com.melrs.mingle.data.repositories.userBalance.UserBalanceRepository;
import com.melrs.mingle.data.repositories.userBalance.UserBalanceRepositoryResolver;
import com.melrs.mingle.domain.balance.events.BalanceEvent;
import com.melrs.mingle.domain.balance.events.EventType;
import com.melrs.mingle.domain.balance.events.MingleItemEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.money.Monetary;
import javax.money.MonetaryAmount;

public class BalanceCalculator {

    private final MingleUser mingleUser;
    private final MingleItemRepository mingleItemRepository;
    private final UserBalanceRepository userBalanceRepository;

    public BalanceCalculator(MingleUser mingleUser, MingleItemRepository mingleItemRepository, UserBalanceRepository userBalanceRepository) {
        this.mingleUser = mingleUser;
        this.mingleItemRepository = mingleItemRepository;
        this.userBalanceRepository = userBalanceRepository;
    }

    public static BalanceCalculator create(
            MingleUser mingleUser,
            MingleItemRepository mingleItemRepository,
            UserBalanceRepository userBalanceRepository
    ) {
        return new BalanceCalculator(mingleUser, mingleItemRepository, userBalanceRepository);
    }

    public void register() {
        EventBus.getDefault().register(this);
    }

    public void unregister() {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onMingleItemEvent(MingleItemEvent event) {
        UserBalance userBalance = calculateUserBalance();
        userBalanceRepository.upsertUserBalance(userBalance);
        EventBus.getDefault().post(BalanceEvent.create(userBalance, EventType.CREATE));
        Log.d("EventBus", Thread.currentThread().getName());
        Log.d("EventBus", "Balance updated");
    }

    public UserBalance calculateUserBalance() {
        MonetaryAmount balanceAmount = mingleItemRepository
                .getUserMingleItemsByStatus(mingleUser.getUserId(), MingleStatus.OP)
                .stream()
                .map(this::transformAmount)
                .reduce(this.zero(), MonetaryAmount::add);

        return UserBalance.create(mingleUser.userId, balanceAmount.getNumber().toString(), balanceAmount.getCurrency().getCurrencyCode());
    }

    private MonetaryAmount transformAmount(MingleItem mingleItem) {
        switch (mingleItem.getType()) {
            case MI: return mingleItem.getAmount();
            case MO: return mingleItem.getAmount().negate();
            default: return zero();
        }
    }

    private MonetaryAmount zero() {
        return Monetary.getDefaultAmountFactory().setCurrency("BRL").setNumber(0).create();
    }

}
