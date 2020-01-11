package com.bawei6.basemodule.bean;

public class FindGroupBean {

    /**
     * code : 200
     * data : {"id":60,"groupname":"爱狗人士交流群","groupnumber":"2153572","groupdesc":"1","createor":"xiaoxingxing","grouptypeid":1,"grouptypename":"这是全1705A最秀的群","grouptypeimg":"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=280218504,2820617605&fm=26&gp=0.jpg","groupimg":"1"}
     * msg : 操作成功
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
         * id : 60
         * groupname : 爱狗人士交流群
         * groupnumber : 2153572
         * groupdesc : 1
         * createor : xiaoxingxing
         * grouptypeid : 1
         * grouptypename : 这是全1705A最秀的群
         * grouptypeimg : https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=280218504,2820617605&fm=26&gp=0.jpg
         * groupimg : 1
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
