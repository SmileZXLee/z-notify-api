package cn.zxlee.znotifyapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatisticsVisitorBy {
    IP( "ip"),
    TAG( "tag");

    private final String value;
}
