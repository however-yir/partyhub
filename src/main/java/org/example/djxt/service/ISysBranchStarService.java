package org.example.djxt.service;

import org.example.djxt.domain.sysBranchStar;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ISysBranchStarService {
    List<sysBranchStar> listAll();

    sysBranchStar getById(Long id);
}
