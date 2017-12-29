package com.huak.auth.dao;


import com.huak.auth.model.RoleFuncRel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleFuncRelDao {
    int insert(RoleFuncRel record);

    int deleteRelByRole(String roleId);

    List<RoleFuncRel> selectGrantByRoleKey(String roleId);
}