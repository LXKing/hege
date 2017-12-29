package com.huak.org;

import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.sys.model.Administrative;
import com.huak.org.model.Company;
import com.huak.org.model.Org;
import com.huak.sys.model.SysDic;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/9.
 */


@Service
public interface OrgService {

    public boolean delete(String id);

    public int insert(Administrative season);

    public PageResult<Administrative> queryByPage(String name, Page page);

    public Administrative  selectAdministrator();

    List<Administrative> selectAll();

    List<Org> selectOrgAll(Map<String,Object> params);

    public boolean insertOrg(Org org);

    boolean checkOrgName(String orgName);

    public Org selectByPrimaryKey(String id);

    public boolean updateOrg(Org org);

    public List<Map<String,Object>> selectOrgByMap(Map<String,Object> params);

    List<Company> selectCompanyAll();

    List<SysDic>  selectSysDicAll(String code);

    List<Map<String, Object>> selectOrgTree(Map<String, Object> paramsMap);

    List<Map<String, Object>> selectFeedByid(String orgId);

    List<Map<String, Object>> selectStationByid(String orgId);

    List<Map<String, Object>> selectViewByMap(Map<String, Object> paramsMap);
}
