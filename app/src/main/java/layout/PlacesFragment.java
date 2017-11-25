package layout;

import android.app.*;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ca.bcit.fyndit.R;

public class PlacesFragment extends ListFragment {
    private static final String PLACEHOLDER_NAME = "assets/placeHolder.jpg";

    List<LocationDetail> locationDetails = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        locationDetails = getDetails();
        List<Map<String, String>> data = getDataSet(locationDetails);

        SimpleAdapter adapter = new SimpleAdapter(getActivity(), data,
                R.layout.places_view, new String[]{"name", "image"}, new int[]{R.id.placeName, R.id.imageViewList});

        setListAdapter(adapter);

        getListView().setDivider(null);

        super.onActivityCreated(savedInstanceState);
    }

    public List<LocationDetail> getDetails() {
        List<LocationDetail> details = new ArrayList<>();

        String dataString = getArguments() == null ? null : getArguments().getString("data");

        if (dataString != null) {
            try {
                JSONArray data = new JSONArray(dataString);
                for (int i = 0; i < data.length(); i++) {
                    LocationDetail detail = LocationDetail.fromJson(data.getJSONObject(i));
                    details.add(detail);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return details;
    }

    public static List<Map<String, String>> getDataSet(List<LocationDetail> details) {
        List<Map<String, String>> dataSet = new ArrayList<>();

        for (LocationDetail detail : details) {
            try {
                HashMap<String, String> mapData = new HashMap<>();
                mapData.put("name", detail.getName());
                mapData.put("image", "content://ca.bcit.fyndit.assets/" + detail.getImageName());
                dataSet.add(mapData);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return dataSet;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        showCardDialog(this.locationDetails.get(position));
    }

    public void showCardDialog(LocationDetail d) {

        // DialogFragment.show() will take care of adding the fragment
        // in a transaction.  We also want to remove any currently showing
        // dialog, so make our own transaction and take care of that here.
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        TextView textView = (TextView) getView().findViewById(R.id.placeName);

        Toast.makeText(getActivity(), d.getName(),
                Toast.LENGTH_LONG).show();

        // Create and show the dialog.
        DialogFragment newFragment = CardDialog.newInstance(d);
        newFragment.show(ft, "dialog");
    }
}