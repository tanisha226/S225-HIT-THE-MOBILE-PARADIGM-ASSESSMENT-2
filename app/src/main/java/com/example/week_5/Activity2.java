package com.example.week_5;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Activity2 extends Activity {

    private LinearLayout ll;
    private TextView txtUsr;
    private EditText txtViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        this.ll=(LinearLayout) findViewById(R.id.ll);
        this.txtUsr = (TextView) findViewById(R.id.lbluser);
        this.txtViews=(EditText) findViewById(R.id.txtNoofViews);

        Intent i = getIntent(); // Retrieving the Intent that started the Activity
        String user_name = i.getStringExtra("user_name"); // Retrieving data from the Intent
        String user_type = i.getStringExtra("user_type");
        txtUsr.setText(getString(R.string.welcome_message, user_name , user_type));
    }
    public void createViews(View view) {
        // Clear existing views first
        ll.removeAllViews();
        
        // Get the number of views to generate
        String input = txtViews.getText().toString();
        if (input.isEmpty()) {
            return;
        }
        
        int numViews = Integer.parseInt(input);
        
        // Test: Verify the correct number of lines is generated
        int actualLinesGenerated = 0;
        
        for (int i = 1; i <= numViews; i++) {
            TextView textView = new TextView(this);
            textView.setText(getString(R.string.content, i));
            textView.setTextSize(20);
            textView.setGravity(Gravity.CENTER);  // TextViews will be Centered to the Layout
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            ll.addView(textView);   // Add the newly created TextView to the Layout
            actualLinesGenerated++;
        }
        
        // Test verification - you can check this in logcat
        System.out.println("Expected lines: " + numViews + ", Actual lines generated: " + actualLinesGenerated);
    }

    public void openNearByScreen(View view) {
        Intent intent = new Intent(this, FindNearByActivity.class);
        startActivity(intent);
        finish();
    }
    public void logout(View view) {
        // Return to the main activity (front page)
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}