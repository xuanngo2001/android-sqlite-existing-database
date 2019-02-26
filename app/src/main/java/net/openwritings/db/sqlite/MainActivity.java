package net.openwritings.db.sqlite;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayout = this.findViewById(R.id.main_linearlayout_view);

        // Fetch data and display.
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db;

        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
        Cursor cursor = db.rawQuery("SELECT stop_code, name FROM bus", null);
        while(cursor.moveToNext())
        {
            int i=0;
            final String stopCode = cursor.getString(i++);
            final String name = cursor.getString(i++);
            TextView textView = new TextView(this);
            String text = String.format("Stop code=%s, name=%s", stopCode, name);
            textView.setText(text);
            linearLayout.addView(textView);
        }
        cursor.close();


    }
}
