package com.minglang.suiuu.entity;

/**
 * Created by Administrator on 2015/4/22.
 */
public class LoopDetailsData {

    public String cId;

    public String articleId;

    public String aTitle;

    public String aImg;

    public String aCmtCount;

    public String aContent;

    public String aSupportCount;

    public String aCreateUserSign;

    public String aCreateTime;

    public String aLastUpdateTime;

    public String aStatus;

    public String aAddr;

    public String nickname;

    public String headImg;

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getaTitle() {
        return aTitle;
    }

    public void setaTitle(String aTitle) {
        this.aTitle = aTitle;
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

    public String getaContent() {
        return aContent;
    }

    public void setaContent(String aContent) {
        this.aContent = aContent;
    }

    public String getaSupportCount() {
        return aSupportCount;
    }

    public void setaSupportCount(String aSupportCount) {
        this.aSupportCount = aSupportCount;
    }

    public String getaCreateUserSign() {
        return aCreateUserSign;
    }

    public void setaCreateUserSign(String aCreateUserSign) {
        this.aCreateUserSign = aCreateUserSign;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    @Override
    public String toString() {
        return "LoopDetailsData{" +
                "cId='" + cId + '\'' +
                ", articleId='" + articleId + '\'' +
                ", aTitle='" + aTitle + '\'' +
                ", aImg='" + aImg + '\'' +
                ", aCmtCount='" + aCmtCount + '\'' +
                ", aContent='" + aContent + '\'' +
                ", aSupportCount='" + aSupportCount + '\'' +
                ", aCreateUserSign='" + aCreateUserSign + '\'' +
                ", aCreateTime='" + aCreateTime + '\'' +
                ", aLastUpdateTime='" + aLastUpdateTime + '\'' +
                ", aStatus='" + aStatus + '\'' +
                ", aAddr='" + aAddr + '\'' +
                ", nickname='" + nickname + '\'' +
                ", headImg='" + headImg + '\'' +
                '}';
    }
}
