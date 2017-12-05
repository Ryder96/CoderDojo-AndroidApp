package modularity.andres.it.coderdojo.ui.widgets;

import android.content.Context;
import android.preference.DialogPreference;
import android.util.AttributeSet;

/**
 * Created by Andres on 12/5/2017.
 */

public class LocalPositionPreference extends DialogPreference {

    private Context mContext;
    private String mDialogMessage, mSuffix;
    private String mPosition;

    private final String androidns="http://schemas.android.com/apk/res/android";

    public LocalPositionPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        // Get string value for dialogMessage :
        int mDialogMessageId = attrs.getAttributeResourceValue(androidns, "dialogMessage", 0);
        if(mDialogMessageId == 0) mDialogMessage = attrs.getAttributeValue(androidns, "dialogMessage");
        else mDialogMessage = mContext.getString(mDialogMessageId);

        // Get string value for suffix (text attribute in xml file) :
        int mSuffixId = attrs.getAttributeResourceValue(androidns, "text", 0);
        if(mSuffixId == 0) mSuffix = attrs.getAttributeValue(androidns, "text");
        else mSuffix = mContext.getString(mSuffixId);

    }

}
