package com.outsource.gotopartjob.model;


import com.google.gson.annotations.SerializedName;

public class Info {
    @SerializedName(value = "trafficDistance")
    private double trafficDistance; //도보 제외 총 이동 거리

    @SerializedName(value = "totalWalk")
    private int totalWalk; //총 도보 이동 거리

    @SerializedName(value = "totalTime")
    private int totalTime; //총 소요 거리

    @SerializedName(value = "payment")
    private int payment; //총 요금

    @SerializedName(value = "busTransitCount")
    private int busTransitCount; // 버스 환승 카운트

    @SerializedName(value = "subwayTransitCount")
    private int subwayTransitCount; //지하철 환승 카운트

    @SerializedName(value = "mapObj")
    private String mapObj; //보간점 API를 호출하기 위한 파라미터 값

    @SerializedName(value = "firstStartStation")
    private String firstStartStation; //최초 출발역/정류장

    @SerializedName(value = "lastEndStation")
    private String lastEndStation; //최종 도착역/정류장

    @SerializedName(value = "totalStationCount")
    private int totalStationCount; //총 정류장 합

    @SerializedName(value = "busStationCount")
    private int busStationCount; //버스 정류장 합

    @SerializedName(value = "subwayStationCount")
    private int subwayStationCount; //지하철 정류장 합

    @SerializedName(value = "totalDistance")
    private int totalDistance; //총 거리

    @SerializedName(value = "totalWalkTime")
    private int totalWalkTime; //총 도보 거리

    public Info() {
    }

    public Info(double trafficDistance, int totalWalk, int totalTime, int payment, int busTransitCount, int subwayTransitCount, String mapObj, String firstStartStation, String lastEndStation, int totalStationCount, int busStationCount, int subwayStationCount, int totalDistance, int totalWalkTime) {
        this.trafficDistance = trafficDistance;
        this.totalWalk = totalWalk;
        this.totalTime = totalTime;
        this.payment = payment;
        this.busTransitCount = busTransitCount;
        this.subwayTransitCount = subwayTransitCount;
        this.mapObj = mapObj;
        this.firstStartStation = firstStartStation;
        this.lastEndStation = lastEndStation;
        this.totalStationCount = totalStationCount;
        this.busStationCount = busStationCount;
        this.subwayStationCount = subwayStationCount;
        this.totalDistance = totalDistance;
        this.totalWalkTime = totalWalkTime;
    }

    public void setTrafficDistance(double trafficDistance) {
        this.trafficDistance = trafficDistance;
    }

    public int getTotalWalkTime() {
        return totalWalkTime;
    }

    public void setTotalWalkTime(int totalWalkTime) {
        this.totalWalkTime = totalWalkTime;
    }

    public double getTrafficDistance() {
        return trafficDistance;
    }

    public void setTrafficDistance(Double trafficDistance) {
        this.trafficDistance = trafficDistance;
    }

    public int getTotalWalk() {
        return totalWalk;
    }

    public void setTotalWalk(int totalWalk) {
        this.totalWalk = totalWalk;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public int getBusTransitCount() {
        return busTransitCount;
    }

    public void setBusTransitCount(int busTransitCount) {
        this.busTransitCount = busTransitCount;
    }

    public int getSubwayTransitCount() {
        return subwayTransitCount;
    }

    public void setSubwayTransitCount(int subwayTransitCount) {
        this.subwayTransitCount = subwayTransitCount;
    }

    public String getMapObj() {
        return mapObj;
    }

    public void setMapObj(String mapObj) {
        this.mapObj = mapObj;
    }

    public String getFirstStartStation() {
        return firstStartStation;
    }

    public void setFirstStartStation(String firstStartStation) {
        this.firstStartStation = firstStartStation;
    }

    public String getLastEndStation() {
        return lastEndStation;
    }

    public void setLastEndStation(String lastEndStation) {
        this.lastEndStation = lastEndStation;
    }

    public int getTotalStationCount() {
        return totalStationCount;
    }

    public void setTotalStationCount(int totalStationCount) {
        this.totalStationCount = totalStationCount;
    }

    public int getBusStationCount() {
        return busStationCount;
    }

    public void setBusStationCount(int busStationCount) {
        this.busStationCount = busStationCount;
    }

    public int getSubwayStationCount() {
        return subwayStationCount;
    }

    public void setSubwayStationCount(int subwayStationCount) {
        this.subwayStationCount = subwayStationCount;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(int totalDistance) {
        this.totalDistance = totalDistance;
    }
}
