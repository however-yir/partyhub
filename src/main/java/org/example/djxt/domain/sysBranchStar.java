package org.example.djxt.domain;


import com.sun.tracing.ProbeName;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Table(name = "sys_branch_star")
public class sysBranchStar {
    private Long id;

    private String sodsacs;

    private String partybranchname;

    private String establishtime;

    private Integer partynumber;

    private Integer dept_id;

    private String dept_name;

    private Integer branchuser_id;
    private String branchuser_name;

    private Integer branch_secretary_id;
    private String branch_secretary_name;
    private String self_star;
    private String context;
    private Integer foundationitem;
    private Integer pluses;
    private Integer minuses;
    private Integer score;
    private String starrating;
    private String outcome;
    private String del_flag;
    private String organizationcomment;
    private String comments;
    private Integer process;
    @Transient
    private String processName;
    private String sbYear;
    private String file_name;
    private String file_path;

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    private Integer ygly_id;
    private String ygly_name;
    private String create_by;
    private String create_time;
    private String update_by;
    private String update_time;
    private String remark;
    private Integer commentitems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSodsacs() {
        return sodsacs;
    }

    public void setSodsacs(String sodsacs) {
        this.sodsacs = sodsacs;
    }

    public String getPartybranchname() {
        return partybranchname;
    }

    public void setPartybranchname(String partybranchname) {
        this.partybranchname = partybranchname;
    }

    public String getEstablishtime() {
        return establishtime;
    }

    public void setEstablishtime(String establishtime) {
        this.establishtime = establishtime;
    }

    public Integer getPartynumber() {
        return partynumber;
    }

    public void setPartynumber(Integer partynumber) {
        this.partynumber = partynumber;
    }

    public Integer getDeptId() {
        return dept_id;
    }

    public void setDeptId(Integer deptId) {
        this.dept_id = deptId;
    }

    public String getDeptName() {
        return dept_name;
    }

    public void setDeptName(String deptName) {
        this.dept_name = deptName;
    }

    public Integer getBranchuserId() {
        return branchuser_id;
    }

    public void setBranchuserId(Integer branchuserId) {
        this.branchuser_id = branchuserId;
    }

    public String getBranchuserName() {
        return branchuser_name;
    }

    public void setBranchuserName(String branchuserName) {
        this.branchuser_name = branchuserName;
    }

    public Integer getBranchSecretaryId() {
        return branch_secretary_id;
    }

    public void setBranchSecretaryId(Integer branchSecretaryId) {
        this.branch_secretary_id = branchSecretaryId;
    }

    public String getBranchSecretaryName() {
        return branch_secretary_name;
    }

    public void setBranchSecretaryName(String branchSecretaryName) {
        this.branch_secretary_name = branchSecretaryName;
    }

    public String getSelfStar() {
        return self_star;
    }

    public void setSelfStar(String selfStar) {
        this.self_star = selfStar;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Integer getFoundationitem() {
        return foundationitem;
    }

    public void setFoundationitem(Integer foundationitem) {
        this.foundationitem = foundationitem;
    }

    public Integer getPluses() {
        return pluses;
    }

    public void setPluses(Integer pluses) {
        this.pluses = pluses;
    }

    public Integer getMinuses() {
        return minuses;
    }

    public void setMinuses(Integer minuses) {
        this.minuses = minuses;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getStarrating() {
        return starrating;
    }

    public void setStarrating(String starrating) {
        this.starrating = starrating;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public String getDelFlag() {
        return del_flag;
    }

    public void setDelFlag(String delFlag) {
        this.del_flag = delFlag;
    }

    public String getOrganizationcomment() {
        return organizationcomment;
    }

    public void setOrganizationcomment(String organizationcomment) {
        this.organizationcomment = organizationcomment;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getProcess() {
        return process;
    }

    public void setProcess(Integer process) {
        this.process = process;
    }

    public String getSbYear() {
        return sbYear;
    }

    public void setSbYear(String sbYear) {
        this.sbYear = sbYear;
    }

    public String getFileName() {
        return file_name;
    }

    public void setFileName(String fileName) {
        this.file_name = fileName;
    }

    public String getFilePath() {
        return file_path;
    }

    public void setFilePath(String filePath) {
        this.file_path = filePath;
    }

    public Integer getYglyId() {
        return ygly_id;
    }

    public void setYglyId(Integer yglyId) {
        this.ygly_id = yglyId;
    }

    public String getYglyName() {
        return ygly_name;
    }

    public void setYglyName(String yglyName) {
        this.ygly_name = yglyName;
    }

    public String getCreateBy() {
        return create_by;
    }

    public void setCreateBy(String createBy) {
        this.create_by = createBy;
    }

    public String getCreateTime() {
        return create_time;
    }

    public void setCreateTime(String createTime) {
        this.create_time = createTime;
    }

    public String getUpdateBy() {
        return update_by;
    }

    public void setUpdateBy(String updateBy) {
        this.update_by = updateBy;
    }

    public String getUpdateTime() {
        return update_time;
    }

    public void setUpdateTime(String updateTime) {
        this.update_time = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCommentitems() {
        return commentitems;
    }

    public void setCommentitems(Integer commentitems) {
        this.commentitems = commentitems;
    }

    @Override
    public String toString() {
        return "sysBranchStar{" +
                "id=" + id +
                ", sodsacs='" + sodsacs + '\'' +
                ", partybranchname='" + partybranchname + '\'' +
                ", establishtime='" + establishtime + '\'' +
                ", partynumber=" + partynumber +
                ", dept_id=" + dept_id +
                ", dept_name='" + dept_name + '\'' +
                ", branchuser_id=" + branchuser_id +
                ", branchuser_name='" + branchuser_name + '\'' +
                ", branch_secretary_id=" + branch_secretary_id +
                ", branch_secretary_name='" + branch_secretary_name + '\'' +
                ", self_star='" + self_star + '\'' +
                ", context='" + context + '\'' +
                ", foundationitem=" + foundationitem +
                ", pluses=" + pluses +
                ", minuses=" + minuses +
                ", score=" + score +
                ", starrating='" + starrating + '\'' +
                ", outcome='" + outcome + '\'' +
                ", del_flag='" + del_flag + '\'' +
                ", organizationcomment='" + organizationcomment + '\'' +
                ", comments='" + comments + '\'' +
                ", process=" + process +
                ", sbYear='" + sbYear + '\'' +
                ", file_name='" + file_name + '\'' +
                ", file_path='" + file_path + '\'' +
                ", ygly_id=" + ygly_id +
                ", ygly_name='" + ygly_name + '\'' +
                ", create_by='" + create_by + '\'' +
                ", create_time='" + create_time + '\'' +
                ", update_by='" + update_by + '\'' +
                ", update_time='" + update_time + '\'' +
                ", remark='" + remark + '\'' +
                ", commentitems=" + commentitems +
                '}';
    }
}
