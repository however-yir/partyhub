package org.example.djxt.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PageResult<T> {
    private List<T> items;
    private long total;
    private int page;
    private int size;
    private int totalPages;

    public PageResult() {
    }

    public PageResult(List<T> items, long total, int page, int size) {
        this.items = items;
        this.total = total;
        this.page = page;
        this.size = size;
        this.totalPages = size <= 0 ? 0 : (int) Math.ceil((double) total / (double) size);
    }

    public static <T> PageResult<T> fromList(List<T> raw, int page, int size) {
        List<T> safe = raw == null ? Collections.emptyList() : raw;
        int safePage = Math.max(page, 1);
        int safeSize = Math.max(size, 1);

        int fromIndex = (safePage - 1) * safeSize;
        if (fromIndex >= safe.size()) {
            return new PageResult<>(Collections.emptyList(), safe.size(), safePage, safeSize);
        }

        int toIndex = Math.min(fromIndex + safeSize, safe.size());
        return new PageResult<>(new ArrayList<>(safe.subList(fromIndex, toIndex)), safe.size(), safePage, safeSize);
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
