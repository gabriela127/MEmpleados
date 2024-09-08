package com.gv.MEmpleados.controller;

import com.gv.MEmpleados.entity.*;
import com.gv.MEmpleados.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empleados")
@Validated
public class EmpleadoController {

    private final IEmpleadosRepository empleadoRepository;

    public EmpleadoController(IEmpleadosRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @PostMapping
    public ResponseEntity<Empleados> crearEmpleado(@Validated @RequestBody Empleados empleado) {
        Empleados nuevoEmpleado = empleadoRepository.save(empleado);
        return new ResponseEntity<>(nuevoEmpleado, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Empleados> listarEmpleados() {
        return empleadoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleados> obtenerEmpleado(@PathVariable Long id) {
        Optional<Empleados> empleado = empleadoRepository.findById(id);
        return empleado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleados> actualizarEmpleado(@PathVariable Long id, @Validated @RequestBody Empleados empleadoActualizado) {
        if (!empleadoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        empleadoActualizado.setId(id);
        Empleados empleado = empleadoRepository.save(empleadoActualizado);
        return ResponseEntity.ok(empleado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Long id) {
        if (!empleadoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        empleadoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
