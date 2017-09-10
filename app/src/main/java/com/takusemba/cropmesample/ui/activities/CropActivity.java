package com.takusemba.cropmesample.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.takusemba.cropmesample.R;
import com.takusemba.cropmesample.clients.AlbumClient;
import com.takusemba.cropmesample.models.Album;
import com.takusemba.cropmesample.ui.adapters.AlbumAdapter;

import java.util.List;

public class CropActivity extends AppCompatActivity {

    AlbumClient client;
    AlbumAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop);

        client = new AlbumClient(this);

        findViewById(R.id.cross).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.crop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CropActivity.this, ResultActivity.class));
            }
        });

        client.getAlbums(new AlbumClient.OnLoadedListener() {
            @Override
            public void onSuccess(List<Album> albums) {
                if (albums.isEmpty()) {
                    Snackbar.make(findViewById(R.id.container), R.string.error_no_photos_found, Snackbar.LENGTH_LONG).show();
                } else {
                    adapter = new AlbumAdapter(albums);
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CropActivity.this, LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }


}
