package com.outsource.gotopartjob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapPOIItem;

import java.util.ArrayList;
import java.util.List;

public class GetTMapDataActivity extends AppCompatActivity implements TMapData.FindAllPOIListenerCallback {

    /**
     * 아래 코드는 SKT본사 기준으로부터 검색결과 리스트를 나열합니다.
     * 따라서 베스킨라빈스를 예로 들면 베스킨라빈스 광화문점부터 뜹니다.
     * 상세한 주소 입력을 하여야 잘 나옵니다.
     * 주변검색일 경우 tmapdata. {} 의 함수를 findaround 로 하셔야합니
     * */
    TMapData tmapdata;

    static ArrayList<TMapPOIItem> list = new ArrayList<>();
    static List<String> name;
    static List<String> address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_t_map_data);

        Intent intent = getIntent();
        String str = intent.getExtras().getString("address");

        name = new ArrayList<>();
        address = new ArrayList<>();

        tmapdata = new TMapData();
        tmapdata.findAllPOI(str, 20, this);
    }

    @Override
    public void onFindAllPOI(ArrayList<TMapPOIItem> arrayList) {

        list = arrayList;

        name.clear();
        address.clear();
        for(TMapPOIItem item : list){

            Log.d("MainActivity_", " "+item.getPOIName());
            name.add(item.getPOIName());
            address.add(item.getPOIAddress());
        }

        setResult(RESULT_OK);
        finish();
    }
}