package com.digitro.service;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;


import com.digitro.dao.DaoException;
import com.digitro.dao.DocumentoDao;
import com.digitro.model.Documento;

public class DocumentoServiceMockTeste {

	// Teste Unitário
	@Test
	public void deveLancarErroQuandoSalvaDocumentoComId() throws DaoException {

		Documento documento = new Documento();
		documento.setId(1l);
		documento.setTitulo("xx");
		documento.setCorpo("Corpo texto pequeno");
		DocumentoDao documentoDao = Mockito.mock(DocumentoDao.class);
		when(documentoDao.salvar(documento)).thenReturn(null);
		DocumentoService documentoService = new DocumentoService();
		try {
			documentoService.salvar(documento);
			fail();
		} catch (RuntimeException e) {
			Assert.assertEquals("Ao salvar um documento o ID deve ser nulo.", e.getMessage());
		}

	}
	
	
//	@Test
//	public void deveLancarDaoExceptionQuandoSalvarDocumento() throws Exception  {
//		
//		Documento documento = new Documento();
//		documento.setTitulo("x000x");
//		documento.setCorpo("Corpo texto pequeno");
//
//		DocumentoDao documentoDao = Mockito.mock(DocumentoDao.class);
//		//doThrow(new DaoException("Ao salvar um documento. Motivo: ",null)).when(documentoDao.salvar(documento));
//		//when(documentoDao.salvar(documento)).thenThrow(new DaoException ("Ao salvar um documento. Motivo: ",null));
//		DocumentoService documentoService = new DocumentoService(documentoDao);
//		
//		try {
//			documentoService.salvar(documento);
//			fail();
//		} catch (RuntimeException e) {
//			Assert.assertEquals("Ao salvar um documento. Motivo: ", null);
//		
//		
//		}		
//		
//	}
	
	
	
	@Test
	public void deveLancarErroQuandoSalvarDocumentoComTituloInvalido() throws DaoException {
		Documento documento = new Documento();
		documento.setCorpo("Corpo texto pequeno");
		Assert.assertNotNull(documento);
		
		DocumentoDao documentoDao = Mockito.mock(DocumentoDao.class);
		when(documentoDao.salvar(documento)).thenReturn(null);
		DocumentoService documentoService = new DocumentoService();
		
		documento.setTitulo("");
		try {
			documentoService.salvar(documento);
			fail();
		} catch (RuntimeException e) {
			Assert.assertEquals("O titulo é inválido: titulo deve conter de 1 a 50 caracteres", e.getMessage());
		}
		
		documento.setTitulo(null);
		try {
			documentoService.salvar(documento);
			fail();
		} catch (RuntimeException e) {
			Assert.assertEquals("O titulo é inválido: titulo deve conter de 1 a 50 caracteres", e.getMessage());
		}
		
		String tituloMaisDe50Caracteres = "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";
				
		documento.setTitulo(tituloMaisDe50Caracteres);
		
		try {
			documentoService.salvar(documento);
			fail();
		} catch (RuntimeException e) {
			Assert.assertEquals("O titulo é inválido: titulo deve conter de 1 a 50 caracteres", e.getMessage());
		}
	}

	@Test
	public void deveLancaErroQuandoSalvarDocumentoComCorpoInvalido() throws DaoException {
		Documento documento = new Documento();
		documento.setTitulo("54543123123");
		Assert.assertNotNull(documento);
		
		DocumentoDao documentoDao = Mockito.mock(DocumentoDao.class);
		DocumentoService documentoService = new DocumentoService();
		when(documentoDao.salvar(documento)).thenReturn(null);

		
		
		
		documento.setCorpo("");
		try {
			documentoService.salvar(documento);
			fail();
		} catch (RuntimeException e) {
			Assert.assertEquals("O corpo é inválido: corpo deve conter de 1 a 500 caracteres", e.getMessage());
		}

		documento.setCorpo(null);
		try {
			documentoService.salvar(documento);
			fail();
		} catch (RuntimeException e) {
			Assert.assertEquals("O corpo é inválido: corpo deve conter de 1 a 500 caracteres", e.getMessage());
		}

		String corpoMaisDe500Caracteres = "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"
				+ "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"
				+ "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"
				+ "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"
				+ "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012234";

		documento.setCorpo(corpoMaisDe500Caracteres);
		try {
			documentoService.salvar(documento);
			fail();
		} catch (RuntimeException e) {
			Assert.assertEquals("O corpo é inválido: corpo deve conter de 1 a 500 caracteres", e.getMessage());
		}

	}

	@Test
	public void deveLancarErroQuantoAtualizarDocumentoSemId() throws DaoException {

		Documento documento = new Documento();
		documento.setTitulo("Titulo do documento");
		documento.setCorpo("Corpo texto pequeno");

		DocumentoDao documentoDao = Mockito.mock(DocumentoDao.class);
		when(documentoDao.atualizar(documento)).thenReturn(null);
		DocumentoService documentoService = new DocumentoService();
		try {
			documentoService.atualizar(documento);
			fail();
		} catch (RuntimeException e) {
			Assert.assertEquals("Ao atualizar um documento o ID não deve ser nulo.", e.getMessage());
		}
	}

