package dev.diemigo.inventario.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventarioDTO {

    @NotBlank(message = "Debe tener título")
    private String titulo;

    @NotBlank(message = "Debe indicar estado")
    private String estado;

    @NotBlank(message = "Debe indicar la ubicación")
    private String ubicacion;
}
