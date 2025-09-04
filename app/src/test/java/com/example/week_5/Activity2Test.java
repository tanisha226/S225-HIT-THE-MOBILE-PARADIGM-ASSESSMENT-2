package com.example.week_5;

import android.content.Intent;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(RobolectricTestRunner.class)
public class Activity2Test {

    private Activity2 activity;
    private ActivityController<Activity2> controller;
    private LinearLayout mockLinearLayout;
    private TextView mockTxtUsr;
    private EditText mockTxtViews;

    @Before
    public void setUp() {
        // Create Intent with test data
        Intent intent = new Intent();
        intent.putExtra("user_name", "TestUser");
        intent.putExtra("user_type", "Admin");

        // Create activity controller with intent
        controller = Robolectric.buildActivity(Activity2.class, intent);
        activity = controller.create().get();

        // Mock the UI components (if needed for isolated testing)
        mockLinearLayout = mock(LinearLayout.class);
        mockTxtUsr = mock(TextView.class);
        mockTxtViews = mock(EditText.class);
    }

    @Test
    public void testOnCreate_IntentDataRetrieved() {
        // Test that onCreate properly retrieves and displays intent data
        TextView txtUsr = activity.findViewById(R.id.lbluser);
        assertNotNull("txtUsr should not be null", txtUsr);

        String expectedText = "Welcome TestUser. You have logged in as Admin";
        assertEquals("Welcome message should match expected format",
                expectedText, txtUsr.getText().toString());
    }

    @Test
    public void testOnCreate_ViewsInitialized() {
        // Test that all views are properly initialized
        LinearLayout ll = activity.findViewById(R.id.ll);
        TextView txtUsr = activity.findViewById(R.id.lbluser);
        EditText txtViews = activity.findViewById(R.id.txtNoofViews);

        assertNotNull("LinearLayout should be initialized", ll);
        assertNotNull("txtUsr should be initialized", txtUsr);
        assertNotNull("txtViews should be initialized", txtViews);
    }

    @Test
    public void testCreateViews_WithValidInput() {
        // Set up the EditText with a valid number

        EditText txtViews = activity.findViewById(R.id.txtNoofViews);
        LinearLayout ll = activity.findViewById(R.id.ll);

        txtViews.setText("3");

        // Get initial child count
        int initialChildCount = ll.getChildCount();

        // Call the method
        activity.createViews(null);

        // Verify that 3 TextViews were added
        assertEquals("Should add 3 TextViews to LinearLayout",
                initialChildCount +3, ll.getChildCount());


    }


}