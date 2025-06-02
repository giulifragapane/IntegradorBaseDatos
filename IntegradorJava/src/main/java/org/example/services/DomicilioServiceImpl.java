package org.example.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.dao.DomicilioDaoImpl;
import org.example.models.Domicilio;

import java.sql.SQLException;
import java.util.List;
@NoArgsConstructor
//@AllArgsConstructor
@SuperBuilder
public class DomicilioServiceImpl implements GenericService<Domicilio>{
    private final DomicilioDaoImpl domicilioDao= new DomicilioDaoImpl();

    @Override
    public void guardar(Domicilio entidad) throws Exception{

        domicilioDao.guardar(entidad);
    }

    @Override
    public void eliminar(Domicilio entity) throws SQLException {

    }

    @Override
    public void actualizar(Domicilio entidad) {

    }

    @Override
    public Domicilio buscar(Domicilio entity) throws Exception {
        return null;
    }

    @Override
    public List<Domicilio> buscarTodos() throws Exception {
        return List.of();
    }

}
