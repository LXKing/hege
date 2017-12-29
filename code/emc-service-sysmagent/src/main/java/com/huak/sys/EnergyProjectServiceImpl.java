package com.huak.sys;

import com.github.pagehelper.PageHelper;
import com.huak.common.page.Convert;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.sys.dao.EnergyProjectDao;
import com.huak.sys.model.EnergyProject;
import com.huak.sys.model.EnergyProjectVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class EnergyProjectServiceImpl implements EnergyProjectService {
	
    @Resource
    private EnergyProjectDao energyProjectDao;

    /**
     * 分页查询
     */
	@Override
    @Transactional(readOnly = true)
	public PageResult<EnergyProjectVo> list(
			Map<String, Object> paramsMap, Page page) {
		PageHelper.startPage(page.getPageNumber(), page.getPageSize());
		return Convert.convert(energyProjectDao.selectPageByMap(paramsMap));
	}

	/**
     * 新增
     */
	@Override
    @Transactional(readOnly = false)
	public int save(EnergyProject energyProject) {
		int result = 0;
		try {
			result = energyProjectDao.insert(energyProject);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	/**
     * 根据主键查询
     */
	@Override
	@Transactional(readOnly=true)
	public EnergyProject get(String id) {
		return energyProjectDao.get(id);
	}

	/**
     * 修改
     */
	@Override
    @Transactional(readOnly = false)
	public int edit(EnergyProject energyProject) {
		int result = 0;
		try {
			result = energyProjectDao.update(energyProject);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	/**
     * 删除
     */
	@Override
    @Transactional(readOnly = false)
	public int remove(String id) {
		int result = 0;
		try {
			result = energyProjectDao.delete(id);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	/**
     * 导出
     */
	@Override
	@Transactional(readOnly=true)
	public List<Map<String, Object>> export(
			Map<String, Object> paramsMap) {
		return energyProjectDao.export(paramsMap);
	}
	/**
     * 校验唯一性
     */
	@Override
	@Transactional(readOnly=true)
	public Long hasUnique(Map<String, Object> paramsMap) {
		return energyProjectDao.hasUnique(paramsMap);
	}

	/**
	 * 采暖季下拉框html
	 */
	@Override
    @Transactional(readOnly = true)
	public String getSeasonSelectHtml(Map<String, Object> paramsMap, String selectedId) {
		List<Map<String,String>> listMap = energyProjectDao.getSeasonSelectHtml(paramsMap);
		StringBuilder sb = new StringBuilder();
		if(null!=listMap&&listMap.size()>0){
			sb.append("<option value='' >").append("").append("</option>");
			for(Map<String,String> map:listMap){
				String id = map.get("ID")==null?"":String.valueOf(map.get("ID"));
				String name = map.get("NAME")==null?"":String.valueOf(map.get("NAME"));
				sb.append("<option value='").append(id).append("'");
				if(id.equals(selectedId)){
					sb.append(" selected='selected'");
				}
				sb.append(">").append(name).append("</option>");
			}
		}
		return sb.toString();
	}

}
