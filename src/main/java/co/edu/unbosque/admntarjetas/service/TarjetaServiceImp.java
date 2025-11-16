package co.edu.unbosque.admntarjetas.service;

import co.edu.unbosque.admntarjetas.model.Exception.TarjetaNoAdminitaException;
import co.edu.unbosque.admntarjetas.model.Exception.TarjetaNotFoundException;
import co.edu.unbosque.admntarjetas.model.dto.TarjetaDto;
import co.edu.unbosque.admntarjetas.model.entity.ESTADOTARJETA;
import co.edu.unbosque.admntarjetas.model.entity.FRANQUICIA;
import co.edu.unbosque.admntarjetas.model.entity.Tarjeta;
import co.edu.unbosque.admntarjetas.repo.TarjetaRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarjetaServiceImp implements TarjetaService {

    @Autowired
    private TarjetaRepo tarjetaRepo;
    @Autowired
    private ModelMapper mapper;

    @Override
    public void createTarjeta(TarjetaDto tarjeta) {
        if(tarjetaRepo.existsByNumeroTarjeta(tarjeta.getNumeroTarjeta())){
            throw new TarjetaNoAdminitaException("Esta tarjeta ya existe");
        }
        tarjeta.setEstadoTarjeta(ESTADOTARJETA.ACTIVO);
        tarjeta.setCupoTotal(tarjeta.getCupoTotal());
        tarjeta.setCupoDisponible(tarjeta.getCupoTotal() - tarjeta.getCupoUsado());
        tarjeta.setCupoUsado(tarjeta.getCupoUsado());
        tarjeta.setCupoDisponible(tarjeta.getCupoTotal() - tarjeta.getCupoUsado());
        tarjeta.setFranquicia(franquicia(String.valueOf(tarjeta.getNumeroTarjeta())));
        if (tarjeta.getFranquicia() == null) {
            throw  new TarjetaNoAdminitaException("Tarjeta no adminita");
        }
        System.out.println(tarjeta.getFranquicia());
        tarjetaRepo.save(mapper.map(tarjeta, Tarjeta.class));
    }

    @Override
    public TarjetaDto updateTarjeta(TarjetaDto tarjeta, Long idCliente) {
        TarjetaDto tarjetaToUpdate = mapper.map(tarjetaRepo.findByCliente_IdCliente(idCliente), TarjetaDto.class);
        tarjetaToUpdate.setIdCliente(idCliente);
        tarjetaToUpdate.setCupoTotal(tarjeta.getCupoTotal());
        tarjetaRepo.save(mapper.map(tarjetaToUpdate, Tarjeta.class));
        return tarjetaToUpdate;
    }

    @Override
    public void deleteTarjeta(Long numeroTarjeta) {
        Tarjeta tarjeta = tarjetaRepo.findByNumeroTarjeta(numeroTarjeta)
                .orElseThrow(() -> new TarjetaNotFoundException("Tarjeta No encontrada"));
        tarjeta.setEstadoTarjeta(ESTADOTARJETA.INACTIVO);
        tarjetaRepo.save(tarjeta);
    }

    @Override
    public TarjetaDto getTarjetaDto(TarjetaDto tarjeta) {
        return null;
    }

    @Override
    public List<TarjetaDto> listarTarjetas() {
        return List.of();
    }

    private FRANQUICIA franquicia(String numeroTarjeta) {
        if (numeroTarjeta.length() == 16
                && isNumeric(numeroTarjeta)
                && isInRange(numeroTarjeta.substring(0, 2), 51, 55)) {
            return FRANQUICIA.MASTERCARD;
        } else if (numeroTarjeta.length() == 16
                && isNumeric(numeroTarjeta)
                && numeroTarjeta.startsWith("4")) {
            return FRANQUICIA.VISA;
        } else if (numeroTarjeta.length() == 15
                && isNumeric(numeroTarjeta)
                && (numeroTarjeta.startsWith("34") || numeroTarjeta.startsWith("37"))) {
            return FRANQUICIA.AMEX;
        }
        return null;
    }

    private boolean isNumeric(String numeroTarjeta) {
        return numeroTarjeta.chars().allMatch(Character::isDigit);
    }

    private boolean isInRange(String prefix, int min, int max) {
        int num = Integer.parseInt(prefix);
        return num >= min && num <= max;
    }
}
