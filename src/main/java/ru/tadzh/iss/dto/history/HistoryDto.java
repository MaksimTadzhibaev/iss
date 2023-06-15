package ru.tadzh.iss.dto.history;

import lombok.*;
import ru.tadzh.iss.dto.securities.SecuritiesDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryDto {

    private Long id;
    private String tradeDate;
    private String secId;
    private String numTrades;
    private String open;
    private SecuritiesDto securitiesDto;
}
