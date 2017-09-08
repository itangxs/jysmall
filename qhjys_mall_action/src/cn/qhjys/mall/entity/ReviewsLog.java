package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.util.Date;

public class ReviewsLog implements Serializable {
    private Long id;

    private Long detailId;

    private Long storeId;

    private Long prodId;

    private Long replyId;

    private Integer score;

    private String tag;

    private String reviews;

    private String images;

    private Integer anonymous;

    private String ip;

    private Date createTime;

    private Date sellerReplyTime;

    private String sellerReplyContent;

    private Integer enabled;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getProdId() {
        return prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews == null ? null : reviews.trim();
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    public Integer getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(Integer anonymous) {
        this.anonymous = anonymous;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getSellerReplyTime() {
        return sellerReplyTime;
    }

    public void setSellerReplyTime(Date sellerReplyTime) {
        this.sellerReplyTime = sellerReplyTime;
    }

    public String getSellerReplyContent() {
        return sellerReplyContent;
    }

    public void setSellerReplyContent(String sellerReplyContent) {
        this.sellerReplyContent = sellerReplyContent == null ? null : sellerReplyContent.trim();
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }
}