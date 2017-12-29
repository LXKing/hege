package com.huak.org;


import com.huak.sys.model.Administrative;

import java.util.List;
import java.util.Map;

/**
 * Created by MR-BIN on 2017/5/17.
 */
public interface AdministrativeService {
    boolean deleteByPrimaryKey(String admCode);

    boolean insert(Administrative record);

    int insertSelective(Administrative record);

    Administrative selectByPrimaryKey(String admCode);

    boolean updateByPrimaryKeySelective(Administrative record);

    int updateByPrimaryKey(Administrative record);

    List<Map<String,Object>> findAllByLevel(Map<String, String> paramsMap);

    List<Administrative> findAllAdministrative();

    List<Administrative> getAdministrativeSize(String admCode);

    List<Administrative> getAdministrativeSizeCheckName(String admCode);
}
