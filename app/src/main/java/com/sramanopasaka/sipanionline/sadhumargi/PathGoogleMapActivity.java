package com.sramanopasaka.sipanionline.sadhumargi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.vision.barcode.Barcode;

import org.w3c.dom.Document;

import java.util.Locale;

public class PathGoogleMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    //List<Overlay> mapOverlays;
    Barcode.GeoPoint point1, point2;
    LocationManager locManager;
    Drawable drawable;
    Document document;
    GMapV2GetRouteDirection v2GetRouteDirection;
    LatLng fromPosition;
    LatLng toPosition;
    GoogleMap mGoogleMap;
    MarkerOptions markerOptions;
    Location location;
Button dir;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_google_map);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolmap);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.statusbarcolor));
        }
        ActionBar actionbar = this.getSupportActionBar();
        actionbar.setTitle(Html.fromHtml("<font color='#000000'>विहार</font>"));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_btn);

        v2GetRouteDirection = new GMapV2GetRouteDirection();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
       /* SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mGoogleMap = supportMapFragment.getMap();*/

        // Enabling MyLocation in Google Map
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
       // mGoogleMap.setMyLocationEnabled(true);



    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mGoogleMap = googleMap;
        // Enabling MyLocation Layer of Google Map
        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.getUiSettings().setAllGesturesEnabled(true);
        googleMap.setTrafficEnabled(true);
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(12));

        markerOptions = new MarkerOptions();

        try
        {
            Bundle b = getIntent().getExtras();
            final double srca1 = b.getDouble("my_latitude");
            final double srcb1 = b.getDouble("my_longitude");
            final double desa1 = b.getDouble("des_latitude");
            final double desb1 = b.getDouble("des_longitude");

            fromPosition = new LatLng(srca1, srcb1);
            toPosition = new LatLng(desa1, desb1);

            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(toPosition, 10);
            mGoogleMap.animateCamera(cameraUpdate);

            mGoogleMap.addMarker(markerOptions.position(toPosition));

            dir=(Button)findViewById(R.id.direction);
            dir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?saddr=%f,%f(%s)&daddr=%f,%f (%s)", srca1, srcb1, "Your Location ", desa1, desb1, "Your Place");
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                    startActivity(intent);
                }
            });

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}