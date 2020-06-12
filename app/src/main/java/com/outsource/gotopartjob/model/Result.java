package com.outsource.gotopartjob.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    @SerializedName(value = "searchType")
    private int searchType; //결과 구분 (도시내 or 도시간 직통 or 도시간 환승) 0-도시내, 1-도시간 직통, 2-도시간 환승

    @SerializedName(value = "outTrafficCheck")
    private int outTrafficCheck; //도시간 "직통" 탐색 결과 유무(환승 X) 0-False, 1-True

    @SerializedName(value = "busCount")
    private int busCount; //버스 결과 개수

    @SerializedName(value = "subwayCount")
    private int subwayCount; //지하철 결과 개수

    @SerializedName(value = "subwayBusCount")
    private int subwayBusCount; //“버스+지하철” 결과 개수

    @SerializedName(value = "pointDistance")
    private double pointDistance; //출발지(SX, SY)와 도착지(EX, EY)의 직선 거리 (미터)

    @SerializedName(value = "startRadius")
    private int startRadius; //출발지 반경

    @SerializedName(value = "endRadius")
    private int endRadius; //도착지 반경

    @SerializedName(value = "path")
    private List<Path> path; //결과 리스트 확장 노드

    public int getSearchType() {
        return searchType;
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }

    public int getOutTrafficCheck() {
        return outTrafficCheck;
    }

    public void setOutTrafficCheck(int outTrafficCheck) {
        this.outTrafficCheck = outTrafficCheck;
    }

    public int getBusCount() {
        return busCount;
    }

    public void setBusCount(int busCount) {
        this.busCount = busCount;
    }

    public int getSubwayCount() {
        return subwayCount;
    }

    public void setSubwayCount(int subwayCount) {
        this.subwayCount = subwayCount;
    }

    public int getSubwayBusCount() {
        return subwayBusCount;
    }

    public void setSubwayBusCount(int subwayBusCount) {
        this.subwayBusCount = subwayBusCount;
    }

    public double getPointDistance() {
        return pointDistance;
    }

    public void setPointDistance(double pointDistance) {
        this.pointDistance = pointDistance;
    }

    public int getStartRadius() {
        return startRadius;
    }

    public void setStartRadius(int startRadius) {
        this.startRadius = startRadius;
    }

    public int getEndRadius() {
        return endRadius;
    }

    public void setEndRadius(int endRadius) {
        this.endRadius = endRadius;
    }

    public List<Path> getPath() {
        return path;
    }

    public void setPath(List<Path> path) {
        this.path = path;
    }
}
