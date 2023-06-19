package ru.tadzh.iss.dto.securities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecuritiesDto {

    private String secId;
    private String regNumber;
    private String name;
    private String emitentTitle;
}
