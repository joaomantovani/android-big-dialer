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
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joao.dialogforhyperopia.utils.CountryCode;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
    private TextView mNumberCallTextView;
    private static String NULL_START = "";
    private Vibrator vibrator;
    private PhoneNumberUtil pnu;
    private Phonenumber.PhoneNumber pn;
    private String mCountryCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnLongClickListener(this);

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
        findViewById(R.id.btn_0).setOnLongClickListener(this);

        findViewById(R.id.btn_asterisk).setOnClickListener(this);
        findViewById(R.id.btn_hashtag).setOnClickListener(this);

        findViewById(R.id.btn_call).setOnClickListener(this);

        mNumberCallTextView = findViewById(R.id.tv_number_call);
        mNumberCallTextView.setText(NULL_START);

        vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        pnu = PhoneNumberUtil.getInstance();
        pn = null;

        // Automatic get the country code
        mCountryCode = new CountryCode(getSystemService((Context.TELEPHONY_SERVICE)))
                .getmCountryZipCode();
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.btn_delete:
                mNumberCallTextView.setText(NULL_START);
                break;
            case R.id.btn_0:
                addNumberToDialer("+");
                break;
        }

        if (vibrator != null) {
            vibrator.vibrate(getResources().getInteger(R.integer.digit_erase_all_vibration_milliseconds));
        }
        
        return true;
    }

    @Override
    public void onClick(View v) {
        String mNumber = mNumberCallTextView.getText().toString();

        switch (v.getId()) {
            case R.id.btn_delete:
                // If the content of the textView is not empty
                if (!mNumberCallTextView.getText().toString().isEmpty()) {
                    //Delete the last digit
                    mNumberCallTextView.setText(
                            mNumber.substring(0, mNumber.length() - 1)
                    );
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
                addNumberToDialer(number);

                break;
        }

        if (vibrator != null) {
            vibrator.vibrate(getResources().getInteger(R.integer.digit_erase_once_vibration_milliseconds));
        }
    }

    /**
     * TODO: 6/30/18 Improve performance here
     *
     * @param number the string number to append on the dialer
     */
    private void addNumberToDialer(String number) {
        mNumberCallTextView.append(number);
        String callNumber = mNumberCallTextView.getText().toString();

        try {
            pn = pnu.parse(callNumber, mCountryCode);
            mNumberCallTextView.setText(pnu.format(pn, PhoneNumberUtil.PhoneNumberFormat.NATIONAL));
        } catch (NumberParseException e) {
            e.printStackTrace();
        }
    }
}