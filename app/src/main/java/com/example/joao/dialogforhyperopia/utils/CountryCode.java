package com.example.joao.dialogforhyperopia.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

import java.util.Locale;

public class CountryCode {
    private String mCountryZipCode;

    public CountryCode(Object systemService) {
        Locale.getDefault().getCountry();

        TelephonyManager tm = (TelephonyManager) systemService;

        // Get the country code
        this.mCountryZipCode = tm.getSimCountryIso().toUpperCase();
    }

    public String getmCountryZipCode() {
        return mCountryZipCode;
    }
}
