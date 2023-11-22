package com.dearnewyear.dny.user.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"User"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
}
