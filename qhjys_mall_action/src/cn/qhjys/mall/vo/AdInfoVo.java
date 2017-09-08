package cn.qhjys.mall.vo;

import cn.qhjys.mall.common.BaseVo;

/***
 * 广告vo
 * @author zengrong
 *
 */
public class AdInfoVo extends BaseVo{

	private Long id;//广告编号
	private String name;//广告名称
	private String link;//广告链接地址
	private String code;//广告链接表现：文字广告就是文字，图片和flash就是它们的地址，代码广告就是代码内容
	private Long positionId;//广告位置编号
	private Integer mediaType;//广告类型
	private String startTime;//广告开始时间
	private String endTime;//广告结束时间
	private Long clickCount;//广告点击次数
	private Integer enabled;//广告是否启用
	private String positionName;//广告位名称
	private Integer positionType;//广告位置类型
	private Integer width;//广告位宽度
	private Integer hight;//广告位高度
	private String desc;//广告位描述
	private String positionStyle;//广告位模板代码
	private Integer positionEnabled;//广告位是否启用
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getPositionId() {
		return positionId;
	}
	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}
	public Integer getMediaType() {
		return mediaType;
	}
	public void setMediaType(Integer mediaType) {
		this.mediaType = mediaType;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Long getClickCount() {
		return clickCount;
	}
	public void setClickCount(Long clickCount) {
		this.clickCount = clickCount;
	}
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public Integer getPositionType() {
		return positionType;
	}
	public void setPositionType(Integer positionType) {
		this.positionType = positionType;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHight() {
		return hight;
	}
	public void setHight(Integer hight) {
		this.hight = hight;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getPositionStyle() {
		return positionStyle;
	}
	public void setPositionStyle(String positionStyle) {
		this.positionStyle = positionStyle;
	}
	public Integer getPositionEnabled() {
		return positionEnabled;
	}
	public void setPositionEnabled(Integer positionEnabled) {
		this.positionEnabled = positionEnabled;
	}
	
}
