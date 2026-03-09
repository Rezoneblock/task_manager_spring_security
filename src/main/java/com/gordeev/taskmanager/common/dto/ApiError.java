package com.gordeev.taskmanager.common.dto;

import java.util.Map;

public record ApiError(String message, String code, Map<String, String> details) {
}
