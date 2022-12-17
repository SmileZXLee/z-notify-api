package cn.zxlee.znotifyapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatisticsBadgeType {
    VIEW_COUNT( "view_count"),
    VISITOR_COUNT( "visitor_count");

    private final String value;
}
