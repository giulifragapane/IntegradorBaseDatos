package org.example.dao;

import org.example.models.Persona;

public class PersonaDaoImpl implements GenericDao {
    public void save(Persona entity){
        //String query = "INSERT INTO persona (nombre, apellido, fr_key_domicilio) VALUES (?, ?, ?)";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmn = conn.getStat()){
            stmn.setString(1, entity.getNombre());
    }
}
}
