package com.example.aky.spamdetector;


import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SMS extends Fragment {

    StringBuffer sb;
    TextView tv;
    ListView listsms;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_sm, container, false);
       tv=(TextView)v.findViewById(R.id.tv);
       listsms=(ListView)v.findViewById(R.id.listsms);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayList<String> arrayl = getArguments().getStringArrayList("etsms");
        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,arrayl);
        listsms.setAdapter(arrayAdapter);

        }

    }

