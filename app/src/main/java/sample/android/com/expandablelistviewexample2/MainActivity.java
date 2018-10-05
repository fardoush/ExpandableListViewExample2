package sample.android.com.expandablelistviewexample2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private List<String> title = new ArrayList<>();
    private Map<String, List<String>> child = new HashMap<>();

    int lastPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        preparedData();
        addListener();
    }

    private void addListener() {

        // For Expand the group
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {     // here i is the position of the group

                if (lastPosition != -1 && lastPosition != i) {
                    expandableListView.collapseGroup(lastPosition);
                }

                lastPosition = i;
            }
        });

        // For Child click
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {

                // --- here i is the position of group, and i1 is the position of children
                String children = child.get(title.get(i)).get(i1);

                Toast.makeText(MainActivity.this, "" + children, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private void preparedData() {
        // ----- Add Title ---
        title.add("Afridi");
        title.add("Sakib");
        title.add("Tamim");
        title.add("Nasir");
        title.add("Mashrafe");

        // ---- Add Child ---
        List<String> afridi = new ArrayList<>();
        afridi.add("Boom Boom");
        afridi.add("Sixer");
        child.put(title.get(0), afridi);

        // ---- Add Child ---
        List<String> sakib = new ArrayList<>();
        sakib.add("All Rounder");
        sakib.add("Bowler");
        child.put(title.get(1), sakib);


        // ---- Add Child ---
        List<String> tamim = new ArrayList<>();
        tamim.add("Batsman");
        tamim.add("Openner");
        child.put(title.get(2), tamim);


        // ---- Add Child ---
        List<String> nasir = new ArrayList<>();
        nasir.add("Lover Boy");
        nasir.add("Finisher");
        child.put(title.get(3), nasir);


        // ---- Add Child ---
        List<String> mash = new ArrayList<>();
        mash.add("Best Captain Ever");
        mash.add("Bowler");
        child.put(title.get(4), mash);


        //---- Now Create The Adapter Object
        ExLvAdapter adapter = new ExLvAdapter(this, title, child);
        expandableListView.setAdapter(adapter);
    }

    private void initView() {

        expandableListView = findViewById(R.id.exLvId);
    }
}
