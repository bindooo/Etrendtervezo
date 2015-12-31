package com.faveoffate.etrendtervezo;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by Geri on 2015.10.15..
 */
public class MainActivity extends Activity {

    Button dietButton;
    Button producListButton;
    Button scanButton;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_main_activity);

        //database
        DataBaseHelper myDbHelper;
        myDbHelper = new DataBaseHelper(this);

        try {

            myDbHelper.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }

        try {

            myDbHelper.openDataBase();

        }catch(SQLException sqle){

            throw sqle;

        }

        dietButton = (Button)findViewById(R.id.dietButton);
        producListButton = (Button)findViewById(R.id.producListButton);
        scanButton = (Button)findViewById(R.id.scanButton);

        dietButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,DietActivity.class);
                startActivity(i);
            }
        });

        producListButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this,ProductListActivity.class);
//                startActivity(i);

                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.layout_productlistbutton_dialog);
                dialog.setTitle("Válassz egy listát!");

                TextView ready = (TextView) dialog.findViewById(R.id.ready);
                ready.setText("Készételek");

                TextView raw = (TextView) dialog.findViewById(R.id.raw);
                raw.setText("Alapanyagok");

                ready.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this,ProductListActivity.class);
                        i.putExtra("extra_clicked", "ready");
                        startActivity(i);
                        dialog.dismiss();
                    }
                });

                raw.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this,ProductListActivity.class);
                        i.putExtra("extra_clicked", "raw");
                        startActivity(i);
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }
}
