package com.melrs.mingle.data.model;

import android.annotation.SuppressLint;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.melrs.mingle.data.MingleStatus;
import com.melrs.mingle.data.MingleType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.money.Monetary;
import javax.money.MonetaryAmount;

public class MingleItem {

    private final int id;
    private final String userId;
    private final String friendId;
    private final MonetaryAmount amount;
    private final @Nullable LocalDateTime created_at;
    private final String description;
    private final MingleType type;
    private final MingleStatus status;
    private final @Nullable LocalDateTime updated_at;


    public MingleItem(
            int id,
            String userId,
            String friendId,
            MonetaryAmount amount,
            LocalDateTime created_at, 
            String description, 
            MingleType type, 
            MingleStatus status, 
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.userId = userId;
        this.friendId = friendId;
        this.created_at = created_at;
        this.description = description;
        this.type = type;
        this.status = status;
        this.updated_at = updatedAt;
        this.amount = amount;
    }
    
    @SuppressLint("WeekBasedYear")
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static MingleItem create(
            int id,
            String userId,
            String friendId,
<<<<<<< HEAD
            String amount,
=======
            String balance,
>>>>>>> 182f0d2 (User Profile)
            String currencyCode,
            String created_at,
            String description,
            String type,
            String status,
            String updated_at
    ) {
        return new MingleItem(
                id,
                userId,
                friendId,
                Monetary.getDefaultAmountFactory().setNumber(Double.parseDouble(amount)).setCurrency(currencyCode).create(),
                LocalDateTime.parse(created_at, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                description,
                MingleType.valueOf(type),
                MingleStatus.valueOf(status),
                LocalDateTime.parse(updated_at, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
    }

    public static MingleItem newItem(
            String userId,
            String friendId,
            String amount,
            String currencyCode,
            String description,
            String type,
            String status
    ) {
        return new MingleItem(
                0,
                userId,
                friendId,
                Monetary.getDefaultAmountFactory().setNumber(Double.parseDouble(amount)).setCurrency(currencyCode).create(),
                null,
                description,
                MingleType.valueOf(type),
                MingleStatus.valueOf(status),
                null
        );
    }

    public int getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }
    
    public String getFriendId() {
        return friendId;
    }
    
    public LocalDateTime getCreatedAt() {
        return created_at;
    }
    
    public String getDescription() {
        return description;
    }
    
    public MingleType getType() {
        return type;
    }
    
    public MingleStatus getStatus() {
        return status;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updated_at;
    }

    public MonetaryAmount getAmount() {
        return amount;
    }

}