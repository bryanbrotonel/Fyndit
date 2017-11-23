package layout;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import ca.bcit.fyndit.R;

public class CategoryActivity extends AppCompatActivity {

    private ArrayList<LocationDetail> locations = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Intent intent = getIntent();
        String category = intent.getStringExtra("category");
        String rawJson = intent.getStringExtra("data");

        try {
            JSONArray jsonData = new JSONArray(rawJson);

            for (int i = 0; i < jsonData.length(); i++)
                locations.add(LocationDetail.fromJson(jsonData.getJSONObject(i)));

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);

        TextView actionBarTitle = (TextView)findViewById(R.id.action_bar_title);
        actionBarTitle.setText(category);
    }

}
