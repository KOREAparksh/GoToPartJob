package com.outsource.gotopartjob;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapView;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

public class FindLocationActivity extends AppCompatActivity {

    private ArrayList<TMapPOIItem> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private List<String> name;
    private List<String> address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_location);

        name = new ArrayList<>();
        address = new ArrayList<>();

        Intent sub_intent = getIntent();
        String str = sub_intent.getExtras().getString("address");

        Intent intent = new Intent(this, GetTMapDataActivity.class);
        intent.putExtra("address", str);
        startActivityForResult(intent, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(resultCode == RESULT_OK){
                recyclerView = (RecyclerView)findViewById(R.id.main_recyclerView) ;
                recyclerView.setHasFixedSize(true); // 성능강화
                recyclerView.setLayoutManager(new LinearLayoutManager(FindLocationActivity.this)) ;
                Adapter adapter = new Adapter(FindLocationActivity.this);
                recyclerView.setAdapter(adapter);

            }
        }
    }
}