package com.matheushudson.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.matheushudson.cursomc.domain.Categoria;
import com.matheushudson.cursomc.domain.Cidade;
import com.matheushudson.cursomc.domain.Cliente;
import com.matheushudson.cursomc.domain.Endereco;
import com.matheushudson.cursomc.domain.Estado;
import com.matheushudson.cursomc.domain.ItemPedido;
import com.matheushudson.cursomc.domain.Pagamento;
import com.matheushudson.cursomc.domain.PagamentoComBoleto;
import com.matheushudson.cursomc.domain.PagamentoComCartao;
import com.matheushudson.cursomc.domain.Pedido;
import com.matheushudson.cursomc.domain.Produto;
import com.matheushudson.cursomc.domain.enums.EstadoPagamento;
import com.matheushudson.cursomc.domain.enums.Perfil;
import com.matheushudson.cursomc.domain.enums.TipoCliente;
import com.matheushudson.cursomc.repositories.CategoriaRepository;
import com.matheushudson.cursomc.repositories.CidadeRepository;
import com.matheushudson.cursomc.repositories.ClienteRepository;
import com.matheushudson.cursomc.repositories.EnderecoRepository;
import com.matheushudson.cursomc.repositories.EstadoRepository;
import com.matheushudson.cursomc.repositories.ItemPedidoRepository;
import com.matheushudson.cursomc.repositories.PagamentoRepository;
import com.matheushudson.cursomc.repositories.PedidoRepository;
import com.matheushudson.cursomc.repositories.ProdutoRepository;

@Service
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	

	public void instantiateTestDatabase() throws ParseException {

		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		Categoria cat3 = new Categoria(null, "Casa mesa e banhos");
		Categoria cat4 = new Categoria(null, "Alvenarias");
		Categoria cat5 = new Categoria(null, "Acessorios");
		Categoria cat6 = new Categoria(null, "Genericos");
		Categoria cat7 = new Categoria(null, "Geral");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de escritório", 300.00);
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Colcha", 200.00);
		Produto p7 = new Produto(null, "TV true color", 1200.00);
		Produto p8 = new Produto(null, "Roçadeira", 800.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 180.00);
		Produto p11 = new Produto(null, "Shampoo", 90.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));

		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "Uberlândia", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "matheushudson2013@gmail.com", "36378912377", TipoCliente.PESSOAFISICA, pe.encode("123456") );
		cli1.getTelefones().addAll(Arrays.asList("274564641", "2368487411"));
		
		Cliente cli2 = new Cliente(null, "Ana Martins", "matheushudson2018@hotmail.com", "15656504760", TipoCliente.PESSOAFISICA, pe.encode("123456") );
		cli2.getTelefones().addAll(Arrays.asList("4464001", "454464690"));
		cli2.addPerfil(Perfil.ADMIN);
		
		
		Endereco e1 = new Endereco(null, "Rua lopes", "300", "Apto 505", "Praça", "5221", cli1, c1);
		Endereco e2 = new Endereco(null, "Av Juan", "1545", "casa 211", "Centro", "1561464", cli1, c2);
		Endereco e3 = new Endereco(null, "Av Joaquim Silverio", "0264", "Apartamento 125", "Planada", "121031", cli2, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		cli2.getEnderecos().addAll(Arrays.asList(e3));
		
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(e1,e2,e3));
		
		SimpleDateFormat sdf = 	new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null,sdf.parse("22/08/2020 11:15"), cli1, e1);
		Pedido ped2 = new Pedido(null,sdf.parse("18/11/2020 12:37"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null,EstadoPagamento.PEDENTE, ped2, sdf.parse("19/11/2020 10:07"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1,p1,0.00,1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1,p3	,0.00,2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2,p2	,100.00,1, 800.00);
		
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
	}
}
