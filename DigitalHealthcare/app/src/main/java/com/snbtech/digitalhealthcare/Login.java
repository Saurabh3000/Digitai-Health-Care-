package com.snbtech.digitalhealthcare;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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


public class Login extends AppCompatActivity {
    EditText uname,pwd;
    Button login,signup;

    private RadioGroup radioGroup;
    private RadioButton radioButton;

    public static String unameString,pwdString,option;

    SQLiteDatabase db;
    private ProgressDialog progress;
    final Context context=this;
    public static String receivedValue="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        uname=(EditText)findViewById(R.id.Username);
        pwd=(EditText)findViewById(R.id.password);
        login=(Button) findViewById(R.id.login);
        signup=(Button)findViewById(R.id.signup);
        radioGroup = (RadioGroup) findViewById(R.id.rg);


        db=openOrCreateDatabase("Health", Context.MODE_PRIVATE,null);
        db.execSQL("create table if not exists register (Username varchar(255))");


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                unameString=uname.getText().toString();
                pwdString=pwd.getText().toString();

                int selectedId = radioGroup.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);
                option = radioButton.getText().toString();



                if (unameString.equals("")){
                    uname.setError("Enter Username");
                }else if(pwdString.equals("")){
                    pwd.setError("Enter Password");
                }
                else if (radioButton.getText().toString().equals("")) {
                    Toast.makeText(context, "Select Option", Toast.LENGTH_SHORT).show();
                }
                else{

                    progress=new ProgressDialog(context);
                    progress.setMessage("Wait...");
                    progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progress.setIndeterminate(false);
                    progress.setProgress(0);
                    progress.setCancelable(false);
                    progress.show();
                    new login().execute();
                }


            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i=new Intent(getApplicationContext(),UserRegister.class);
                startActivity(i);
            }
        });
    }


    private class login extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... params)
        {

            HttpClient client=new DefaultHttpClient();
            HttpPost post=new HttpPost(url.url+"/login.php");
            //temp=params[0];
            List<NameValuePair> pairs=new ArrayList<NameValuePair>(1);
            pairs.add(new BasicNameValuePair("e1",unameString));
            pairs.add(new BasicNameValuePair("e2",pwdString));
            pairs.add(new BasicNameValuePair("e3",option));
            try
            {
                post.setEntity(new UrlEncodedFormEntity(pairs));
            }
            catch (Exception ex)
            {
                // e1=ex.toString();
                //  Toast.makeText(getApplicationContext(), "Error 1="+ex.toString(), Toast.LENGTH_SHORT).show();
            }
            //Perform HTTP Request
            try
            {
                ResponseHandler<String> responseHandler=new BasicResponseHandler();
                receivedValue =client.execute(post,responseHandler);
                // Toast.makeText(getApplicationContext(), receivedValue, Toast.LENGTH_SHORT).show();
                //name.setText(receivedValue);
            }
            catch (Exception ex)
            {
                // e2=ex.toString();
                // Toast.makeText(getApplicationContext(), "Error 2="+ex.toString(), Toast.LENGTH_SHORT).show();
            }

            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            progress.dismiss();


            Toast.makeText(context, receivedValue, Toast.LENGTH_SHORT).show();

            if (receivedValue.contains("user")) {
                Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                //Welcome.uname = sname;
                db.execSQL("delete from register");
                db.execSQL("insert into register values('"+unameString+"')");
                Intent i = new Intent(getApplicationContext(), User.class);
                startActivity(i);
                finish();
            } else if (receivedValue.contains("hospital")) {
                Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                // Welcome.uname = sname;

                db.execSQL("delete from register");
                db.execSQL("insert into register values('"+unameString+"')");
                Intent i = new Intent(getApplicationContext(), Hospital.class);
                startActivity(i);
                finish();

            }
            else if (receivedValue.contains("admin")) {
                Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                // Welcome.uname = sname;

                db.execSQL("delete from register");
                db.execSQL("insert into register values('"+unameString+"')");
                Intent i = new Intent(getApplicationContext(), Admin.class);
                startActivity(i);
                finish();

            }
            else {
                Toast.makeText(context, "Invalid Authentication", Toast.LENGTH_SHORT).show();
            }


            // Toast.makeText(context, "E1="+e1, Toast.LENGTH_SHORT).show();
            //  Toast.makeText(context, "E2="+e2, Toast.LENGTH_SHORT).show();
            if(receivedValue.contains("wro"))
            {
                Toast.makeText(context, "Invalid Authentication", Toast.LENGTH_SHORT).show();
            }
            else if(receivedValue.contains("success"))
            {
                Toast.makeText(context, "Login Successfully", Toast.LENGTH_SHORT).show();
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


