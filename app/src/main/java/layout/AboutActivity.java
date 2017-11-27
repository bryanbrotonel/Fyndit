package layout;

import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import ca.bcit.fyndit.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);

//        Action bar title
        TextView actionBarTitle = (TextView)findViewById(R.id.action_bar_title);
        actionBarTitle.setText(R.string.app_name);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Righteous-Regular.ttf");

        actionBarTitle.setTypeface(custom_font);
    }
}
