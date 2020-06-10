package com.sentaroh.android.SMBSync2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.sentaroh.android.Utilities.Dialog.MessageDialogFragment;

public class ActivityIntentHandler extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(new GlobalParameters().setNewLocale(base, false));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_transrucent);

        Intent received_intent=getIntent();
        if (received_intent.getAction()!=null && !received_intent.getAction().equals("")) {
            Intent in=new Intent(received_intent.getAction());
            in.setClass(ActivityIntentHandler.this, SyncService.class);
            if (received_intent.getExtras() != null) in.putExtras(received_intent.getExtras());
            final FragmentManager fm=getSupportFragmentManager();
            try {
                startService(in);
            }catch(Exception e){
                MessageDialogFragment mdf=MessageDialogFragment.newInstance(false, "ActivityIntentHandler start service error", e.getMessage(), null);
                mdf.showDialog(fm, mdf, null);
            }
        }

        finish();
    }

}
