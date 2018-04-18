package com.example.dell.railways;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.dell.railways.MainActivity.address;
import static com.example.dell.railways.MainActivity.id;
import static com.example.dell.railways.MainActivity.name;

public class RegisterComplaint extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    TextView textView;
    TextView textView1;
    TextView textView2;
    EditText editText1;
    EditText editText;
    Spinner spinner;
    String item;
    Integer pos;
    private Button button;
    HttpUrlConnection backgroundWorker;

    AlertDialog.Builder alertDialogBuilder;
    AlertDialog alertDialog;

    ArrayAdapter<String> dataAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.complaint_register, container, false);
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        textView = (TextView) view.findViewById(R.id.textView2);
        textView.setText(" NAME : " + name);
        textView1 = (TextView) view.findViewById(R.id.textView3);
        textView1.setText(" QUARTER NO. : " + address);
        textView2 = (TextView) view.findViewById(R.id.textView4);
        textView2.setText(" EMPLOYEE ID: " + id);
        editText1 = (EditText) view.findViewById(R.id.editText2);
        editText = (EditText) view.findViewById(R.id.editText);
        spinner = (Spinner) view.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        button = (Button) view.findViewById(R.id.button);

        button.setOnClickListener(this);


        List<String> categories = new ArrayList<String>();
        categories.add("SELECT COMPLAINT TYPE ");
        categories.add("CIVIL");
        categories.add("SANITARY");
        categories.add("ELECTRICAL");
        categories.add("S & T");

        dataAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

       getActivity().setTitle("RWF COMPLAINT REGISTER");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        item = parent.getItemAtPosition(position).toString();
        pos = position;

    }

    private void getData(){

        String contact = editText1.getText().toString();
        String description = editText.getText().toString();

        backgroundWorker = new HttpUrlConnection(this.getActivity());
        backgroundWorker.execute(id , name, address,contact, item,description);
        editText.setText("");
        editText1.setText("");
        spinner.setAdapter(dataAdapter);


    }

    @Override
    public void onClick(View v) {
        int res = 0;
        String contact = editText1.getText().toString();
        String description = editText.getText().toString();
        if (contact.isEmpty() || description.isEmpty() || pos == 0) {
            res =1;
            Toast.makeText(this.getActivity(), "Please fill all the details !!", Toast.LENGTH_LONG).show();
        }else{
        try {
            Long num = Long.parseLong(contact);
            if(contact.length()<10 || contact.length()>11 || !contact.matches("^[789]\\d{9}$")) {
                res =1;
                Toast.makeText(this.getActivity(), "Enter a valid contact number !!", Toast.LENGTH_LONG).show();
            }
        } catch (NumberFormatException e) {
            res =1;
            Toast.makeText(this.getActivity(), "Enter a valid contact number!!", Toast.LENGTH_LONG).show();

        }}
        if(res==0) {
            getData();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}

