package org.example.djxt.controller;

import org.example.djxt.common.ApiResponse;
import org.example.djxt.common.PageResult;
import org.example.djxt.domain.SysFeedback;
import org.example.djxt.service.SysFeedbackService;
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
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    private final SysFeedbackService feedbackService;

    public FeedbackController(SysFeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    public ApiResponse<PageResult<SysFeedback>> list(
            @RequestParam(required = false) Integer messageId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ApiResponse.ok(feedbackService.list(messageId, page, size));
    }

    @GetMapping("/{id}")
    public ApiResponse<SysFeedback> get(@PathVariable Integer id) {
        return ApiResponse.ok(feedbackService.getById(id));
    }

    @PostMapping
    public ApiResponse<SysFeedback> create(@RequestBody SysFeedback feedback) {
        return ApiResponse.ok("created", feedbackService.create(feedback));
    }

    @PutMapping("/{id}")
    public ApiResponse<SysFeedback> update(@PathVariable Integer id, @RequestBody SysFeedback feedback) {
        return ApiResponse.ok("updated", feedbackService.update(id, feedback));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Integer id) {
        feedbackService.delete(id);
        return ApiResponse.ok("deleted", "ok");
    }
}
