package org.iesvegademijas.tienda_informatica.servicio;

import org.iesvegademijas.tienda_informatica.dao.ComercialDAO;
import org.iesvegademijas.tienda_informatica.dao.FabricanteDAO;
import org.iesvegademijas.tienda_informatica.dao.PedidoDAO;
import org.iesvegademijas.tienda_informatica.modelo.Cliente;
import org.iesvegademijas.tienda_informatica.modelo.Comercial;
import org.iesvegademijas.tienda_informatica.modelo.Fabricante;
import org.iesvegademijas.tienda_informatica.modelo.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ComercialService {

    @Autowired
    private ComercialDAO comercialDAO;
    @Autowired
    private PedidoDAO pedidoDAO;
    public List<Comercial> listAll() {

        return comercialDAO.getAll();

    }
    public List<Pedido> listAllpedido(){
        return pedidoDAO.getAll();
    }
    public List<Pedido> listAllpedido(int id){
        return pedidoDAO.getAllByComercial(id);
    }
    public double media(int id){
        return pedidoDAO.getMedia(pedidoDAO.getAllByComercial(id));
    }
    public Comercial one(Integer id) {
        Optional<Comercial> optCom = comercialDAO.find(id);
        if (optCom.isPresent())
            return optCom.get();
        else
            return null;
    }

    public void newComercial(Comercial comercial) {

        comercialDAO.create(comercial);

    }

    public void replaceComercial(Comercial comercial) {

        comercialDAO.update(comercial);

    }


    public void deleteComercial(int id) {

        comercialDAO.delete(id);

    }
    public List<Cliente> listaClienteCosto(int id){
        return pedidoDAO.getClientesOrdenadosPorCuantiaTotal(id);
    }
    public double sumatorioPedido(int id){
        return pedidoDAO.getSumatorio(pedidoDAO.getAllByComercial(id));
    }
}
