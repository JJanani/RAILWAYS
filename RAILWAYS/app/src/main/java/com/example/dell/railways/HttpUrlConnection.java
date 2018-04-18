package com.example.dell.railways;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class HttpUrlConnection extends AsyncTask<String,Void,String> {
    private ProgressDialog loading;
    Context context;
    AlertDialog.Builder alertDialogBuilder;
    AlertDialog alertDialog;


    String id;
    String name;
    String  hse_no;
    String contact;
    String comp_type;
    String desc;
    private ProgressDialog Loading;


    HttpUrlConnection (Context ctx) {
        context = ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        id = params[0];
        name = params[1];
        hse_no = params[2];
        contact = params[3];
        comp_type = params[4];
        desc = params[5];
        try {

            URL url = new URL(Config.DATA_INSERT_URL);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8")+"&";
            post_data+=URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&";
            post_data+=URLEncoder.encode("hse_no","UTF-8")+"="+URLEncoder.encode(hse_no,"UTF-8")+"&";
            post_data+=URLEncoder.encode("contact","UTF-8")+"="+URLEncoder.encode(contact,"UTF-8")+"&";
            post_data+=URLEncoder.encode("comp_type","UTF-8")+"="+URLEncoder.encode(comp_type,"UTF-8")+"&";
            post_data+=URLEncoder.encode("desc","UTF-8")+"="+URLEncoder.encode(desc,"UTF-8");

            bufferedWriter.write(post_data);

            bufferedWriter.flush();
            bufferedWriter.close();

            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String result="";
            String line="";
            while((line = bufferedReader.readLine())!= null) {
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("STATUS!!");
        Loading = ProgressDialog.show(context,"Uploading data...","Please wait ...",false,false);


    }

    @Override
    protected void onPostExecute(String result) {

        Loading.dismiss();
        alertDialog.setMessage("\n"+result+"\n\nComplaint type: "+comp_type+"\n\nDescription: "+ desc+"\n\nStatus: REGISTERED\n");
        alertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}