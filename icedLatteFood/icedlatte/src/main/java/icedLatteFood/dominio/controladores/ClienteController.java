package icedLatteFood.dominio.controladores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import icedLatteFood.persistencia.ClienteDAO;
import icedLatteFood.dominio.entidades.Cliente;

@Controller
public class ClienteController{
    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ClienteDAO clienteDAO;

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        logger.info("Obteniendo todos los clientes");
        List<Cliente> clientes = clienteDAO.findAllClientes();
        return ResponseEntity.ok(clientes);
    }

    //otros metodos
}