package com.yangruihan.wub.request;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Locale;

/**
 * Http Cookie类
 * 
 * @author Yrh
 *
 */
public class Cookie implements Cloneable, Serializable {

	private static final long serialVersionUID = 6582924222216744457L;
	
	/**
	 * Cookie 本身的值
	 */
	private String name;
	private String value;
	
    //
	// 编码在头部的Cookie域参数
    //
    private String comment;	// ;Comment=VALUE ... describes cookie's use
				// ;Discard ... implied by maxAge < 0
    private String domain;	// ;Domain=VALUE ... domain that sees cookie
    private int maxAge = -1;	// ;Max-Age=VALUE ... cookies auto-expire
    private String path;	// ;Path=VALUE ... URLs that see the cookie
    private boolean secure;	// ;Secure ... e.g. use SSL
    private int version = 0;	// ;Version=1 ... means RFC 2109++ style
    private boolean isHttpOnly = false;

	/**
	 * Ctor.
	 * 
	 * @param name
	 * @param value
	 */
	public Cookie(String name, String value) {
		if (name == null || name.length() == 0) {
			throw new IllegalArgumentException("Cookie's name cannot be empty!");
		}
		if (name.equalsIgnoreCase("Comment") || // rfc2019
			name.equalsIgnoreCase("Discard") || // 2019++
			name.equalsIgnoreCase("Domain") || 
			name.equalsIgnoreCase("Expires") || // (old cookies)
			name.equalsIgnoreCase("Max-Age") || // rfc2019
			name.equalsIgnoreCase("Path") || 
			name.equalsIgnoreCase("Secure") || 
			name.equalsIgnoreCase("Version") || 
			name.startsWith("$")) {
			String errMsg = "Cookies's name error: ";
			Object[] errArgs = new Object[1];
			errArgs[0] = name;
			errMsg = MessageFormat.format(errMsg, errArgs);
			throw new IllegalArgumentException(errMsg);
		}

		this.name = name;
		this.value = value;
	}
	
	/**
	 * 指定这个Cookie特殊的目的
	 * @param purpose
	 */
	public void setComment(String purpose) {
        comment = purpose;
    }
	
	/**
	 * 得到这个Cookie的特殊目的
	 * @return
	 */
	public String getComment() {
        return comment;
    }
	
	/**
	 * 设置表现域
	 * @param domain
	 */
	public void setDomain(String domain) {
        this.domain = domain.toLowerCase(Locale.ENGLISH); // IE allegedly needs this
    }
	
	/**
	 * 得到表现域
	 * @return
	 */
	public String getDomain() {
        return domain;
    }
	
	/**
	 * 设置最大存活时间
	 * @param expiry
	 */
	public void setMaxAge(int expiry) {
        maxAge = expiry;
    }
	
	/**
	 * 得到最大存活时间
	 * @return
	 */
	public int getMaxAge() {
        return maxAge;
    }
	
	/**
	 * 为Cookie指定一个路径，表明哪一个客户端需要返回这个Cookie
	 * @param uri
	 */
	public void setPath(String uri) {
        path = uri;
    }
	
	/**
	 * 得到路径
	 * @return
	 */
	public String getPath() {
        return path;
    }

	/**
	 * 设置浏览器是否需要使用一个安全的协议传送Cookie
	 * @param flag
	 */
	public void setSecure(boolean flag) {
        secure = flag;
    }
	
	/**
	 * 表明浏览器是否需要使用一个安全的协议传送Cookie
	 * @return true 使用， false 不适用
	 */
	public boolean getSecure() {
        return secure;
    }
	
	/**
	 * 得到Cookie的名字
	 * @return
	 */
	public String getName() {
        return name;
    }
	
	/**
	 * 设置值
	 * @param newValue
	 */
	public void setValue(String newValue) {
        value = newValue;
    }
	
	/**
	 * 得到值
	 * @return
	 */
	public String getValue() {
        return value;
    }
	
	/**
	 * 设置版本信息
	 * @param v
	 */
	public void setVersion(int v) {
        version = v;
    }
	
	/**
	 * 得到版本信息
	 * @return
	 */
	public int getVersion() {
        return version;
    }
	
	/**
	 * 标记是否 HttpOnly
	 * @param isHttpOnly
	 */
	public void setHttpOnly(boolean isHttpOnly) {
        this.isHttpOnly = isHttpOnly;
    }
	
	/**
	 * 得到是否 HttpOnly
	 * @return
	 */
	public boolean isHttpOnly() {
        return isHttpOnly;
    }
	
	@Override
	public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
