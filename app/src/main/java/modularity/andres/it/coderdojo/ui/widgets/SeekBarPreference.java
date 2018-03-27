package modularity.andres.it.coderdojo.ui.widgets;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import modularity.andres.it.coderdojo.settings.UserPreferences;

/**
 * Created by Andres on 12/5/2017.
 */

public class SeekBarPreference extends DialogPreference implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {
    // ------------------------------------------------------------------------------------------
    // Private attributes :
    private final String androidns = "http://schemas.android.com/apk/res/android";

    private UserPreferences userPref;

    private SeekBar mSeekBar;
    private TextView mValueText;
    private Context mContext;

    private String mDialogMessage, mSuffix;
    private int mDefault, mMax, mValue = 0;
    private final int MIN = 1;
    // ------------------------------------------------------------------------------------------


    // ------------------------------------------------------------------------------------------
    // Constructor :
    public SeekBarPreference(Context context, AttributeSet attrs) {

        super(context, attrs);
        mContext = context;

        //loading user preferences
        userPref = new UserPreferences(context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE));

        setSummary(Integer.toString(userPref.getSearchRange()) + " km");

        // Get string value for dialogMessage :
        setDialogMessage(attrs);

        // Get string value for suffix (text attribute in xml file) :
        int mSuffixId = attrs.getAttributeResourceValue(androidns, "text", 0);
        if (mSuffixId == 0) mSuffix = attrs.getAttributeValue(androidns, "text");
        else mSuffix = mContext.getString(mSuffixId);

        // Get default and max seekbar values :
        mDefault = userPref.getSearchRange();
        mMax = 300;

        if (shouldPersist()) {
            persistInt(mDefault);
        }
    }

    private void setDialogMessage(AttributeSet attrs) {
        int mDialogMessageId = attrs.getAttributeResourceValue(androidns, "dialogMessage", 0);
        if (mDialogMessageId == 0)
            mDialogMessage = attrs.getAttributeValue(androidns, "dialogMessage");
        else mDialogMessage = mContext.getString(mDialogMessageId);
    }
    // ------------------------------------------------------------------------------------------


    // ------------------------------------------------------------------------------------------
    // DialogPreference methods :
    @Override
    protected View onCreateDialogView() {

        LinearLayout.LayoutParams params;
        LinearLayout layout = new LinearLayout(mContext);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(6, 6, 6, 6);

        TextView mSplashText = new TextView(mContext);
        mSplashText.setPadding(30, 10, 30, 10);
        if (mDialogMessage != null)
            mSplashText.setText(mDialogMessage);
        layout.addView(mSplashText);

        mValueText = new TextView(mContext);
        mValueText.setGravity(Gravity.CENTER_HORIZONTAL);
        mValueText.setTextSize(32);
        params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(mValueText, params);

        mSeekBar = new SeekBar(mContext);
        mSeekBar.setOnSeekBarChangeListener(this);
        layout.addView(mSeekBar, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        if (shouldPersist())
            mValue = getPersistedInt(mDefault);
        mSeekBar.setMax(mMax);
        mSeekBar.setProgress(mValue);

        return layout;
    }

    @Override
    protected void onBindDialogView(View v) {
        super.onBindDialogView(v);
        mSeekBar.setMax(mMax);
        mSeekBar.setProgress(mValue);
    }

    @Override
    protected void onSetInitialValue(boolean restore, Object defaultValue) {
        super.onSetInitialValue(restore, defaultValue);
        if (restore)
            mValue = shouldPersist() ? getPersistedInt(mDefault) : 0;
        else
            mValue = (Integer) defaultValue;
    }
    // ------------------------------------------------------------------------------------------


    // ------------------------------------------------------------------------------------------
    // OnSeekBarChangeListener methods :
    @Override
    public void onProgressChanged(SeekBar seek, int value, boolean fromTouch) {
        if (value < MIN) {
            value = MIN;
            seek.setProgress(value);
        }
        String t = String.valueOf(value) + " km";
        mValueText.setText(mSuffix == null ? t : t.concat(" " + mSuffix));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seek) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seek) {
    }

    public void setMax(int max) {
        mMax = max;
    }

    public int getMax() {
        return mMax;
    }

    public void setProgress(int progress) {
        mValue = progress;
        if (mSeekBar != null)
            mSeekBar.setProgress(progress);
    }

    public int getProgress() {
        return mValue;
    }
    // ------------------------------------------------------------------------------------------


    // ------------------------------------------------------------------------------------------
    // Set the positive button listener and onClick action :
    @Override
    public void showDialog(Bundle state) {

        super.showDialog(state);

        Button positiveButton = ((AlertDialog) getDialog()).getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mValue = mSeekBar.getProgress();
        persistInt(mValue);
        callChangeListener(mValue);
        setSummary(Integer.toString(mValue) + " km");
        userPref.setSearchRange(mValue);


        getDialog().dismiss();
    }


    // ------------------------------------------------------------------------------------------
}