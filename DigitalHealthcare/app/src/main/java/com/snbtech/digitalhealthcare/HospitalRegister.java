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

public class HospitalRegister extends AppCompatActivity {
    EditText id,name,contact,address,email,password,cpassword;
    Button register;

    private ProgressDialog progress;
    final Context context=this;
    String receivedValue="";

    public static String uid,nm,mob,add,mail,pass,cpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_register);

        id=(EditText)findViewById(R.id.userIdD);
        name=(EditText)findViewById(R.id.userNameD);
        contact=(EditText)findViewById(R.id.userContactD);
        address=(EditText)findViewById(R.id.userAddressD);
        email=(EditText)findViewById(R.id.userEmailD);
        password=(EditText)findViewById(R.id.userPasswordD);
        cpassword=(EditText)findViewById(R.id.userCPasswordD);

        register=(Button)findViewById(R.id.addUserD);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                nm=name.getText().toString();
                mob=contact.getText().toString();
                add=address.getText().toString();
                mail=email.getText().toString();
                uid=id.getText().toString();
                pass=password.getText().toString();
                cpass=cpassword.getText().toString();



                if (nm.equals("")){
                    name.setError("Enter Name");
                    Snackbar.make(view, "Enter Name", Snackbar.LENGTH_LONG)
                            .show();
                }else if(mob.equals("") || mob.length()<10){
                    contact.setError("Check Mobile no");
                    Snackbar.make(view, "Check Mobile no", Snackbar.LENGTH_LONG)
                            .show();
                }else if (add.equals("")){
                    address.setError("Enter Address");
                    Snackbar.make(view, "Enter Address", Snackbar.LENGTH_LONG)
                            .show();
                }else if(mail.equals("") || !mail.matches("[a-zA-Z0-9.-_]+@[a-z]+\\.[a-z]+")){
                    email.setError("Check Email Id");
                    Snackbar.make(view, "Check Email Id", Snackbar.LENGTH_LONG)
                            .show();
                }
                else if(uid.equals("")) {
                    id.setError("Enter UID");
                    Snackbar.make(view, "Enter UID", Snackbar.LENGTH_LONG)
                            .show();
                }
                else if(pass.equals("") || pass.length()<4){
                    password.setError("Password Should not be less than 4 char");
                    Snackbar.make(view, "Password Should not be less than 4 char", Snackbar.LENGTH_LONG)
                            .show();
                }else if (!pass.equals(cpass)){
                    cpassword.setError("Enter correct password");
                    Snackbar.make(view, "Enter correct password", Snackbar.LENGTH_LONG)
                            .show();
                }else{



                    try {
                        progress=new ProgressDialog(context);
                        progress.setMessage("Wait...");
                        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progress.setIndeterminate(false);
                        progress.setProgress(0);
                        progress.setCancelable(false);
                        progress.show();
                        new addUser().execute();

                    }
                    catch (Exception ex)
                    {
                        Toast.makeText(context, "Error="+ex.toString(), Toast.LENGTH_SHORT).show();
                    }




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
            HttpPost post=new HttpPost(url.url+"/addHospital.php");
            //temp=params[0];

            //uid,nm,mob,add,mail,pass,cpass;
            List<NameValuePair> pairs=new ArrayList<NameValuePair>(1);
            pairs.add(new BasicNameValuePair("e1",nm));
            pairs.add(new BasicNameValuePair("e2",mob));
            pairs.add(new BasicNameValuePair("e3",add));
            pairs.add(new BasicNameValuePair("e4",mail));
            pairs.add(new BasicNameValuePair("e5",uid));
            pairs.add(new BasicNameValuePair("e6",pass));
            try
            {
                post.setEntity(new UrlEncodedFormEntity(pairs));
            }
            catch (Exception ex)
            {
                //e1=ex.toString();
                Toast.makeText(getApplicationContext(), "Error 1="+ex.toString(), Toast.LENGTH_SHORT).show();
            }
            //Perform HTTP Request
            try
            {
                ResponseHandler<String> responseHandler=new BasicResponseHandler();
                receivedValue =client.execute(post,responseHandler);
                //Toast.makeText(getApplicationContext(), receivedValue, Toast.LENGTH_SHORT).show();

            }
            catch (Exception ex)
            {
                // e2=ex.toString();
                //Toast.makeText(getApplicationContext(), "Error 2="+ex.toString(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(context, "Registered Successfully", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getApplicationContext(),Login.class);
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
