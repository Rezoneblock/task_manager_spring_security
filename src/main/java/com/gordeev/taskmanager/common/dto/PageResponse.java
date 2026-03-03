package com.gordeev.taskmanager.common.dto;

import java.util.List;

public record PageResponse<T>(
        List<T> data,

        Metadata meta
) {
    public record Metadata(
            long size,
            long totalElements,
            long totalPages,
            long number
    ) {}
}
