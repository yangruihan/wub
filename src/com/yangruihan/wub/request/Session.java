package com.yangruihan.wub.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Http Session类
 * @author Yrh
 *
 */
public class Session {
	
	/**
	 * 自身属性值
	 */
	private String id; // Id
	private Date creationTime; // 创建时间
	private Date lastAccessedTime; // 最后一次访问时间
	private int maxInactiveInterval; // 最大非活动时间
	private boolean invalidate; // 是否失效
	
	private Map<String, Object> attributes; // 属性
	
	/**
	 * Ctor.
	 * @param id
	 */
	public Session(String id) {
		this.id = id;
		// 更新创建时间和最后一次访问时间
		this.creationTime = new Date();
		this.lastAccessedTime = new Date();
		
		this.invalidate = false; // 设置失效为false
	}

	/**
	 * 得到会话创建时间
	 * @return
	 */
	public long getCreationTime() {
		return this.creationTime.getTime();
	}
	
	/**
	 * 得到会话 Id
	 * @return
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * 得到最后访问时间
	 * @return
	 */
	public long getLastAccessedTime() {
		return this.lastAccessedTime.getTime();
	}
	
	/**
	 * 设置最大非活动时间间隔
	 * @param interval
	 */
	public void setMaxInactiveInterval(int interval) {
		this.maxInactiveInterval = interval;
	}
	
	/**
	 * 得到最大非活动时间间隔
	 * @return
	 */
	public int getMaxInactiveInterval() {
		return this.maxInactiveInterval;
	}
	
	/**
	 * 得到一个属性
	 * @param name
	 * @return
	 */
	public Object getAttribute(String name) {
		return this.attributes.get(name);
	}
	
	/**
	 * 得到所有的属性名
	 * @return
	 */
	public List<String> getAttributeNames() {
		List<String> names = new ArrayList<>();
		
		for (String name : this.attributes.keySet()) {
			names.add(name);
		}
		
		return names;
	}
	
	/**
	 * 设置属性
	 * @param name
	 * @param value
	 */
	public void setAttribute(String name, Object value) {
		this.attributes.put(name, value);
	}
	
	/**
	 * 移除一个属性
	 * @param name
	 */
	public void removeAttribute(String name) {
		this.attributes.remove(name);
	}
	
	/**
	 * 失效
	 */
	public void invalidate() {
		this.invalidate = true;
	}
	
	/**
	 * 判断这个Session是不是一个新的
	 * @return
	 */
	public boolean isNew() {
		if (this.creationTime.getTime() == this.lastAccessedTime.getTime()) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断这个Session是否失效
	 * @return
	 */
	public boolean isInvalidate() {
		return this.invalidate;
	}
}
