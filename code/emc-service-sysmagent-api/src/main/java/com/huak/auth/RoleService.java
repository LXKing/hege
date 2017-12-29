package com.huak.auth;

import com.huak.auth.model.Role;
import com.huak.auth.model.RoleFuncRel;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.auth<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/8<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public interface RoleService {
    public int deleteByPrimaryKey(String id);

    public int insertSelective(Role record);

    public Role selectByPrimaryKey(String id);

    public int updateByPrimaryKeySelective(Role record);

    public PageResult<Role> queryByPage(Map<String,Object> paramsMap, Page page);

    public List<Map<String,Object>> exportRoles(Map<String, Object> paramsMap);

    public List<RoleFuncRel> selectGrantByRoleKey(String id);

    void grantRoleFunc(String roleId, List<RoleFuncRel> rels);

    Long checkRoleName(Map<String, Object> paramsMap);
}
