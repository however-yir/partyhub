package org.example.djxt.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.djxt.domain.sysBranchStar;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.List;

@Repository
public interface SysBranchStarMapper extends BaseMapper<sysBranchStar> {


    @Select("select * from sys_branch_star")
    List<sysBranchStar> selectAll();

    @Select("select * from sys_branch_star where id = #{id}")
    sysBranchStar selectById(Long id);
}
