package com.gabrieleglvs.jornada_api.controller;

import com.gabrieleglvs.jornada_api.service.DepoimentoService;
import com.gabrieleglvs.jornada_api.service.DestinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class DestinoController {

    @Autowired
    private DestinoService service;

    @GetMapping("destinos")
    public ResponseEntity listarDestinos(){
        return service.listarDestinos();
    }
}
