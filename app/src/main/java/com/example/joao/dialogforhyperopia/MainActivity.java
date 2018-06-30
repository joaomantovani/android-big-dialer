package com.example.joao.dialogforhyperopia;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mNumberCallTextView;
    private static String NULL_START = "";
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_0).setOnClickListener(this);
        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
        findViewById(R.id.btn_4).setOnClickListener(this);
        findViewById(R.id.btn_5).setOnClickListener(this);
        findViewById(R.id.btn_6).setOnClickListener(this);
        findViewById(R.id.btn_7).setOnClickListener(this);
        findViewById(R.id.btn_8).setOnClickListener(this);
        findViewById(R.id.btn_9).setOnClickListener(this);
        findViewById(R.id.btn_0).setOnClickListener(this);

        findViewById(R.id.btn_delete_once).setOnClickListener(this);
        findViewById(R.id.btn_delete_all).setOnClickListener(this);

        findViewById(R.id.btn_call).setOnClickListener(this);

        mNumberCallTextView = findViewById(R.id.tv_number_call);
        mNumberCallTextView.setText(NULL_START);

        vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    public void onClick(View v) {
        String mNumber = mNumberCallTextView.getText().toString();

        switch (v.getId()) {
            case R.id.btn_delete_all:
                // Reset the string to ""
                mNumberCallTextView.setText(NULL_START);

                if (vibrator != null) {
                    vibrator.vibrate(getResources().getInteger(R.integer.digit_erase_all_vibration_milliseconds));
                }

                break;
            case R.id.btn_delete_once:
                // If the content of the textView is not empty
                if (!mNumberCallTextView.getText().toString().isEmpty()) {
                    //Delete the last digit
                    mNumberCallTextView.setText(
                            mNumber.substring(0, mNumber.length() - 1)
                    );

                    if (vibrator != null) {
                        vibrator.vibrate(getResources().getInteger(R.integer.digit_erase_once_vibration_milliseconds));
                    }
                }
                break;
            case R.id.btn_call:
                String mNumberToCall = new NumberFormatter(mNumberCallTextView.getText()
                        .toString()).getmRawNumber();

                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+55" + mNumberToCall));

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            MainActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            1);
                } else {
                    startActivity(intent);
                }

                break;
            default:
                // get the button clicked
                Button button = (Button) v;

                // Get the text from the button clicked
                String number = button.getText().toString();
                mNumberCallTextView.append(number);

                String mFormattedNumber = new NumberFormatter(mNumberCallTextView.getText()
                        .toString()).getmNumberFormatted();

                mNumberCallTextView.setText(mFormattedNumber);

                if (vibrator != null) {
                    vibrator.vibrate(getResources().getInteger(R.integer.digit_number_vibration_milliseconds));
                }

                break;
        }
    }
}