package layout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import ca.bcit.fyndit.R;

public class CardDialog extends DialogFragment {
    String title;

    public static CardDialog newInstance(String name) {
        CardDialog cardDialog = new CardDialog();
        Bundle args = new Bundle();
        args.putString("name", name);

        cardDialog.setArguments(args);
        return cardDialog;
    }

    public void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        title = getArguments().getString("name");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout to use as dialog or embedded fragment
        return inflater.inflate(R.layout.fragment_card_dialog, container, false);
    }

    /** The system calls this only when creating the layout in a dialog. */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        return dialog;
    }
}
