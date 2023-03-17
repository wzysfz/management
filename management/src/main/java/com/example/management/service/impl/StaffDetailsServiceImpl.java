package com.example.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.management.mapper.StaffMapper;
import com.example.management.domain.LoginStaff;
import com.example.management.pojo.Staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class StaffDetailsServiceImpl implements UserDetailsService {

    @Autowired(required = false)
    private StaffMapper staffMapper;


    //实现UserDetailsService接口，重写UserDetails方法，自定义用户的信息从数据中查询
    @Override
    public UserDetails loadUserByUsername(String staffAccount) throws UsernameNotFoundException {

        //（认证，即校验该用户是否存在）查询用户信息
        LambdaQueryWrapper<Staff> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Staff::getStaffAccount,staffAccount);
        Staff staff = staffMapper.selectOne(queryWrapper);
        //如果没有查询到用户
        if (Objects.isNull(staff)){
            throw new RuntimeException("用户名或者密码错误");
        }


        //TODO (授权，即查询用户具有哪些权限)查询对应的用户信息


        //把数据封装成UserDetails返回
        return new LoginStaff(staff);
    }
}