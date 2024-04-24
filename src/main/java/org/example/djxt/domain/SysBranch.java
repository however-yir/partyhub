package org.example.djxt.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@TableName("sys_branch")
public class SysBranch {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String partybranchname;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate establishtime;

    private Integer partynumber;

    @TableField("dept_id")
    private Integer deptId;

    @TableField("dept_name")
    private String deptName;

    @TableField("branchuser_id")
    private Integer branchuserId;

    @TableField("branchuser_name")
    private String branchuserName;
    private String status;

    @TableField("branch_secretary_id")
    private Integer branchSecretaryId;

    @TableField("branch_secretary_name")
    private String branchSecretaryName;

    @TableField("create_by")
    private String createBy;

    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @TableField("update_by")
    private String updateBy;

    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPartybranchname() {
        return partybranchname;
    }

    public void setPartybranchname(String partybranchname) {
        this.partybranchname = partybranchname;
    }

    public LocalDate getEstablishtime() {
        return establishtime;
    }

    public void setEstablishtime(LocalDate establishtime) {
        this.establishtime = establishtime;
    }

    public Integer getPartynumber() {
        return partynumber;
    }

    public void setPartynumber(Integer partynumber) {
        this.partynumber = partynumber;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getBranchuserId() {
        return branchuserId;
    }

    public void setBranchuserId(Integer branchuserId) {
        this.branchuserId = branchuserId;
    }

    public String getBranchuserName() {
        return branchuserName;
    }

    public void setBranchuserName(String branchuserName) {
        this.branchuserName = branchuserName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getBranchSecretaryId() {
        return branchSecretaryId;
    }

    public void setBranchSecretaryId(Integer branchSecretaryId) {
        this.branchSecretaryId = branchSecretaryId;
    }

    public String getBranchSecretaryName() {
        return branchSecretaryName;
    }

    public void setBranchSecretaryName(String branchSecretaryName) {
        this.branchSecretaryName = branchSecretaryName;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
