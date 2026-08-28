package com.example.LAB1GTICS20262_20222366.controller;


import com.example.LAB1GTICS20262_20222366.entity.Equipo;
import com.example.LAB1GTICS20262_20222366.repository.EquipoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    private final EquipoRepository equipoRepository;

    public HomeController(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    @GetMapping("/equipo")
    public String listarEquipo(
            @RequestParam(required = false) String valor,
            Model model) {

        List<Equipo> equipo;

        if (valor == null || valor.trim().isEmpty()) {

            equipo = equipoRepository.findAll();

        } else {

            switch (valor) {

                case "codigo":
                    equipo = equipoRepository.findByCodigoContainingIgnoreCase(valor);
                    break;

                default:
                    equipo = equipoRepository.findAll();
                    break;
            }
        }

        model.addAttribute("equipo", equipo);
        model.addAttribute("valor", valor);

        return "equipo";
    }

    //Mostrar formulario
    @GetMapping("/equipo/nuevo")
    public String mostrarFormulario(Model model) {

        model.addAttribute("equipo", new Equipo());

        return "nuevo-equipo";
    }

    //Guardamos equipo
    @PostMapping("/equipo/guardar")
    public String guardarEquipo(@ModelAttribute Equipo equipo) {

        equipoRepository.save(equipo);


        return "redirect:/equipo";
    }






}
