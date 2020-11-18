//package com.decade.mall.ums.controller;
//
//import com.decade.mall.ums.util.Result;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/sso")
//@Api(value = "MemberController", tags = "用户Controller")
//public class MemberController {
//
//    private final MemberService memberService;
//
//    @Autowired
//    public MemberController(MemberService memberService) {
//        this.memberService = memberService;
//    }
//
//    @GetMapping("/getAuthCode")
//    @ApiOperation("获取验证码")
//    @PreAuthorize("hasAuthority('ums:sso:getAuthCode')")
//    public Result getAuthCode(@RequestParam String telephone) {
//        return memberService.generateAuthCode(telephone);
//    }
//
//    @GetMapping("/verifyAuthCode")
//    @ApiOperation("判断验证码是否正确")
//    public Result verifyAuthCode(@RequestParam String telephone,
//                                 @RequestParam String authCode) {
//        return memberService.verifyAuthCode(telephone, authCode);
//    }
//}
