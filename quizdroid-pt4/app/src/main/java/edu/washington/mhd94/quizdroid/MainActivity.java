package edu.washington.mhd94.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import java.lang.Object.*;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {
    ArrayList<String> subjects;

    static QuizApp app = QuizApp.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subjects = app.getRepository().initializeSubjects();

        ListView listView = (ListView) findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, subjects);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long d) {
                Intent nextActivity = new Intent(MainActivity.this, SecondActivity.class);

                String subject = subjects.get(position);
                if(subject.contains("Math")) {
                    nextActivity.putExtra("subject", "Math");
                } else if(subject.contains("Physics")) {
                    nextActivity.putExtra("subject", "Physics");
                } else {
                    nextActivity.putExtra("subject", "Marvel Super Heroes");
                }

          startActivity(nextActivity);
          finish();
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
            Intent nextActivity = new Intent(MainActivity.this, Preferences.class);
            startActivity(nextActivity);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
