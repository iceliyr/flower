package cn.edu.scnu.controller;



import cn.edu.scnu.entity.Flower;
import cn.edu.scnu.entity.MyFlower;
import cn.edu.scnu.service.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private FlowerService flowerService;

    @RequestMapping("/admin")
    public String index(Model model){
        List<Flower> flowerlist=flowerService.findAll();
        model.addAttribute("flowers",flowerlist);
        return "flowerindex";
    }
    @RequestMapping("/admin/flowerindex")
    public String flowerindex(Model model){
        List<Flower> flowerlist=flowerService.findAll();
        model.addAttribute("flowers",flowerlist);
        return "flowerindex";
    }
    @RequestMapping("/admin/floweradd")
    public String floweradd(Model model){
        List<String> fclasslist=flowerService.findfclass();
        model.addAttribute("fclasses",fclasslist);
        return "floweradd";
    }
    @RequestMapping("/admin/saveFlower")
    public String saveFlower(MyFlower myFlower, Model model){
        String msg=flowerService.saveFlower(myFlower);
        model.addAttribute("msg",msg);
        return "redirect:/admin/flowerindex";
    }
    @RequestMapping("/admin/flowerdelete")
    public String deleteFlower(Integer flowerid){
        flowerService.deleteById(flowerid);
        return "redirect:/admin/flowerindex";
    }

    @RequestMapping("/admin/flowerupdate")
    public String updateFlower(String flowerid, Model model){
        List<String> fclasses=flowerService.findfclass();
        model.addAttribute("fclasses",fclasses);
        Flower flower=flowerService.findFlowerById(flowerid);
        model.addAttribute("flower",flower);
        return "flowerupdate";
    }

    @RequestMapping("/admin/saveUpdate")
    public String saveUpdate(Flower flower){
        flowerService.saveUpdate(flower) ;
        return "redirect:/admin/flowerindex";
    }
}