package com.example.NotCursedWork.Controllers;

import com.example.NotCursedWork.models.Order1;
import com.example.NotCursedWork.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ZapisController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/zapis")
    public String ZapisController(Model model){
        Iterable<Order1> order1s = orderRepository.findAll();
        model.addAttribute("order1s", order1s);
        return "zapis";
    }

    @GetMapping("/zapis/{id}")
    public String MoreZapisController(@PathVariable(value = "id") long id, Model model){
        if(!orderRepository.existsById(id)){
                   return "redirect:/index";
        }
        Optional<Order1> order1 = orderRepository.findById(id);
        ArrayList<Order1> res = new ArrayList<>();
        order1.ifPresent(res::add);
        model.addAttribute("order1", res);
        return "zapis-details";
    }

    @GetMapping("/zapis/{id}/edit")
    public String EditZapisController(@PathVariable(value = "id") long id, Model model){
        if(!orderRepository.existsById(id)){
            return "redirect:/index";
        }
        Optional<Order1> order1 = orderRepository.findById(id);
        ArrayList<Order1> res = new ArrayList<>();
        order1.ifPresent(res::add);
        model.addAttribute("order1", res);
        return "zapis-edit";
    }

    @PostMapping("/zapis/{id}/edit")
    public String ZapisUpdate(@PathVariable(value = "id") long id, @RequestParam double Price, @RequestParam String List_of_products, @RequestParam boolean Status, Model model){
        Order1 order1 = orderRepository.findById(id).orElseThrow();
        order1.setPrice(Price);
        order1.setList_of_products(List_of_products);
        order1.setStatus(Status);
        orderRepository.save(order1);
        return "redirect:/zapis";
    }
    @PostMapping("/zapis/{id}/remove")
    public String ZapisDelete(@PathVariable(value = "id") long id, Model model){
        Order1 order1 = orderRepository.findById(id).orElseThrow();
        orderRepository.delete(order1);
        return "redirect:/zapis";
    }

    @GetMapping("/zapis/add")
    public String ZapisAddController(Model model){
        return "zapis-add";
    }

    @PostMapping("/zapis/add")
    public String ZapishuController(@RequestParam String List_of_products, @RequestParam Long client_id, @RequestParam Long register_id, Model model){
        Order1 order1 = new Order1(List_of_products, client_id, register_id);
        orderRepository.save(order1);
        return "redirect:/zapis";
    }

    @GetMapping("/zapis/filter")
    public String ZapisFlt(Model model){
        return "zapis-filter";
    }

    @PostMapping("/zapis/filter")
    public String ZapisFilter(@RequestParam Long id, Model model){
        List<Order1> order1s = orderRepository.findAllbyidId(id);
        model.addAttribute("Order1s", order1s);
        return "zapis-filter";
    }
    @GetMapping("/zapis/filterv2")
    public String ZapisFilt(Model model){
        return "zapis-filterv2";
    }

    @PostMapping("/zapis/filterv2")
    public String ZapisFilter(Model model){
        List<Order1> order1s = orderRepository.findByPrice();
        model.addAttribute("Order1s", order1s);
        return "zapis-filterv2";
    }

    @GetMapping("/zapis/filterv3")
    public String ZapisFilte(Model model){
        return "zapis-filterv3";
    }

    @PostMapping("/zapis/filterv3")
    public String ZapisFilter1(@RequestParam String List_of_products, Model model){
        List<Order1> order1s = orderRepository.findAllbyProducts(List_of_products);
        model.addAttribute("Order1s", order1s);
        return "zapis-filterv3";
    }
}
