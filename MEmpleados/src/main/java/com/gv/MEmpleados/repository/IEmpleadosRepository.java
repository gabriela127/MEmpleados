package com.gv.MEmpleados.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gv.MEmpleados.entity.Empleados;


@Repository
public interface IEmpleadosRepository  extends JpaRepository<Empleados, Long>{

}
