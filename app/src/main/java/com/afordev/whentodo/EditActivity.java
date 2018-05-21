package com.afordev.whentodo;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditActivity extends AppCompatActivity {

    private TextInputEditText etTitle, etRecentDate;
    private DBManager dbManager;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        dbManager = DBManager.getInstance(this);

        etTitle = findViewById(R.id.edit_et_title);
        etRecentDate = findViewById(R.id.edit_et_recent);
        btnSave = findViewById(R.id.edit_btn_save);

        etRecentDate.setText("");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataWhen data;
                if(etRecentDate.getText().toString().equals("")){
                    data = new DataWhen(-1, etTitle.getText().toString(), etRecentDate.getText().toString(), etRecentDate.getText().toString());
                }else{
                    data = new DataWhen(-1, etTitle.getText().toString(), etRecentDate.getText().toString(), etRecentDate.getText().toString());
                }
                dbManager.insertWhen(data);
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}
