package Flaming_Temptation_Coffee;

import Flaming_Temptation_Coffee.Produto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("carrinho")
public class HomeController {

    @ModelAttribute("carrinho")
    public List<Produto> carrinho() {
        return new ArrayList<>();
    }

    @GetMapping("/")
    public String index(Model model, @ModelAttribute("carrinho") List<Produto> carrinho) {
        List<Produto> produtos = List.of(
            new Produto("Espresso", 10.00),
            new Produto("Latte", 15.00)
        );
        model.addAttribute("produtos", produtos);
        model.addAttribute("carrinho", carrinho);
        return "index";
    }

    @PostMapping("/adicionar")
    public String adicionarAoCarrinho(@RequestParam String nome, @RequestParam double preco,
                                      @ModelAttribute("carrinho") List<Produto> carrinho) {
        carrinho.add(new Produto(nome, preco));
        return "redirect:/";
    }

    @PostMapping("/remover")
    public String removerDoCarrinho(@RequestParam int index,
                                    @ModelAttribute("carrinho") List<Produto> carrinho) {
        if (index >= 0 && index < carrinho.size()) {
            carrinho.remove(index);
        }
        return "redirect:/";
    }

    @PostMapping("/limpar")
    public String limparCarrinho(@ModelAttribute("carrinho") List<Produto> carrinho) {
        carrinho.clear();
        return "redirect:/";
    }
}