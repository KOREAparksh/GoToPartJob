package com.outsource.gotopartjob.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Path {

    @SerializedName(value = "pathType")
    private int pathType; //결과 종류 1-지하철, 2-버스, 3-버스+지하철

    @SerializedName(value = "info")
    private List<Info> info; //요약 정보 확장 노드

    @SerializedName(value = "subPath")
    private List<SubPath> subPath; //이동 교통 수단 정보 확장 노드
}
