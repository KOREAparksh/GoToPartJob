package com.outsource.gotopartjob;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.odsay.odsayandroidsdk.API;
import com.odsay.odsayandroidsdk.ODsayData;
import com.odsay.odsayandroidsdk.ODsayService;
import com.odsay.odsayandroidsdk.OnResultCallbackListener;
import com.outsource.gotopartjob.model.Result;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    public final static String APP_KEY = "41hw5FQLS9/58MMOAkJcmAB9m6ljep5QEgbkSsVKCpM";
    private ODsayService oDsayService= ODsayService.init(this,  APP_KEY);//‘ODsayService’ 객체 생성시 사용합니다. // 오디세이 API를 사용하기 위한 싱글톤객체
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        oDsayService.setConnectionTimeout(10000);//서버 연결 제한 시간 설정합니다.(default : 5초, 단위 : millisecond )
        oDsayService.setReadTimeout(10000);//데이터 획득 제한 시간 설정합니다.(default : 5초, 단위 : millisecond )

        button = (Button)findViewById(R.id.main_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getResult();// 대중교통 길찾기 받아오는 오디세이 함수가 들어있는 메쏘드
            }
        });
    }

    void getResult(){
        // OPT : 0: 최단거리, 1 타입별정렬
        oDsayService.requestSearchPubTransPath(String.valueOf(127.0735),String.valueOf(37.5495), String.valueOf(127.10420), String.valueOf(37.54480)
                , /*OPT*/String.valueOf(0), String.valueOf(0), String.valueOf(0), new OnResultCallbackListener() {
                    @Override
                    public void onSuccess(ODsayData oDsayData, API api) {
                        Log.d("MainActivity_", "success");
                        if(api == API.SEARCH_PUB_TRANS_PATH){
                            receiveData(oDsayData);
                        }
                    }

                    @Override
                    public void onError(int i, String s, API api) {
                        Log.d("MainActivity_", "error : "+ s);
                    }
                });
    }

    void receiveData(ODsayData oDsayData){
        Log.d("MainActivity_", "in method");
        Gson gson = new GsonBuilder().create();
        Log.d("MainActivity_", "in method2");
        Result result = gson.fromJson(oDsayData.toString(), Result.class);
        Log.d("MainActivity_", "result : " + result);
        Log.d("MainActivity_", "result.getBusCount : " + result.getBusCount());
    }

}