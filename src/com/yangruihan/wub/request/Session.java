package com.yangruihan.wub.request;

import java.util.List;

/**
 * 会话接口
 * @author Yrh
 *
 */
public interface Session {

	/**
	 * 得到会话创建时间
	 * @return
	 */
	long getCreationTime();
	
	/**
	 * 得到会话 Id
	 * @return
	 */
	String getId();
	
	/**
	 * 得到最后访问时间
	 * @return
	 */
	long getLastAccessedTime();
	
	/**
	 * 设置最大非活动时间间隔
	 * @param interval
	 */
	void setMaxInactiveInterval(int interval);
	
	/**
	 * 得到最大非活动时间间隔
	 * @return
	 */
	int getMaxInactiveInterval();
	
	/**
	 * 得到一个属性
	 * @param name
	 * @return
	 */
	Object getAttribute(String name);
	
	/**
	 * 得到所有的属性名
	 * @return
	 */
	List<String> getAttributeNames();
	
	/**
	 * 设置属性
	 * @param name
	 * @param value
	 */
	void setAttribute(String name, Object value);
	
	/**
	 * 移除一个属性
	 * @param name
	 */
	public void removeAttribute(String name);
	
	/**
	 * 失效
	 */
	public void invalidate();
	
	/**
	 * 判断这个Session是不是一个新的
	 * @return
	 */
	public boolean isNew();
}
