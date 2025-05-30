package com.pfe.ffs.services.admin.stock;

import com.pfe.ffs.dto.PrixUnitaireDto;
import com.pfe.ffs.dto.StockDto;
import com.pfe.ffs.entity.PrixUnitaire;
import com.pfe.ffs.entity.Produit;
import com.pfe.ffs.entity.Stock;
import com.pfe.ffs.entity.Voix;
import com.pfe.ffs.enums.EtatStock;
import com.pfe.ffs.repository.ProduitRepo;
import com.pfe.ffs.repository.StockRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StockServiceImpl implements StockService{
    private final ProduitRepo produitRepo;
    private final StockRepo stockRepo;

    public StockDto addStock(StockDto stockDto){
        Stock stock = new Stock();
        stock.setEn_m3(stockDto.getEn_m3());
        stock.setEn_tm(stockDto.getEn_tm());

        Produit produit = produitRepo.findById(stockDto.getProduitId()).orElseThrow();
        stock.setProduit(produit);

        stock.setIndiq_en_tm(stockDto.getIndique_en_tm());
        stock.setIndiq_en_m3(stockDto.getIndique_en_m3());

        BigDecimal Idiqe_m3 = stockDto.getIndique_en_m3();
        BigDecimal Idiqe_tm = stockDto.getIndique_en_tm();
        BigDecimal m3 = stockDto.getEn_m3();
        BigDecimal tm = stockDto.getEn_tm();

        stock.setEtatTm(TestStockTM(tm,Idiqe_tm));
        stock.setEtatM3(TestStockM3(m3,Idiqe_m3));

        return stockRepo.save(stock).getDto();

    }
    @Override
    public EtatStock TestStockM3(BigDecimal En_m3 , BigDecimal Indiq) {
        if (En_m3.compareTo(BigDecimal.ZERO) == 0){
            return EtatStock.EN_RUPTURE;
        } else if (Indiq.compareTo(En_m3)<=0){
            return EtatStock.NORMAL;
        }else {
            return EtatStock.AVERTISSEMENT;
        }
    }

    @Override
    public EtatStock TestStockTM(BigDecimal En_tm , BigDecimal Indiq) {
        if (En_tm.compareTo(BigDecimal.ZERO) == 0){
            return EtatStock.EN_RUPTURE;
        } else if (Indiq.compareTo(En_tm)<=0){
            return EtatStock.NORMAL;
        }else {
            return EtatStock.AVERTISSEMENT;
        }
    }

    public List<StockDto> getAllStock(){
        List<Stock> stocks = stockRepo.findAll();
        return stocks.stream().map(Stock::getDto).collect(Collectors.toList());
    }

    public Boolean deleteStock(Long idStock){
        Optional<Stock> stock = stockRepo.findById(idStock);
        if (stock.isPresent()){
            stockRepo.deleteById(idStock);
            return true;
        }
        return false;
    }

    public StockDto getStockById(Long id){
        Optional<Stock> stock = stockRepo.findById(id);
        if (stock.isPresent()){
            return stock.get().getDto();
        }else {
            return null;
        }
    }

    public StockDto updateStock(Long Id,StockDto stockDto){
        Optional<Stock> optionalStock = stockRepo.findById(Id);
        Optional<Produit> optionalProduit = produitRepo.findById(stockDto.getProduitId());

        if (optionalStock.isPresent()&&optionalProduit.isPresent()){
            Stock stock = optionalStock.get();
            stock.setEn_m3(stockDto.getEn_m3());
            stock.setEn_tm(stockDto.getEn_tm());
            stock.setIndiq_en_m3(stockDto.getIndique_en_m3());
            stock.setIndiq_en_tm(stockDto.getIndique_en_tm());
            stock.setProduit(optionalProduit.get());
            stock.setEtatTm(TestStockTM(stockDto.getEn_tm(),stockDto.getIndique_en_tm()));
            stock.setEtatM3(TestStockM3(stockDto.getEn_m3(),stockDto.getIndique_en_m3()));
            return stockRepo.save(stock).getDto();
        }else {
            return null;
        }
    }
}
