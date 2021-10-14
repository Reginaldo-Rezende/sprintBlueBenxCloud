package br.com.sprintBlueBenxCloud.sprintBlueBenxCloud.controller;

import br.com.sprintBlueBenxCloud.sprintBlueBenxCloud.model.Documento;
import br.com.sprintBlueBenxCloud.sprintBlueBenxCloud.repository.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import java.util.Optional;


@Controller
public class DocumentoController {

    @Autowired
    private DocumentoRepository documentoRepository;



    @RequestMapping(method = RequestMethod.GET,value = "/cadastrodocumento")
    public ModelAndView inicio(){
        ModelAndView modelAndView= new ModelAndView("cadastro/cadastrodocumento");
        modelAndView.addObject("documentoobj", new Documento());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST ,value="**/salvardocumento")
    public ModelAndView salvar(Documento documento){
        documentoRepository.save(documento);
        ModelAndView andView= new ModelAndView("cadastro/cadastrodocumento");
        Iterable<Documento> documentoIt = documentoRepository.findAll();
        andView.addObject("documentos",documentoIt);
        andView.addObject("documentoobj", new Documento());
        return andView;
    }

    @RequestMapping(method=RequestMethod.GET,value="/listadocumento")
    public ModelAndView documentos(){
        ModelAndView andView= new ModelAndView("cadastro/cadastrodocumento");
        Iterable<Documento> documentoIt = documentoRepository.findAll();
        andView.addObject("documentos",documentoIt);
        andView.addObject("documentoobj", new Documento());
        return andView;
    }
    @GetMapping("/editardocumento/{iddocumento}")
    public ModelAndView editar(@PathVariable("iddocumento")Long iddocumento){
        Optional<Documento> documento= documentoRepository.findById(iddocumento);
        ModelAndView modelAndView= new ModelAndView("cadastro/cadastrodocumento");
        modelAndView.addObject("documentoobj",documento.get());
        return modelAndView;
    }

    @GetMapping("/excluirdocumento/{iddocumento}")
    public ModelAndView excluir(@PathVariable("iddocumento")Long iddocumento){
        documentoRepository.deleteById(iddocumento);
        ModelAndView modelAndView= new ModelAndView("cadastro/cadastrodocumento");
        modelAndView.addObject("documentos",documentoRepository.findAll());
        modelAndView.addObject("documentoobj",new Documento());
        return modelAndView;
    }


}




