package com.zhangke.funnyread.ZhiHu.entity;

import java.util.List;

/**
 * Created by ZhangKe at 2016/12/12
 */
public class ZhiHuDiaryDetailEntity {
    private String body;

    private String image;

    private String share_url;

    private List<Js> js ;

    private String ga_prefix;

    private List<String> images ;

    private int type;

    private int id;

    private List<String> css ;

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }

    public void setBody(String body){
        this.body = body;
    }
    public String getBody(){
        return this.body;
    }
    public void setImage(String image){
        this.image = image;
    }
    public String getImage(){
        return this.image;
    }
    public void setShare_url(String share_url){
        this.share_url = share_url;
    }
    public String getShare_url(){
        return this.share_url;
    }
    public void setJs(List<Js> js){
        this.js = js;
    }
    public List<Js> getJs(){
        return this.js;
    }
    public void setGa_prefix(String ga_prefix){
        this.ga_prefix = ga_prefix;
    }
    public String getGa_prefix(){
        return this.ga_prefix;
    }
    public void setType(int type){
        this.type = type;
    }
    public int getType(){
        return this.type;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public class Images {

    }
    public class Css {

    }
    public class Js {

    }
}
