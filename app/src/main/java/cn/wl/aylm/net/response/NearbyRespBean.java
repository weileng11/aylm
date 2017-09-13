package cn.wl.aylm.net.response;

import java.io.Serializable;
import java.util.List;

/**
 * Author:lt
 * time:2017/7/29.
 * contact：weileng143@163.com
 *
 * @description  附近的店
 */

public class NearbyRespBean{
//    {"data":[{"guid":"3cb5ec99-1b07-4065-903e-4b86b4abde96","title":
//        "刘刚口腔诊所","content":"http://aclmmanage.jshec.cn/ImgUpload/kindeditor/image/20161230/20161230144135_7544.jpg",
//                "keywords":"13725150568","source":"113.328859,23.144347","mobile":"","stars":"4.6","address":"中山大道西101号",
//            "area":"广东省广州市天河区","officetel":"","isbound":0,"range":12160.18,"pointx":"113.328859","pointy":"23.144347"}]}

    public List<NearbyBeanList> data;

    public static class NearbyBeanList implements Serializable{
        public String guid;
        public String title;
        public String content;
        public String keywords;
        public String source;
        public String mobile;
        public String stars;
        public String address;
        public String area;
        public String officetel;
        public int isbound;
        public double range;
        public String pointx;
        public String pointy;


        @Override
        public String toString() {
            return "NearbyBeanList{" +
                    "guid='" + guid + '\'' +
                    ", title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    ", keywords='" + keywords + '\'' +
                    ", source='" + source + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", stars='" + stars + '\'' +
                    ", address='" + address + '\'' +
                    ", area='" + area + '\'' +
                    ", officetel='" + officetel + '\'' +
                    ", isbound=" + isbound +
                    ", range=" + range +
                    ", pointx='" + pointx + '\'' +
                    ", pointy='" + pointy + '\'' +
                    '}';
        }
    }



}
