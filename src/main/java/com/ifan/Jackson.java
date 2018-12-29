package com.ifan;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

public class Jackson {

    /**
     * ObjectMapper主要进行Java对象与JSON的相互转换
     */
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String objectToJson(Object data)
    {
        try {
            return MAPPER.writeValueAsString(data);
        }catch (JsonProcessingException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T jsonToPojo(String data,Class<T> beanType)
    {
        try {
            return MAPPER.readValue(data,beanType);
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> List<T> jsonToList(String data,Class<T> beanType)
    {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class,beanType);
        try {
            return MAPPER.readValue(data,javaType);
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String str = "{\"movie\":\"1193\",\"rate\":\"5\",\"timeStamp\":\"978300760\",\"uid\":\"1\"}";

        String str1 = "[{\"movie\":\"1193\",\"rate\":\"5\",\"timeStamp\":\"978300760\",\"uid\":\"1\"},{\"movie\":\"661\",\"rate\":\"3\",\"timeStamp\":\"978302109\",\"uid\":\"1\"}]";


        MovieBean movieBean = Jackson.jsonToPojo(str,MovieBean.class);

        System.out.println("uid: " + movieBean.getUid());
        System.out.println("movie: " + movieBean.getMovie());
        System.out.println("rate: " + movieBean.getRate());
        System.out.println("time: " + movieBean.getTimeStamp());


        String string = Jackson.objectToJson(movieBean);

        System.out.println("result: " + string);

        List<MovieBean> list = Jackson.jsonToList(str1,MovieBean.class);

        System.out.println(list);


        //Json

        JSONObject jsonObject = JSONObject.parseObject(str);

        System.out.println("movie: " + jsonObject.getString("movie"));

        JSONArray jsonArray = JSONArray.parseArray(str1);

        for(int i=0; i<jsonArray.size(); i++)
        {
            JSONObject object = JSONObject.parseObject(jsonArray.get(i).toString());
            System.out.println(object.getString("movie"));
        }


        //URL Decode
        String str2 = "e93d03558.em21,/data/logs/ssjs.log,1545836401.903`2018-12-26T23:00:01+08:00`11.224.21.60"
            + "`ut2.shuqistat.com`-`POST /3.gif HTTP/1.1`200`185`-`shuqireader/4.0.7 (iPhone; iOS 12.1; Scale/2.00)"
            + "`-`-`adts=1545836401&ai=app_ydsc&apv=4.0.7.1&argnum=3&brd=Apple&ext=&fr=&imei"
            + "=4D0B42149CD7EDBA97D5A5092CA362DB78148980&ipli=111111&istsad=n&log=%7B%22app%22%3A%5B%7B%22arg2%22%3A"
            + "%5B%7B%22cid%22%3A%22723727%22%2C%22time%22%3A%5B%7B%22stime%22%3A%221545836395%22%2C%22etime%22%3A"
            + "%221545836396%22%7D%2C%7B%22stime%22%3A%221545836396%22%2C%22etime%22%3A%221545836401%22%7D%5D%7D%5D"
            + "%2C%22arg3%22%3A%22n%22%2C%22arg1%22%3A%226720460%22%7D%5D%7D&mnf=Apple&mod=iPhone8&msdk=8.0.0&pbd"
            + "=181205&plf=iOS&pli=111111&reqEncryptParam=%3A&reqEncryptType=-1&resEncryptType=-1&sdk=12.1&sn"
            + "=A57E8D9F48EF80BCF5EE07784772915956B52517&ts=1545836401&user_id=1084801556&wh=750x1334`0.001";

        try{

            String str3 = URLDecoder.decode(str2,"UTF-8");
            System.out.println(str3);

            String str4 = URLEncoder.encode(str3);
            System.out.println(URLDecoder.decode(str4));

            String str5 = "=";
            System.out.println(str5.replaceAll("=","%3d"));

            System.out.println();
            System.out.println();

            str2 = str2.replaceAll("=","%3d");

            System.out.println(URLDecoder.decode(str2.replaceAll("%3d","=")));

            System.out.println(str3.equals(URLDecoder.decode(str2.replaceAll("%3d","="))));

        }catch (Exception e)
        {
            e.printStackTrace();
        }





    }


}
