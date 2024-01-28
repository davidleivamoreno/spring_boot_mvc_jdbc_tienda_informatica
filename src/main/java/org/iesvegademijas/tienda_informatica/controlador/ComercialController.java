package org.iesvegademijas.tienda_informatica.controlador;

import org.iesvegademijas.tienda_informatica.modelo.Cliente;
import org.iesvegademijas.tienda_informatica.modelo.Comercial;
import org.iesvegademijas.tienda_informatica.modelo.Fabricante;
import org.iesvegademijas.tienda_informatica.modelo.Pedido;
import org.iesvegademijas.tienda_informatica.servicio.ComercialService;
import org.iesvegademijas.tienda_informatica.servicio.FabricanteService;
import org.iesvegademijas.tienda_informatica.servicio.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Scanner;


import java.util.List;
@Controller
public class ComercialController {

    private static final Logger logger = LoggerFactory.getLogger(ComercialController.class);

    @Autowired
    private ComercialService comercialService;
    @Autowired
    private PedidoService pedidoService;
    @GetMapping("/comerciales")
    public String listar(Model model) {

        List<Comercial> listAllFab =  comercialService.listAll();
        model.addAttribute("listaComerciales", listAllFab);

        return "comerciales";

    }

    @GetMapping("/comerciales/{id}")
    public String detalle(Model model, @PathVariable Integer id ) {

        Comercial comercial = comercialService.one(id);
        model.addAttribute("comercial", comercial);
        List<Pedido> listaPedidos=pedidoService.listAllById(id);
        model.addAttribute("listapedidos",listaPedidos);
        Double media =pedidoService.media(id);
        model.addAttribute(media);
        Double sumatorio= comercialService.sumatorioPedido(id);
        model.addAttribute(sumatorio);
        List<Cliente> listadoClientes=comercialService.listaClienteCosto(id);
        model.addAttribute(listadoClientes);
        return "detalle-comercial";

    }

    @GetMapping("/comerciales/crear")
    public String crear(Model model) {

        Comercial comercial = new Comercial();
        model.addAttribute("comercial", comercial);

        return "crear-comercial";

    }

    @PostMapping("/comerciales/crear")
    public RedirectView submitCrear(@ModelAttribute("comercial") Comercial comercial) {

        comercialService.newComercial(comercial);
        return new RedirectView("/comerciales") ;

    }

    @GetMapping("/comerciales/editar/{id}")
    public String editar(Model model, @PathVariable Integer id) {

        Comercial comercial = comercialService.one(id);
        model.addAttribute("comercial", comercial);

        return "editar-comercial";

    }


    @PostMapping("/comerciales/editar/{id}")
    public RedirectView submitEditar(@ModelAttribute("comercial") Comercial comercial) {

        comercialService.replaceComercial(comercial);

        return new RedirectView("/comerciales");
    }

    @PostMapping("/comerciales/borrar/{id}")
    public RedirectView submitBorrar(@PathVariable Integer id) {
        logger.warn("¡Advertencia! La condición no se cumple. ¿Aceptar? (s/n)");
        
        comercialService.deleteComercial(id);

        return new RedirectView("/comerciales");
    }

}
