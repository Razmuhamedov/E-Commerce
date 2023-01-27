package com.example.magazine.controller;

import com.example.magazine.dto.rate.RateCreateDto;
import com.example.magazine.dto.rate.RateDto;
import com.example.magazine.service.RateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rate")
public class RateController {

    private final RateService rateService;

    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createRate(@RequestBody @Valid RateCreateDto dto){
        Double result = rateService.create(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        RateDto result = rateService.getById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/byProfile/{id}")
    public ResponseEntity<?> getByProfileId(@PathVariable("id") Long id,
                                            @RequestParam("page") int page,
                                            @RequestParam("size") int size){
        List<RateDto> result = rateService.getByProfileId(id, page, size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/byProduct/{id}")
    public ResponseEntity<?> getByProductId(@PathVariable("id") Long id,
                                            @RequestParam("page") int page,
                                            @RequestParam("size") int size){
        List<RateDto> result = rateService.getByProductId(id, page, size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(@RequestParam("page") int page,
                                    @RequestParam("size") int size){
        List<RateDto> result = rateService.getAll(page, size);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        String result = rateService.delete(id);
        return ResponseEntity.ok(result);
    }

}
