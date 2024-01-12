package org.iesvegademijas.tienda_informatica.controlador;

import org.iesvegademijas.tienda_informatica.modelo.Comercial;
import org.iesvegademijas.tienda_informatica.modelo.Fabricante;
import org.iesvegademijas.tienda_informatica.servicio.ComercialService;
import org.iesvegademijas.tienda_informatica.servicio.FabricanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
@Controller
public class ComercialController {
    @Autowired
    private ComercialService comercialService;

    @GetMapping("/comerciales")
    public String listar(Model model) {

        List<Comercial> listAllFab =  comercialService.listAll();
        model.addAttribute("listaComerciales", listAllFab);

        return "comerciales";

    }

    @GetMapping("/fabricantes/{id}")
    public String detalle(Model model, @PathVariable Integer id ) {

        Comercial comercial = comercialService.one(id);
        model.addAttribute("comercial", comercial);

        return "detalle-comercial";

    }

    @GetMapping("/fabricantes/crear")
    public String crear(Model model) {

        Fabricante fabricante = new Fabricante();
        model.addAttribute("fabricante", fabricante);

        return "crear-fabricante";

    }

    @PostMapping("/fabricantes/crear")
    public RedirectView submitCrear(@ModelAttribute("fabricante") Fabricante fabricante) {

        fabricanteService.newFabricante(fabricante);

        return new RedirectView("/fabricantes") ;

    }

    @GetMapping("/fabricantes/editar/{id}")
    public String editar(Model model, @PathVariable Integer id) {

        Fabricante fabricante = fabricanteService.one(id);
        model.addAttribute("fabricante", fabricante);

        return "editar-fabricante";

    }


    @PostMapping("/fabricantes/editar/{id}")
    public RedirectView submitEditar(@ModelAttribute("fabricante") Fabricante fabricante) {

        fabricanteService.replaceFabricante(fabricante);

        return new RedirectView("/fabricantes");
    }

    @PostMapping("/fabricantes/borrar/{id}")
    public RedirectView submitBorrar(@PathVariable Integer id) {

        fabricanteService.deleteFabricante(id);

        return new RedirectView("/fabricantes");
    }

}
