package au.edu.utas.szhang21.assignment2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "==MainActivity==";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView btmNavView = findViewById(R.id.bottom_nav);
        btmNavView.setOnNavigationItemSelectedListener(navListener);

        //define add btn
        FloatingActionButton btn_add = (FloatingActionButton)findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Ding~~~");
                Intent i = new Intent(MainActivity.this, ChooseMood.class);
                startActivity(i);
            }
        });

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new MoodFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectFragment = new MoodFragment();
                            break;

                        case R.id.nav_search:
                            selectFragment = new SearchFragment();
                            break;

                        case R.id.nav_track:
                            selectFragment = new TrackFragment();
                            break;

                        case R.id.nav_bin:
                            selectFragment = new RecycleFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction()
                                                .replace(R.id.fragment_container, selectFragment)
                                                 .commit();
                    return true;
                }

            };
}
