package com.example.joao.dialogforhyperopia;

import android.content.Intent;

/**
 * Class responsible to format a string to a brazilian phone number
 *
 * // TODO: 6/25/18 Fix english grammars mistakes
 * // TODO: 6/25/18 Improve the class docs
 */
public class NumberFormatter {
    private String mRawNumber;
    private String mNumberFormatted;

    public NumberFormatter(String mNumberFormatted) {
        this.mNumberFormatted = mNumberFormatted;

        this.mRawNumber = mNumberFormatted.replace(" ", "");
        this.mRawNumber = mRawNumber.replace("-", "");
        this.mRawNumber = mRawNumber.replace("(", "");
        this.mRawNumber = mRawNumber.replace(")", "");
    }

    public String getmRawNumber() {
        return mRawNumber;
    }

    public void setmRawNumber(String mRawNumber) {
        this.mRawNumber = mRawNumber;
    }

    public String getmNumberFormatted() {
        formatNumber();

        return mNumberFormatted;
    }

    public void setmNumberFormatted(String mNumberFormatted) {
        this.mNumberFormatted = mNumberFormatted;
    }

    /**
     * Format the number to fetch the brazilian call pattern. Ex: 0000-0000 or 90000-0000
     *
     * @// TODO: 6/25/18 Format to fetch the DDD pattern (11), (19), (41) etc...
     * @// TODO: 6/25/18 Format to fetch the Full DDD parameter with phone carrier Ex. (01511)
     * @// TODO: 6/25/18 Format to fetch the house phone pattern Ex. (4538-2121)
     * @// TODO: 6/25/18 Format to fetch the cellphone pattern Ex. (97538-2121)
     *
     */
    private void formatNumber(){
        boolean isCellphone = mRawNumber.length() == 9;
        boolean isHomephone = mRawNumber.length() == 8;
        Integer substringBreak = 0;

        if (isCellphone) substringBreak = 5;
        if (isHomephone) substringBreak = 4;

        if (isCellphone || isHomephone) {
            mNumberFormatted = mNumberFormatted.replace("-", "");
            mNumberFormatted = mNumberFormatted.substring(0, substringBreak) + "-" +
                    mNumberFormatted.substring(substringBreak, mNumberFormatted.length());
        }
    }
}
