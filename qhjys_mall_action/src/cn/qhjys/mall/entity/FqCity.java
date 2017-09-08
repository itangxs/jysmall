package cn.qhjys.mall.entity;

import java.io.Serializable;

public class FqCity implements Serializable {
    private Long id;

    private String city;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }
}