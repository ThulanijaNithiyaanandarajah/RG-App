package com.example.user.rgapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by USER on 11/30/2015.
 */
public class Categories extends AppCompatActivity{

    private ExpandableListView expandableListView;
    CategoryListAdapter categoryListAdapter;
    private List<String>categoryHeaderInfo;
    private HashMap<String, String> categoryIcon;
    private HashMap<String, List<SubCategory>> categoryChildInfo;
    private ProgressBar pb;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        pb = (ProgressBar)findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);

        categoryHeaderInfo = new ArrayList<>();
        categoryIcon = new HashMap<>();
        categoryChildInfo = new HashMap<>();
        new CategoryAsyncTask().execute("http://www.dev.retailgenius.lk/cms/xml/header.xml");

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);

        categoryListAdapter = new CategoryListAdapter(this, categoryHeaderInfo, categoryChildInfo);
        expandableListView.setAdapter(categoryListAdapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Intent intent = new Intent(getApplicationContext(), List_View.class);
                startActivity(intent);
                return false;
            }
        });

        // Listview Group expanded listener
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(), categoryHeaderInfo.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }




    private class CategoryAsyncTask extends AsyncTask<String, Void, Boolean>{
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(Categories.this);
            dialog.setMessage("Loading, please wait");
            dialog.setTitle("Connecting server");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected Boolean doInBackground(String... params) {
            XMLParser parser = new XMLParser();

            try {
                String xml = parser.getXmlFromUrl(params[0]); // getting XML
                Document doc = parser.getDomElement(xml); // getting DOM element

                NodeList nl = doc.getElementsByTagName("main");
                for (int i = 0; i < nl.getLength(); i++) {
                    Element e = (Element) nl.item(i);
                    String header_main_title = Html.fromHtml(e.getAttribute("header_main_title")).toString();
                    String image_url = e.getAttribute("image_url");
                    categoryHeaderInfo.add(header_main_title);
                    categoryIcon.put(header_main_title, image_url);

                    List<SubCategory> subList = new ArrayList<>();
                    NodeList child = e.getChildNodes();
                    for (int j=0; j<child.getLength();j++){

                        Element element = (Element) child.item(j);
                        String sub_name = Html.fromHtml(element.getAttribute("header_sub_title")).toString();
                        String sub_link = Html.fromHtml(element.getAttribute("header_sub_title_link")).toString();
                        SubCategory subCategory = new SubCategory(sub_name, sub_link);
                        subList.add(subCategory);
                    }
                    categoryChildInfo.put(header_main_title, subList);
                }
            }catch (Exception e1){
                e1.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {

            dialog.cancel();

            categoryListAdapter.notifyDataSetChanged();
            if(result == false)
                Toast.makeText(getApplicationContext(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();
        }

    }
}
