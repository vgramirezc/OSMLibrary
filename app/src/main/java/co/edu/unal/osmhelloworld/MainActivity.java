package co.edu.unal.osmhelloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.MinimapOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MapView mOpenMapView;
    MyLocationNewOverlay mLocationOverlay;
    RotationGestureOverlay mRotationGestureOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ItemizedIconOverlay.OnItemGestureListener<OverlayItem> myOnItemGestureListener
                = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {

            @Override
            public boolean onItemLongPress(int arg0, OverlayItem arg1) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {
                Toast.makeText(
                        MainActivity.this,
                        item.getSnippet()+ "\n" + item.getTitle() + "\n"
                                + item.getPoint().getLatitude() + " : "
                                + item.getPoint().getLongitude(),
                        Toast.LENGTH_LONG).show();

                return true;
            }

        };

        mOpenMapView = (MapView) findViewById(R.id.map);
        mOpenMapView.setBuiltInZoomControls(true);
        IMapController myMapController = mOpenMapView.getController();
        myMapController.setZoom(4);
        mOpenMapView.setMultiTouchControls(true);

        ArrayList<OverlayItem> points = new ArrayList<OverlayItem>();
        points.add(new OverlayItem("US", "US", new GeoPoint(38.883333, -77.016667)));
        points.add(new OverlayItem("China", "China", new GeoPoint(39.916667, 116.383333)));
        points.add(new OverlayItem("United Kingdom", "United Kingdom", new GeoPoint(51.5, -0.116667)));
        points.add(new OverlayItem("Germany", "Germany", new GeoPoint(52.516667, 13.383333)));
        points.add(new OverlayItem("Korea", "Korea", new GeoPoint(38.316667, 127.233333)));
        points.add(new OverlayItem("India", "India", new GeoPoint(28.613333, 77.208333)));
        points.add(new OverlayItem("Russia", "Russia", new GeoPoint(55.75, 37.616667)));
        points.add(new OverlayItem("France", "France", new GeoPoint(48.856667, 2.350833)));
        points.add(new OverlayItem("Canada", "Canada", new GeoPoint(45.4, -75.666667)));

        ItemizedOverlayWithFocus<OverlayItem> myItemizedOverlay =
                new ItemizedOverlayWithFocus<OverlayItem>(this, points, myOnItemGestureListener);
        mOpenMapView.getOverlays().add(myItemizedOverlay);
        myItemizedOverlay.setFocusItemsOnTap(true);

        MinimapOverlay miniMapOverlay =
                new MinimapOverlay(this, mOpenMapView.getTileRequestCompleteHandler());
        miniMapOverlay.setZoomDifference(5);
        miniMapOverlay.setHeight(200);
        miniMapOverlay.setWidth(200);
        mOpenMapView.getOverlays().add(miniMapOverlay);

        mRotationGestureOverlay = new RotationGestureOverlay(mOpenMapView);
        mRotationGestureOverlay.setEnabled(true);
        mOpenMapView.setMultiTouchControls(true);
        mOpenMapView.getOverlays().add(this.mRotationGestureOverlay);

        mLocationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider( this ),mOpenMapView);
        mLocationOverlay.enableMyLocation();
        mOpenMapView.getOverlays().add(mLocationOverlay);

    }
}