	@Test
	public void deveLancarErroQuandoAtualizarDocumentoComTituloInvalido() throws DaoException {
		Documento documento = new Documento();
		documento.setId(1l);
		
		documento.setCorpo("Corpo texto pequeno");
		Assert.assertNotNull(documento);
		DocumentoDao documentoDao = Mockito.mock(DocumentoDao.class);
		when(documentoDao.atualizar(documento)).thenReturn(null);
		DocumentoService documentoService = new DocumentoService();
		

		documento.setTitulo("");
		try {
			documentoService.atualizar(documento);
			fail();
		} catch (RuntimeException e) {
			Assert.assertEquals("O titulo é inválido: titulo deve conter de 1 a 50 caracteres", e.getMessage());
		}
		
		documento.setTitulo(null);
		try {
			documentoService.atualizar(documento);
			fail();
		} catch (RuntimeException e) {
			Assert.assertEquals("O titulo é inválido: titulo deve conter de 1 a 50 caracteres", e.getMessage());
		}
		
		String tituloMaisDe50Caracteres = "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";
				
		documento.setTitulo(tituloMaisDe50Caracteres);
		
		
		try {
			documentoService.atualizar(documento);
			fail();
		} catch (RuntimeException e) {
			Assert.assertEquals("O titulo é inválido: titulo deve conter de 1 a 50 caracteres", e.getMessage());
		}
	}

	@Test
	public void deveLancarErroQuandoAtualizarDocumentoComCorpoInvalido() throws DaoException {
		Documento documento = new Documento();
		documento.setId(1l);
		documento.setTitulo("Titulo do documento");
		
		Assert.assertNotNull(documento);
		DocumentoDao documentoDao = Mockito.mock(DocumentoDao.class);
		when(documentoDao.atualizar(documento)).thenReturn(null);
		DocumentoService documentoService = new DocumentoService();
		
		
		documento.setCorpo("");
		try {
			documentoService.atualizar(documento);
			fail();
		} catch (RuntimeException e) {
			Assert.assertEquals("O corpo é inválido: corpo deve conter de 1 a 500 caracteres", e.getMessage());
		}
		
		documento.setCorpo(null);
		try {
			documentoService.atualizar(documento);
			fail();
		} catch (RuntimeException e) {
			Assert.assertEquals("O corpo é inválido: corpo deve conter de 1 a 500 caracteres", e.getMessage());
		}
		
		String corpoMaisDe500Caracteres = "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"
				+ "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"
				+ "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"
				+ "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"
				+ "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012234";
		
		documento.setCorpo(corpoMaisDe500Caracteres);
		
		
		
		try {
			documentoService.atualizar(documento);
			fail();
		} catch (RuntimeException e) {
			Assert.assertEquals("O corpo é inválido: corpo deve conter de 1 a 500 caracteres", e.getMessage());
		}
	}
	

	@Test
	public void deveLancarErroQuandoExcluirDocumentoSemId() throws DaoException {
		DocumentoService documentoService = new DocumentoService();
		Long id = null;

		try {
			documentoService.exclui(id);
			fail();
		} catch (RuntimeException e) {
			Assert.assertEquals("Ao excluir um documento o ID não deve ser nulo.", e.getMessage());
		}
	}

	// Teste integracão

	@Test
	public void deveSalvarUmDocumentoValido() throws DaoException {
		Documento documento = new Documento();
		documento.setTitulo("teste final");
		documento.setCorpo("corpo valido");

		Documento documentoRetorno = new Documento();
		documentoRetorno.setId(1l);
		documentoRetorno.setTitulo("teste final");
		documentoRetorno.setCorpo("corpo valido");
		documentoRetorno.setData(new Date(0));

		DocumentoDao documentoDao = Mockito.mock(DocumentoDao.class);
		when(documentoDao.salvar(documento)).thenReturn(documentoRetorno);

		DocumentoService documentoService = new DocumentoService(documentoDao);
		Documento resultado = documentoService.salvar(documento);

		Assert.assertSame(resultado, documentoRetorno);
		Assert.assertEquals(resultado.getId(), resultado.getId());
		Assert.assertEquals(documento.getTitulo(), resultado.getTitulo());
		Assert.assertEquals(documento.getCorpo(), resultado.getCorpo());
		Assert.assertNotNull(resultado.getData());
	}

	@Test
	public void deveRetornarUmListaComTodosDocumentosArmazenados() throws DaoException {
		
		DocumentoDao documentoDao = Mockito.mock(DocumentoDao.class);
		List<Documento> listaTodos = new ArrayList<>();
		when(documentoDao.listar(null, null)).thenReturn(listaTodos);
		DocumentoService documentoService = new DocumentoService(documentoDao);
		listaTodos = documentoService.listar(null, null);
		Assert.assertNotNull(listaTodos);
		Assert.assertTrue(listaTodos.isEmpty());
		Documento documento = new Documento();
		documento.setTitulo("teste lista");
		documento.setCorpo("Testelista");
		listaTodos.add(documento);

		documentoService.listar(null, null);
		Assert.assertEquals(1, listaTodos.size());
		assertEquals(listaTodos.get(0), documento);
	}

