package ejiang.online.publicutils.bean;

import java.io.Serializable;

public class UserBean implements Serializable {
    //用户ID
    private Integer userId;
    //用户昵称
    private String nickName;
    //用户电话
    private String phone;
    //用户年龄
    private Integer age;
    //用户工龄
    private Integer workerAge;
    //用户头像
    private String headerUrl;
    //请求的TOKEN
    private String token;
    //权限列表
    private Integer[] auth;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getWorkerAge() {
        return workerAge;
    }

    public void setWorkerAge(Integer workerAge) {
        this.workerAge = workerAge;
    }

    public String getHeaderUrl() {
        return headerUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer[] getAuth() {
        return auth;
    }

    public void setAuth(Integer[] auth) {
        this.auth = auth;
    }

}
