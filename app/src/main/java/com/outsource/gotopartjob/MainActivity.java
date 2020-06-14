package com.outsource.gotopartjob;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.odsay.odsayandroidsdk.API;
import com.odsay.odsayandroidsdk.ODsayData;
import com.odsay.odsayandroidsdk.ODsayService;
import com.odsay.odsayandroidsdk.OnResultCallbackListener;
import com.outsource.gotopartjob.model.Answer;
import com.outsource.gotopartjob.model.Path;
import com.outsource.gotopartjob.model.Result;
import com.skt.Tmap.TMapGpsManager;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TMapGpsManager.onLocationChangedCallback {

    public final static String APP_KEY_ODsay = "41hw5FQLS9/58MMOAkJcmAB9m6ljep5QEgbkSsVKCpM";
    public final static String APP_KEY_Tmap = "l7xx6b69052b6d5c41ec82916fb72944ee23";

    private ODsayService oDsayService = ODsayService.init(this, APP_KEY_ODsay);//‘ODsayService’ 객체 생성시 사용합니다. // 오디세이 API를 사용하기 위한 싱글톤객체
    private Button button;
    private Result result;
    private ProgressBar progressBar;

    private TMapGpsManager gps;
    /**
     * 티맵 데이터 사용을 위해 키값 보내기 위해 만들었습니다.
     * 해당 TmapView 사용하시면 쉽게 지도 띄울 수 있습니다
     */
    private TMapView tMapView;


    private TextView busCount, subwayCount, subwayBusCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}, 1); //위치권한 탐색 허용 관련 내용
        }

        initView();

    }

    private void initView() {
        tMapView = new TMapView(this);
        tMapView.setSKTMapApiKey(MainActivity.APP_KEY_Tmap);

        oDsayService.setConnectionTimeout(5000);//서버 연결 제한 시간 설정합니다.(default : 5초, 단위 : millisecond )
        oDsayService.setReadTimeout(5000);//데이터 획득 제한 시간 설정합니다.(default : 5초, 단위 : millisecond )

        busCount = (TextView) findViewById(R.id.busCount_tv);
        subwayCount = (TextView) findViewById(R.id.subwayCount_tv);
        subwayBusCount = (TextView) findViewById(R.id.subwayBusCount_tv);
        progressBar = (ProgressBar) findViewById(R.id.progress);

        progressBar.setVisibility(View.VISIBLE);

        button = (Button) findViewById(R.id.main_button); //
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //알바위치 저장하는 버튼
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                final EditText et = new EditText(MainActivity.this); // 주소를 입력하시면 네를 눌렀을때 해당 string을 다음 액티비티로 넘김니다.
                builder.setView(et).setTitle("알바 주소 입력하기").setMessage("주소를 입력해주세요");

                builder
                        .setPositiveButton("네", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String str = et.getText().toString();
                                Intent intent = new Intent(MainActivity.this, FindLocationActivity.class);
                                intent.putExtra("address", str);
                                startActivityForResult(intent, 1);
                            }
                        })
                        .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });

        /**
         * 티맵 API를 이용해서 현재위치 찾기. 구글꺼 사용하는거보다 10000만배 쉽습니다.
         * 나중에 맵 만드실때 티맵으로 해보세
         */
        gps = new TMapGpsManager(this);
        gps.setMinTime(1000);
        gps.setMinDistance(5);
        //gps.setProvider(gps.GPS_PROVIDER);//GPS로 위치를 받을 때는 이걸 쓰시면 됩니다.
        gps.setProvider(TMapGpsManager.NETWORK_PROVIDER); //네트워크로 위치를 받을 때는 이걸 쓰시면 됩니다.
        gps.OpenGps(); //gps를 오픈하면 위치를 받아와서 자동으로 onLocationChange함수를 호출합니다.

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {

            gps.OpenGps(); //gps를 오픈하면 위치를 받아와서 자동으로 onLocationChange함수를 호출합니다.

        }
    }

    void receiveData(ODsayData oDsayData) {
        Gson gson = new GsonBuilder().create();
        try {
            Iterator i = oDsayData.getJson().keys();
            String key = i.next().toString();

            if (key.equals("result")) {
                Answer answer = gson.fromJson(oDsayData.getJson().toString(), Answer.class); //수신되는 json데이터는 "result"라는 key에 담겨옵니다. Answer 객체를 만들어 result라는 변수를 만들어 데이터를 받습니다.
                result = answer.getResult();//실 데이터는  Answer객체 안에 있는 Result객체의 result라는 변수에 저장됩니다.
                setData();
            } else if (key.equals("error")) {
                gps.CloseGps();
                progressBar.setVisibility(View.INVISIBLE);
                String msg = oDsayData.getJson().getJSONObject(key).getString("msg");
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Log.d("MainActivity_", "error in receive : " + e.getMessage());
        }
    }

    private void setData() {
        busCount.setText(String.valueOf(result.getBusCount()));
        subwayCount.setText(String.valueOf(result.getSubwayCount()));
        subwayBusCount.setText(String.valueOf(result.getSubwayBusCount()));


        //빠른 순으로 정렬되어있음
        for (int i = 0; i < result.getPath().size(); i++) {
            Path path = result.getPath().get(i);
            Log.d("MainActivity_", " ");
            Log.d("MainActivity_", " ");
            Log.d("MainActivity_", "Path 순서 : " + i);
            Log.d("MainActivity_", "Path Type (1 : 지하철, 2 : 버스, 3: 지하철+버스) : " + path.getPathType());
            Log.d("MainActivity_", "Path info");
            Log.d("MainActivity_", "Path info : 출발역 = " + path.getInfo().getFirstStartStation());
            Log.d("MainActivity_", "Path info : 도착역 = " + path.getInfo().getLastEndStation());
            Log.d("MainActivity_", "Path info : 총 소요시간 (도보제외) = " + path.getInfo().getTotalTime());
            Log.d("MainActivity_", "Path info : 총 도보시간 = " + path.getInfo().getTotalWalkTime());
            Log.d("MainActivity_", "Path info : 버스 정류장 개수 = " + path.getInfo().getBusStationCount());
            Log.d("MainActivity_", "Path info : 버스 환승 횟수 = " + path.getInfo().getBusTransitCount());
            Log.d("MainActivity_", "Path info : 지하철 정류장 횟수 = " + path.getInfo().getSubwayStationCount());
            Log.d("MainActivity_", "Path info : 지하철 환승 횟수 = " + path.getInfo().getSubwayTransitCount());
            Log.d("MainActivity_", "Path info : 나머지는 클래스 참고");
            for(int k=0; k<path.getSubPath().size(); k++){
                Log.d("MainActivity_", "\n======================");
                Log.d("MainActivity_", "Path subPath :traffic ( 1:지하철 2:버스 3:도보 ) : " + path.getSubPath().get(k).getTrafficType());
                Log.d("MainActivity_", "Path subPath :이동거리 : " + path.getSubPath().get(k).getDistance());
                Log.d("MainActivity_", "Path subPath :이동 소요 시간 : " + path.getSubPath().get(k).getSectionTime());
                Log.d("MainActivity_", "나머지는 클래스 참고 ( 도보, 지하철, 버스 일때마다 저장되는 값이 다르니 홈페이지 참고)");
                Log.d("MainActivity_", "======================\n");
            }

        }

        gps.CloseGps();//gps를 계속 켜두면 계속 경로를 수정하니까 끕니다.
        progressBar.setVisibility(View.INVISIBLE);
    }


    /**
     * Tmap GPS를 사용하려면 해당 함수가 필요합니다. TMapGpsManager.onLocationChangedCallback를 상속받으세요.
     */
    @Override
    public void onLocationChange(Location location) {

        //저장된 위치가 있는지 확인
        if (PreferenceManager.getString(this, PreferenceManager.KEY_LON) == null || PreferenceManager.getString(this, PreferenceManager.KEY_LAT) == null) {
            //저장된 위치가 없다면
            Log.d("MainActivity_", "failed");
            Toast.makeText(MainActivity.this, "저장된 주소가 없습니다", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.INVISIBLE);
            return;
        } else
            //저장된 위치가 있다면
            Log.d("MainActivity_", "sucess");


        String lat = String.valueOf(location.getLatitude()); //37.XXXXX
        String lon = String.valueOf(location.getLongitude());
        String des_lat = PreferenceManager.getString(MainActivity.this, PreferenceManager.KEY_LAT);
        String des_lon = PreferenceManager.getString(MainActivity.this, PreferenceManager.KEY_LON);

        oDsayService.requestSearchPubTransPath(/*시작위치*/lon, lat, /*도착위치*/des_lon, des_lat
                , /*OPT*/String.valueOf(0), String.valueOf(0), String.valueOf(0), new OnResultCallbackListener() {
                    @Override
                    public void onSuccess(ODsayData oDsayData, API api) {
                        Log.d("MainActivity_", "success");
                        if (api == API.SEARCH_PUB_TRANS_PATH) {

                            receiveData(oDsayData);

                        }
                    }

                    @Override
                    public void onError(int i, String s, API api) {
                        Log.d("MainActivity_", "error : " + s);
                        gps.CloseGps();
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(), "앱을 껐다가 다시 켜주세요", Toast.LENGTH_SHORT).show();

                    }
                });
    }
}