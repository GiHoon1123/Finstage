package io.dustin.salesmgmt.common.response;

import lombok.Getter;
import java.util.List;

@Getter
public class PaginatedResponse<T> {
    private final int status;
    private final String message;
    private final long total;
    private final int page;
    private final int limit;
    private final long totalPages;
    private final long remainingPages;
    private final List<T> data;

    public PaginatedResponse(int status, String message, long total, int page, int limit, List<T> data) {
        this.status = status;
        this.message = message;
        this.total = total;
        this.page = page;
        this.limit = limit;
        this.totalPages = (long) Math.ceil((double) total / limit);
        this.remainingPages = Math.max(totalPages - page, 0);
        this.data = data;
    }

    public static <T> PaginatedResponse<T> of(int status, String message, long total, int page, int limit, List<T> data) {
        return new PaginatedResponse<>(status, message, total, page, limit, data);
    }

    public static <T> PaginatedResponse<T> success(long total, int page, int limit, List<T> data) {
        return new PaginatedResponse<>(200, "Success", total, page, limit, data);
    }
}