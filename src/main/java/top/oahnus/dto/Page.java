package top.oahnus.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by oahnus on 2017/3/31
 * 14:51.
 */
@Data
public class Page<T extends List> {
    private T content;
    private long totalRecord;
    private int totalPage;
    private int currentPage;
    private int limit;

    public Page() {}

    public Page(T content, long totalRecord, int currentPage, int limit) {
        this.content = content;
        this.totalRecord = totalRecord;
        this.currentPage = currentPage;
        this.limit = limit;
        this.totalPage = (int)totalRecord/limit + (totalRecord%limit > 0 ? 1 : 0);
    }
}
