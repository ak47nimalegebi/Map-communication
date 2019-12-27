package com.bawei6.basemodule.bean;

public class TokenBean {


    /**
     * access_token : 6UlwI33QpcUxX_UVJSmVLjdz8Vde6jUyBJosfIJdoidP_HIlx2hcpJeEWkX4RK2aZHubRgXHzyXKkF4xaGtjmrbYDWzDSq44Kw20e3BE6rCLdZxPt7Ai5ewoqoVtQcKpq4Ki8gNIYCN4Ob82MebWnRay_3u7-9a3ngtPQWJ2fuavy9m0WLlPEL_h7C2byk6FyiLCE9JsI9pmow3SAbJ57zvsDz9ozwUNuRmIXoFzBbC79YvAHFLsLacJOfHpZWcSi2jrkWE1HmoK3n8Aa0oNEeLXEbusUadSGhugdJXUqEw
     * token_type : bearer
     * expires_in : 86399
     */

    private String access_token;
    private String token_type;
    private int expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
}
