package com.digitro.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.digitro.dao.DaoException;
import com.digitro.model.Documento;
import com.digitro.service.DocumentoService;

@Path("/documento")
public class DocumentoController {

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Documento> listar(@QueryParam("titulo")String titulo) throws DaoException{
		DocumentoService documentoService = new DocumentoService();
		
		List<Documento> documentos = documentoService.listar(titulo,null); 
	
		return documentos;
	}


	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Documento getDocumento(@PathParam("id") Long id) throws DaoException {
		DocumentoService documentoService = new DocumentoService();
		Documento documento = documentoService.get(id);

		return documento;
	}

	@DELETE
	@Path("/{id}")
	public Response excluiDocumento(@PathParam("id") Long id) throws DaoException {
		DocumentoService documentoService = new DocumentoService();
		documentoService.get(id);
		return Response.status(Status.OK).build();
	}
	
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Documento salvarDocumento()throws DaoException{
//		// tem que receber o String JSON  e converte em dados para o objeto Documento
//		Documento documento = new Documento();
//		
//		DocumentoService documentoService = new DocumentoService();
//		documento = documentoService.salvar(documento);
//		
//		return documento;
//		
//	}
	

//    @PUT
//    public Response createUser(User user) throws SQLException, 
//      InstantiationException, IllegalAccessException, 
//      ClassNotFoundException {
//          Connect connect = new Connect();
//          connect.insertUser(user);
//          connect.closeConnection();
//          return Response.status(Status.OK).build();
//    }
//

}
