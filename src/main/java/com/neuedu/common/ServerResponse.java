package com.neuedu.common;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.xml.internal.ws.developer.Serialization;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> {

    private int status;
    private String msg;
    private T data;

    private ServerResponse() {

    }
    private ServerResponse(int status,T data, String msg) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    private ServerResponse(int status){
        this.status = status;

    }
    private ServerResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }
    private ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }


    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }


    /**
     * 成功时的方法
     * */
    public static ServerResponse serverResponseBySuccess(){
        return new ServerResponse(StatusCode.SUCCESS);
    }

    public static <T> ServerResponse serverResponseBySuccess(T data){
        return new ServerResponse(StatusCode.SUCCESS,data);
    }

    public static <T> ServerResponse serverResponseBySuccess(int status,T data,String msg){
        return new ServerResponse(StatusCode.SUCCESS,data,msg);
    }
    public static <T> ServerResponse serverResponseBySuccess(int status,String msg){
        return new ServerResponse(StatusCode.SUCCESS,msg);
    }

    /**
     * 失败时返回前端的方法
     * */
    public static ServerResponse serverResponseByError(){
        return new ServerResponse(StatusCode.ERROR);
    }
    public static ServerResponse serverResponseByError(String msg){
        return new ServerResponse(StatusCode.ERROR,msg);
    }
    public static ServerResponse serverResponseByError(int status){
        return new ServerResponse(status);
    }
    public static ServerResponse serverResponseByError(int status,String msg){
        return new ServerResponse(status,msg);
    }

    /**
     * 判断接口是否返回成功
     * */
    @JsonIgnore
    public boolean isSuccess(){
        return this.status==StatusCode.SUCCESS;
    }



}
