package com.faveoffate.etrendtervezo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by Geri on 2015.10.15..
 */
public class ProductListActivity extends Activity {

    protected String clickedTextView;
    protected SQLiteDatabase db;
    protected Cursor cursor;
    protected SimpleCursorAdapter adapter;
    protected ListView productList;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_productlist_activity);

        Intent i = getIntent();
        clickedTextView = i.getStringExtra("extra_clicked");

        db = (new DataBaseHelper(this)).getReadableDatabase();

        productList = (ListView) findViewById(R.id.productList);

        search(clickedTextView);

    }

    public void search(String clickedTextView) {

        if (clickedTextView.equals("raw")) {

            cursor = db.rawQuery("SELECT _id, rawName, rawImage FROM RawProducts", null);
            adapter = new SimpleCursorAdapter(this, R.layout.product_list_item, cursor, new String[]{"rawName", "rawImage"}, new int[]{R.id.readyName, R.id.readyImage});

            adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
                @Override
                public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                    if (view.getId() == R.id.readyImage) {
                        ImageView IV = (ImageView) view;
                        int resID = getApplicationContext().getResources().getIdentifier(cursor.getString(columnIndex), "drawable", getApplicationContext().getPackageName());
                        IV.setImageDrawable(getApplicationContext().getResources().getDrawable(resID));
                        return true;
                    }
                    return false;
                }
            });
        }

        if (clickedTextView.equals("ready")) {

            cursor = db.rawQuery("SELECT _id, readyName, readyImage FROM ReadyProducts", null);
            adapter = new SimpleCursorAdapter(this, R.layout.product_list_item, cursor, new String[]{"readyName", "readyImage"}, new int[]{R.id.readyName, R.id.readyImage});

            adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
                @Override
                public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                    if (view.getId() == R.id.readyImage) {
                        ImageView IV = (ImageView) view;
                        int resID = getApplicationContext().getResources().getIdentifier(cursor.getString(columnIndex), "drawable", getApplicationContext().getPackageName());
                        IV.setImageDrawable(getApplicationContext().getResources().getDrawable(resID));
                        return true;
                    }
                    return false;
                }
            });
        }

        productList.setAdapter(adapter);
    }

}
