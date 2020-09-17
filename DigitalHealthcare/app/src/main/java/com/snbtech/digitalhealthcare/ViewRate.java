package com.snbtech.digitalhealthcare;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.ExceptionUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewRate extends AppCompatActivity {

    public static String[] scategory,ssubcategory,sname,sarate,sorate;
    public static int pos=0;
    ListView lv;
    Button bn;
    Spinner cat,subcat;


    int cnt=0;
    public String a;

    String myJSON;
    JSONArray peoples = null;
    private static final String TAG_RESULTS="result";
    ArrayList<HashMap<String, String>> personList;
    private ProgressDialog progress;
    final Context context=this;
    public static String receivedValue="";
    public static String main,sub;
    public int subcnt=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rate);




        lv=(ListView)findViewById(R.id.lv);
        bn=(Button) findViewById(R.id.bn);
        cat=(Spinner) findViewById(R.id.sp1);
        subcat=(Spinner) findViewById(R.id.sp2);

        List<String> categories=new ArrayList<String>();
        categories.add("Heart");
        categories.add("Brain");
        categories.add("Eye");
        categories.add("Hair");
        categories.add("Pentist");
        categories.add("Cancer");
        categories.add("Handicap");
        categories.add("Eair");
        categories.add("Bones");
        categories.add("Stomach");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cat.setAdapter(dataAdapter);

        main=cat.getSelectedItem().toString();

        cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                subcnt=position;
                main=adapterView.getItemAtPosition(position).toString();
                subList();

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        subcat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sub=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getData();
            }
        });



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    pos = position;
                    String selectedItem= sarate[+position];
                    if(selectedItem.equals(""))
                    {

                    }
                    else
                    {
//                        Intent i=new Intent(getApplicationContext(),ServiceDetails.class);
//                        startActivity(i);
                    }

                }
                catch (Exception ex)
                {

                }
            }
        });



    }

    public void subList()
    {

        List<String> civil=new ArrayList<String>();

        if(subcnt==0)
        {
            civil.add("Arrhythmia_Treatment");
            civil.add("Aneurysm_Repair");
            civil.add("Ventricular_Assist_Devices");
            civil.add("Heart_Transplant");
        }
        else if(subcnt==1)
        {
            civil.add("Biopsy");
            civil.add("Craniotomy");
            civil.add("MRI");
            civil.add("Neuroendoscopy");
            civil.add("Endonasal_Endoscopy");

        }else if(subcnt==2)
        {
            civil.add("Lasik");
            civil.add("Prk");
            civil.add("Laser");
            civil.add("Rle");
            civil.add("Prelex");


        }else if(subcnt==3)
        {
            civil.add("Strip_Harresting");
            civil.add("Follicular_Unit_Extraction");
            civil.add("Follicular_Unit_Transplant");
            civil.add("Hair_Plantation");
        }else if(subcnt==4)
        {
            civil.add("Endodontist");
            civil.add("Prosthodontist");
            civil.add("Oral_Maxillofacial");

        }else if(subcnt==5) {

            civil.add("Blood");
            civil.add("Skin");
            civil.add("Mouth");
            civil.add("Lungs");


        }else if(subcnt==6) {

            civil.add("Cognitive");
            civil.add("Hearing_Loss");
            civil.add("Vision_Loss");
            civil.add("Invisible_Disability");

        }else if(subcnt==7) {
            civil.add("Stapendectomy");
            civil.add("Tympanoplasty");
            civil.add("Myringotomy");

        }else if(subcnt==8) {
            civil.add("Crack");
            civil.add("Gap");

        }else if(subcnt==9) {
            civil.add("Roux_en_Y_Gastric_Bypass");
            civil.add("Sleeve_gastrectomy");
            civil.add("Duodenal_Swith_with_Biliopancreatic_Divesion");


        }



        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, civil);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subcat.setAdapter(dataAdapter);

    }

    public void getData(){
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost = new HttpPost(url.url+"/searchRate.php?m="+main+"&s="+sub);

                // Depends on your web service
                httppost.setHeader("Content-type", "application/json");

                InputStream inputStream = null;
                String result = null;
                try {
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();

                    inputStream = entity.getContent();
                    // json is UTF-8 by default
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                }
                catch (Exception e) {
                    // Oops
                }
                finally {
                    try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result){
                myJSON=result;
                 Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();
    }
    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);
            for(int i=0;i<peoples.length();i++){
                cnt++;
            }
            scategory=new String[cnt];
            ssubcategory=new String[cnt];
            sname=new String[cnt];
            sarate=new String[cnt];
            sorate=new String[cnt];


            for(int i=0;i<peoples.length();i++){
                cnt++;
                JSONObject c = peoples.getJSONObject(i);
                scategory[i]=( c.getString("v1"));
                ssubcategory[i]=( c.getString("v2"));
                sname[i]=( c.getString("v3"));
                sarate[i]=("Actaul Price : "+ c.getString("v4"));
                sorate[i]=( "Offer Price : "+c.getString("v5"));
            }
            Search_Adapter adapter=new Search_Adapter(this, sname,ssubcategory,sarate,sorate);
            lv.setAdapter(adapter);
        } catch (Exception  e) {
            Toast.makeText(context, ""+e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
