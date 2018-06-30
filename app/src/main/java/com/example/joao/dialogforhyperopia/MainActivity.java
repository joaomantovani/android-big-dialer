package com.example.joao.dialogforhyperopia;

import android.content.Context;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mNumberCallTextView;
    private static String NULL_START = "";

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
    }

    @Override
    public void onClick(View v) {
        String mNumber = mNumberCallTextView.getText().toString();

        switch (v.getId()) {
            case R.id.btn_delete_all:
                // Reset the string to ""
                mNumberCallTextView.setText(NULL_START);
                break;
            case R.id.btn_delete_once:
                // If the content of the textView is not empty
                if (!mNumberCallTextView.getText().toString().isEmpty()) {
                    //Delete the last digit
                    mNumberCallTextView.setText(
                            mNumber.substring(0, mNumber.length() - 1)
                    );
                }
                break;
            case R.id.btn_call:
                // TODO: 6/25/18 Call the activity to phone call
                Toast.makeText(this, "Call", Toast.LENGTH_SHORT).show();
                break;
            default:
                // get the button clicked
                Button button = (Button) v;

                Vibrator vibrator = (Vibrator) v.getContext().getSystemService(Context.VIBRATOR_SERVICE);
                if (vibrator != null) {
                    vibrator.vibrate(getResources().getInteger(R.integer.vibration_milliseconds));
                }

                // Get the text from the button clicked
                String number = button.getText().toString();
                mNumberCallTextView.append(number);

                String mFormattedNumber = new NumberFormatter(mNumberCallTextView.getText()
                        .toString()).getmNumberFormatted();

                mNumberCallTextView.setText(mFormattedNumber);

                break;
        }
    }
}