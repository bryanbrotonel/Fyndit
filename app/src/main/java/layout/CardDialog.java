package layout;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import ca.bcit.fyndit.R;

public class CardDialog extends DialogFragment {
    private LocationDetail detail;
    private ShareActionProvider shareActionProvider;


    public static CardDialog newInstance(LocationDetail detail) {
        CardDialog cardDialog = new CardDialog();
        Bundle args = new Bundle();
        args.putString("data", detail.getJson());

        cardDialog.setArguments(args);
        return cardDialog;
    }

    public void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);

        try {
            detail = LocationDetail.fromJson(new JSONObject(getArguments().getString("data")));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout to use as dialog or embedded fragment
        return inflater.inflate(R.layout.fragment_card_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ((TextView)view.findViewById(R.id.title)).setText(detail.getName());
        ((TextView)view.findViewById(R.id.textView)).setText(detail.getAddress());
        FloatingActionButton shareFAB = (FloatingActionButton)view.findViewById(R.id.share);

        ((Button)view.findViewById(R.id.info)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CardDialog.this.onNavigate(view);
            }
        });

        shareFAB.setOnClickListener(new View.OnClickListener() {

//            Floating Acition Bar onClick method
            @Override
            public void onClick(View view) {
//                Place sharing
                String shareBody = "Come with me to Fynd the " + detail.getName() +
                        " with the Fyndit - New West app!";
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Come Fyndit with me!");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.shareTitle)));
            }
        });

        ImageView imgView = (ImageView) view.findViewById(R.id.thumbnail);

        try (InputStream ims = getActivity().getAssets().open(detail.getImageName())) {
            Drawable d = Drawable.createFromStream(ims, null);
            imgView.setImageDrawable(d);
        } catch (IOException ex) {
        }
    }

        /** The system calls this only when creating the layout in a dialog. */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        return dialog;
    }

    public void onNavigate(View v) {
        Uri gmmIntentUri = Uri.parse("geo:49.2057,122.9110?q=" + Uri.encode(detail.getAddress()));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

}
