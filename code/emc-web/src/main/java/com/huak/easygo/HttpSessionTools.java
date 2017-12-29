package com.huak.easygo;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class HttpSessionTools {
	/**
	 * 获取RSESSIONID
	 * @author ID
	 * @Date 2017年4月6日 下午3:24:34
	 * @version 1.0.0
	 * @param HttpServletRequest
	 * @return String
	 */
	public static String getRSessionId(HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if ("RSESSIONID".equals(cookie.getName())) {
				return cookie.getValue();
			}
		}
		throw new Exception("RSESSIONID在Redis中未找到！");
	}
	
	/**
	 * 通过HttpServletRequest 获取登录的session信息
	 * @author IG
	 * @Date 2017年4月6日 下午4:08:10
	 * @version 1.0.0
	 * @param request
	 * @return Map
	 */
	public static Map<String, String> getSessionInfo(HttpServletRequest request) throws Exception {
		JedisPool pool = RedisTools.getPool();
		Jedis jedis = pool.getResource();
		Map<String, String> map = jedis.hgetAll(getRSessionId(request));
		pool.returnBrokenResource(jedis);
		return map;
	}
	
	/**
	 * 通过rsessionID 获取登录的session信息
	 * @author IG
	 * @Date 2017年4月6日 下午4:08:50
	 * @version 1.0.0
	 * @param rsessionid
	 * @return Map
	 */
	public static Map<String, String> getSessionInfo(String rsessionid) {
		JedisPool pool = RedisTools.getPool();
		Jedis jedis = pool.getResource();
		Map<String, String> map = jedis.hgetAll(rsessionid);
		pool.returnBrokenResource(jedis);
		return map;
	}
	
	public static void setSessionInfo(HttpServletResponse response, Map<String, String> map) {
		/**
		 * 获取redis连接
		 */
		
		JedisPool pool = RedisTools.getPool();
		Jedis jedis = pool.getResource();
		/**
		 * 通过UUID方式生成redis-key 和 会话cookie-key
		 * 首先生成一个，并判断redis里面是否存在，如果存在，从新生成UUID
		 */
		String rsessionid = null;
		do {
			rsessionid = UUID.randomUUID().toString();
		} while (jedis.exists(rsessionid));
		/**
		 * 保存用户信息到redis
		 */
		jedis.hmset(rsessionid, map);
		jedis.expire(rsessionid, 3600);
		/**
		 * 释放资源到连接池
		 */
		pool.returnBrokenResource(jedis);
		/**
		 * 向浏览器写入会话cookie
		 */
		Cookie cookie = new Cookie("RSESSIONID", rsessionid);
		cookie.setMaxAge(3600);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
}
