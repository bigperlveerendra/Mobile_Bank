package com.example.mobilebankingapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

class GeocoderHandler extends Handler {
   
   public static  String locationAddress;;
   @Override
   public void handleMessage(Message message) {
      
       switch (message.what) {
           case 1:
               Bundle bundle = message.getData();
               locationAddress = bundle.getString("address");
               break;
           default:
               locationAddress = null;
       }
      
   }
}