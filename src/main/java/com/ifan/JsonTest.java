package com.ifan;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonTest {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String objectToJson(Object data)
    {
        try{
            //使用jackson的ObjectMapper的writeValueAsString方法可以把pojo类输出成json字符串
            return MAPPER.writeValueAsString(data);
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    //Pojo 普通Java类
    public static <T> T jsonToPojo(String data, Class<T> beanType)
    {
        try{
            //使用jackson的ObjectMapper的readValue方法可以把json字符串输出成pojo类
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

        try{
            return MAPPER.readValue(data,javaType);
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String str = "{\"movie\":\"1193\",\"rate\":\"5\",\"timeStamp\":\"978300760\",\"uid\":\"1\"}";
        String str2 = "[{\"movie\":\"1193\",\"rate\":\"5\",\"timeStamp\":\"978300760\",\"uid\":\"1\"},{\"movie\":\"661\",\"rate\":\"3\",\"timeStamp\":\"978302109\",\"uid\":\"1\"}]";

        MovieBean movieBean = JsonTest.jsonToPojo(str,MovieBean.class);

        String str1 = JsonTest.objectToJson(movieBean);

        List<MovieBean> list = JsonTest.jsonToList(str2,MovieBean.class);


        System.out.println(movieBean);
        System.out.println(str1);
        System.out.println(list.get(0).getMovie());
    }
}
