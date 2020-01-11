package com.bawei6.basemodule.bean;

import java.util.List;

public class FindGroupNameBean {


    /**
     * code : 200
     * data : [{"id":72,"groupname":"yxh伐木累","groupnumber":"3208566","groupdesc":"XXXXXXXXXXX","createor":"13131856542","grouptypeid":1,"grouptypename":"娱乐","grouptypeimg":"","groupimg":""}]
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
         * id : 72
         * groupname : yxh伐木累
         * groupnumber : 3208566
         * groupdesc : XXXXXXXXXXX
         * createor : 13131856542
         * grouptypeid : 1
         * grouptypename : 娱乐
         * grouptypeimg :
         * groupimg :
         */

        private int id;
        private String groupname;
        private String groupnumber;
        private String groupdesc;
        private String createor;
        private int grouptypeid;
        private String grouptypename;
        private String grouptypeimg;
        private String groupimg;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGroupname() {
            return groupname;
        }

        public void setGroupname(String groupname) {
            this.groupname = groupname;
        }

        public String getGroupnumber() {
            return groupnumber;
        }

        public void setGroupnumber(String groupnumber) {
            this.groupnumber = groupnumber;
        }

        public String getGroupdesc() {
            return groupdesc;
        }

        public void setGroupdesc(String groupdesc) {
            this.groupdesc = groupdesc;
        }

        public String getCreateor() {
            return createor;
        }

        public void setCreateor(String createor) {
            this.createor = createor;
        }

        public int getGrouptypeid() {
            return grouptypeid;
        }

        public void setGrouptypeid(int grouptypeid) {
            this.grouptypeid = grouptypeid;
        }

        public String getGrouptypename() {
            return grouptypename;
        }

        public void setGrouptypename(String grouptypename) {
            this.grouptypename = grouptypename;
        }

        public String getGrouptypeimg() {
            return grouptypeimg;
        }

        public void setGrouptypeimg(String grouptypeimg) {
            this.grouptypeimg = grouptypeimg;
        }

        public String getGroupimg() {
            return groupimg;
        }

        public void setGroupimg(String groupimg) {
            this.groupimg = groupimg;
        }
    }
}
