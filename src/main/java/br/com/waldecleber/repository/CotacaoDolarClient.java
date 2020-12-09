package br.com.waldecleber.repository;


import br.com.waldecleber.dto.CotacaoDollarDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "cotacao-dolar", url = "https://economia.awesomeapi.com.br/USD-BRL")
public interface CotacaoDolarClient {

    @GetMapping
    List<CotacaoDollarDTO> getColacaoDolar();
}
