package com.huak.common.utils;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Created by MR-BIN on 2017/7/12.
 */
public class HttpWeatherUtils {

    /**
     * 天气接口
     */
    public static String URL = "http://api.k780.com:88/";
    private static  String ACCEPT = "accept";
    private static  String CONNECT = "connection";
    private static  String USER_AGENT = "user-agent";
    private static  String ACCEPT_VALUE = "*/*";
    private static  String CONNECT_VALUE = "Keep-Alive";
    private static  String USER_AGENT_VALUE = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)";
    private static  String DATA_FORMAT = "format";
    private static  String APP = "app";
    private static  String WEAID = "weaid";
    private static  String APPKEY = "appkey";
    private static  String SIGN = "sign";
    /**
     * 使用Get方式获取数据
     *
     * @param url
     *            URL包括参数，http://HOST/XX?XX=XX&XXX=XXX
     * @param charset
     * @return
     */
    public static String sendGet(String url, String charset) {
        String result = "";
        BufferedReader in = null;

        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            connection.setConnectTimeout(30000);
            // 设置通用的请求属性
            connection.setRequestProperty(ACCEPT, ACCEPT_VALUE);
            connection.setRequestProperty(CONNECT,CONNECT_VALUE);
            connection.setRequestProperty(USER_AGENT, USER_AGENT_VALUE);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * POST请求，字符串形式数据
     *
     * @param url
     *            请求地址
     * @param param
     *            请求数据
     * @param charset
     *            编码方式
     */
    public static String sendPostUrl(String url, String param, String charset) {

        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty(ACCEPT, ACCEPT_VALUE);
            connection.setRequestProperty(CONNECT,CONNECT_VALUE);
            connection.setRequestProperty(USER_AGENT, USER_AGENT_VALUE);
            // 发送POST请求必须设置如下两行
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setConnectTimeout(30000);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(connection.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * POST请求，Map形式数据
     *
     * @param url
     *            请求地址
     * @param param
     *            请求数据
     * @param charset
     *            编码方式
     */
    public static String sendPost(String url, Map<String, String> param, String charset) {

        StringBuffer buffer = new StringBuffer();
        if (param != null && !param.isEmpty()) {
            for (Map.Entry<String, String> entry : param.entrySet()) {
                buffer.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue())).append("&");

            }
        }
        buffer.deleteCharAt(buffer.length() - 1);

        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty(ACCEPT, ACCEPT_VALUE);
            conn.setRequestProperty(CONNECT,CONNECT_VALUE);
            conn.setRequestProperty(USER_AGENT, USER_AGENT_VALUE);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(buffer);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
    /**
     * POST请求，Map形式数据
     *
     * @param url
     *            请求地址
     * @param param
     *            请求数据
     * @param charset
     *            编码方式
     */
    public static String sendWorkPost(String url, String charset) {

        StringBuffer buffer = new StringBuffer();

        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty(ACCEPT, ACCEPT_VALUE);
            conn.setRequestProperty(CONNECT,CONNECT_VALUE);
            conn.setRequestProperty(USER_AGENT, USER_AGENT_VALUE);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(buffer);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
    /**
     *
     * @param cityName 城市名字
     * @param format 返回格式  json或xml  默认json
     * @return 七天内天气情况
     */
    public static  String getWeatherFuture(String cityName , String format){
        Map<String , String > param = new HashMap<String , String>();
        if(null != cityName && !cityName.equals("") ){
            param.put("app", "weather.future");
            param.put("weaid", cityName);
            param.put("appkey", "10003");
            param.put("sign", "b59bc3ef6191eb9f747dd4e83c99f2a4");
            if(format.equals("xml")){
                param.put(DATA_FORMAT, format);
            }else{
                param.put(DATA_FORMAT, "json");
            }

        }else{
            return "城市名字为空  ！ " ;
        }
        return sendPost(URL, param, "utf-8") ;
    }
    /**
     *
     * @param weatherid 城市名字
     * @param format 返回格式  json或xml  默认json
     * @return 当天天气情况
     */
    public static  String getCurrtentWeather(String weatherid , String format){
        Map<String , String > param = new HashMap<String , String>();
        if(null != weatherid && !weatherid.equals("") ){
            param.put(APP, "weather.today");
            param.put(WEAID, weatherid);
            param.put(APPKEY, "10003");
            param.put(SIGN, "b59bc3ef6191eb9f747dd4e83c99f2a4");
            if(format.equals("xml")){
                param.put(DATA_FORMAT, format);
            }else{
                param.put(DATA_FORMAT, "json");
            }

        }else{
            return "城市名字为空  ！ " ;
        }
        return sendPost(URL, param, "utf-8") ;
    }


    /**
     *
     * @param weatherid 城市名字
     * @param format 返回格式  json或xml  默认json
     * @return 当天天气情况
     */
    public static  String getCurrentAQI(String weatherid , String format){
        Map<String , String > param = new HashMap<String , String>();
        if(null != weatherid && !weatherid.equals("") ){
            param.put(APP, "weather.pm25");
            param.put(WEAID, weatherid);
            param.put(APPKEY, "10003");
            param.put(SIGN, "b59bc3ef6191eb9f747dd4e83c99f2a4");
            if(format.equals("xml")){
                param.put(DATA_FORMAT, format);
            }else{
                param.put(DATA_FORMAT, "json");
            }

        }else{
            return "城市名字为空  ！ " ;
        }
        return sendPost(URL, param, "utf-8") ;
    }

    /**
     * 工单消息推送
     * @param id
     * @param url
     * @return
     */
    public static  String sendWorkOrderMsg(String id , String url){
        return sendWorkPost(url + id, "utf-8") ;
    }

}
