package org.example.djxt.mapper;

import org.apache.ibatis.annotations.Select;
import org.example.djxt.domain.sysBranchStar;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface SysBranchStarMapper extends Mapper<sysBranchStar> {

    String BASE_COLUMNS = "id,sodsacs,partybranchname,establishtime,partynumber,dept_id,dept_name," +
            "branchuser_id,branchuser_name,branch_secretary_id,branch_secretary_name,self_star,context," +
            "foundationitem,pluses,minuses,score,starrating,outcome,del_flag,organizationcomment,comments," +
            "process,sb_year,file_name,file_path,ygly_id,ygly_name,create_by,create_time,update_by,update_time," +
            "remark,commentitems";

    @Select("select " + BASE_COLUMNS + " from sys_branch_star")
    List<sysBranchStar> selectAll();

    @Select("select " + BASE_COLUMNS + " from sys_branch_star where id = #{id}")
    sysBranchStar selectById(Long id);
}
