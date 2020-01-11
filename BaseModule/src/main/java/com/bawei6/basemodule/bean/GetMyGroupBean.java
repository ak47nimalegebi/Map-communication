package com.bawei6.basemodule.bean;

import java.util.List;

public class GetMyGroupBean {

    /**
     * code : 200
     * data : [{"id":76,"groupname":"jojo","groupnumber":"8788216","groupdesc":"啊啊啊啊啊","createor":"sample string 5","grouptypeid":6,"grouptypename":"sample string 7","grouptypeimg":"sample string 8","groupimg":"sample string 9"},{"id":60,"groupname":"爱狗人士交流群","groupnumber":"2153572","groupdesc":"1","createor":"xiaoxingxing","grouptypeid":1,"grouptypename":"这是全1705A最秀的群","grouptypeimg":"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=280218504,2820617605&fm=26&gp=0.jpg","groupimg":"1"},{"id":58,"groupname":"康博哥哥","groupnumber":"9749077","groupdesc":"康博哥哥","createor":"b3481cf9c4eb4174a070f9c701575df4","grouptypeid":0,"grouptypename":"","grouptypeimg":"","groupimg":"http://pic4.zhimg.com/50/v2-848b1a190d937e270e8d062d00865493_hd.jpg"}]
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
         * id : 76
         * groupname : jojo
         * groupnumber : 8788216
         * groupdesc : 啊啊啊啊啊
         * createor : sample string 5
         * grouptypeid : 6
         * grouptypename : sample string 7
         * grouptypeimg : sample string 8
         * groupimg : sample string 9
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
