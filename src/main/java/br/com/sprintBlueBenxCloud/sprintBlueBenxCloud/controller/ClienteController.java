package br.com.sprintBlueBenxCloud.sprintBlueBenxCloud.controller;

import br.com.sprintBlueBenxCloud.sprintBlueBenxCloud.model.Cliente;
import br.com.sprintBlueBenxCloud.sprintBlueBenxCloud.repository.ClienteRepository;
import br.com.sprintBlueBenxCloud.sprintBlueBenxCloud.repository.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import java.util.Optional;

@Controller
public class ClienteController {


    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private DocumentoRepository documentoRepository;


    @RequestMapping(method = RequestMethod.GET,value = "/cadastrocliente")
    public ModelAndView inicio(){
        ModelAndView modelAndView= new ModelAndView("cadastro/cadastrocliente");
        modelAndView.addObject("clienteobj", new Cliente());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST ,value="**/salvarcliente")
    public ModelAndView salvar(Cliente cliente){
        clienteRepository.save(cliente);
        ModelAndView andView= new ModelAndView("cadastro/cadastrocliente");
        Iterable<Cliente> clienteIt = clienteRepository.findAll();
        andView.addObject("clientes",clienteIt);
        andView.addObject("clienteobj", new Cliente());
        return andView;
    }

    @RequestMapping(method=RequestMethod.GET,value="/listaclientes")
    public ModelAndView clientes(){
        ModelAndView andView= new ModelAndView("cadastro/cadastrocliente");
        Iterable<Cliente> clienteIt = clienteRepository.findAll();
        andView.addObject("clientes",clienteIt);
        andView.addObject("clienteobj", new Cliente());
        return andView;
    }
    @GetMapping("/editarcliente/{idcliente}")
    public ModelAndView editar(@PathVariable("idcliente")Long idcliente){
        Optional<Cliente> cliente= clienteRepository.findById(idcliente);
        ModelAndView modelAndView= new ModelAndView("cadastro/cadastrocliente");
        modelAndView.addObject("clienteobj",cliente.get());
        return modelAndView;
    }

    @GetMapping("/excluircliente/{idcliente}")
    public ModelAndView excluir(@PathVariable("idcliente")Long idcliente){
        clienteRepository.deleteById(idcliente);
        ModelAndView modelAndView= new ModelAndView("cadastro/cadastrocliente");
        modelAndView.addObject("clientes",clienteRepository.findAll());
        modelAndView.addObject("clienteobj",new Cliente());
        return modelAndView;
    }

}