package cn.qhjys.mall.util;

public enum FileState {
	UPLOAD_SUCCSSS(0, "操作文件成功！"), UPLOAD_FAILURE(1, "操作文件失败！"), UPLOAD_TYPE_ERROR(2, "文件类型错误！"), UPLOAD_OVERSIZE(3,
			"上传文件过大！"), UPLOAD_ZEROSIZE(4, "上传文件为空！"), UPLOAD_NOTFOUND(5, "文件路径错误！"), UPLOAD_NOT_LOGIN(6, "当前未登录用户！"), UPLOAD_NOT_AUTH(
			7, "当前用户没有权限！");

	private String state;
	private int flag;

	public String getState() {
		return this.state;
	}

	public int getFlag() {
		return this.flag;
	}

	FileState(int flag, String state) {
		this.state = state;
		this.flag = flag;
	}
}