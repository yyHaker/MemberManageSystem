package cn.com.shiro.exception;

public class UserExistException extends Exception {
	private static final long serialVersionUID = 7748640326885889227L;

	@Override
	public String getMessage() {
		return "该用户名已经存在";
	}
}
