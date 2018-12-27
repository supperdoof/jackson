package com.ifan;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    public static void main(String[] args) {
        String str = "{\"movie\":\"1193\",\"rate\":\"5\",\"timeStamp\":\"978300760\",\"uid\":\"1\"}";


        MovieBean movieBean = Jackson.jsonToPojo(str,MovieBean.class);

        System.out.println("uid: " + movieBean.getUid());
        System.out.println("movie: " + movieBean.getMovie());
        System.out.println("rate: " + movieBean.getRate());
        System.out.println("time: " + movieBean.getTimeStamp());


        String string = Jackson.objectToJson(movieBean);

        System.out.println("result: " + string);




    }


}
