package ru.tadzh.iss.service;

import org.springframework.data.domain.Page;
import ru.tadzh.iss.dto.history.HistoryDto;
import ru.tadzh.iss.dto.HistoryAndSecuritiesListParams;
import ru.tadzh.iss.dto.securities.SecuritiesDto;

import java.util.List;
import java.util.Optional;

public interface HistoryAndSecuritiesService {

    void saveHistory(HistoryDto historyDto);
    List<HistoryDto> findAllHistory();
    Page<HistoryDto> findAllHistoryAndSecuritiesWithParam(HistoryAndSecuritiesListParams historyAndSecuritiesListParams);
    Optional<HistoryDto> findByIdHistory(Long id);
    void deleteByIdHistory(Long id);

    void saveSecurities(SecuritiesDto securitiesDto);

    List<SecuritiesDto> findAllSecurities();

    Optional<SecuritiesDto> findByIdSecurities(String id);

    void deleteByIdSecurities(String id);
}
