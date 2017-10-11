package com.sramanopasaka.sipanionline.sadhumargi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class ExamsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams);

        Toast.makeText(this, "Hi", Toast.LENGTH_SHORT).show();
    }
}
