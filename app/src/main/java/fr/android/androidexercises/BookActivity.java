package fr.android.androidexercises;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
         String message = getIntent().getStringExtra(LibraryActivity.EXTRA_MESSAGE);
         Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }
}
