package layout;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import ca.bcit.fyndit.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);

        TextView actionBarTitle = (TextView)findViewById(R.id.action_bar_title);
        actionBarTitle.setText(R.string.app_name);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Righteous-Regular.ttf");

        actionBarTitle.setTypeface(custom_font);

    }


    public void onClickCategory(View view)
    {
        String buttonText = ((Button)view).getText().toString();
        Intent intent = new Intent(this, CategoryActivity.class);
        intent.putExtra("category", buttonText);
        startActivity(intent);
    }

    public void onClickAbout(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
}
