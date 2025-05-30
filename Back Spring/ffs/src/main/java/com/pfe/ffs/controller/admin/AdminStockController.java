package com.pfe.ffs.controller.admin;

import com.pfe.ffs.dto.PrixUnitaireDto;
import com.pfe.ffs.dto.ProduitDto;
import com.pfe.ffs.dto.StockDto;
import com.pfe.ffs.services.admin.stock.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminStockController {
    private final StockService stockService;

    @PostMapping("/stock")
    public ResponseEntity<StockDto> addStock(StockDto stockDto) {
        StockDto stockDto1 = stockService.addStock(stockDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(stockDto1);
    }

    @GetMapping("/stocks")
    public ResponseEntity<List<StockDto>> getAllStock(){
        List<StockDto> stockDtos = stockService.getAllStock();
        return ResponseEntity.ok(stockDtos);
    }

    @DeleteMapping("/stock/{stockId}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long stockId){
        Boolean delete = stockService.deleteStock(stockId);
        if (delete){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/stock/{stockId}")
    public ResponseEntity<StockDto> getStockById(@PathVariable Long stockId){
        StockDto stockDto = stockService.getStockById(stockId);
        if (stockDto != null){
            return ResponseEntity.ok(stockDto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/stock/{stockId}")
    public ResponseEntity<StockDto> updateStock(@PathVariable Long stockId, StockDto stockDto){
        StockDto updatedStock = stockService.updateStock(stockId,stockDto);
        if (updatedStock != null){
            return ResponseEntity.ok(updatedStock);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
