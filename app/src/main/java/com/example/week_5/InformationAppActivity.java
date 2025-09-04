package com.example.week_5;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InformationAppActivity extends Activity {

    private EditText newsletterEmail;
    private EditText communityName;
    private EditText eventTitle;
    private EditText eventDate;
    private EditText eventDescription;
    private TextView statusText;

    
    private ExecutorService executorService;
    private Handler mainHandler;
    private SharedPreferences sharedPreferences;
    
    private List<String> events = new ArrayList<>();
    private List<String> newsletterSubscribers = new ArrayList<>();
    private List<String> communityMembers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_app_activity);
        
        // Initialize async components FIRST
        executorService = Executors.newSingleThreadExecutor();
        mainHandler = new Handler(Looper.getMainLooper());
        sharedPreferences = getSharedPreferences("InformationApp", MODE_PRIVATE);
        
        // Initialize components
        initializeViews();
        loadSavedData();
    }

    private void initializeViews() {
        newsletterEmail = findViewById(R.id.newsletterEmail);
        communityName = findViewById(R.id.communityName);
        eventTitle = findViewById(R.id.eventTitle);
        eventDate = findViewById(R.id.eventDate);
        eventDescription = findViewById(R.id.eventDescription);
        statusText = findViewById(R.id.statusText);

    }



    public void registerNewsletter(View view) {
        String email = newsletterEmail.getText().toString().trim();
        
        if (email.isEmpty()) {
            showStatus("Please enter a valid email address");
            return;
        }

        showStatus("Processing newsletter registration...");
        
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1500);
                
                newsletterSubscribers.add(email);
                saveNewsletterData();
                
                mainHandler.post(() -> {
                    showStatus("Successfully registered for newsletter: " + email);
                    newsletterEmail.setText("");
                    updateNewsletterDisplay();
                });
                
            } catch (InterruptedException e) {
                mainHandler.post(() -> showStatus("Registration failed. Please try again."));
            }
        }, executorService);
    }

    public void joinCommunity(View view) {
        String name = communityName.getText().toString().trim();
        
        if (name.isEmpty()) {
            showStatus("Please enter your name");
            return;
        }

        showStatus("Processing community membership...");
        
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
                
                communityMembers.add(name);
                saveCommunityData();
                
                mainHandler.post(() -> {
                    showStatus("Welcome to the community, " + name + "!");
                    communityName.setText("");
                    updateCommunityDisplay();
                });
                
            } catch (InterruptedException e) {
                mainHandler.post(() -> showStatus("Failed to join community. Please try again."));
            }
        }, executorService);
    }

    public void addEvent(View view) {
        String title = eventTitle.getText().toString().trim();
        String date = eventDate.getText().toString().trim();
        String description = eventDescription.getText().toString().trim();
        
        if (title.isEmpty() || date.isEmpty()) {
            showStatus("Please enter event title and date");
            return;
        }

        showStatus("Adding event to calendar...");
        
        CompletableFuture.runAsync(() -> {
            try {
                // Simulate processing delay
                Thread.sleep(800);
                
                String event = title + " - " + date + ": " + description;
                events.add(event);
                saveEventData();
                
                mainHandler.post(() -> {
                    showStatus("Event added successfully: " + title);
                    eventTitle.setText("");
                    eventDate.setText("");
                    eventDescription.setText("");
                    updateCalendarDisplay();
                });
                
            } catch (InterruptedException e) {
                mainHandler.post(() -> showStatus("Failed to add event. Please try again."));
            }
        }, executorService);
    }

    private void saveNewsletterData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("newsletter_subscribers", String.join(",", newsletterSubscribers));
        editor.apply();
    }

    private void saveCommunityData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("community_members", String.join(",", communityMembers));
        editor.apply();
    }

    private void saveEventData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("events", String.join("|", events));
        editor.apply();
    }

    private void loadSavedData() {
        String newsletterData = sharedPreferences.getString("newsletter_subscribers", "");
        if (!newsletterData.isEmpty()) {
            String[] emails = newsletterData.split(",");
            for (String email : emails) {
                if (!email.isEmpty()) {
                    newsletterSubscribers.add(email);
                }
            }
        }

        String communityData = sharedPreferences.getString("community_members", "");
        if (!communityData.isEmpty()) {
            String[] names = communityData.split(",");
            for (String name : names) {
                if (!name.isEmpty()) {
                    communityMembers.add(name);
                }
            }
        }

        // Load events
        String eventData = sharedPreferences.getString("events", "");
        if (!eventData.isEmpty()) {
            String[] eventArray = eventData.split("\\|");
            for (String event : eventArray) {
                if (!event.isEmpty()) {
                    events.add(event);
                }
            }
        }

        updateAllDisplays();
    }

    private void updateAllDisplays() {
        updateNewsletterDisplay();
        updateCommunityDisplay();
        updateCalendarDisplay();
    }

    private void updateNewsletterDisplay() {
        TextView subscribersText = findViewById(R.id.subscribersText);
        if (subscribersText != null) {
            subscribersText.setText("Subscribers: " + newsletterSubscribers.size());
        }
    }

    private void updateCommunityDisplay() {
        TextView membersText = findViewById(R.id.membersText);
        if (membersText != null) {
            membersText.setText("Members: " + communityMembers.size());
        }
    }

    private void updateCalendarDisplay() {
        LinearLayout eventsContainer = findViewById(R.id.eventsContainer);
        if (eventsContainer != null) {
            eventsContainer.removeAllViews();
            
            for (String event : events) {
                TextView eventView = new TextView(this);
                eventView.setText(event);
                eventView.setTextSize(16);
                eventView.setTextColor(Color.BLACK);
                eventView.setPadding(20, 10, 20, 10);
                eventView.setBackgroundColor(Color.parseColor("#E8F5E8"));
                eventView.setGravity(Gravity.LEFT);
                
                Animation fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
                eventView.startAnimation(fadeIn);
                
                eventsContainer.addView(eventView);
            }
        }
    }

    private void showStatus(String message) {
        statusText.setText(message);
        statusText.setVisibility(View.VISIBLE);
        
        mainHandler.postDelayed(this::hideStatus, 3000);
    }

    private void hideStatus() {
        statusText.setVisibility(View.GONE);
    }

    public void goBack(View view) {
        // Return to the main activity (front page)
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (executorService != null) {
            executorService.shutdown();
        }
    }
}
