package org.iesvegademijas.tienda_informatica.controlador;

import org.iesvegademijas.tienda_informatica.modelo.Comercial;
import org.iesvegademijas.tienda_informatica.modelo.Pedido;
import org.iesvegademijas.tienda_informatica.servicio.ComercialService;
import org.iesvegademijas.tienda_informatica.servicio.PedidoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class PedidoController {
    private static final Logger logger = LoggerFactory.getLogger(PedidoController.class);

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/pedidos")
    public String listar(Model model) {

        List<Pedido> listAllFab =  pedidoService.listAll();
        model.addAttribute("listaPedidos", listAllFab);

        return "pedidos";

    }

    @GetMapping("/pedidos/{id}")
    public String detalle(Model model, @PathVariable Integer id ) {

        Pedido pedido = pedidoService.one(id);
        model.addAttribute("pedido", pedido);

        return "detalle-pedido";

    }

    @GetMapping("/pedidos/crear")
    public String crear(Model model) {

        Pedido pedido = new Pedido();
        model.addAttribute("comercial", pedido);

        return "crear-comercial";

    }

    @PostMapping("/pedidos/crear")
    public RedirectView submitCrear(@ModelAttribute("pedido") Pedido pedidos) {

        pedidoService.newPedido(pedidos);
        return new RedirectView("/pedidos") ;

    }
    @PostMapping("/pedidos/editar/{id}")
    public RedirectView submitEditar(@ModelAttribute("pedido") Pedido pedido) {

        pedidoService.replacePedido(pedido);

        return new RedirectView("/pedidos");
    }
    @GetMapping("/pedidos/editar/{id}")
    public String editar(Model model, @PathVariable Integer id) {

        Pedido pedido = pedidoService.one(id);
        model.addAttribute("pedido", pedido);

        return "editar-pedido";

    }
    @PostMapping("/pedidos/borrar/{id}")
    public RedirectView submitBorrar(@PathVariable Integer id) {
        logger.warn("¡Advertencia! La condición no se cumple. ¿Aceptar? (s/n)");
        pedidoService.deletePedido(id);

        return new RedirectView("/pedidos");
    }
}
