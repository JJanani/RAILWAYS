package com.example.dell.railways;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;



public class HomePageContent extends Fragment {

    FloatingActionButton fab1, fab ,fab2 , fab3;
    private boolean fabMenuOpen = false;
    private boolean fabMenuOpen1 = false;
    private boolean fabMenuOpen2 = false;
    private boolean fabMenuOpen3 = false;
    Drawable drawable, drawable1;
    TextView editText1, editText2, editText3;
    RelativeLayout relativeLayout , relativeLayout1, relativeLayout2;
    Button button, button1, button2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.home_page_content, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        fab = (FloatingActionButton) view.findViewById(R.id.fab);

        drawable = getResources().getDrawable(R.drawable.error);
        drawable1 = getResources().getDrawable(R.drawable.plus);

        button = (Button) view.findViewById(R.id.button);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.layout);

        button1 = (Button) view.findViewById(R.id.button5);
        relativeLayout1 = (RelativeLayout) view.findViewById(R.id.layout1);

        button2 = (Button) view.findViewById(R.id.button4);
        relativeLayout2 = (RelativeLayout) view.findViewById(R.id.layout2);



        editText1 = (TextView) view.findViewById(R.id.textView11);
        editText2 = (TextView) view.findViewById(R.id.textView16);
        editText3 = (TextView) view.findViewById(R.id.textView14);


        fab1 = (FloatingActionButton) view.findViewById(R.id.fab_1);
        fab2 = (FloatingActionButton) view.findViewById(R.id.fab_2);
        fab3 = (FloatingActionButton) view.findViewById(R.id.fab_3);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fabMenuOpen == false){

                    fabMenuOpen1 =true;

                    fab.setImageDrawable(drawable);
                    fab1.setVisibility(view.VISIBLE);
                    fab2.setVisibility(view.VISIBLE);
                    fab3.setVisibility(view.VISIBLE);
                    editText1.setVisibility(view.VISIBLE);
                    editText2.setVisibility(view.VISIBLE);
                    editText3.setVisibility(view.VISIBLE);

                    fabMenuOpen = true;
                }

                else if(fabMenuOpen == true){
                    fab.setImageDrawable(drawable1);

                    fab1.setVisibility(view.INVISIBLE);
                    fab2.setVisibility(view.INVISIBLE);
                    fab3.setVisibility(view.INVISIBLE);
                    editText1.setVisibility(view.INVISIBLE);
                    editText2.setVisibility(view.INVISIBLE);
                    editText3.setVisibility(view.INVISIBLE);

                    fabMenuOpen = false;
                }
            }
        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(fabMenuOpen2 == true){
                    relativeLayout1.setVisibility(view.INVISIBLE);
                    button1.setVisibility(view.INVISIBLE);
                }
                if(fabMenuOpen3 == true){
                    relativeLayout2.setVisibility(view.INVISIBLE);
                    button2.setVisibility(view.INVISIBLE);


                }

                fabMenuOpen1 = true;

                    relativeLayout.setVisibility(view.VISIBLE);
                    button.setVisibility(view.VISIBLE);

                    fab.setImageDrawable(drawable1);
                    fab1.setVisibility(view.INVISIBLE);
                    fab2.setVisibility(view.INVISIBLE);
                    fab3.setVisibility(view.INVISIBLE);
                    editText1.setVisibility(view.INVISIBLE);
                    editText2.setVisibility(view.INVISIBLE);
                    editText3.setVisibility(view.INVISIBLE);
                    fabMenuOpen = false;

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                relativeLayout.setVisibility(view.INVISIBLE);
                button.setVisibility(view.INVISIBLE);
                fabMenuOpen1 = false;

            }
        });


        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(fabMenuOpen1 == true){
                    relativeLayout.setVisibility(view.INVISIBLE);
                    button.setVisibility(view.INVISIBLE);
                }
                if(fabMenuOpen3 == true){
                    relativeLayout2.setVisibility(view.INVISIBLE);
                    button2.setVisibility(view.INVISIBLE);


                }

                fabMenuOpen2 = true;



                relativeLayout1.setVisibility(view.VISIBLE);
                button1.setVisibility(view.VISIBLE);

                fab.setImageDrawable(drawable1);
                fab1.setVisibility(view.INVISIBLE);
                fab2.setVisibility(view.INVISIBLE);
                fab3.setVisibility(view.INVISIBLE);
                editText1.setVisibility(view.INVISIBLE);
                editText2.setVisibility(view.INVISIBLE);
                editText3.setVisibility(view.INVISIBLE);
                fabMenuOpen = false;

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                relativeLayout1.setVisibility(view.INVISIBLE);
                button1.setVisibility(view.INVISIBLE);

                fabMenuOpen2 = false;
            }
        });

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(fabMenuOpen2 == true){
                    relativeLayout1.setVisibility(view.INVISIBLE);
                    button1.setVisibility(view.INVISIBLE);
                }
                if(fabMenuOpen1 == true){
                    relativeLayout.setVisibility(view.INVISIBLE);
                    button.setVisibility(view.INVISIBLE);

                }

                fabMenuOpen3 = true;

                relativeLayout2.setVisibility(view.VISIBLE);
                button2.setVisibility(view.VISIBLE);

                fab.setImageDrawable(drawable1);
                fab1.setVisibility(view.INVISIBLE);
                fab2.setVisibility(view.INVISIBLE);
                fab3.setVisibility(view.INVISIBLE);
                editText1.setVisibility(view.INVISIBLE);
                editText2.setVisibility(view.INVISIBLE);
                editText3.setVisibility(view.INVISIBLE);
                fabMenuOpen = false;

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                relativeLayout2.setVisibility(view.INVISIBLE);
                button2.setVisibility(view.INVISIBLE);
                fabMenuOpen3 = false;
            }
        });

        getActivity().setTitle("RWF COMPLAINT REGISTER");
    }
}

