package au.edu.utas.szhang21.assignment2;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView btmNavView = findViewById(R.id.bottom_nav);
        btmNavView.setOnNavigationItemSelectedListener(navListener);

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
