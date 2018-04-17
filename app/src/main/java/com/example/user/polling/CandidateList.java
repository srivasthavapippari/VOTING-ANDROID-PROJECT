package com.example.user.polling;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by user on 13/4/18.
 */

public class CandidateList extends AppCompatActivity {
    private ListView candidateList;//create a new instance variable ListView called candidaeList

    private MyDBManager db;//instance variable for the database.

    private String db_candidate;//a new instance variable called db_candidate to insert into the database.

    //Created instance variables for Strings bundled over from the Questions2 class.
    private String db_married;
    private String db_doesUserHaveChildren;
    private String db_area;
    private String db_incomeEarned;
    private String db_age;
    private String db_politicalParty;
    private String db_vote;
    private String db_gender;

    //Created a String array which contains all the names of the candidates for Galway West.
    private String[] Candidates = {"Narendhra Modi", "Rahul Gandhi", "Mayawati", "Chandrashekhar Rao"
            , "Chandrababu Naidu", "Karunanidhi", "Mamatha Baneerja", "Pawan Kalyan",
            "Sitaram Yachury", "Jagan Mohan Reddy"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        //the java code is linked to list.xml file which outlines the layout of the android page
        //the onCreate method bundles the java code to the list.xml file and the javacode makes the ListView function.


        //The answers from the Questions2 class are sent to CandidateList class using a bundle.
        //The instance variables (db_married, db_doesUserHaveChildren, db_area, db_incomeEarned, db_age, db_political, db_vote,
        // db_gender) are assigned the value of the answers from the Questions2 class which have been bundled to the CandidateList
        // class using the intent.putExtra() method and has been received using the getIntent().getExtras() methods.
        Bundle bundle = getIntent().getExtras();
        db_married = (bundle.getString("married"));
        db_doesUserHaveChildren = (bundle.getString("doesUserHaveChildren"));
        db_area = (bundle.getString("area"));
        db_incomeEarned = (bundle.getString("incomeEarned"));
        db_age = (bundle.getString("age"));
        db_politicalParty = (bundle.getString("politicalParty"));
        db_vote = (bundle.getString("vote"));
        db_gender = (bundle.getString("gender"));


        db = new MyDBManager(this);// A MyDBManager object is created called db which links the CandidateList class to the MyDBManager class
        // so the answers to the questions can be inserted into the database in MyDBManager.


        candidateList = (ListView) findViewById(R.id.listcand);
        //The "candidateList" ListView is linked to the "listcand" ListView in list.xml.

        //The candidateList calls a setAdapter method which calls the MyCustomAdapter method in it's parameter's.
        //In the MyCustomAdapters methods parameters it inserts this CandidateList class, the rows.xml layout, and the Candidates array.
        candidateList.setAdapter(new MyCustomAdapter(CandidateList.this, R.layout.rows, Candidates));
        //The MyCustomAdapter class is carried out before the next command is carried out in CandidateList is carried out.

        //The candidateList Listview calls an OnClickListener when the user selects a candidate in the Listview.
        candidateList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                db_candidate = (String) candidateList.getItemAtPosition(position);
                //db_candidate is assigned the value of the name of the candidate selected which has been converted to a String.


                Toast.makeText(getApplicationContext(), db_candidate,
                        Toast.LENGTH_LONG).show(); //db_candidate has been toasted to make into text to go in MyDBManager class.

                Intent intent = new Intent(CandidateList.this, TaoiseachList.class);

                //An intent brings the user to another page when the candidate has been selected in the ListView, by clicking a row in the ListView.
                //The OnClickListener is called and the intent object brings the user  to the TaoiseachList Class.
                //In the parameters of the intent object it calls this class (CandidateList.this) and then the TaoiseachList Class(TaoiseachList.Class).
                //The user is brought to another question in the poll which contains a list of Taoiseach candidates and asks the user to select a preferred Taoiseach.
                //The assigned values of the instance variables (db_candidate, db_age, db_political, db_vote, db_gender, db_married,
                //db_doesUserHaveChildren, db_area, db_incomeEarned) are put into the parameters of an intent.putExtra()method.
                //The answers of the questions are bundled to the TaoiseachList Class.
                //Each answer is given a String name (example: "age", "area") which are called in the TaoiseachList Class by the getIntent().getExtras() methods.
                //The db_candidate instance variable has been added to a list of instance variables which have been bundled over from Questions.class.
                intent.putExtra("candidate", db_candidate);
                intent.putExtra("age", db_age);
                intent.putExtra("politicalParty", db_politicalParty);
                intent.putExtra("vote", db_vote);
                intent.putExtra("gender", db_gender);
                intent.putExtra("married", db_married);
                intent.putExtra("doesUserHaveChildren", db_doesUserHaveChildren);
                intent.putExtra("area", db_area);
                intent.putExtra("incomeEarned", db_incomeEarned);
                startActivity(intent);
                finish();
            }
        });
    }


    public class MyCustomAdapter extends ArrayAdapter<String> {
        //MyCustomAdapter called above in the CandidateList class.

        String[] names;//A Strings array local variable is called names.

        // Creating our own adaptor because we want to be able to customise each row
        public MyCustomAdapter(Context context, int textViewResourceId,
                               String[] objects) {
            super(context, textViewResourceId, objects);

            names = objects;
        }

        @Override
        // This getview method is called each time a row needs to be formatted for the list

        public View getView(int position, View convertView, ViewGroup parent) {

            //this method creates the layout for the rows.xml and this rows.xml has been added to the
            //list.xml


            //return super.getView(position, convertView, parent);
            LayoutInflater inflater = getLayoutInflater();
            View row = inflater.inflate(R.layout.rows, parent, false);//a layout is made for rows.xml
            TextView label = (TextView) row.findViewById(R.id.candidate);
            //a textview row is created which calls candidate TextView in rows.xml.
            label.setText(names[position]);
            label.setTextSize(24);
            //TextView labe calls the text from the names String Array which calls text from the
            // Candidates String array in CandidateList.class
            ImageView icon = (ImageView) row.findViewById(R.id.icon);
            //an ImageView row is created which calls icon ImageView in rows.xml.

            //an ImageView row is created which calls icon2 ImageView in rows.xml.

            //if a string from the Candidates String array is inserted into the TextView label
            //then images are inserted into ImageView icon and ImageView icon2.
            //These images are called from the drawable folder.

            if (Candidates[position] == "Narendhra Modi") {
                icon.setImageResource(R.drawable.fb);
            } else if (Candidates[position] == "Rahul Gandhi") {
                icon.setImageResource(R.drawable.fb);
            } else if (Candidates[position] == "Mayawati") {
                icon.setImageResource(R.drawable.fb);
            } else if (Candidates[position] == "Chandrashekhar Rao") {
                icon.setImageResource(R.drawable.fb);
            } else if (Candidates[position] == "Chandrababu Naidu") {
                icon.setImageResource(R.drawable.fb);
            } else if (Candidates[position] == "Karunanidhi") {
                icon.setImageResource(R.drawable.fb);
            } else if (Candidates[position] == "Mamatha Baneerja") {
                icon.setImageResource(R.drawable.fb);
            } else if (Candidates[position] == "Pawan Kalyan") {
                icon.setImageResource(R.drawable.fb);
            } else if (Candidates[position] == "Sitaram Yachury") {
                icon.setImageResource(R.drawable.fb);
            } else if (Candidates[position] == "Jagan Mohan Reddy") {
                icon.setImageResource(R.drawable.fb);
            } else if (Candidates[position] == "John Connolly") {
                icon.setImageResource(R.drawable.fb);
            }

            return row;
            //when each row is created it is added to rows.xml.
            //all the rows are then created as a list and added to list.xml.
            //When this method is finished and the row is returned
            // then the programme is continued in the CandidateList class
            //at candidateList.setOnItemClickListener.
        }
}}
