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
import com.huak.org.dao.CellDao;
import com.huak.org.model.Cell;

/**
 * 单元service实现
 * @author Administrator
 *
 */
@Service
public class CellServiceImpl implements CellService {
	
	@Resource
	private CommunityService communityService;
	
	@Resource
	private CellDao cellDao;

	/**
	 * 查询单元列表信息
	 */
	@Override
    @Transactional(readOnly = true)
	public PageResult<Cell> list(Map<String, String> params,Page page) {
		PageHelper.startPage(page.getPageNumber(), page.getPageSize());
		return Convert.convert(cellDao.selectByPage(params));
	}

	/**
	 * 新增单元信息
	 */
	@Override
    @Transactional(readOnly = false)
	public int save(Cell cell) {
		int result = 0;
		try {
			cell.setId(UUIDGenerator.getUUID());
			result = cellDao.insertCell(cell);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	/**
	 * 修改单元信息
	 */
	@Override
    @Transactional(readOnly = false)
	public int edit(Cell cell) {
		int result = 0;
		try {
			result = cellDao.updateCell(cell);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	/**
	 * 删除单元信息
	 */
	@Override
    @Transactional(readOnly = false)
	public int delete(String cellId) {
		int result = 0;
		try {
			result = cellDao.deleteCell(cellId);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	/**
	 * 根据主键获取单元信息
	 */
	@Override
    @Transactional(readOnly = true)
	public Cell get(String id) {
		return cellDao.get(id);
	}

	/**
	 * 导出单元信息
	 */
	@Override
    @Transactional(readOnly = true)
	public List<Map<String, Object>> exportCell(Map<String, String> param) {
		return cellDao.export(param);
	}

	@Override
    @Transactional(readOnly = true)
	public String getCompanySelectHtmlStr(String selectedComId) {
		return communityService.getCompanySelectHtmlStr(selectedComId);
	}

	@Override
    @Transactional(readOnly = true)
	public String getCommunitySelectHtmlStr(Map<String, String> param, String selectedCommunityId) {
		return communityService.getCommunitySelectHtmlStr(param, selectedCommunityId);
	}
	
	@Override
    @Transactional(readOnly = true)
	public String getBanSelectHtmlStr(Map<String, String> param, String selectBanId) {
		return communityService.getBanSelectHtmlStr(param, selectBanId);
	}

	/**
	 * 单元名称唯一
	 */
	@Override
    @Transactional(readOnly = true)
	public Long checkCellName(Map<String,String> param) {
		return cellDao.selectCellCount(param);
	}

}
