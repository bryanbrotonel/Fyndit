package ca.bcit.fyndit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClickBtn(View view)
    {
        String buttonText = ((Button)view).getText().toString();
        Intent intent = new Intent(this, CategoryActivity.class);
        intent.putExtra("category", buttonText);
        Toast.makeText(this, "Clicked on " + buttonText + " button", Toast.LENGTH_LONG).show();
        startActivity(intent);
    }
}
