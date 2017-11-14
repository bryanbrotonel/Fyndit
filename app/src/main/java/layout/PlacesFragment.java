package layout;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.ListFragment;
import android.widget.Toast;
import ca.bcit.fyndit.R;

public class PlacesFragment extends ListFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String[] values = new String[]{"BCIT", "SFU", "UBC", "VCC", "Langara", "Douglas"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.places_view, R.id.placeName, values);
        setListAdapter(adapter);

        getListView().setDivider(null);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(getActivity(), "Showing Card",
                Toast.LENGTH_LONG).show();
        showCardDialog();
    }

    public void showCardDialog() {
        DialogFragment newFragment = new CardDialog();
        newFragment.show(getFragmentManager(), "text");
    }
}