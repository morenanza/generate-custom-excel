package com.morenanza.excel.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SheetData {
    String[] header;
    List<Object[]> data;
}
