package org.example.djxt.demos.web;

import org.example.djxt.domain.sysBranchStar;
import org.example.djxt.service.ISysBranchStarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/star")
public class StarController {
    @Autowired
    private ISysBranchStarService ISysBranchStarService;

    @RequestMapping("")
    public List<sysBranchStar> listAll() {
        return ISysBranchStarService.listAll();
    }

    @GetMapping("/{id}")
    public sysBranchStar getById(@PathVariable Long id) {
        return ISysBranchStarService.getById(id);
    }
}
