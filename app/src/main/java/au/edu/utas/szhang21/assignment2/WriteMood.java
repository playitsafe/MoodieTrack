package au.edu.utas.szhang21.assignment2;

import android.content.Intent;
import android.support.constraint.Placeholder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WriteMood extends AppCompatActivity {
    private Placeholder showSelectedMood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_mood);

        showSelectedMood = findViewById(R.id.showSelectedMood);

        getSupportActionBar().setTitle("Write Detail About Your Mood");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WriteMood.this, MainActivity.class);
                startActivity(i);

            }
        });

    }
}
