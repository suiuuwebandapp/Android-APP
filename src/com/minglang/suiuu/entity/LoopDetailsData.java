package com.minglang.suiuu.entity;

/**
 * Created by Administrator on 2015/4/22.
 */
public class LoopDetailsData {

    public String articleId;

    public String cId;

    public String aTitle;

    public String aContent;

    public String aImg;

    public String aCmtCount;

    public String aSupportCount;

    public String aCreateUserId;

    public String aCreateTime;

    public String aLastUpdateTime;

    public String aStatus;

    public String aAddr;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getaTitle() {
        return aTitle;
    }

    public void setaTitle(String aTitle) {
        this.aTitle = aTitle;
    }

    public String getaContent() {
        return aContent;
    }

    public void setaContent(String aContent) {
        this.aContent = aContent;
    }

    public String getaImg() {
        return aImg;
    }

    public void setaImg(String aImg) {
        this.aImg = aImg;
    }

    public String getaCmtCount() {
        return aCmtCount;
    }

    public void setaCmtCount(String aCmtCount) {
        this.aCmtCount = aCmtCount;
    }

    public String getaSupportCount() {
        return aSupportCount;
    }

    public void setaSupportCount(String aSupportCount) {
        this.aSupportCount = aSupportCount;
    }

    public String getaCreateUserId() {
        return aCreateUserId;
    }

    public void setaCreateUserId(String aCreateUserId) {
        this.aCreateUserId = aCreateUserId;
    }

    public String getaCreateTime() {
        return aCreateTime;
    }

    public void setaCreateTime(String aCreateTime) {
        this.aCreateTime = aCreateTime;
    }

    public String getaLastUpdateTime() {
        return aLastUpdateTime;
    }

    public void setaLastUpdateTime(String aLastUpdateTime) {
        this.aLastUpdateTime = aLastUpdateTime;
    }

    public String getaStatus() {
        return aStatus;
    }

    public void setaStatus(String aStatus) {
        this.aStatus = aStatus;
    }

    public String getaAddr() {
        return aAddr;
    }

    public void setaAddr(String aAddr) {
        this.aAddr = aAddr;
    }

    @Override
    public String toString() {
        return "LoopDetailsData{" +
                "articleId='" + articleId + '\'' +
                ", cId='" + cId + '\'' +
                ", aTitle='" + aTitle + '\'' +
                ", aContent='" + aContent + '\'' +
                ", aImg='" + aImg + '\'' +
                ", aCmtCount='" + aCmtCount + '\'' +
                ", aSupportCount='" + aSupportCount + '\'' +
                ", aCreateUserId='" + aCreateUserId + '\'' +
                ", aCreateTime='" + aCreateTime + '\'' +
                ", aLastUpdateTime='" + aLastUpdateTime + '\'' +
                ", aStatus='" + aStatus + '\'' +
                ", aAddr='" + aAddr + '\'' +
                '}';
    }
}
