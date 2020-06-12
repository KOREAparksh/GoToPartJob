package com.outsource.gotopartjob.model;

import com.google.gson.annotations.SerializedName;

public class Lane {

    @SerializedName(value = "name")
    private String name; //지하철 노선명 (지하철인 경우에만 필수)

    @SerializedName(value = "busNo")
    private String busNo; //버스 번호 (버스인 경우에만 필수)

    @SerializedName(value = "type")
    private int type; //버스 타입 (버스인 경우에만 필수,최하단 참조)

    @SerializedName(value = "busID")
    private int busID; //버스 코드 (버스인 경우에만 필수)

    @SerializedName(value = "subwayCode")
    private int subwayCode; //지하철 노선 번호 (지하철인 경우에만 필수)

    @SerializedName(value = "subwayCityCode")
    private int subwayCityCode	; //지하철 도시코드 (지하철인 경우에만 필수)

    public Lane() {
    }

    public Lane(String name, String busNo, int type, int busID, int subwayCode, int subwayCityCode) {
        this.name = name;
        this.busNo = busNo;
        this.type = type;
        this.busID = busID;
        this.subwayCode = subwayCode;
        this.subwayCityCode = subwayCityCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusNo() {
        return busNo;
    }

    public void setBusNo(String busNo) {
        this.busNo = busNo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getBusID() {
        return busID;
    }

    public void setBusID(int busID) {
        this.busID = busID;
    }

    public int getSubwayCode() {
        return subwayCode;
    }

    public void setSubwayCode(int subwayCode) {
        this.subwayCode = subwayCode;
    }

    public int getSubwayCityCode() {
        return subwayCityCode;
    }

    public void setSubwayCityCode(int subwayCityCode) {
        this.subwayCityCode = subwayCityCode;
    }
}
