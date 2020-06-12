package com.outsource.gotopartjob;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.odsay.odsayandroidsdk.API;
import com.odsay.odsayandroidsdk.ODsayData;
import com.odsay.odsayandroidsdk.ODsayService;
import com.odsay.odsayandroidsdk.OnResultCallbackListener;
import com.outsource.gotopartjob.model.Answer;
import com.outsource.gotopartjob.model.Path;
import com.outsource.gotopartjob.model.Result;

public class MainActivity extends AppCompatActivity {

    public final static String APP_KEY = "41hw5FQLS9/58MMOAkJcmAB9m6ljep5QEgbkSsVKCpM";
    private ODsayService oDsayService= ODsayService.init(this,  APP_KEY);//‘ODsayService’ 객체 생성시 사용합니다. // 오디세이 API를 사용하기 위한 싱글톤객체
    private Button button;
    private Result result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        oDsayService.setConnectionTimeout(5000);//서버 연결 제한 시간 설정합니다.(default : 5초, 단위 : millisecond )
        oDsayService.setReadTimeout(5000);//데이터 획득 제한 시간 설정합니다.(default : 5초, 단위 : millisecond )

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
        oDsayService.requestSearchPubTransPath(String.valueOf(127.0735),String.valueOf(37.5495), String.valueOf(127.06690), String.valueOf(37.53200)
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
                        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                    }
                });
    }


    void receiveData(ODsayData oDsayData)  {
        Log.d("MainActivity_", "in method");
        Gson gson = new GsonBuilder().create();
        try{

            Log.d("MainActivity_", "json : "+ oDsayData.getJson().toString());
            Answer answer = gson.fromJson(oDsayData.getJson().toString(), Answer.class);
            Log.d("MainActivity_", "answer : "+ answer.getResult().getBusCount());

            result = answer.getResult();

        }catch (Exception e){
            Log.d("MainActivity_", "error in receive : " + e.getMessage());
        }
    }

}