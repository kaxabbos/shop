package com.shop.controller;

import com.shop.controller.main.Attributes;
import com.shop.model.Cart;
import com.shop.model.Product;
import com.shop.model.Stat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartCont extends Attributes {
    @GetMapping()
    String cart(Model model) {
        AddAttributesCart(model);
        return "cart";
    }

    @PostMapping("/add/{idProduct}")
    String cartAdd(@RequestParam int quantity, @PathVariable Long idProduct) {
        Cart cart = cartRepo.saveAndFlush(new Cart(quantity, getUser().getId()));
        Product product = productRepo.getReferenceById(idProduct);
        product.addCart(cart);
        productRepo.save(product);
        cartRepo.save(cart);
        return "redirect:/product/all";
    }

    @PostMapping("/edit/{idCart}")
    String cartEdit(@RequestParam int quantity, @PathVariable Long idCart) {
        Cart cart = cartRepo.getReferenceById(idCart);
        cart.setQuantity(quantity);
        cartRepo.save(cart);
        return "redirect:/cart";
    }

    @GetMapping("/delete/{idCart}")
    String cartDelete(@PathVariable Long idCart) {
        Cart cart = cartRepo.getReferenceById(idCart);
        Product product = cart.getProduct();
        product.removeCart(cart);
        cartRepo.delete(cart);
        productRepo.save(product);
        return "redirect:/cart";
    }

    @GetMapping("/delete/all")
    String cartDeleteAll() {
        List<Cart> cartList = cartRepo.findAllByUser(getUser().getId());
        for (Cart i : cartList) {
            Product product = i.getProduct();
            product.removeCart(i);
            cartRepo.delete(i);
            productRepo.save(product);
        }
        return "redirect:/cart";
    }

    @GetMapping("/buy")
    String buy(Model model) {
        List<Cart> cartList = cartRepo.findAllByUser(getUser().getId());

        for (Cart i : cartList) {
            if (i.getQuantity() > i.getProduct().getQuantity()) {
                AddAttributesCart(model);
                model.addAttribute("message", "Недостаточно продуктов для покупки");
                return "cart";
            }
        }

        for (Cart i : cartList) {
            Product product = i.getProduct();

            Stat stat = i.getProduct().getStat();

            stat.setQuantity(stat.getQuantity() + i.getQuantity());
            stat.setPrice(stat.getPrice() + (i.getQuantity() * product.getPrice()));

            product.setQuantity(product.getQuantity() - i.getQuantity());

            product.removeCart(i);
            cartRepo.delete(i);
            productRepo.save(product);
        }
        return "redirect:/cart";
    }
}
