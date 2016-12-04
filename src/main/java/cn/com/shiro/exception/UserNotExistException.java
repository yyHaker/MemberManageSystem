package cn.com.shiro.exception;

public class UserNotExistException extends Exception {
	private static final long serialVersionUID = -7916483515360305448L;

	@Override
	public String getMessage() {
		return "该用户不存在";
	}
}
