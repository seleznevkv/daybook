package com.elysey.daybook;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.elysey.daybook.fragments.AddNoteFragment;
import com.elysey.daybook.fragments.ListFragment;

public class MainActivity extends AppCompatActivity {


    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        getSupportFragmentManager().beginTransaction().add(R.id.container, new ListFragment(), "listNote").commit();

    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new AddNoteFragment();
                Bundle bundle = new Bundle();

                fragment.setArguments(bundle);

                setFragment(fragment);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.info) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Об авторе")
                    .setMessage("Это приложение разработал \nстудент группы СКС-14 \n" +
                            "Селезнев Кирилл\n\n 2016")
                    .setCancelable(false)
                    .setNegativeButton("Ок",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();
            return true;
        }

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment,"list").addToBackStack(null).commit();


    }

    public void setVisibleFab(boolean visibleFab) {
        if (visibleFab)
            fab.show();
        else
            fab.hide();
    }

    public void showSnackbar(String text) {
        Snackbar.make(fab.getRootView(), text, Snackbar.LENGTH_SHORT).show();
    }
}
