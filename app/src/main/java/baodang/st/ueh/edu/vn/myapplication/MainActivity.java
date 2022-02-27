package baodang.st.ueh.edu.vn.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    GridView mygridview;
    TextView randomIcon;
    ArrayList<Integer> ds;
    ArrayList<Integer> indexRemain;
    int currentIndex;
    final int total = 50;
    int wrong = 0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mygridview = findViewById(R.id.mygridview);
        randomIcon = findViewById(R.id.randomIcon);

        // Tạo danh sách các icon
        ds = new ArrayList<>();
        TaoDSICon(ds);

        // Tạo danh sách các index hiện có
        indexRemain = new ArrayList<>();
        for (int i = 0; i < ds.size(); i++) {
            indexRemain.add(i);
        }
        // Chọn ngẫu nhiên một index
        currentIndex = this.runRandom(this.indexRemain);
        runGame();
    }

    private void runGame() {

        MyAdapter myAdapter = new MyAdapter(this, R.layout.items, ds);
        mygridview.setAdapter(myAdapter);
        Log.d("MainActivity", String.valueOf(indexRemain.size()));
        Log.d("MainActivity", String.valueOf(currentIndex));
        mygridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Log.d("MainActivity", String.valueOf(i));
                int selectedIndex = i;
                if (selectedIndex == currentIndex) {
                    Log.d("MainActivity", "Chon dung");
                    TextView selectedView = (TextView) view;
                    selectedView.setVisibility(View.INVISIBLE);

                    indexRemain.remove(indexRemain.indexOf(currentIndex));
                    if (indexRemain.size() > 0) {
                        currentIndex = runRandom(indexRemain);
                        Log.d("MainActivity", String.valueOf(currentIndex));
                        Log.d("MainActivity", String.valueOf(indexRemain.size()));
                    }
                    else{
                        startActivity(new Intent(getApplicationContext(), SuccessActivity.class));
                    }
                }
                else{
                    wrong++;
                    Toast.makeText(getApplicationContext(),
                            "Failed! Remaining: "+(3-wrong)+" lives",
                            Toast.LENGTH_SHORT).show();
                    if (wrong >3) {
                        startActivity(new Intent(getApplicationContext(), FailedActivity.class));
                    }

                }
            }
        });

    }

    private void TaoDSICon(List<Integer> ds){
        ds.add(R.drawable.ic_baseline_pin_drop_24);
        ds.add(R.drawable.ic_baseline_play_arrow_24);
        ds.add(R.drawable.ic_baseline_psychology_24);
        ds.add(R.drawable.ic_baseline_query_builder_24);
        ds.add(R.drawable.ic_baseline_sick_24);
        ds.add(R.drawable.ic_baseline_tablet_24);
        ds.add(R.drawable.ic_baseline_tag_faces_24);
        ds.add(R.drawable.ic_baseline_wb_cloudy_24);
        ds.add(R.drawable.ic_baseline_wine_bar_24);
        ds.add(R.drawable.ic_baseline_public_24);
    }
    private int runRandom(ArrayList<Integer> indexRemain){
        Random random = new Random();
        int index = random.nextInt(indexRemain.size());
        this.randomIcon.setBackgroundResource(ds.get(indexRemain.get(index)));
        return indexRemain.get(index);
    }
}