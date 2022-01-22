package com.thiennam.messtar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/room")
public class RoomController {
    @GetMapping("/messtar")
    public String messtar() {
        return "room/messtar";
    }
}
