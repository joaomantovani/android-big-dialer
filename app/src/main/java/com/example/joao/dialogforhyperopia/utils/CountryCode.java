package com.example.joao.dialogforhyperopia.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.util.Locale;

public class CountryCode {
    private String mCountryZipCode;
    private String mIntentNumberPreCall;
    private PhoneNumberUtil pnu;
    private Phonenumber.PhoneNumber pn;

    public CountryCode(Object systemService) {
        Locale.getDefault().getCountry();

        TelephonyManager tm = (TelephonyManager) systemService;

        pnu = PhoneNumberUtil.getInstance();
        pn = null;

        // Get the country code
        this.mCountryZipCode = tm.getSimCountryIso().toUpperCase();
    }

    public String getmCountryZipCode() {
        return mCountryZipCode;
    }

    public String getmIntentCallNumber(String callNumber) throws NumberParseException {
//        return "tel:" + getmCountryZipCode();
        pn = pnu.parse(callNumber, getmCountryZipCode());

        return "tel:" + pnu.format(pn, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL);
    }

    public String getNationalNumber(String callNumber) throws NumberParseException {
        pn = pnu.parse(callNumber, getmCountryZipCode());

        return pnu.format(pn, PhoneNumberUtil.PhoneNumberFormat.NATIONAL);
    }
}
