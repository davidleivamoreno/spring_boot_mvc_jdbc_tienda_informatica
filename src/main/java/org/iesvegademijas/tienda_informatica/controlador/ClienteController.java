package org.iesvegademijas.tienda_informatica.controlador;

import jakarta.validation.Valid;
import org.iesvegademijas.tienda_informatica.mapstruct.DetalleClienteMapper;
import org.iesvegademijas.tienda_informatica.modelo.Cliente;
import org.iesvegademijas.tienda_informatica.modelo.Comercial;
import org.iesvegademijas.tienda_informatica.servicio.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class ClienteController {
    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes")
    public String listar(Model model) {

        List<Cliente> listAllFab =  clienteService.listAll();
        model.addAttribute("listaClientes", listAllFab);

        return "clientes";

    }
    @Autowired
     DetalleClienteMapper clienteMapper;

    @Autowired
    private DetalleClienteMapper detalleClienteMapper;

    @GetMapping("/clientes/{id}")
    public String detalle(Model model, @PathVariable Integer id) {
        Cliente cliente = clienteService.one(id);
        model.addAttribute("cliente", cliente);

        // Obtener la lista de comerciales asociados al cliente
        List<Comercial> comerciales = clienteService.listadoComerciales(id);

        // Mapear la lista de comerciales a una lista de ComercialDTO
        int conteotrimestre =clienteService.calcularConteoPedidosUltimoTrimestre(cliente);

        model.addAttribute("conteoPedidosUltimoTrimestre", conteotrimestre);
        int conteoSemestre=clienteService.calcularConteoPedidosUltimoSemestre(cliente);

        model.addAttribute("conteoPedidosUltimoSemestre", conteoSemestre);

        int conteoAnio =clienteService.calcularConteoPedidosUltimoAnio(cliente);

        model.addAttribute("conteoPedidosUltimoAnio", conteoAnio);
        int conteoLustro =clienteService.calcularConteoPedidosUltimoLustro(cliente);

        model.addAttribute("conteoPedidosUltimoLustro", conteoLustro);

        // Agregar la lista de comerciales al modelo
        model.addAttribute("comerciales", comerciales);

        return "detalle-cliente";
    }


    @GetMapping("/clientes/crear")
    public String crear(Model model) {

        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);

        return "crear-cliente";

    }


    @PostMapping("/clientes/crear")
    public String submitCrear(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult bindingResult, Model model) {

       if(bindingResult.hasErrors()){
           model.addAttribute("cliente",cliente);
           return "crear-cliente";
       }
       clienteService.newCliente(cliente);
        return "redirect:/clientes?newClienteID="+cliente.getId();

    }

    @GetMapping("/clientes/editar/{id}")
    public String editarCliente(Model model, @PathVariable Integer id) {

        Cliente cliente = clienteService.one(id);
        model.addAttribute("cliente", cliente);

        return "cliente-editar";

    }



    @PostMapping("/clientes/editar/{id}")
    public String submitEditarCliente(@ModelAttribute("cliente") Cliente cliente, BindingResult bindingResult,Model model) {


        if(bindingResult.hasErrors()){
            model.addAttribute("cliente",cliente);
            return "cliente-editar";
        }
        clienteService.replaceCliente(cliente);
        return "redirect:/clientes?editClienteID="+cliente.getId();

    }

    @PostMapping("/clientes/borrar/{id}")
    public RedirectView submitBorrar(@PathVariable Integer id) {
        logger.warn("¡Advertencia! La condición no se cumple. ¿Aceptar? (s/n)");
        clienteService.deleteCliente(id);

        return new RedirectView("/clientes");
    }
}
