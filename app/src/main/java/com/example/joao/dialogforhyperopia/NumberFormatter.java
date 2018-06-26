package com.example.joao.dialogforhyperopia;

/**
 * Class responsible to format a string to a brazilian phone number
 *
 * // TODO: 6/25/18 Fix english grammars mistakes
 * // TODO: 6/25/18 Improve the class docs
 */
public class NumberFormatter {
    private String mCallNumber;

    /**
     * Creates a new number Formater
     *
     * @param mCallNumber the phone number
     */
    public NumberFormatter(String mCallNumber) {
        this.mCallNumber = mCallNumber;
    }

    /**
     * Get the entire call number
     *
     * @return the phone number
     */
    public String getmCallNumber() {
        return mCallNumber;
    }

    /**
     * Set a new value for the call number
     *
     * @param mCallNumber the phone number
     */
    public void setmCallNumber(String mCallNumber) {
        this.mCallNumber = formatNumber(mCallNumber);
    }

    /**
     * Format the number to fetch the brazilian call pattern. Ex: 0000-0000 or 90000-0000
     *
     * @// TODO: 6/25/18 Format to fetch the DDD pattern (11), (19), (41) etc...
     * @// TODO: 6/25/18 Format to fetch the Full DDD parameter with phone carrier Ex. (01511)
     * @// TODO: 6/25/18 Format to fetch the house phone pattern Ex. (4538-2121)
     * @// TODO: 6/25/18 Format to fetch the cellphone pattern Ex. (97538-2121)
     *
     * @param mCallNumber the phone number
     * @return the phone number
     */
    private String formatNumber(String mCallNumber){

        return mCallNumber;
    }
}
