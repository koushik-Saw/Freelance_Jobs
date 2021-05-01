package com.example.collageproject.fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collageproject.R;
import com.example.collageproject.adapters.FacultyAdapter;
import com.example.collageproject.models.Courses;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_LONG;


public class OnlineClassesFacultyFragment extends Fragment implements FacultyAdapter.onFacultyListener {

    DatabaseReference databaseReference;
    ArrayList<Courses> onlinecourseslist1;
    RecyclerView onlinefaculty_recyclerView;
    FacultyAdapter onlinefacultyAdapter;
    String a;
    public OnlineClassesFacultyFragment() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_online_classes_faculty, container, false);
        if (getArguments() != null) {
            Log.e("ff", "onCreateView: ");
            String b = getArguments().getString("faculty_name");

            a = b;
            Toast.makeText(getContext(), a, LENGTH_LONG).show();
        }
        databaseReference = FirebaseDatabase.getInstance().getReference().child("year");
        onlinecourseslist1 = new ArrayList<>();
        onlinefaculty_recyclerView = view.findViewById(R.id.onlinefaculty_recycleId);
        onlinefacultyAdapter = new FacultyAdapter(onlinecourseslist1, this);
        return view;
    }

    @Override
    public void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                onlinecourseslist1.clear();
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    try {
                        onlinecourseslist1.add(dataSnapshot1.getValue(Courses.class));
                    } catch (Exception e) {

                    }
                }
                try {
                    if (onlinefacultyAdapter != null) {
                        onlinefacultyAdapter.getFilter().filter(a);
                        onlinefacultyAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    Toast.makeText(getContext(), "" + e, Toast.LENGTH_SHORT).show();
                }
                onlinefaculty_recyclerView.setAdapter(onlinefacultyAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }
    TextView g;
    String b, c;
    @Override
    public void onClickItem(int position, View view) {
        onlinecourseslist1.get(position);

        g = onlinefaculty_recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.coursenm);

        b = g.getText().toString().trim();


        if (a.equals("General") && b.equals("1st Year")) {
            c = "gen11";
        } else if (a.equals("General") && b.equals("2nd Year")) {
            c = "gen12";
        } else if (a.equals("Vocational") && b.equals("2nd Year")) {
            c = "voc12";
        } else if (a.equals("Vocational") && b.equals("1st Year")) {
            c = "voc11";
        } else if (a.equals("Science") && b.equals("1st Year")) {
            c = "sci1";
        } else if (a.equals("Science") && b.equals("2nd Year")) {
            c = "sci2";
        } else if (a.equals("Science") && b.equals("3rd Year")) {
            c = "sci3";
        } else if (a.equals("Science") && b.equals("4th Year")) {
            c = "sci4";
        } else if (a.equals("Business Studies") && b.equals("1st Year")) {
            c = "bs1";
        } else if (a.equals("Business Studies") && b.equals("2nd Year")) {
            c = "bs2";
        } else if (a.equals("Business Studies") && b.equals("3rd Year")) {
            c = "bs3";
        } else if (a.equals("Business Studies") && b.equals("4th Year")) {
            c = "bs4";
        } else if (a.equals("Social Sci.") && b.equals("1st Year")) {
            c = "ss1";
        } else if (a.equals("Social Sci.") && b.equals("2nd Year")) {
            c = "ss2";
        } else if (a.equals("Social Sci.") && b.equals("3rd Year")) {
            c = "ss3";
        } else if (a.equals("Social Sci.") && b.equals("4th Year")) {
            c = "ss4";
        } else if (a.equals("Arts") && b.equals("1st Year")) {
            c = "art1";
        } else if (a.equals("Arts") && b.equals("2nd Year")) {
            c = "art2";
        } else if (a.equals("Arts") && b.equals("3rd Year")) {
            c = "art3";
        } else if (a.equals("Arts") && b.equals("4th Year")) {
            c = "art4";
        } else if (a.equals("Degree") && b.equals("1st Year")) {
            c = "dg1";
        } else if (a.equals("Degree") && b.equals("2nd Year")) {
            c = "dg2";
        } else if (a.equals("Degree") && b.equals("3rd Year")) {
            c = "dg3";
        } else if (a.equals("Degree") && b.equals("4th Year")) {
            c = "dg4";
        }


        Bundle bundle = new Bundle();
        bundle.putString("fac_year", c);
        Toast.makeText(getContext(), c, LENGTH_LONG).show();
        Navigation.findNavController((Activity) getContext(), R.id.nav_host_fragment).navigate(R.id.onlineClassesSubjectfragment, bundle);
    }
}