package com.example.begin.imagepageview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImagePageView imagePageView = (ImagePageView) findViewById(R.id.image_page_view);
        LinkedList<Object> linkedList = new LinkedList<>();
        linkedList.add(R.drawable.cert_01);
        linkedList.add(R.drawable.cert_02);
        linkedList.add(R.drawable.cert_03);
        linkedList.add(R.drawable.cert_04);
        linkedList.add(R.drawable.cert_05);
        imagePageView.setImages(linkedList,ImagePageView.RESID);

    }
}
