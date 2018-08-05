package com.roachfu.solar.generator;

import lombok.*;

/**
 * @author roach
 * @date 2018/7/12 20:48
 */

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableColumn {

    private String columnName ;
    private String camelColumnName;
    private String columnType;
    private String dataType ;
    private String nullable ;
    private String columnDefault ;
    private String columnComment ;
}
