package ca.bcit.fyndit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Intent intent = getIntent();
        String category = intent.getStringExtra("category");

        TextView categoryTitle  = (TextView)findViewById(R.id.categoryText);

        categoryTitle.setText(category);
    }

}
