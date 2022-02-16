package com.godinho.appproduct.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import com.godinho.appproduct.model.Product;
import com.godinho.appproduct.repository.ProductRepository;

@Service
@Transactional
@Controller
public class ProductController {

	@Autowired
	private ProductRepository rep;
	
	public List<Product> listAll() {
		return rep.findAll();
	}
	
	public Product getProduct(long id) {
		return rep.findById(id).get();
	}
	
	public void save(Product product) {
		rep.save(product);
	}
	
	public void delete(long id) {
		rep.deleteById(id);
	}
	
	
	@RequestMapping("/")
	public String callList(Model model) {
		List<Product> listProduct = listAll();
		model.addAttribute("listProduct", listProduct);
		return "index";
	}
	
	@RequestMapping("newProduct")
	public ModelAndView callNew() {
		ModelAndView model = new ModelAndView("newProduct");
		Product product = new Product();
		model.addObject("product", product);
		return model;
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView callEdit(@PathVariable(name = "id") long id) {
		ModelAndView model = new ModelAndView("edit.html");
		Product product = getProduct(id);
		model.addObject(product);
		return model;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String callSave(@ModelAttribute("product") Product product) {
		save(product);
		return "redirect:/";
	}
	
	@RequestMapping("/delete/{id}")
	public String callDelete(@PathVariable(name = "id") long id) {
		delete(id);
		return "redirect:/";
	}
}
