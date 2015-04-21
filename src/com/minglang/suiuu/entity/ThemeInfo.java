package com.minglang.suiuu.entity;

/**
 * 圈子-主题数据实体类
 * <p/>
 * Created by Administrator on 2015/4/21.
 */
public class ThemeInfo {

    /**
     * 图片地址
     */
    public String imagePath;

    /**
     * 标题
     */
    public String title;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ThemeInfo{" +
                "imagePath='" + imagePath + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
