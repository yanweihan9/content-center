package com.youngzi.contentcenter.content.controller;

import com.youngzi.contentcenter.dto.content.ShareDTO;
import com.youngzi.contentcenter.service.content.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shares")
public class ShareController {

    @Autowired
    private ShareService shareService;

    @GetMapping("/{id}")
    public ShareDTO findById(@PathVariable Integer id) throws Exception {
        return shareService.findById(id);
    }
}
