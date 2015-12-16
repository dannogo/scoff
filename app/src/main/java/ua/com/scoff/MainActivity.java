package ua.com.scoff;

import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import serving.DividerItemDecoration;


public class MainActivity extends AppCompatActivity {

    RecyclerView spansList;
    SpansAdapter spansAdapter;
    DatabaseAdapter databaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseAdapter = new DatabaseAdapter(this);

//        databaseAdapter.getTables();

        spansList = (RecyclerView) findViewById(R.id.spansList);
        spansList.addItemDecoration(new DividerItemDecoration(this, null, true, true));
        float offsetPx = getResources().getDimension(R.dimen.bottom_offset_dp);
        StaticUtils.BottomOffsetDecoration bottomOffsetDecoration = new StaticUtils.BottomOffsetDecoration((int)offsetPx);
        spansList.addItemDecoration(bottomOffsetDecoration);
        spansList.setLayoutManager(new LinearLayoutManager(this));
        spansAdapter = new SpansAdapter(this);

        spansList.setAdapter(spansAdapter);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add_span);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                StaticUtils.showAddProductDialog(MainActivity.this);
                DialogAddSpan dialogAddSpan = new DialogAddSpan();
                dialogAddSpan.show(MainActivity.this.getFragmentManager(), "FragmentAddSpan");
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
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
