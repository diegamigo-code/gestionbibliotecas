package dev.proveedores.controller;
import dev.proveedores.model.DTO.proveedorDTO;
import dev.proveedores.model.proveedor;
import dev.proveedores.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/proveedor")
public class proveedorController {
    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public List<proveedor> findAll() {
        return proveedorService.findAll();
    }

    @GetMapping("Id")
    public proveedorDTO findById(@RequestParam Integer id) {
        return proveedorService.getproveedor(id);
    }
    @PutMapping("")
    public proveedor updateProveedor(@RequestBody proveedor proveedor) {
        return proveedorService.updateproveedor(proveedor);
    }
    @PostMapping("")
    public proveedor addProveedor(@RequestBody proveedor proveedor) {
        return proveedorService.postproveedor(proveedor);
    }

    @DeleteMapping("")
    public void deleteProveedor(@RequestParam Integer id) {
        proveedorService.deleteproveedor(id);
    }

}

