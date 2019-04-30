package au.edu.utas.szhang21.assignment2;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.constraint.Placeholder;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class WriteMood extends AppCompatActivity {
    private static final String TAG = "==WriteMood==";

    private static final int ALBUM_REQUEST = 100;
    private static final int CAMERA_REQUEST = 200;
    private static final int REQUEST_CAMERA_PERMISSION = 300;

    private Placeholder showSelectedMood;
    private TextView lblDateTime;
    private Button btnAddImg;
    private Uri imgUri;
    private ImageView journalImg;
    private Button btnSaveJournal;
    private String filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_mood);

        showSelectedMood = findViewById(R.id.showSelectedMood);
        lblDateTime = findViewById(R.id.lblDateTime);
        btnAddImg = findViewById(R.id.btnAddImg);
        journalImg = findViewById(R.id.journalImg);
        btnSaveJournal = findViewById(R.id.btnSaveJournal);



        DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");

        String currentTime = dateFormat.format(Calendar.getInstance().getTime());
        lblDateTime.setText(currentTime);

        getSupportActionBar().setTitle("Write Detail About Your Mood");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnAddImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set camera permmision
                if (Build.VERSION.SDK_INT >= 23) {
                    requestPermissions(new String[]{ Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CAMERA_PERMISSION);
                }

                PopupMenu popupMenu = new PopupMenu(WriteMood.this, btnAddImg);
                popupMenu.getMenuInflater().inflate(R.menu.selectimg_popup, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId()==R.id.fromAlbum) {
                            Log.d(TAG, "fromAlbum clicked");
                            openAlbum();
                        }
                        if (item.getItemId()==R.id.takePhoto) {
                            Log.d(TAG, "takePhoto clicked");
                            openCamera();
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

        btnSaveJournal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WriteMood.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void openAlbum() {
        Intent iAlbum = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(iAlbum, ALBUM_REQUEST);
    }

    private void openCamera() {

        Intent iCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //startActivityForResult(iCamera, ALBUM_REQUEST);
        if (iCamera.resolveActivity(getPackageManager()) != null) {
            //create a file that photo gonna be stored
            File photoFile = null;
            photoFile = createPhotoFile();
            if (photoFile != null) {
                filePath = photoFile.getAbsolutePath();
                Uri photoUri = FileProvider.getUriForFile(WriteMood.this, "au.edu.utas.szhang21.assignment2", photoFile);
                iCamera.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(iCamera, CAMERA_REQUEST);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == ALBUM_REQUEST) {
            imgUri = data.getData();
            journalImg.setImageURI(imgUri);
        }

        if (resultCode == RESULT_OK && requestCode == CAMERA_REQUEST) {
            Bitmap bitmap = BitmapFactory.decodeFile(filePath);
            journalImg.setImageBitmap(bitmap);
        }
    }

    private File createPhotoFile() {
        String photoName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File storeageDir = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = null;
        try {
            image = File.createTempFile(photoName, ".jpg", storeageDir);
        } catch (IOException e) {
            Log.d(TAG, "Error: " + e.toString());
        }
        return image;
    }

//    public void onSelectFromAlbumClicked(View v) {
//        Log.d(TAG, "onSelectFromAlbumClicked");
//    }
//
//    public void onTakePhotoClicked(View v) {
//        Log.d(TAG, "onTakePhotoClicked");
//    }
}
