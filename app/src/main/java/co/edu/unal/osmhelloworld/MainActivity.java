package co.edu.unal.osmhelloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
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

    MyLocationNewOverlay mLocationOverlay;
    CompassOverlay mCompassOverlay;
    RotationGestureOverlay mRotationGestureOverlay;
    ScaleBarOverlay mScaleBarOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Important! Set your user agent to prevent getting banned from the OSM servers
        //org.osmdroid.tileprovider.constants.OpenStreetMapTileProviderConstants.setUserAgentValue(BuildConfig.APPLICATION_ID);

        ItemizedIconOverlay.OnItemGestureListener<OverlayItem> myOnItemGestureListener = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {

            @Override
            public boolean onItemLongPress(int arg0, OverlayItem arg1) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {
                Toast.makeText(
                        MainActivity.this,
                        item.getSnippet()+ "n" + item.getTitle() + "n"
                                + item.getPoint().getLatitude() + " : "
                                + item.getPoint().getLongitude(),
                        Toast.LENGTH_LONG).show();

                return true;
            }

        };

        MapView myOpenMapView = (MapView)findViewById(R.id.map);
        myOpenMapView.setBuiltInZoomControls(true);
        IMapController myMapController = myOpenMapView.getController();
        myMapController.setZoom(4);
        myOpenMapView.setMultiTouchControls(true);

        ArrayList<OverlayItem> anotherOverlayItemArray;
        anotherOverlayItemArray = new ArrayList<OverlayItem>();

        anotherOverlayItemArray.add(new OverlayItem("0, 0", "0, 0", new GeoPoint(0.0, 0.0)));
        anotherOverlayItemArray.add(new OverlayItem("US", "US", new GeoPoint(38.883333, -77.016667)));
        anotherOverlayItemArray.add(new OverlayItem("China", "China", new GeoPoint(39.916667, 116.383333)));
        anotherOverlayItemArray.add(new OverlayItem("United Kingdom", "United Kingdom", new GeoPoint(51.5, -0.116667)));
        anotherOverlayItemArray.add(new OverlayItem("Germany", "Germany", new GeoPoint(52.516667, 13.383333)));
        anotherOverlayItemArray.add(new OverlayItem("Korea", "Korea", new GeoPoint(38.316667, 127.233333)));
        anotherOverlayItemArray.add(new OverlayItem("India", "India", new GeoPoint(28.613333, 77.208333)));
        anotherOverlayItemArray.add(new OverlayItem("Russia", "Russia", new GeoPoint(55.75, 37.616667)));
        anotherOverlayItemArray.add(new OverlayItem("France", "France", new GeoPoint(48.856667, 2.350833)));
        anotherOverlayItemArray.add(new OverlayItem("Canada", "Canada", new GeoPoint(45.4, -75.666667)));

        ItemizedOverlayWithFocus<OverlayItem> anotherItemizedIconOverlay = new ItemizedOverlayWithFocus<OverlayItem>(this, anotherOverlayItemArray, myOnItemGestureListener);
        myOpenMapView.getOverlays().add(anotherItemizedIconOverlay);

        anotherItemizedIconOverlay.setFocusItemsOnTap(true);

        /*MinimapOverlay miniMapOverlay = new MinimapOverlay(this, myOpenMapView.getTileRequestCompleteHandler());
        miniMapOverlay.setZoomDifference(5);
        miniMapOverlay.setHeight(200);
        miniMapOverlay.setWidth(200);
        myOpenMapView.getOverlays().add(miniMapOverlay);*/

        /*mCompassOverlay = new CompassOverlay(this, new InternalCompassOrientationProvider(this), myOpenMapView);
        mCompassOverlay.enableCompass();
        myOpenMapView.getOverlays().add(mCompassOverlay);*/

        /*mScaleBarOverlay = new ScaleBarOverlay(myOpenMapView);
        mScaleBarOverlay.setCentred(true);
        //play around with these values to get the location on screen in the right place for your application
        mScaleBarOverlay.setScaleBarOffset( 200 , 50);
        myOpenMapView.getOverlays().add(this.mScaleBarOverlay);*/

        mRotationGestureOverlay = new RotationGestureOverlay(this, myOpenMapView);
        mRotationGestureOverlay.setEnabled(true);
        myOpenMapView.setMultiTouchControls(true);
        myOpenMapView.getOverlays().add(this.mRotationGestureOverlay);

        mLocationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider( this ),myOpenMapView);
        mLocationOverlay.enableMyLocation();
        myOpenMapView.getOverlays().add(mLocationOverlay);

    }
}
