package cn.qhjys.mall.entity;

import java.io.Serializable;

public class TaskAnswer implements Serializable {
    private Long id;

    private Long qyestionId;

    private Integer answerType;

    private String answerContent;

    private Integer isCorrect;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQyestionId() {
        return qyestionId;
    }

    public void setQyestionId(Long qyestionId) {
        this.qyestionId = qyestionId;
    }

    public Integer getAnswerType() {
        return answerType;
    }

    public void setAnswerType(Integer answerType) {
        this.answerType = answerType;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent == null ? null : answerContent.trim();
    }

    public Integer getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Integer isCorrect) {
        this.isCorrect = isCorrect;
    }
}