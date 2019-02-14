package com.digitro.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.digitro.dao.DaoException;
import com.digitro.model.Documento;
import com.digitro.service.DocumentoService;

@Path("documento")
public class DocumentoController {

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Documento> listarDocumento(Documento documento) throws DaoException{
		DocumentoService documentoService = new DocumentoService();
		List<Documento> documentos = documentoService.listar(documento.getTitulo(), documento.getCorpo()); 
		System.out.println("Lista encontrado com "+documentos.size()+" documento(s)");
		return documentos;
	}
	

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizaDocumento(@PathParam("id") Long id, Documento documento)throws DaoException{
		DocumentoService documentoService = new DocumentoService();
		documento = documentoService.atualizar(documento);
		
		return Response.status(Status.OK).entity(documento).build();
		
	}
	
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDocumento(@PathParam("id") Long id) throws DaoException {
		DocumentoService documentoService = new DocumentoService();
		Documento documento = documentoService.get(id);

		return Response.status(Status.OK).entity(documento).build();
	}

	@DELETE
	@Path("/{id}")
	public Response excluiDocumento(@PathParam("id") Long id) throws DaoException {
		DocumentoService documentoService = new DocumentoService();
		Documento doc1 = documentoService.get(id);
		documentoService.exclui(id);
		Documento doc =	documentoService.get(id);
		if(doc.getId()==null && doc1.getId()==null) {
			return Response.status(Status.BAD_REQUEST).build();
		}else {	
			return Response.status(Status.OK).build();
		}
		
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response salvarDocumento(Documento documento)throws DaoException{
		
		DocumentoService documentoService = new DocumentoService();
		documento = documentoService.salvar(documento);
		
		return Response.status(Status.CREATED).entity(documento).build();

	}
	

	 
}
