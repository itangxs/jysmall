package cn.qhjys.mall.entity;

import java.io.Serializable;

public class CommonInfo implements Serializable {
    private Long id;

    private String jkey;

    private String jvalue;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJkey() {
        return jkey;
    }

    public void setJkey(String jkey) {
        this.jkey = jkey == null ? null : jkey.trim();
    }

    public String getJvalue() {
        return jvalue;
    }

    public void setJvalue(String jvalue) {
        this.jvalue = jvalue == null ? null : jvalue.trim();
    }
}