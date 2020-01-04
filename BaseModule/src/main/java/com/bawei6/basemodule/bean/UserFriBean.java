package com.bawei6.basemodule.bean;

import java.util.List;

public class UserFriBean {

    /**
     * code : 200
     * data : [{"id":44,"usercode":"0b5a4c7711d7414991c245e1ef3448a3","username":"123w","pwd":null,"sex":"1","birthday":"1","headerimg":"1","nick":"1","utype":8,"imuseraccount":"1","signdesc":"1","openlocation":0,"openmsgalert":3},{"id":44,"usercode":"0b5a4c7711d7414991c245e1ef3448a3","username":"123w","pwd":null,"sex":"1","birthday":"1","headerimg":"1","nick":"1","utype":8,"imuseraccount":"1","signdesc":"1","openlocation":0,"openmsgalert":3},{"id":44,"usercode":"0b5a4c7711d7414991c245e1ef3448a3","username":"123w","pwd":null,"sex":"1","birthday":"1","headerimg":"1","nick":"1","utype":8,"imuseraccount":"1","signdesc":"1","openlocation":0,"openmsgalert":3},{"id":44,"usercode":"0b5a4c7711d7414991c245e1ef3448a3","username":"123w","pwd":null,"sex":"1","birthday":"1","headerimg":"1","nick":"1","utype":8,"imuseraccount":"1","signdesc":"1","openlocation":0,"openmsgalert":3},{"id":46,"usercode":"a3a20356b8f9476294979476dd0fccfc","username":"159","pwd":null,"sex":"1","birthday":"sample string 6","headerimg":"sample string 7","nick":"sample string 8","utype":9,"imuseraccount":"sample string 10","signdesc":"sample string 11","openlocation":0,"openmsgalert":3},{"id":46,"usercode":"a3a20356b8f9476294979476dd0fccfc","username":"159","pwd":null,"sex":"1","birthday":"sample string 6","headerimg":"sample string 7","nick":"sample string 8","utype":9,"imuseraccount":"sample string 10","signdesc":"sample string 11","openlocation":0,"openmsgalert":3},{"id":46,"usercode":"a3a20356b8f9476294979476dd0fccfc","username":"159","pwd":null,"sex":"1","birthday":"sample string 6","headerimg":"sample string 7","nick":"sample string 8","utype":9,"imuseraccount":"sample string 10","signdesc":"sample string 11","openlocation":0,"openmsgalert":3},{"id":46,"usercode":"a3a20356b8f9476294979476dd0fccfc","username":"159","pwd":null,"sex":"1","birthday":"sample string 6","headerimg":"sample string 7","nick":"sample string 8","utype":9,"imuseraccount":"sample string 10","signdesc":"sample string 11","openlocation":0,"openmsgalert":3},{"id":46,"usercode":"a3a20356b8f9476294979476dd0fccfc","username":"159","pwd":null,"sex":"1","birthday":"sample string 6","headerimg":"sample string 7","nick":"sample string 8","utype":9,"imuseraccount":"sample string 10","signdesc":"sample string 11","openlocation":0,"openmsgalert":3},{"id":46,"usercode":"a3a20356b8f9476294979476dd0fccfc","username":"159","pwd":null,"sex":"1","birthday":"sample string 6","headerimg":"sample string 7","nick":"sample string 8","utype":9,"imuseraccount":"sample string 10","signdesc":"sample string 11","openlocation":0,"openmsgalert":3},{"id":48,"usercode":"6684536f04d84ee2a7addd5199f88b41","username":"111111","pwd":null,"sex":"1","birthday":"sample string 6","headerimg":"sample string 7","nick":"sample string 8","utype":9,"imuseraccount":"sample string 10","signdesc":"sample string 11","openlocation":0,"openmsgalert":3},{"id":48,"usercode":"6684536f04d84ee2a7addd5199f88b41","username":"111111","pwd":null,"sex":"1","birthday":"sample string 6","headerimg":"sample string 7","nick":"sample string 8","utype":9,"imuseraccount":"sample string 10","signdesc":"sample string 11","openlocation":0,"openmsgalert":3},{"id":48,"usercode":"6684536f04d84ee2a7addd5199f88b41","username":"111111","pwd":null,"sex":"1","birthday":"sample string 6","headerimg":"sample string 7","nick":"sample string 8","utype":9,"imuseraccount":"sample string 10","signdesc":"sample string 11","openlocation":0,"openmsgalert":3}]
     * msg : 操作成功
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 44
         * usercode : 0b5a4c7711d7414991c245e1ef3448a3
         * username : 123w
         * pwd : null
         * sex : 1
         * birthday : 1
         * headerimg : 1
         * nick : 1
         * utype : 8
         * imuseraccount : 1
         * signdesc : 1
         * openlocation : 0
         * openmsgalert : 3
         */

        private int id;
        private String usercode;
        private String username;
        private Object pwd;
        private String sex;
        private String birthday;
        private String headerimg;
        private String nick;
        private int utype;
        private String imuseraccount;
        private String signdesc;
        private int openlocation;
        private int openmsgalert;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsercode() {
            return usercode;
        }

        public void setUsercode(String usercode) {
            this.usercode = usercode;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Object getPwd() {
            return pwd;
        }

        public void setPwd(Object pwd) {
            this.pwd = pwd;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getHeaderimg() {
            return headerimg;
        }

        public void setHeaderimg(String headerimg) {
            this.headerimg = headerimg;
        }

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public int getUtype() {
            return utype;
        }

        public void setUtype(int utype) {
            this.utype = utype;
        }

        public String getImuseraccount() {
            return imuseraccount;
        }

        public void setImuseraccount(String imuseraccount) {
            this.imuseraccount = imuseraccount;
        }

        public String getSigndesc() {
            return signdesc;
        }

        public void setSigndesc(String signdesc) {
            this.signdesc = signdesc;
        }

        public int getOpenlocation() {
            return openlocation;
        }

        public void setOpenlocation(int openlocation) {
            this.openlocation = openlocation;
        }

        public int getOpenmsgalert() {
            return openmsgalert;
        }

        public void setOpenmsgalert(int openmsgalert) {
            this.openmsgalert = openmsgalert;
        }
    }
}
