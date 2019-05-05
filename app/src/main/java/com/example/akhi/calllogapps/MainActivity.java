package com.example.akhi.calllogapps;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Date;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Cursor mCursor= managedQuery(CallLog.Calls.CONTENT_URI,null,null,null,null);
       int number=mCursor.getColumnIndex(CallLog.Calls.NUMBER);
       int date = mCursor.getColumnIndex(CallLog.Calls.DATE);
        int duration=mCursor.getColumnIndex(CallLog.Calls.DURATION);
       int type=mCursor.getColumnIndex(CallLog.Calls.TYPE);
       StringBuilder sb=new StringBuilder();
       while (mCursor.moveToNext()){
           String phonenumber=mCursor.getString(number);
           String callduration=mCursor.getString(duration);
           String calltype=mCursor.getString(type);
           String calldate=mCursor.getString(date);
           Date d=new Date(calldate);
           String callTypeStr="";
           switch (Integer.parseInt(calltype) ){
               case CallLog.Calls.OUTGOING_TYPE:
                   callTypeStr="Outgoing";
               break;
               case CallLog.Calls.INCOMING_TYPE:
                   callTypeStr="Incoming";
                   break;
               case CallLog.Calls.MISSED_TYPE:
                   callTypeStr="Missed";
                   break;

           }
           sb.append("Phone number"+ phonenumber);
           sb.append("Call duration" +callduration);
           sb.append("Call type" +callTypeStr);
           sb.append("Call date"+d);
           sb.append("--------------");
           sb.append(System.getProperty("line.separator"));
           }
         TextView callDetails = (TextView)findViewById(R.id.calllog);
        callDetails.setText(sb.toString());
    }


}
