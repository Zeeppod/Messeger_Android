package messeger.e.messeger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tw;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef1;
    private DataAdapter1 dataAdapter1;
    private EditText enteredMessege1;
    private ArrayList<String> arrayList1= new ArrayList<>();
    private RecyclerView mess_exit1;
    private String names = LoginActivity.name;
    private Button sendMessege1;
    private String userMesseg;
    private Intent notificationIntent;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sendMessege1 = findViewById(R.id.sendMessegeBtn1);

        mess_exit1 = findViewById(R.id.exit_mess1);

        mess_exit1.setLayoutManager(new LinearLayoutManager(this));
        dataAdapter1 = new DataAdapter1(this,arrayList1);

        mess_exit1.setAdapter(dataAdapter1);

        
        enteredMessege1 = findViewById(R.id.enteredMessege1);

        tw = findViewById(R.id.mess_item);

        notificationIntent = new Intent(this, MainActivity.class);

        tabLayout= findViewById(R.id.tabs);
        viewPager = findViewById(R.id.container);

        myRef1 = database.getReference("messeger_room1");
        updateChat1();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 0){
                    myRef1 = database.getReference("messeger_room1");
                    updateChat1();
                }else if(tab.getPosition() == 1){
                    myRef1 = database.getReference("messeger_room2");
                    updateChat1();
                }else if(tab.getPosition() == 2){
                    myRef1 = database.getReference("messeger_room3");
                    updateChat1();
                }else if(tab.getPosition() == 3){
                    myRef1 = database.getReference("messeger_room4");
                    updateChat1();
                }else if(tab.getPosition() == 4){
                    myRef1 = database.getReference("messeger_room5");
                    updateChat1();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        sendMessege1();
    }

    private void sendMessege1() {
        sendMessege1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userMesseg = enteredMessege1.getText().toString();
                if(userMesseg.trim().length() != 0) {

                    myRef1.push().setValue(names + ": " + userMesseg);
                    enteredMessege1.setText(null);

                }
            }
        });

    }


    public void updateChat1(){
        myRef1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String msg = dataSnapshot.getValue().toString();
                arrayList1.add(msg);
                dataAdapter1.notifyDataSetChanged();
                mess_exit1.smoothScrollToPosition(arrayList1.size());
                if(((MyAplication) getApplicationContext()).isAppForeground()) {
                    stopService(new Intent(MainActivity.this, MyService.class));
                }else {
                    Intent intent = new Intent(MainActivity.this, MyService.class);
                    intent.putExtra("msg", msg);
                    startService(intent);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        arrayList1.clear();
    }

}
