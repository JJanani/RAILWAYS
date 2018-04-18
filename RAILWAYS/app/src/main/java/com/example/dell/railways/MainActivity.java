package com.example.dell.railways;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText userid , password;
    private Button Loginbtn;
    private ImageButton InfoBtn;
    private ProgressDialog Loading;
    AlertDialog.Builder alertDialogBuilder;
    AlertDialog alertDialog;

    //These string name, address, id are made public.
    //So it can be accessed in other java classes
    public static String name = "";
    public static String address = "";
    public static String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userid = (EditText) findViewById(R.id.edittext1);
        password = (EditText) findViewById(R.id.edittext2);
        Loginbtn = (Button) findViewById(R.id.button);
        InfoBtn = (ImageButton) findViewById(R.id.imageButton);

        Loginbtn.setOnClickListener(this);
        InfoBtn.setOnClickListener(this);
    }

    //Checks for wifi or mobile data connection when user tries to login
    public static boolean CheckInternet(Context context)
    {
        ConnectivityManager connec = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo wifi = connec.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        android.net.NetworkInfo mobile = connec.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        return wifi.isConnected() || mobile.isConnected();
    }

    //When Login Button is clicked
    private void getdata() {

        String id = userid.getText().toString().trim();
        // checks if user has entered employee Id and password
        if (id.equals("")) {
            Toast.makeText(this,"Please Enter Your Id",Toast.LENGTH_LONG).show();
            return;
        }

        //checks for active internet connection
        Boolean ch = CheckInternet(this);

        if(ch == true){
            Loading = ProgressDialog.show(this, "Authenticating ...", "Please wait ...", false, false);
            //fetches user ID and password from database
            String url = Config.DATA_LOGIN_URL + userid.getText().toString().trim();
            StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Loading.dismiss();
                    //Passes user ID and password to showJSON() for validation
                    showJSON(response);
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //Displays DataBase Connectivity error
                            Toast.makeText(MainActivity.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                        }
                    });

            //Accessing the RequestQueue and Transmitting Data Using Volley
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
        else{
            //Displays Toast on Network Error
            Toast.makeText(this,"Please Check Your Internet Connection And Try Again.",Toast.LENGTH_LONG).show();
        }
    }

    private void showJSON(String response){
        //Creates an Intent to move to HomePage java class on successful login
        Intent intent =  new Intent(this,HomePage.class);
        Bundle extras = new Bundle();

        //Using JSON to fetch data from database
        try{
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            JSONObject Data = result.getJSONObject(0);
            name = Data.getString(Config.KEY_NAME);
            address = Data.getString(Config.KEY_HOUSENUMBER);
            id = Data.getString(Config.KEY_ID);

        }catch(JSONException e){
            e.printStackTrace();
        }

        //store fetched data from database into local variables
        String txt = name.toString();
        String txt1 = address.toString();
        String txt2 = id.toString();

        //DataBase returns null if entered employee Id or Password do not match with database details.
        //And it is considered as incorrect Password  or Id.
        if(txt.equals("null") ){
            alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage(getString(R.string.Incorrect_password_id));
            alertDialogBuilder.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {

                        }
                    });

            alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

        else{
            //On successful validation, it opens homepage java class.
            Toast.makeText(this,txt+" Validation purpose "+txt1+ " "+txt2,Toast.LENGTH_LONG).show();
            intent.putExtras(extras);
            startActivity(intent);
        }
    }



    public void onClick(View v){
        //When login button is clicked
        if(v.getId() == R.id.button){
            getdata();
        }
        //When question mark button is clicked
        else if(v.getId() == R.id.imageButton){
            //Opens up a alertDialog box window to show password hint
            alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage(getString(R.string.password_hint));
            alertDialogBuilder.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {

                        }
                    });

            alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }
}
