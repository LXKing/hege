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
import com.huak.org.dao.CommunityDao;
import com.huak.org.model.Community;

/**
 * 小区service实现
 * @author Administrator
 *
 */
@Service
public class CommunityServiceImpl implements CommunityService {
	
	@Resource
	private CommunityDao communityDao;
	
	@Resource
	private AdministrativeService administrativeService;

	/**
	 * 查询小区列表信息
	 */
	@Override
    @Transactional(readOnly = true)
	public PageResult<Community> list(Map<String, String> params,Page page) {
		PageHelper.startPage(page.getPageNumber(), page.getPageSize());
		return Convert.convert(communityDao.selectByPage(params));
	}

	/**
	 * 新增小区信息
	 */
	@Override
    @Transactional(readOnly = false)
	public int save(Community community) {
		int result = 0;
		try {
			community.setId(UUIDGenerator.getUUID());
			result = communityDao.insertCommunity(community);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	/**
	 * 修改小区信息
	 */
	@Override
    @Transactional(readOnly = false)
	public int edit(Community community) {
		int result = 0;
		try {
			result = communityDao.updateCommunity(community);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	/**
	 * 删除小区信息
	 */
	@Override
    @Transactional(readOnly = false)
	public int delete(String communityId) {
		int result = 0;
		try {
			result = communityDao.deleteCommunity(communityId);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	/**
	 * 根据主键获取小区信息
	 */
	@Override
    @Transactional(readOnly = true)
	public Community get(String id) {
		return communityDao.get(id);
	}

	/**
	 * 导出小区信息
	 */
	@Override
    @Transactional(readOnly = true)
	public List<Map<String, Object>> exportCommunity(Map<String, String> param) {
		return communityDao.export(param);
	}
	
	//============================================================================
	//下面是小区、楼座、单元和户可能要用到的方法，为了一些方法的公用，统一管理
	
	/**
	 * 公司下拉框HTML文
	 */
	@Override
    @Transactional(readOnly = true)
	public String getCompanySelectHtmlStr(String selectedComId) {
		return getSelectHtmlStr(communityDao.selectCompanySelectHtmlStr(),selectedComId);
	}
	
	/**
	 * 小区下拉框HTML文
	 * @param params
	 * @return
	 */
	@Override
    @Transactional(readOnly = true)
	public String getCommunitySelectHtmlStr(Map<String,String> params,String selectedCommunityId) {
		return getSelectHtmlStr(communityDao.selectCommunitySelectHtmlStr(params),selectedCommunityId);
	}
	
	/**
	 * 楼座下拉框HTML文
	 */
	@Override
    @Transactional(readOnly = true)
	public String getBanSelectHtmlStr(Map<String, String> params, String selectedBanId) {
		return getSelectHtmlStr(communityDao.selectBanSelectHtmlStr(params),selectedBanId);
	}

	/**
	 * 单元下拉框HTML文
	 */
	@Override
    @Transactional(readOnly = true)
	public String getCellSelectHtmlStr(Map<String, String> params, String selectedCellId) {
		return getSelectHtmlStr(communityDao.selectCellSelectHtmlStr(params),selectedCellId);
	}
	
	@Override
    @Transactional(readOnly = true)
	public String getLineSelectHtmlStr(Map<String, String> param,String selectedLineId) {
		return getSelectHtmlStr(communityDao.selectLineSelectHtmlStr(param), selectedLineId);
	}
	
	@Override
    @Transactional(readOnly = true)
	public String getHeatTypeSelectHtmlStr(String heatTypeSelected) {
		return getSelectHtmlStr(communityDao.getHeatTypeSelectHtmlStr(),heatTypeSelected);
	}
	/**
	 * 查询结果List<Map<String,String>>转为String
	 * @param list
	 * @return
	 */
	private String getSelectHtmlStr(List<Map<String, String>> list,String selectedCommunityId){
		StringBuilder sb = new StringBuilder();
		if(null!=list&&list.size()>0){
			sb.append("<option value=\"\" >").append("").append("</option>");
			for(Map<String,String> map:list){
				String id = String.valueOf(map.get("ID"));
				String name = String.valueOf(map.get("NAME"));
				sb.append("<option ");
				if(selectedCommunityId!=null&&id.equals(selectedCommunityId)){
					sb.append(" selected ");
				}
				sb.append(" value=\"").append(id).append("\">").append(name).append("</option>");
			}
		}
		return sb.toString();
	}

}
