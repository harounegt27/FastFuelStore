package com.pfe.ffs.services.admin.stock;

import com.pfe.ffs.dto.StockDto;
import com.pfe.ffs.enums.EtatStock;

import java.math.BigDecimal;
import java.util.List;

public interface StockService {
    public StockDto addStock(StockDto stockDto);

    public EtatStock TestStockM3(BigDecimal En_m3 , BigDecimal Indiq);

    public EtatStock TestStockTM(BigDecimal En_tm , BigDecimal Indiq);

    public List<StockDto> getAllStock();
    public Boolean deleteStock(Long idStock);
    public StockDto getStockById(Long id);
    public StockDto updateStock(Long Id,StockDto stockDto);

}
