package com.pablofersep.practicaintegradora.formobjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineaCarritoDTO {
    private String email;
    private String id;
    private Integer unidades;
}
