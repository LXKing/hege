package com.huak.org;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.huak.common.UUIDGenerator;
import com.huak.common.page.Convert;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.org.dao.BanDao;
import com.huak.org.model.Ban;

/**
 * 楼座service实现
 * @author Administrator
 *
 */
@Service
public class BanServiceImpl implements BanService {
	
	@Resource
	private CommunityService communityService;
	
	@Resource
	private BanDao banDao;

	/**
	 * 查询楼座列表信息
	 */
	@Override
	@Transactional
	public PageResult<Ban> list(Map<String, String> params,Page page) {
		PageHelper.startPage(page.getPageNumber(), page.getPageSize());
		return Convert.convert(banDao.selectByPage(params));
	}

	/**
	 * 新增楼座信息
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int save(Ban ban) {
		int result = 0;
		try {
			ban.setId(UUIDGenerator.getUUID());
			result = banDao.insertBan(ban);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	/**
	 * 修改楼座信息
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int edit(Ban ban) {
		int result = 0;
		try {
			result = banDao.updateBan(ban);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	/**
	 * 删除楼座信息
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int delete(String banId) {
		int result = 0;
		try {
			result = banDao.deleteBan(banId);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	/**
	 * 根据主键获取楼座信息
	 */
	@Override
	@Transactional
	public Ban get(String id) {
		return banDao.get(id);
	}

	/**
	 * 导出楼座信息
	 */
	@Override
	@Transactional
	public List<Map<String, Object>> exportBan(Map<String, String> param) {
		return banDao.export(param);
	}

	@Override
	@Transactional
	public String getCompanySelectHtmlStr(String selectedComId) {
		return communityService.getCompanySelectHtmlStr(selectedComId);
	}

	/**
	 * 查询小区下拉框html str
	 * @param param
	 * @return
	 */
	@Override
	@Transactional
	public String getCommunitySelectHtmlStr(Map<String, String> param,String selectedId) {
		return communityService.getCommunitySelectHtmlStr(param,selectedId);
	}

	/**
	 * 楼座名称唯一
	 */
	@Override
	@Transactional
	public Long checkBanName(Map<String,String> param) {
		return banDao.selectBanCount(param);
	}

}
