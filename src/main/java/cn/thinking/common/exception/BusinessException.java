package cn.thinking.common.exception;

/**
 * 
 * Description: 系统业务异常
 * 
 * Department: 沈阳开发二部 
 * @author liu.jia_neu
 * @update [修改人] [修改时间]
 * @version 1.0
 *
 */
public class BusinessException extends RuntimeException {

	/** serialVersionUID */
	private static final long serialVersionUID = 2332608236621015980L;

	private String code;

	public BusinessException() {
		super();
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String code, String message) {
		super(message);
		this.code = code;
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
