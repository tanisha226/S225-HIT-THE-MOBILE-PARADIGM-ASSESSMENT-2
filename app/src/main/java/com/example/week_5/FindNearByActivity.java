package com.example.week_5;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class FindNearByActivity extends AppCompatActivity {
    private static final int REQ_LOCATION = 101;
    private TextView tvLocation;

    private final List<Office> offices = Arrays.asList(
            new Office("Main Help Desk", "+61-2-9000-1234", -33.8688, 151.2093),   // Sydney CBD
            new Office("Community Centre", "+61-2-9555-5678", -33.8731, 151.2110), // near Town Hall
            new Office("IT Support Hub", "+61-2-9222-2468", -33.8642, 151.2070)    // Circular Quay
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_find_near_by);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvLocation = findViewById(R.id.tvLocation);
        RecyclerView rv = findViewById(R.id.rvOffices);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new OfficeAdapter(offices, (office, action) -> {
            if ("call".equals(action)) {
                startActivity(new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:" + office.phone)));
            } else if ("map".equals(action)) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + office.lat + "," + office.lng +
                        "(" + Uri.encode(office.name) + ")");
                startActivity(new Intent(Intent.ACTION_VIEW, gmmIntentUri));
            }
        }));

        requestLocation();
    }

    @SuppressLint("DefaultLocale")
    private void requestLocation() {
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        String perm = Manifest.permission.ACCESS_FINE_LOCATION;

        if (ActivityCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{perm}, REQ_LOCATION);
            // Still show list; feature works without precise location.
            tvLocation.setText("Location permission needed for precise position. You can still use Call/Map.");
            return;
        }

        try {
            String provider = LocationManager.GPS_PROVIDER;
            lm.requestSingleUpdate(provider, new LocationListener() {
                @Override public void onLocationChanged(@NonNull Location location) {
                    tvLocation.setText(String.format("Your location: %.4f, %.4f",
                            location.getLatitude(), location.getLongitude()));
                }
            }, null);

            Location last = lm.getLastKnownLocation(provider);
            if (last != null) {
                tvLocation.setText(String.format("Your location: %.4f, %.4f",
                        last.getLatitude(), last.getLongitude()));
            }
        } catch (Exception e) {
            tvLocation.setText("Unable to read location. You can still use Call/Map.");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQ_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requestLocation();
            } else {
                Toast.makeText(this, "Permission denied. Feature still usable via Call/Map.",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

}