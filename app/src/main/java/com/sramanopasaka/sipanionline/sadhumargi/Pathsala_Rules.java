package com.sramanopasaka.sipanionline.sadhumargi;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Pathsala_Rules extends AppCompatActivity {

    Toolbar toolbar;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pathsala__rules);

        toolbar= (Toolbar) findViewById(R.id.toolrules);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        toolbar.setNavigationIcon(R.drawable.left_arrow_patasala);
        toolbar.setTitle(Html.fromHtml("<font color='#FFFFFF'>पाठशाला रूल्स</font>"));
       // getSupportActionBar().setHomeAsUpIndicator();

        Toast.makeText(Pathsala_Rules.this, "Please wait we are updating soon", Toast.LENGTH_SHORT).show();
    }
}
