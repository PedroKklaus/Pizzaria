package br.com.uniamerica.pizzaria.pizarria.dto;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SaboresDTO {
    private Long id;

    private String NomeSabor;
}
