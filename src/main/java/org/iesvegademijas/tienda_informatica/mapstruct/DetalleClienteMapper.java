package org.iesvegademijas.tienda_informatica.mapstruct;

import org.iesvegademijas.tienda_informatica.dto.ClienteDTO;
import org.iesvegademijas.tienda_informatica.dto.ComercialDTO;
import org.iesvegademijas.tienda_informatica.modelo.Cliente;
import org.iesvegademijas.tienda_informatica.modelo.Comercial;
import org.iesvegademijas.tienda_informatica.servicio.ClienteService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface DetalleClienteMapper {


    ClienteDTO clienteToClienteDTO(Cliente cliente);
    ComercialDTO comercialToComercialDTO(Comercial comercial);
    default List<ComercialDTO> mapComerciales(List<Comercial> comerciales) {
        return comerciales.stream()
                .map(this::comercialToComercialDTO)
                .collect(Collectors.toList());
    }


}
