package au.edu.utas.szhang21.assignment2;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Placeholder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;

public class ChooseMood extends AppCompatActivity {
    private ConstraintLayout layoutMoodSelection;
    private Placeholder selectedMood;
    private Button btnWriteMore;
    private Button btnQuickSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_mood);

        getSupportActionBar().setTitle("Choose Your Current Mood");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnWriteMore = findViewById(R.id.btnWriteMore);
        btnWriteMore.setEnabled(false);

        btnQuickSave = findViewById(R.id.btnQuickSave);
        btnQuickSave.setEnabled(false);

        selectedMood = findViewById(R.id.selectedMood);
        layoutMoodSelection = findViewById(R.id.layoutMoodSelection);

        btnWriteMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChooseMood.this, WriteMood.class);
                startActivity(i);
            }
        });
    }

    public void onSelectMood(View v) {
        //TransitionManager.beginDelayedTransition(layoutMoodSelection);

        selectedMood.setContentId(v.getId());
        btnWriteMore.setEnabled(true);
        btnQuickSave.setEnabled(true);

    }
}
