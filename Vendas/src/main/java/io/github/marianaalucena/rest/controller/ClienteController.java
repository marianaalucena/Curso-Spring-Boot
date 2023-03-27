package io.github.marianaalucena.rest.controller;

import ch.qos.logback.core.net.server.Client;
import io.github.marianaalucena.domain.entity.Cliente;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller //recebe requisições http
@RequestMapping("/api/clientes")
public class ClienteController {

    @ResponseBody
    @RequestMapping(
            value = "/hello/{nome}",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public String helloCliente(@PathVariable("nome") String nomeCliente, @RequestBody Cliente cliente){
        return String.format("Hello %s ", nomeCliente);
    }




}
