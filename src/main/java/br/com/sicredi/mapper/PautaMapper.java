package br.com.sicredi.mapper;

import br.com.sicredi.model.dto.PautaDto;
import br.com.sicredi.model.entity.Pauta;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PautaMapper {
    PautaMapper INSTANCE = Mappers.getMapper(PautaMapper.class);
    Pauta dtoToPauta(PautaDto dto);

    PautaDto pautaToDto(Pauta pauta);

}
