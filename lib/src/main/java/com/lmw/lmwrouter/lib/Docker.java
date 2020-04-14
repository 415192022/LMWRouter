package com.lmw.lmwrouter.lib;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


public class Docker {

    private Intent intent;

    private Context context;

    private Bundle options;

    private boolean shareTransition;

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Bundle getOptions() {
        return options;
    }

    public void setOptions(Bundle options) {
        this.options = options;
    }

    public boolean isShareTransition() {
        return shareTransition;
    }

    public void setShareTransition(boolean shareTransition) {
        this.shareTransition = shareTransition;
    }
}
