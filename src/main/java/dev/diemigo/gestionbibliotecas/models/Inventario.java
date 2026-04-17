package dev.diemigo.gestionbibliotecas.models;


import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventario {
    @Id
    private int id_inventario;

    private boolean nuevo;

    private int ubicacion;
}
