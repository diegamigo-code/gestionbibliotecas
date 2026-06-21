package dev.proveedores.model.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class proveedorDTO {
    @NotNull
    private String nombre_proveedor;

    @NotNull
    private String telefono_proveedor;
    @NotNull
    private String email_proveedor;
    @NotNull
    private String Descripcion;
}

