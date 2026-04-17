package dev.diemigo.gestionbibliotecas.service;

import dev.diemigo.gestionbibliotecas.models.Libro;
import dev.diemigo.gestionbibliotecas.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;

    public List<Libro> findAll(){
        return libroRepository.findAll();
    }

    public Libro findById(int id){
        return libroRepository.findById(id).get();
    }

    public Libro save(Libro libro){
        return libroRepository.save(libro);
    }

    public void delete(int id){
        libroRepository.deleteById(id);
    }

    public Libro update(Libro libro){
        return libroRepository.save(libro);
    }

}