	@Test
	public void deveRetornarUmDocumentoPesquisadoPeloId() throws DaoException {
		Documento documentoRetorno = new Documento();
		documentoRetorno.setId(1l);
		documentoRetorno.setData(new Date(0));
		documentoRetorno.setTitulo("teste final");
		documentoRetorno.setCorpo("corpo valido");

		DocumentoDao documentoDao = Mockito.mock(DocumentoDao.class);
		DocumentoService documentoService = new DocumentoService(documentoDao);
		when(documentoDao.get(documentoRetorno.getId())).thenReturn(documentoRetorno);
		Documento resultado = documentoService.get(documentoRetorno.getId());

		Assert.assertNotNull(resultado.getId());
		Assert.assertNotNull(resultado.getData());
		Assert.assertEquals(resultado.getTitulo(), documentoRetorno.getTitulo());
		Assert.assertEquals(resultado.getCorpo(), documentoRetorno.getCorpo());
		
		documentoRetorno.setId(null);
		try {
			resultado = documentoService.get(documentoRetorno.getId());
			fail();
		} catch (RuntimeException e) {
			Assert.assertEquals("Ao buscar um documento por ID,o ID não deve ser nulo.", e.getMessage());
		}
        
		
		

	}

	@Test
	public void deveVerificarSeFoiExcluido() throws DaoException {
		
		Documento documento = new Documento();
		documento.setId(1l);
		documento.setData(new Date(0));
		documento.setTitulo("teste final");
		documento.setCorpo("corpo valido");

		DocumentoDao documentoDao = Mockito.mock(DocumentoDao.class);
		DocumentoService documentoService = new DocumentoService(documentoDao);
		documentoService.exclui(documento.getId());

		verify(documentoDao).excluir(documento.getId());
		
		Assert.assertNotNull(documento);
		
		
	}

	@Test
	public void deveAtualizarDocumentoArmazenado() throws DaoException {

		Documento documento = new Documento();
		documento.setId(1l);
		documento.setTitulo("Teste de Atualizar Documento 001");
		documento.setCorpo("Corpo do documento 001 Teste de busca por filtros 001");

		Documento documentoAtualizado = new Documento();
		documentoAtualizado.setId(1l);
		documentoAtualizado.setTitulo("documento alturalizado");
		documentoAtualizado.setCorpo("Corpo do documento atualizado");
		documentoAtualizado.setData(new Date(0));

		DocumentoDao documentoDao = Mockito.mock(DocumentoDao.class);
		when(documentoDao.atualizar(documento)).thenReturn(documentoAtualizado);

		DocumentoService documentoService = new DocumentoService(documentoDao);
		Documento documentoNovo = new Documento();
		documentoNovo = documentoService.atualizar(documento);
		Assert.assertNotEquals(documento, documentoNovo);
		Assert.assertEquals(documentoAtualizado.getCorpo(), documentoNovo.getCorpo());
		Assert.assertEquals(documentoAtualizado.getTitulo(), documentoNovo.getTitulo());
		Assert.assertEquals(documentoAtualizado.getId(), documentoNovo.getId());
		Assert.assertEquals(documentoNovo.getData(), documentoAtualizado.getData());

	}

	@Test
	public void deveRetornarListaDeDocumentosBuscadosPelosFiltrosTituloEOuCorpo() throws DaoException {
		Documento documento1 = new Documento();
		documento1.setId(1l);
		documento1.setTitulo("Teste de Buscar por filtros 001");
		documento1.setCorpo("Corpo do documento 001 Teste de busca por filtros 001");
		documento1.setData(new Date(0));
		Documento documento2 = new Documento();
		documento2.setId(2l);
		documento2.setTitulo("Teste de Buscar por filtros 002");
		documento2.setCorpo("Corpo do documento 002 Teste de busca por filtros 002");
		documento2.setData(new Date(0));
		Documento documento3 = new Documento();
		documento3.setId(3l);
		documento3.setTitulo("Teste de Buscar por filtros 003");
		documento3.setCorpo("zzzzzzzzzz   xxxxxxxxxxxxxxxxxx ");
		documento3.setData(new Date(0));
		List<Documento> documentos = new ArrayList<>();
		documentos.add(documento1);
		documentos.add(documento2);
		documentos.add(documento3);
		String titulo = "003";
		String corpo = "";
		List<Documento> documentosFiltro = new ArrayList<>();
		documentosFiltro.add(documento3);
		DocumentoDao documentoDao = Mockito.mock(DocumentoDao.class);
		when(documentoDao.listar(titulo, corpo)).thenReturn(documentosFiltro);

		DocumentoService documentoService = new DocumentoService(documentoDao);
		
		
		List<Documento> listaResultados = documentoService.listar(titulo, corpo);

		Assert.assertEquals(1, ((long) listaResultados.size()));
		Assert.assertNotNull(listaResultados.get(0).getId());
		Assert.assertNotNull(listaResultados.get(0).getData());
		Assert.assertTrue(!titulo.contains(listaResultados.get(0).getTitulo()));

	}
}
