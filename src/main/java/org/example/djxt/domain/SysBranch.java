package org.example.djxt.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "sys_branch")
public class SysBranch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String partybranchname;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate establishtime;

    private Integer partynumber;

    @Column(name = "dept_id")
    private Integer deptId;

    @Column(name = "dept_name")
    private String deptName;

    @Column(name = "branchuser_id")
    private Integer branchuserId;

    @Column(name = "branchuser_name")
    private String branchuserName;
    private String status;

    @Column(name = "branch_secretary_id")
    private Integer branchSecretaryId;

    @Column(name = "branch_secretary_name")
    private String branchSecretaryName;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "update_time")
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
