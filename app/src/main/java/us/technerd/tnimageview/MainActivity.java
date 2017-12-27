package us.technerd.tnimageview;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ImageView androidImage, appleImage, samsungImage;
    private RelativeLayout holderLayout;
    private Button addBtn;
    private TNImageView tnImageView;
    private List<ImageView> imageViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        androidImage = findViewById(R.id.imageview);
        androidImage.setVisibility(View.GONE);
        appleImage = findViewById(R.id.apple);
        appleImage.setVisibility(View.GONE);
        samsungImage = findViewById(R.id.samsung);
        samsungImage.setVisibility(View.GONE);
        holderLayout = findViewById(R.id.holder);
        addBtn = findViewById(R.id.addBtn);


        tnImageView = new TNImageView();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageViews.add(androidImage);
                imageViews.add(appleImage);
                imageViews.add(samsungImage);

                tnImageView.addListofImageViews(imageViews);
                tnImageView.bringToFrontOnTouch(true);

                androidImage.setVisibility(View.VISIBLE);
                appleImage.setVisibility(View.VISIBLE);
                samsungImage.setVisibility(View.VISIBLE);

            }
        });
        }

}

