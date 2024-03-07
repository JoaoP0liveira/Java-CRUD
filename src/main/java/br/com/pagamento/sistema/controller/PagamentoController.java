package br.com.pagamento.sistema.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.pagamento.sistema.exception.PagamentoNotFoundException;
import br.com.pagamento.sistema.models.Pagamento;
import br.com.pagamento.sistema.service.IPagamentoService;

@Controller
@RequestMapping("/pagamento")
public class PagamentoController {

	@Autowired
	private IPagamentoService service;

	@PostMapping("/salvar")
	public String salvarpagamento(@ModelAttribute Pagamento pagamento, Model model) {

		service.salvarPagamento(pagamento);
		// salvar novamente (redundante) retorna o id do nosso pagamento
		Long id = service.salvarPagamento(pagamento).getId();
		// mensagem para o usuario
		String mensagem = "Salvo com o id: " + id + " com sucesso!";
		// adicionado mensagem na resposta
		model.addAttribute(mensagem);
		return "redirect:listar";
	}

	@PostMapping("/atualizar")
	public String atualizarPagamento(@ModelAttribute Pagamento pagamento, RedirectAttributes attributes) {
		service.atualizarPagamento(pagamento);
		Long id = pagamento.getId();
		attributes.addAttribute("message", "Pagamento com o id: " + id + " foi atualizado");
		return "redirect:listar";
	}

	@GetMapping("/deletar")
	public String deletarPagamento(@RequestParam Long id, RedirectAttributes attributes) {
		try {
			service.deletarPagamento(id);
			attributes.addAttribute("message", "O Pagamento foi deletado, id: " + id);
		} catch (PagamentoNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
		}

		return "redirect:listar";
	}

	@GetMapping("/")
	public String paginaInicial() {
		return "paginaInicial";
	}

	@GetMapping("/adicionar")
	public String adicionarPagamento() {
		return "adicionarPagamento";
	}

	@GetMapping("/editar")
	public String editarPagamento(Model model, RedirectAttributes attributes, @RequestParam Long id) {
		String page;
		try {
			Pagamento pagamento = service.buscarPagamentoUnico(id);
			model.addAttribute("pagamento", pagamento);
			page = "editarPagamento";
		} catch (PagamentoNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			page = "redirect:listarPagamento";
		}

		return page;
	}

	@GetMapping("/listar")
	public String listarPagamento(@RequestParam(value = "message", required = false) String message, Model model) {
		// /user /listar? message=hello%word
		// listar dos pagamentos
		List<Pagamento> pagamentos = service.buscarTodosPagamentos();
		model.addAttribute("lista", pagamentos);
		// mensagem caso exista
		model.addAttribute("mensagem", message);
		return "listarPagamento";
	}

}
