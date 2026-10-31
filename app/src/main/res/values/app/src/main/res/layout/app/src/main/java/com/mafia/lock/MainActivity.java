package com.mafia.lock;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String CORRECT_PASSWORD = "1234"; // Change this to your desired password

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnUnlock = findViewById(R.id.btnUnlock);
        
        btnUnlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPasswordDialog();
            }
        });
    }

    private void showPasswordDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("System Authentication");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        input.setHint("Enter 4-digit PIN");
        builder.setView(input);

        builder.setPositiveButton("Unlock", (dialog, which) -> {
            String enteredPassword = input.getText().toString();
            if (enteredPassword.equals(CORRECT_PASSWORD)) {
                Toast.makeText(MainActivity.this, "Device Unlocked", Toast.LENGTH_SHORT).show();
                finish(); // Close the app to simulate unlock
            } else {
                Toast.makeText(MainActivity.this, "Incorrect Password!", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    // Override back button to prevent closing the app easily
    @Override
    public void onBackPressed() {
        // Do nothing to trap the user
        Toast.makeText(this, "Device Locked", Toast.LENGTH_SHORT).show();
    }
}
