package com.afordev.whentodo;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton fabAdd;
    private RecyclerView rcvList;
    private RcvAdapter rcvAdapter;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcvList = findViewById(R.id.main_rcv);
        rcvList.setLayoutManager(new LinearLayoutManager(this));

        dbManager = DBManager.getInstance(this);
        rcvAdapter = new RcvAdapter(this, dbManager);
        rcvList.setAdapter(rcvAdapter);

        fabAdd = findViewById(R.id.main_fab);

        fabAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.main_fab:
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivityForResult(intent, 1001);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1001){
            if(resultCode == RESULT_OK){
                rcvAdapter.onRefresh();
            }
        }
    }
}
