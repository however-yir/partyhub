package org.example.djxt.service.impl;

import org.example.djxt.domain.sysBranchStar;
import org.example.djxt.mapper.SysBranchStarMapper;
import org.example.djxt.service.ISysBranchStarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Component
public class SysBranchStarServiceImp implements ISysBranchStarService {
    @Autowired()
    private SysBranchStarMapper SysBranchStarMapper;

    @Override
    public List<sysBranchStar> listAll() {
        List<sysBranchStar> sysBranchStars = SysBranchStarMapper.selectAll();
        sysBranchStars.forEach(sysBranchStar -> {
            //（1：支部自评，2：评分专家评分，3：管理员审核，4：评分完成）
            if (sysBranchStar.getProcess() == 1){
                sysBranchStar.setProcessName("支部自评");
            } else if (sysBranchStar.getProcess() == 2) {
                sysBranchStar.setProcessName("评分专家评分");
            }else if (sysBranchStar.getProcess() == 3) {
                sysBranchStar.setProcessName("管理员审核");
            }else if (sysBranchStar.getProcess() == 4) {
                sysBranchStar.setProcessName("评分完成");
            }
        });
        return sysBranchStars;
    }

    @Override
    public sysBranchStar getById(Long id) {
        sysBranchStar sysBranchStar = SysBranchStarMapper.selectById(id);
        //（1：支部自评，2：评分专家评分，3：管理员审核，4：评分完成）
        if (sysBranchStar.getProcess() == 1){
            sysBranchStar.setProcessName("支部自评");
        } else if (sysBranchStar.getProcess() == 2) {
            sysBranchStar.setProcessName("评分专家评分");
        }else if (sysBranchStar.getProcess() == 3) {
            sysBranchStar.setProcessName("管理员审核");
        }else if (sysBranchStar.getProcess() == 4) {
            sysBranchStar.setProcessName("评分完成");
        }
        return sysBranchStar;
    }
}
