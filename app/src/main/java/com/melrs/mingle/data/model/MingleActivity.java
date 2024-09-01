package com.melrs.mingle.data.model;

import android.os.Build;

import androidx.annotation.RequiresApi;
import com.melrs.mingle.data.MingleStatus;
import com.melrs.mingle.data.MingleType;
import java.time.LocalDateTime;

import javax.money.Monetary;
import javax.money.MonetaryAmount;

public class MingleActivity {

    private final int id;
    private final int userId;
    private final int friendId;
    private final MonetaryAmount amount;
    private final LocalDateTime created_at;
    private final String description;
    private final MingleType type;
    private final MingleStatus status;
    private final LocalDateTime updated_at;


    public MingleActivity(
            int id,
            int userId, 
            int friendId, 
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
    
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static MingleActivity create(
            int id,
            int userId, 
            int friendId,
            String balance,
            String currencyCode,
            String created_at,
            String description,
            String type,
            String status,
            String updated_at
    ) {
        return new MingleActivity(
                id,
                userId,
                friendId,
                Monetary.getDefaultAmountFactory().setNumber(Double.parseDouble(balance)).setCurrency(currencyCode).create(),
                LocalDateTime.parse(created_at),
                description,
                MingleType.valueOf(type),
                MingleStatus.valueOf(status),
                LocalDateTime.parse(updated_at)
        );
    }
    
    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }
    
    public int getFriendId() {
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