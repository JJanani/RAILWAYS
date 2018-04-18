package com.example.dell.railways;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.dell.railways.MainActivity.id;

public class ViewComplaints extends Fragment implements  View.OnClickListener {

    TextView textViewResult;
    private ProgressDialog loading;
    ListView listView;
    AlertDialog.Builder alertDialogBuilder;
    AlertDialog alertDialog;
    ImageButton imageButton;
    List<GetDataAdapter> GetDataAdapter1;

    RecyclerView recyclerView;

    RecyclerView.LayoutManager recyclerViewlayoutManager;

    RecyclerView.Adapter recyclerViewadapter;
    JsonArrayRequest jsonArrayRequest ;
    RequestQueue requestQueue ;

     @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments

        return inflater.inflate(R.layout.view_complaints, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        String url = Config.DATA_FETCH_URL + id;


        textViewResult =(TextView) view.findViewById(R.id.textView);
        imageButton = (ImageButton) view.findViewById(R.id.imageButton);
        imageButton.setOnClickListener(this);
        GetDataAdapter1 = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView1);
        loading = ProgressDialog.show(this.getContext(), "Please wait...", "Fetching...", false, false);


        //   button = (Button)findViewById(R.id.button) ;

        recyclerView.setHasFixedSize(true);

        recyclerViewlayoutManager = new LinearLayoutManager(this.getActivity());




        recyclerView.setLayoutManager(recyclerViewlayoutManager);
        String URL = Config.DATA_FETCH_COMP_URL+id.toString().trim();

        jsonArrayRequest = new JsonArrayRequest(URL,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        loading.dismiss();
                        if(response.length()>0)
                            showJSON(response);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                    }
                });

        requestQueue = Volley.newRequestQueue(this.getContext());

        requestQueue.add(jsonArrayRequest);

        getActivity().setTitle("RWF COMPLAINT REGISTER");
    }


    private void showJSON(JSONArray array) {

        for(int i = 0; i<array.length(); i++) {

            GetDataAdapter GetDataAdapter2 = new GetDataAdapter();

            JSONObject json = null;
            try {
                json = array.getJSONObject(i);

                GetDataAdapter2.setId(json.getInt("Complaint_no"));

                GetDataAdapter2.setCDate(json.getString("date_of_complaint"));

                GetDataAdapter2.setCType(json.getString("Complaint_type"));

                GetDataAdapter2.setCDescription(json.getString("Complaint_description"));

                GetDataAdapter2.setCStatus(json.getString("Status"));

            } catch (JSONException e) {

                e.printStackTrace();
            }
            GetDataAdapter1.add(GetDataAdapter2);
        }

        recyclerViewadapter = new RecyclerViewAdapter(GetDataAdapter1, this.getContext());

        recyclerView.setAdapter(recyclerViewadapter);

    }

    @Override
    public void onClick(View v) {
        recyclerView.smoothScrollToPosition(0);


    }
}








