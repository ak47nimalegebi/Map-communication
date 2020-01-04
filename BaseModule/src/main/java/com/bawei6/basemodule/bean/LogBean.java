package com.bawei6.basemodule.bean;

public class LogBean {


    /**
     * code : 200
     * data : {"id":6,"usercode":"4cd6bba4e59d47bdb94a59d69e04b69c","username":"1","pwd":null,"sex":"1","birthday":"1","headerimg":"1","nick":"1","utype":9,"imuseraccount":"1","signdesc":"1","openlocation":0,"openmsgalert":3}
     * msg : 用户登录成功
     */

    private int code;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * id : 6
         * usercode : 4cd6bba4e59d47bdb94a59d69e04b69c
         * username : 1
         * pwd : null
         * sex : 1
         * birthday : 1
         * headerimg : 1
         * nick : 1
         * utype : 9
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
