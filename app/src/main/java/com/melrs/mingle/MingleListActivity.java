// MingleListActivity.java
package com.melrs.mingle;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.melrs.mingle.list.MingleRecyclerViewAdapter;


public class MingleListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MingleRecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mingle_list);
    }
}
