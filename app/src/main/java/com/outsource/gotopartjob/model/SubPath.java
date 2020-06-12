package com.outsource.gotopartjob.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubPath {

    @SerializedName(value = "trafficType")
    private int trafficType; //이동 수단 종류 (도보, 버스, 지하철) 1-지하철, 2-버스, 3-도보

    @SerializedName(value = "distance")
    private double distance; //이동 거리

    @SerializedName(value = "sectionTime")
    private int sectionTime; //이동 소요 시간

    @SerializedName(value = "stationCount")
    private int stationCount; //이동 정거장 수(지하철, 버스 경우만 필수)

    @SerializedName(value = "lane")
    private List<Lane> lane; //교통 수단 정보 확장 노드

    @SerializedName(value = "startName")
    private String startName; //승차 정류장/역명

    @SerializedName(value = "startX")
    private double startX; //승차 정류장/역 X 좌표

    @SerializedName(value = "startY")
    private double startY; //승차 정류장/역 Y 좌표

    @SerializedName(value = "endName")
    private String endName; //하차 정류장/역명

    @SerializedName(value = "endX")
    private double endX; //하차 정류장/역 X 좌표

    @SerializedName(value = "endY")
    private double endY; //하차 정류장/역 Y 좌표

    @SerializedName(value = "way")
    private String way; //방면 정보 (지하철인 경우에만 필수)

    @SerializedName(value = "wayCode")
    private int wayCode; //방면 정보 코드(지하철의 첫번째 경로에만 필수) 1 : 상행, 2: 하행

    @SerializedName(value = "door")
    private String door; //지하철 빠른 환승 위치 (지하철인 경우에만 필수)

    @SerializedName(value = "startID")
    private int startID; //출발 정류장/역 코드

    @SerializedName(value = "endID")
    private int endID; //도착 정류장/역 코드

    @SerializedName(value = "passStopList")
    private List<PassStopList> passStopList; //출발 정류장/역 코드

    public int getTrafficType() {
        return trafficType;
    }

    public void setTrafficType(int trafficType) {
        this.trafficType = trafficType;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getSectionTime() {
        return sectionTime;
    }

    public void setSectionTime(int sectionTime) {
        this.sectionTime = sectionTime;
    }

    public int getStationCount() {
        return stationCount;
    }

    public void setStationCount(int stationCount) {
        this.stationCount = stationCount;
    }

    public List<Lane> getLane() {
        return lane;
    }

    public void setLane(List<Lane> lane) {
        this.lane = lane;
    }

    public String getStartName() {
        return startName;
    }

    public void setStartName(String startName) {
        this.startName = startName;
    }

    public double getStartX() {
        return startX;
    }

    public void setStartX(double startX) {
        this.startX = startX;
    }

    public double getStartY() {
        return startY;
    }

    public void setStartY(double startY) {
        this.startY = startY;
    }

    public String getEndName() {
        return endName;
    }

    public void setEndName(String endName) {
        this.endName = endName;
    }

    public double getEndX() {
        return endX;
    }

    public void setEndX(double endX) {
        this.endX = endX;
    }

    public double getEndY() {
        return endY;
    }

    public void setEndY(double endY) {
        this.endY = endY;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public int getWayCode() {
        return wayCode;
    }

    public void setWayCode(int wayCode) {
        this.wayCode = wayCode;
    }

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    public int getStartID() {
        return startID;
    }

    public void setStartID(int startID) {
        this.startID = startID;
    }

    public int getEndID() {
        return endID;
    }

    public void setEndID(int endID) {
        this.endID = endID;
    }

    public List<PassStopList> getPassStopList() {
        return passStopList;
    }

    public void setPassStopList(List<PassStopList> passStopList) {
        this.passStopList = passStopList;
    }
}
