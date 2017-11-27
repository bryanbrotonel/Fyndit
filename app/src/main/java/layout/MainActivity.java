package layout;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import ca.bcit.fyndit.R;

public class MainActivity extends AppCompatActivity {

    private static final String DATA_SET_ASSET_NAME = "data.json";
    private JSONArray dataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //        Action bar title
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);

        TextView actionBarTitle = (TextView)findViewById(R.id.action_bar_title);
        actionBarTitle.setText(R.string.app_name);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Righteous-Regular.ttf");

        actionBarTitle.setTypeface(custom_font);

        try {
            this.dataSet = new JSONArray(readJsonAsset());
        } catch (JSONException e) {
            //Internal constant data, therefore a runtime error.
            throw new RuntimeException(e);
        }
    }

    private String getCategory(String name) {
        try {
            for (int i = 0; i < this.dataSet.length(); i++) {
                JSONObject o = this.dataSet.getJSONObject(i);
                if (o.getString("name").compareTo(name) == 0)
                    return o.getJSONArray("data").toString();
            }
        } catch(JSONException e) {
            throw new RuntimeException(e);
        }

        throw new RuntimeException("Unknown category: " + name);
    }

    private String readJsonAsset() {
        try (BufferedReader is = new BufferedReader(new InputStreamReader(getAssets().open(DATA_SET_ASSET_NAME)))) {
            StringBuilder sb = new StringBuilder();
            String line = null;

            while((line = is.readLine()) != null)
                sb.append(line).append("\n");

            return sb.toString();
        } catch(Exception e) {
            //Since the asset is stored with the application (i.e not externally.) this is therefore
            //a runtime exception.
            throw new RuntimeException(e);
        }


    }


    public void onClickCategory(View view)
    {
        String buttonText = ((Button)view).getText().toString();
        Intent intent = new Intent(this, CategoryActivity.class);
        intent.putExtra("category", buttonText);

        String jsonData = "";
        if(buttonText.compareTo(this.getString(R.string.shopping)) == 0) {
            jsonData = getCategory("shopping");
        } else if(buttonText.compareTo(this.getString(R.string.rec)) == 0) {
            jsonData = getCategory("rec");
        } else if(buttonText.compareTo(this.getString(R.string.park)) == 0) {
            jsonData = getCategory("park");
        } else if(buttonText.compareTo(this.getString(R.string.cafe)) == 0) {
            jsonData = getCategory("cafe");
        } else
            throw new RuntimeException("Unrecognized category: " + buttonText);

        intent.putExtra("data", jsonData);
        startActivity(intent);
    }

    public void onClickAbout(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
}
