package com.example.management.controller;


import com.example.management.service.RecordService;
import com.example.management.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/record")
@CrossOrigin
public class RecordController {
    @Autowired
    private RecordService recordService;

    @PreAuthorize("hasAnyAuthority('销售部','营销部','服务部')")
    @PostMapping("/select")
    public CommonResult selectAll(){
        return recordService.selectAll();
    }

    @PreAuthorize("hasAnyAuthority('销售部','营销部','服务部')")
    @PostMapping("/select/customer")
    public CommonResult selectById(int customerId){
        return recordService.selectById(customerId);
    }

    @PreAuthorize("hasAnyAuthority('销售部','营销部','服务部')")
    @PostMapping("/select/chat")
    public CommonResult selectByChatId(int chatId){
        return recordService.selectByChatId(chatId);
    }
}

