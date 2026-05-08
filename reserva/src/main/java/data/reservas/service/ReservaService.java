package data.reservas.service;
import data.reservas.model.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import data.reservas.repository.ReservaRepository;


@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;
    public Reserva findById(Integer id) {
        return reservaRepository.findById(id).orElse(null);
    }
    






}
