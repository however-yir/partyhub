package org.example.djxt.controller;

import org.example.djxt.common.ApiResponse;
import org.example.djxt.common.PageResult;
import org.example.djxt.domain.SysMessage;
import org.example.djxt.service.SysMessageService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final SysMessageService messageService;

    public MessageController(SysMessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public ApiResponse<PageResult<SysMessage>> list(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer receiveUserId,
            @RequestParam(required = false) String messageFlag,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ApiResponse.ok(messageService.list(keyword, receiveUserId, messageFlag, page, size));
    }

    @GetMapping("/{id}")
    public ApiResponse<SysMessage> get(@PathVariable Integer id) {
        return ApiResponse.ok(messageService.getById(id));
    }

    @PostMapping
    public ApiResponse<SysMessage> create(@RequestBody SysMessage message) {
        return ApiResponse.ok("created", messageService.create(message));
    }

    @PutMapping("/{id}")
    public ApiResponse<SysMessage> update(@PathVariable Integer id, @RequestBody SysMessage message) {
        return ApiResponse.ok("updated", messageService.update(id, message));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Integer id) {
        messageService.delete(id);
        return ApiResponse.ok("deleted", "ok");
    }
}
