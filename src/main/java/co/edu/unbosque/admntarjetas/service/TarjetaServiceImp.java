package co.edu.unbosque.admntarjetas.service;

import co.edu.unbosque.admntarjetas.exception.TarjetaNoAdmitidaException;
import co.edu.unbosque.admntarjetas.exception.TarjetaNotFoundException;
import co.edu.unbosque.admntarjetas.model.dto.TarjetaDto;
import co.edu.unbosque.admntarjetas.model.entity.Cliente;
import co.edu.unbosque.admntarjetas.model.entity.ESTADOTARJETA;
import co.edu.unbosque.admntarjetas.model.entity.FRANQUICIA;
import co.edu.unbosque.admntarjetas.model.entity.Tarjeta;
import co.edu.unbosque.admntarjetas.repo.ClienteRepo;
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
    @Autowired
    private ClienteRepo clienteRepo;

    @Override
    public void createTarjeta(TarjetaDto tarjetaDto) {
        if(tarjetaRepo.existsByNumeroTarjeta(tarjetaDto.getNumeroTarjeta())){
            throw new TarjetaNoAdmitidaException("Esta tarjetaDto ya existe");
        }
        tarjetaDto.setEstadoTarjeta(ESTADOTARJETA.ACTIVO);
        tarjetaDto.setCupoTotal(tarjetaDto.getCupoTotal());
        tarjetaDto.setCupoDisponible(tarjetaDto.getCupoTotal() - tarjetaDto.getCupoUsado());
        tarjetaDto.setCupoUsado(tarjetaDto.getCupoUsado());
        tarjetaDto.setCupoDisponible(tarjetaDto.getCupoTotal() - tarjetaDto.getCupoUsado());
        tarjetaDto.setFranquicia(franquicia(String.valueOf(tarjetaDto.getNumeroTarjeta())));
        if (tarjetaDto.getFranquicia() == null) {
            throw  new TarjetaNoAdmitidaException("Tarjeta no adminita");
        }
        Tarjeta tarjetaNueva = mapper.map(tarjetaDto, Tarjeta.class);
        tarjetaRepo.save((tarjetaNueva));
    }

    @Override
    public TarjetaDto updateTarjeta(TarjetaDto tarjeta) {

        TarjetaDto tarjetaToUpdate
                = mapper.map(tarjetaRepo.findByNumeroTarjeta(tarjeta.getNumeroTarjeta()), TarjetaDto.class);
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
    public List<TarjetaDto> listarTarjetas(Long idCliente) {
        return tarjetaRepo
                .findByCliente_IdCliente(idCliente)
                .stream()
                .map(t -> mapper.map(t,TarjetaDto.class)).toList();
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
