package com.leminhtriet.lab5_ex1;


import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private PhoneAdapter mAdapter;
    private List<Phone> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initData();

        registerForContextMenu(mListView);
    }

    private void initViews() {
        mListView = findViewById(R.id.listView);
    }

    private void initData() {
        mData = new ArrayList<>();
        mData.add(new Phone(R.drawable.icon_mobilephone, "Apple"));
        mData.add(new Phone(R.drawable.icon_mobilephone, "Samsung"));
        mData.add(new Phone(R.drawable.icon_mobilephone, "Nokia"));
        mData.add(new Phone(R.drawable.icon_mobilephone, "Oppo"));
        mData.add(new Phone(R.drawable.icon_mobilephone, "HTC"));
         mAdapter = new PhoneAdapter(this, R.layout.list_row, mData);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        int position = ((AdapterContextMenuInfo) menuInfo).position;
        menu.setHeaderTitle(mData.get(position).getName());

        menu.add("Check/uncheck");
        menu.add("Delete");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = ((AdapterContextMenuInfo) item.getMenuInfo()).position;
        if (item.getTitle().equals("Check/uncheck")) {
            Phone phone = mData.get(position);
            phone.setChecked(!phone.isChecked());
            mAdapter.notifyDataSetChanged();
        } else if (item.getTitle().equals("Delete")) {
            mData.remove(position);
            mAdapter.notifyDataSetChanged();
        }
        return super.onContextItemSelected(item);
    }
}
