package com.outsource.gotopartjob.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Path {

    @SerializedName(value = "pathType")
    private int pathType; //결과 종류 1-지하철, 2-버스, 3-버스+지하철

    @SerializedName(value = "info")
    private Info info; //요약 정보 확장 노드

    @SerializedName(value = "subPath")
    private List<SubPath> subPath; //이동 교통 수단 정보 확장 노드

    public Path() {
    }

    public Path(int pathType, Info info, List<SubPath> subPath) {
        this.pathType = pathType;
        this.info = info;
        this.subPath = subPath;
    }

    public int getPathType() {
        return pathType;
    }

    public void setPathType(int pathType) {
        this.pathType = pathType;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public List<SubPath> getSubPath() {
        return subPath;
    }

    public void setSubPath(List<SubPath> subPath) {
        this.subPath = subPath;
    }
}
