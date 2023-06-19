package ru.tadzh.iss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.tadzh.iss.dto.history.HistoryDto;
import ru.tadzh.iss.dto.HistoryAndSecuritiesListParams;
import ru.tadzh.iss.dto.securities.SecuritiesDto;
import ru.tadzh.iss.entity.History;
import ru.tadzh.iss.entity.Securities;
import ru.tadzh.iss.repository.HistoryRepository;
import ru.tadzh.iss.repository.SecuritiesRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HistoryAndSecuritiesServiceImpl implements HistoryAndSecuritiesService {

    @Autowired
    HistoryRepository historyRepository;
    @Autowired
    SecuritiesRepository securitiesRepository;

    @Override
    public Page<HistoryDto> findAllHistoryAndSecuritiesWithParam(HistoryAndSecuritiesListParams historyAndSecuritiesListParams) {
        return historyRepository.findAll(
                PageRequest.of(
                        Optional.ofNullable(historyAndSecuritiesListParams.getPage()).orElse(1) - 1,
                        Optional.ofNullable(historyAndSecuritiesListParams.getSize()).orElse(3))).map(history -> new HistoryDto(
                history.getId(),
                history.getTradeDate(),
                history.getSecId(),
                history.getNumTrades(),
                history.getOpen(),
                new SecuritiesDto(history.getSecurities().getSecId(), history.getSecurities().getRegNumber(), history.getSecurities().getName(), history.getSecurities().getEmitentTitle())));
    }

    @Override
    public void saveHistory(HistoryDto historyDto) {
        SecuritiesDto securitiesDto = historyDto.getSecuritiesDto();
        History history = new History(
                historyDto.getId(),
                historyDto.getTradeDate(),
                historyDto.getSecId(),
                historyDto.getNumTrades(),
                historyDto.getOpen(),
                new Securities(securitiesDto.getSecId(), securitiesDto.getRegNumber(), securitiesDto.getName(), securitiesDto.getEmitentTitle()));
        historyRepository.save(history);
    }

    @Override
    public List<HistoryDto> findAllHistory() {
        return historyRepository.findAll().stream()
                .map(historyDto -> new HistoryDto(
                        historyDto.getId(),
                        historyDto.getTradeDate(),
                        historyDto.getSecId(),
                        historyDto.getNumTrades(),
                        historyDto.getOpen(),
                        new SecuritiesDto(historyDto.getSecurities().getSecId(), historyDto.getSecurities().getRegNumber(), historyDto.getSecurities().getName(), historyDto.getSecurities().getEmitentTitle())))
                .collect(Collectors.toList());
    }



    @Override
    public Optional<HistoryDto> findByIdHistory(Long id) {
        return historyRepository.findById(id)
                .map(history -> new HistoryDto(
                        history.getId(),
                        history.getTradeDate(),
                        history.getSecId(),
                        history.getNumTrades(),
                        history.getOpen(),
                        new SecuritiesDto(history.getSecurities().getSecId(), history.getSecurities().getRegNumber(), history.getSecurities().getName(), history.getSecurities().getEmitentTitle())));
    }

    @Override
    public void deleteByIdHistory(Long id) {
        historyRepository.deleteById(id);
    }

    @Override
    public void saveSecurities(SecuritiesDto securitiesDto) {
        Securities securities = new Securities(securitiesDto.getSecId(), securitiesDto.getRegNumber(), securitiesDto.getName(), securitiesDto.getEmitentTitle());
        securitiesRepository.save(securities);
    }

    @Override
    public List<SecuritiesDto> findAllSecurities() {
        return securitiesRepository.findAll().stream()
                .map(securities -> new SecuritiesDto(securities.getSecId(),
                        securities.getRegNumber(),
                        securities.getName(),
                        securities.getEmitentTitle()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SecuritiesDto> findByIdSecurities(String id) {
        return securitiesRepository.findById(id)
                .map(securities -> new SecuritiesDto(
                        securities.getSecId(),
                        securities.getRegNumber(),
                        securities.getName(),
                        securities.getEmitentTitle()));
    }

    @Override
    public void deleteByIdSecurities(String id) {
        securitiesRepository.deleteById(id);
    }
}
