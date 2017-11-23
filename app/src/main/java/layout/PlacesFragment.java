package layout;

import android.app.*;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.Set;

import ca.bcit.fyndit.R;

public class PlacesFragment extends ListFragment {
    LocationDetail[] values;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        values = new LocationDetail[]{};
        String dataString = getArguments() == null ? null : getArguments().getString("data");

        if(dataString != null) {
            try {
                JSONArray data = new JSONArray(dataString);
                values = new LocationDetail[data.length()];

                for(int i = 0; i < data.length(); i++) {
                    values[i] = LocationDetail.fromJson(data.getJSONObject(i));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        ArrayAdapter<LocationDetail> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.places_view, R.id.placeName, values);

        setListAdapter(adapter);

        getListView().setDivider(null);

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        showCardDialog(this.values[position]);
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

        TextView textView = (TextView)getView().findViewById(R.id.placeName);

        Toast.makeText(getActivity(), d.getName(),
                Toast.LENGTH_LONG).show();

        // Create and show the dialog.
        DialogFragment newFragment = CardDialog.newInstance(d);
        newFragment.show(ft, "dialog");
    }
}