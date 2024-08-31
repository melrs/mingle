// MingleListActivity.java
package com.melrs.mingle;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.melrs.mingle.data.MingleStatus;
import com.melrs.mingle.data.MingleType;
import com.melrs.mingle.list.MingleItem;
import com.melrs.mingle.list.MingleRecyclerViewAdapter;

import java.util.Collections;
import java.util.List;


public class MingleListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MingleRecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mingle_list);

        recyclerView = findViewById(R.id.mingle_list);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        List<MingleItem> items = Collections.emptyList();
        items.add(
                new MingleItem(
                        "John Doe",
                        "R$ 50,00",
                        MingleStatus.PA,
                        MingleType.MI
                )
        );
        items.add(
                new MingleItem(
                        "Jane Doe",
                        "R$ 25,00",
                        MingleStatus.PE,
                        MingleType.MO
                )
        );
        adapter = new MingleRecyclerViewAdapter(items);
        recyclerView.setAdapter(adapter);
    }
}
