package com.adriesavana.mobileappstest.module.detail;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.adriesavana.mobileappstest.R;
import com.adriesavana.mobileappstest.network.NetworkManager;
import com.adriesavana.mobileappstest.network.NetworkService;
import com.bumptech.glide.Glide;
import com.github.ornolfr.ratingview.RatingView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jakewharton.rxbinding.view.RxView;
import com.mikhaellopez.circularimageview.CircularImageView;

import rx.functions.Action1;

public class PageDetail extends FragmentActivity implements OnMapReadyCallback, DetailView {

    private GoogleMap mMap;
    private RatingView ratingView;
    private SupportMapFragment mapFragment;

    private CircularImageView mImageView;
    private TextView mName, mSpecialis, mSechedule, mDescription;
    private Button mButton;
    private double lat, lng;

    private String area;

    private DetailPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_detail);
        initComponent();
        mPresenter = new DetailPresenterImpl(this);
        mPresenter.getDoctorById(getIntent().getIntExtra("id", 20));
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng mLatLng = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().position(mLatLng).title(area));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mLatLng, 12.4f));
    }

    private void initComponent() {
        ratingView = (RatingView) findViewById(R.id.ratingView);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        mImageView = (CircularImageView) findViewById(R.id.profilePicture);
        mName = (TextView) findViewById(R.id.name);
        mSpecialis = (TextView) findViewById(R.id.speciality);
        mSechedule = (TextView) findViewById(R.id.schedule);
        mDescription = (TextView) findViewById(R.id.description);
        mButton = (Button) findViewById(R.id.btnAnApointment);
        RxView.clicks(mButton).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                Toast.makeText(PageDetail.this, "Book an appointment.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void setRatingView(int recommendation) {
        if (recommendation  < 59) ratingView.setRating(0.5f);
        else if (recommendation  >= 59  && recommendation  < 99) ratingView.setRating(1f);
        else if (recommendation  >= 99  && recommendation  < 199) ratingView.setRating(1.5f);
        else if (recommendation  >= 199 && recommendation  < 249) ratingView.setRating(2f);
        else if (recommendation  >= 249 && recommendation  < 299) ratingView.setRating(2.5f);
        else if (recommendation  >= 299 && recommendation  < 386) ratingView.setRating(3f);
        else if (recommendation  >= 386 && recommendation  < 420) ratingView.setRating(3.5f);
        else if (recommendation  >= 420 && recommendation  < 599) ratingView.setRating(4f);
        else if (recommendation  >= 599 && recommendation  < 801) ratingView.setRating(4.5f);
        else if (recommendation  >= 801) ratingView.setRating(5f);

    }

    @Override
    public void setName(String name) {
        mName.setText(name);
    }

    @Override
    public void setImage(String imgUrl) {
        Glide.with(this).load(imgUrl).fitCenter().dontAnimate().into(mImageView);
    }

    @Override
    public void setSpecialist(String specialist) {
        mSpecialis.setText(specialist);
    }

    @Override
    public void setSechedule(String sechedule) {
        mSechedule.setText(sechedule);
    }

    @Override
    public void setDescription(String description) {
        mDescription.setText(description);
    }

    @Override
    public void setLatLng(String area, double var1, double var3) {
        this.area = area;
        this.lat = var1;
        this.lng = var3;
        mapFragment.getMapAsync(this);
    }
}
