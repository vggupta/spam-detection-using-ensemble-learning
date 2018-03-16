package com.example.aky.spamdetector;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.Telephony;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SPAMACTIVITY extends AppCompatActivity {

    TabLayout tb;
    ViewPager vp;
    StringBuffer stringBuffer;
    TextView textView;
    LinearLayout ll;
    private static final String PREF_ACCOUNT_NAME = "accountName";
    TabLayout.Tab tab1,tab2,tab3;
    int i;
    private static final int PERMISSIONS_REQUEST_PHONE_SMS = 100;
    ArrayList<String> arraysms;
    ArrayList<String> arraylist;
    ArrayList<String> lstSms;
    private int[] imageResId = {
            R.drawable.ic_mail_black_24dp,
            R.drawable.ic_message_black_24dp };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spamactivity);
        vp= (ViewPager) findViewById(R.id.vp);
        ll=(LinearLayout)findViewById(R.id.ll);
        tb=(TabLayout)findViewById(R.id.tablayout);
        textView=(TextView) findViewById(R.id.text);
        Myviewpager myviewpager = new Myviewpager(getSupportFragmentManager());
        vp.setAdapter(myviewpager);
        tb.setupWithViewPager(vp);
        ArrayList<String> arraylist;
        for( i = 0; i <imageResId.length; i++)

        {
            tb.getTabAt(i).setIcon(imageResId[i]);
        }

    }
    class Myviewpager extends FragmentStatePagerAdapter {
        ArrayList<Fragment> lt;

        public Myviewpager(FragmentManager fm) {
            super(fm);
            lt=new ArrayList<>();
            Bundle bundle = getIntent().getExtras();
             arraylist = bundle.getStringArrayList("mylist");
            ArrayList<String> arraymsg=bundle.getStringArrayList("mylistmsg");
            FragmentTransaction ft=fm.beginTransaction();
            Bundle bundle1 = new Bundle();
            bundle1.putStringArrayList("edttext", arraylist);
            bundle1.putStringArrayList("etlistmsg",arraymsg);
            Mail mail=new Mail();
            mail.setArguments(bundle1);
            lt.add(mail);
            //lt.add(new SMS());
            ft.commit();
            ft=fm.beginTransaction();
            lstSms = new ArrayList<String>();
            Cursor c = null; // Default sort order
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                c = getContentResolver().query(Telephony.Sms.Inbox.CONTENT_URI, // Official CONTENT_URI from docs
                        new String[] { Telephony.Sms.Inbox.BODY }, // Select body text
                        null,
                        null,
                        Telephony.Sms.Inbox.DEFAULT_SORT_ORDER);
            }

            int totalSMS = c.getCount();

            if (c.moveToFirst()) {
                for (int i = 0; i < 10; i++) {
                    lstSms.add(c.getString(c.getColumnIndexOrThrow("body")));
                    c.moveToNext();
                }
            } else {
                Toast.makeText(SPAMACTIVITY.this,"NO MESSAGES FOUND",Toast.LENGTH_SHORT).show();
            }
            c.close();
            Bundle bundle2=new Bundle();
            bundle2.putStringArrayList("etsms",lstSms);
            SMS sms_frag =new SMS();
            sms_frag.setArguments(bundle2);
            lt.add(sms_frag);
            ft.commit();
            tb.setupWithViewPager(vp);

        }

        @Override
        public Fragment getItem(int position) {
            return lt.get(position);
        }

        @Override
        public int getCount() {

            return lt.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Drawable image = SPAMACTIVITY.this.getResources().getDrawable(imageResId[position]);
            image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
            // Replace blank spaces with image icon
            SpannableString sb;
            sb = new SpannableString(" ");
            ImageSpan imageSpan = new ImageSpan(image);
            sb.setSpan(imageSpan, 0, 0, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return sb;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi=getMenuInflater();
        mi.inflate(R.menu.mymenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.signout)
        {    SharedPreferences sp = getSharedPreferences("mysp",MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(PREF_ACCOUNT_NAME,null);
            editor.apply();
            editor.commit();
            startActivity(new Intent(SPAMACTIVITY.this,LOGINPAGE.class));
        }
        else if(item.getItemId()==R.id.about)
        {
            AlertDialog.Builder ad=new AlertDialog.Builder(SPAMACTIVITY.this);
            ad.setIcon(R.drawable.ic_001_spam);
            ad.setTitle(getString(R.string.text5));
            ad.setMessage(getString(R.string.text6));
            ad.show();
            Toast.makeText(SPAMACTIVITY.this,"hi guys",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.finish();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();

    }
        }
