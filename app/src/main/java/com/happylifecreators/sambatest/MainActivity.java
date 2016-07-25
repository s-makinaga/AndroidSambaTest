package com.happylifecreators.sambatest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ((Button)findViewById(R.id.buttonRead)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SambaLoader task = new SambaLoader();
                task.setOnCallBack(new SambaLoaderCallBack() {
                    @Override
                    public void CallBack(String Result) {
                        TextView tv = (TextView) findViewById(R.id.textViewContents);
                        tv.setText(Result);
                    }
                });

                String user = ((EditText)findViewById(R.id.editTextUser)).getText().toString();
                String pass = ((EditText)findViewById(R.id.editTextPassword)).getText().toString();
                String server = ((EditText)findViewById(R.id.editTextServer)).getText().toString();
                String path = ((EditText)findViewById(R.id.editTextPath)).getText().toString();
                task.execute(user, pass, server, path);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
