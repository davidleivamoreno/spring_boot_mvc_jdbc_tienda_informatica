package org.iesvegademijas.tienda_informatica.servicio;

import org.iesvegademijas.tienda_informatica.dao.ClienteDAO;
import org.iesvegademijas.tienda_informatica.dao.ComercialDAO;
import org.iesvegademijas.tienda_informatica.modelo.Cliente;
import org.iesvegademijas.tienda_informatica.modelo.Comercial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ClienteService {
    @Autowired
    private ClienteDAO clienteDAO;
    private ClienteService clienteService;

    public List<Cliente> listAll() {

        return clienteDAO.getAll();

    }
    public void setClienteService(ClienteService clienteService){
        this. clienteService=clienteService;
    }

    public Cliente one(Integer id) {
        Optional<Cliente> optCom = clienteDAO.find(id);
        if (optCom.isPresent())
            return optCom.get();
        else
            return null;
    }

    public void newCliente(Cliente cliente) {

        clienteDAO.create(cliente);

    }
    public List<Comercial> listadoComerciales(int id){
        return clienteDAO.getAllByCliente(id);
    }

    public void replaceCliente(Cliente cliente) {

        clienteDAO.update(cliente);

    }

    public void deleteCliente(int id) {

        clienteDAO.delete(id);

    }
    public int calcularConteoPedidosUltimoTrimestre(Cliente cliente) {
        return clienteDAO.calcularConteoPedidosUltimoTrimestre(cliente);
    }

    public int calcularConteoPedidosUltimoSemestre(Cliente cliente) {
        return clienteDAO.calcularConteoPedidosUltimoSemestre(cliente);
    }

    public int calcularConteoPedidosUltimoAnio(Cliente cliente) {
        return clienteDAO.calcularConteoPedidosUltimoAnio(cliente);
    }

    public int calcularConteoPedidosUltimoLustro(Cliente cliente) {
        return clienteDAO.calcularConteoPedidosUltimoLustro(cliente);
    }

}
