package com.snbtech.digitalhealthcare;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class AddRate extends AppCompatActivity {

    EditText category,subcategory,name,arate,orate;
    Button add;

    private ProgressDialog progress;
    final Context context=this;
    String receivedValue="";

    public static String scat,ssubcat,anm,sarate,sorate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rate);

        category=(EditText)findViewById(R.id.category);
        subcategory=(EditText)findViewById(R.id.subcategory);
        name=(EditText)findViewById(R.id.name);
        arate=(EditText)findViewById(R.id.arate);
        orate=(EditText)findViewById(R.id.orate);

        add=(Button)findViewById(R.id.addUserD);

        category.setText(Category.lcategory);
        category.setEnabled(false);

        subcategory.setText(SubCategoryList.subcat);
        subcategory.setEnabled(false);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scat=category.getText().toString();
                ssubcat=subcategory.getText().toString();

                anm=name.getText().toString();
                sarate=arate.getText().toString();
                sorate=orate.getText().toString();


                if (anm.equals("")){
                    name.setError("Enter Name");
                    Snackbar.make(view, "Enter Name", Snackbar.LENGTH_LONG).show();
                }else if (sarate.equals("")){
                    arate.setError("Enter Actual Rate");
                    Snackbar.make(view, "Enter Actual Rate", Snackbar.LENGTH_LONG)
                            .show();
                }else if(sorate.equals("")){
                    orate.setError("Enter Offer Rate");
                    Snackbar.make(view, "Enter Offer Rate", Snackbar.LENGTH_LONG).show();
                }else{

                    progress = new ProgressDialog(context);
                    progress.setMessage("Wait...");
                    progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progress.setIndeterminate(false);
                    progress.setProgress(0);
                    progress.setCancelable(false);
                    progress.show();

                    new addUser().execute();
                }


            }
        });
    }


    private class addUser extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... params)
        {
            HttpClient client=new DefaultHttpClient();
            HttpPost post=new HttpPost(url.url+"/addRate.php");

            List<NameValuePair> pairs=new ArrayList<NameValuePair>(1);
            pairs.add(new BasicNameValuePair("e1",scat));
            pairs.add(new BasicNameValuePair("e2",ssubcat));
            pairs.add(new BasicNameValuePair("e3",anm));
            pairs.add(new BasicNameValuePair("e4",sarate));
            pairs.add(new BasicNameValuePair("e5",sorate));


            try
            {
                post.setEntity(new UrlEncodedFormEntity(pairs));
            }
            catch (Exception ex)
            {
                Toast.makeText(getApplicationContext(), "Error 1="+ex.toString(), Toast.LENGTH_SHORT).show();
            }
            //Perform HTTP Request
            try
            {
                ResponseHandler<String> responseHandler=new BasicResponseHandler();
                receivedValue =client.execute(post,responseHandler);

            }
            catch (Exception ex)
            {
            }
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            progress.dismiss();
            Toast.makeText(context, receivedValue, Toast.LENGTH_SHORT).show();
            if(receivedValue.contains("exists"))
            {
                Toast.makeText(context, "UserId Already Exists", Toast.LENGTH_SHORT).show();
            }
            else if(receivedValue.contains("success"))
            {
                Toast.makeText(context, "Service Added Successfully", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getApplicationContext(),Hospital.class);
                startActivity(i);
                finish();
            }
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }
    }


}
