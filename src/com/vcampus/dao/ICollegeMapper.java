package com.vcampus.dao;

import com.vcampus.entity.College;

import java.util.List;
import java.util.Map;

public interface ICollegeMapper {
    public List<College> listAllInfo();

    public List<College> listInfo(Map map);

    public List<College> listInfoOnlyByMajor(String string);

    public List<College> listInfoOnlyByCollege(String string);

    public Boolean insertInfo(Map map);

    public Boolean changeInfo(Map map);

    public Boolean deleteInfo(Map map);



}
