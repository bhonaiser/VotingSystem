package br.com.sicredi.repository;


import br.com.sicredi.model.entity.Pauta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends MongoRepository<Pauta, String> {


    Pauta findByName(String name);
}
