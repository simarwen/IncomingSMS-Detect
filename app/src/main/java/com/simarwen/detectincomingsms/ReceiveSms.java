package com.simarwen.detectincomingsms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class ReceiveSms extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Check if the intent action is for SMS received
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                // Retrieve the SMS message received
                Object[] pdus = (Object[]) bundle.get("pdus");
                if (pdus != null) {
                    for (Object pdu : pdus) {
                        SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
                        String sender = smsMessage.getDisplayOriginatingAddress();
                        String messageBody = smsMessage.getMessageBody();

                        // Show a toast with the sender and message
                        Toast.makeText(context, "SMS from: " + sender + "\nMessage: " + messageBody, Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }
}