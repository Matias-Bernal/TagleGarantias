package servidor.GestionarPedido_Pieza;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.jdo.Extent;
import javax.jdo.Query;

import servidor.assembler.BdgAssembler;
import servidor.assembler.Devolucion_PiezaAssembler;
import servidor.assembler.Mano_ObraAssembler;
import servidor.assembler.MuletoAssembler;
import servidor.assembler.PedidoAssembler;
import servidor.assembler.Pedido_PiezaAssembler;
import servidor.assembler.PiezaAssembler;
import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Agente;
import servidor.persistencia.dominio.Entidad;
import servidor.persistencia.dominio.Pedido_Pieza;
import servidor.persistencia.dominio.Reclamo;
import common.Cuadruple;
import common.Triple;
import common.DTOs.Devolucion_PiezaDTO;
import common.DTOs.PedidoDTO;
import common.DTOs.Pedido_PiezaDTO;
import common.DTOs.PiezaDTO;
import common.DTOs.AgenteDTO;
import common.GestionarPedido_Pieza.IControlPedido_Pieza;

public class ControlPedido_Pieza extends UnicastRemoteObject implements
		IControlPedido_Pieza {

	private static final long serialVersionUID = 1L;

	public ControlPedido_Pieza() throws RemoteException {
		super();
	}

	@Override
	public Long agregarPedido_Pieza(Pedido_PiezaDTO pedido_PiezaDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			Pedido_PiezaAssembler pedido_piezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			Pedido_Pieza pedido_Pieza = pedido_piezaAssemb.getPedido_PiezaNuevo(pedido_PiezaDTO);
			accesoBD.hacerPersistente(pedido_Pieza);
			id = pedido_Pieza.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarPedido_Pieza(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			Pedido_PiezaAssembler pedido_piezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			Pedido_Pieza pedido_Pieza = pedido_piezaAssemb.getPedido_Pieza(buscarPedido_Pieza(id));
			accesoBD.eliminar(pedido_Pieza);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public void modificarPedido_Pieza(Long id, Pedido_PiezaDTO modificado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			
			Pedido_PiezaAssembler pedido_piezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			Pedido_Pieza pedido_Pieza = pedido_piezaAssemb.getPedido_Pieza(buscarPedido_Pieza(id));
			
			PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
			pedido_Pieza.setPedido(pedidoAssemb.getPedido(modificado.getPedido()));
			
			PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
			pedido_Pieza.setPieza(piezaAssemb.getPieza(modificado.getPieza()));
			
			pedido_Pieza.setNumero_pedido(modificado.getNumero_pedido());
			if(modificado.getFecha_solicitud_fabrica()!=null)
				pedido_Pieza.setFecha_solicitud_fabrica(modificado.getFecha_solicitud_fabrica());
			if(modificado.getFecha_recepcion_fabrica()!=null)
				pedido_Pieza.setFecha_recepcion_fabrica(modificado.getFecha_recepcion_fabrica());
			if(modificado.getFecha_cambio()!=null)
				pedido_Pieza.setFecha_cambio(modificado.getFecha_cambio());
			pedido_Pieza.setPnc(modificado.getPnc());
			MuletoAssembler muletoAssemb = new  MuletoAssembler(accesoBD);
			if(modificado.getMuleto()!=null)
				pedido_Pieza.setMuleto(muletoAssemb.getMuleto(modificado.getMuleto()));
			
			if(modificado.getFecha_solicitud_devolucion()!=null)
				pedido_Pieza.setFecha_solicitud_devolucion(modificado.getFecha_solicitud_devolucion());
			if(modificado.getFecha_aprobacion_solicitud_devolucion()!=null)
				pedido_Pieza.setFecha_aprobacion_solicitud_devolucion(modificado.getFecha_aprobacion_solicitud_devolucion());
			
			Devolucion_PiezaAssembler devolucion_PiezaAssemb = new Devolucion_PiezaAssembler(accesoBD);
			if(modificado.getDevolucion_pieza()!=null)
				pedido_Pieza.setDevolucion_pieza(devolucion_PiezaAssemb.getDevolucion_Pieza(modificado.getDevolucion_pieza()));
			
			pedido_Pieza.setEstado_pedido(modificado.getEstado_pedido());
			
			BdgAssembler bdgAssemb = new BdgAssembler(accesoBD);
			if(modificado.getBdg()!=null)
				pedido_Pieza.setBdg(bdgAssemb.getBdg(modificado.getBdg()));
			
			Mano_ObraAssembler mano_ObraAssemb = new Mano_ObraAssembler(accesoBD);
			if(modificado.getMano_obra()!=null)
				pedido_Pieza.setMano_obra(mano_ObraAssemb.getMano_Obra(modificado.getMano_obra()));
			
			if(modificado.getStock()!=null)
				pedido_Pieza.setStock(modificado.getStock());
			if(modificado.getPropio()!=null)
				pedido_Pieza.setPropio(modificado.getPropio());
			
			if(modificado.getFecha_envio_agente()!=null)
				pedido_Pieza.setFecha_envio_agente(modificado.getFecha_envio_agente());
			if(modificado.getFecha_recepcion_agente()!=null)
				pedido_Pieza.setFecha_recepcion_agente(modificado.getFecha_recepcion_agente());
			
			if(modificado.getPieza_usada()!=null)
				pedido_Pieza.setPieza_usada(modificado.getPieza_usada());

				accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public Vector<Pedido_PiezaDTO> obtenerPedido_Pieza() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Pedido_PiezaAssembler pedido_PiezaAssem = new Pedido_PiezaAssembler(accesoBD);
			Vector<Pedido_Pieza> pedidos_Pieza = new Vector<Pedido_Pieza> (accesoBD.buscarPorFiltro(Pedido_Pieza.class, ""));
			for (int i = 0; i < pedidos_Pieza.size(); i++) {
				Pedido_PiezaDTO pedido_PiezaDTO = pedido_PiezaAssem.getPedido_PiezaDTO(pedidos_Pieza.elementAt(i));
				pedidos_PiezaDTO.add(pedido_PiezaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}

	@Override
	public Vector<Pedido_PiezaDTO> obtenerPedido_Pieza(Date fecha_estado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			Collection movCol =  accesoBD.obtenerObjetosFecha(Pedido_Pieza.class, fecha_estado.getYear(),fecha_estado.getMonth(), fecha_estado.getDay());
			Pedido_PiezaAssembler pedido_piezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				pedidos_PiezaDTO.add(pedido_piezaAssemb.getPedido_PiezaDTO((Pedido_Pieza)(movCol.toArray())[i]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}

	@Override
	public Vector<Pedido_PiezaDTO> obtenerPedido_Pieza(PedidoDTO pedidoDTO)	throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "pedido.id == "+pedidoDTO.getId();
			Collection movCol = accesoBD.buscarPorFiltro(Pedido_Pieza.class, filtro);
			Pedido_PiezaAssembler pedido_piezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				pedidos_PiezaDTO.add(pedido_piezaAssemb.getPedido_PiezaDTO((Pedido_Pieza)(movCol.toArray())[i]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}

	@Override
	public boolean existePedido_Pieza(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Pedido_Pieza) accesoBD.buscarPorId(Pedido_Pieza.class,id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existePedido_Pieza(PedidoDTO pedidoDTO, PiezaDTO piezaDTO)throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "pedido.id == "+pedidoDTO.getId()+ " && pieza.id == "+piezaDTO.getId();
			movCol = accesoBD.buscarPorFiltro(Pedido_Pieza.class, filtro);

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (movCol != null && movCol.size()>=1);
	}

	@Override
	public Pedido_PiezaDTO buscarPedido_Pieza(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Pedido_PiezaDTO pedido_PiezaDTO = null;
		try {
			accesoBD.iniciarTransaccion();

			Pedido_PiezaAssembler pedido_piezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			pedido_PiezaDTO = pedido_piezaAssemb.getPedido_PiezaDTO((Pedido_Pieza) accesoBD.buscarPorId(Pedido_Pieza.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedido_PiezaDTO;
	}

	@Override
	public Pedido_PiezaDTO buscarPedido_Pieza(PedidoDTO pedidoDTO,PiezaDTO piezaDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Pedido_PiezaDTO pedido_PiezaDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "pedido.id == "+pedidoDTO.getId()+ " && pieza.id == "+piezaDTO.getId();
			Collection movCol = accesoBD.buscarPorFiltro(Pedido_Pieza.class, filtro);
			Pedido_PiezaAssembler pedido_piezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			if (movCol.size()>=1 ) {
				pedido_PiezaDTO = pedido_piezaAssemb.getPedido_PiezaDTO((Pedido_Pieza)(movCol.toArray()[0]));
			}
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedido_PiezaDTO;
	}

	@Override
	public boolean existePedido_Pieza(Devolucion_PiezaDTO devolucion_piezaDTO)throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "devolucion_pieza.id == "+devolucion_piezaDTO.getId();
			movCol = accesoBD.buscarPorFiltro(Pedido_Pieza.class, filtro);

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (movCol != null && movCol.size()>=1);
	}

	@Override
	public Pedido_PiezaDTO buscarPedido_Pieza(Devolucion_PiezaDTO devolucion_piezaDTO) throws Exception{
		AccesoBD accesoBD = new AccesoBD();
		Pedido_PiezaDTO pedido_PiezaDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "devolucion_pieza.id == "+devolucion_piezaDTO.getId();
			Collection movCol = accesoBD.buscarPorFiltro(Pedido_Pieza.class, filtro);
			Pedido_PiezaAssembler pedido_piezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			if (movCol.size()>=1 ) {
				pedido_PiezaDTO = pedido_piezaAssemb.getPedido_PiezaDTO((Pedido_Pieza)(movCol.toArray()[0]));
			}
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedido_PiezaDTO;
	}

	@Override
	public boolean existePedido_Pieza(String numero_pedido) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "numero_pedido.equals(\""+numero_pedido+"\")";
			movCol = accesoBD.buscarPorFiltro(Pedido_Pieza.class, filtro);

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (movCol != null && movCol.size()>=1);
	}

	@Override
	public Pedido_PiezaDTO buscarPedido_Pieza(String numero_pedido){
		AccesoBD accesoBD = new AccesoBD();
		Pedido_PiezaDTO pedido_PiezaDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "numero_pedido.equals(\""+numero_pedido+"\")";
			Collection movCol = accesoBD.buscarPorFiltro(Pedido_Pieza.class, filtro);
			Pedido_PiezaAssembler pedido_piezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			if (movCol.size()>=1 ) {
				pedido_PiezaDTO = pedido_piezaAssemb.getPedido_PiezaDTO((Pedido_Pieza)(movCol.toArray()[0]));
			}
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedido_PiezaDTO;
	}
	
	@Override
	public Vector<Pedido_PiezaDTO> obtenerPedido_PiezaSinTurno() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
				
			Extent e1 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q1 = accesoBD.getPersistencia().newQuery(e1, "");
			Collection entidades = (Collection) q1.execute();
			
			String filtro = "fecha_recepcion_fabrica != null && devolucion_pieza == null && pedido.reclamo.fecha_turno == null && entidades.contains(pedido.reclamo.registrante)";       
						
			Extent clnCliente = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, false);
            Query query = accesoBD.getPersistencia().newQuery(clnCliente,filtro);
            //query.setGrouping("pedido.id");
            
            query.declareImports("import java.util.Collection");
            query.declareParameters("Collection entidades");
			Collection c2 = (Collection) query.execute(entidades);

			Vector<Pedido_Pieza> pedidos_Pieza = new Vector<Pedido_Pieza> (c2);	
	
			Pedido_PiezaAssembler pedido_PiezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			for (int i = 0; i < pedidos_Pieza.size(); i++) {
				Pedido_PiezaDTO pedido_PiezaDTO = pedido_PiezaAssemb.getPedido_PiezaDTO(pedidos_Pieza.elementAt(i));
				pedidos_PiezaDTO.add(pedido_PiezaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}

	@Override
	public Vector<Pedido_PiezaDTO> obtenerPedido_PiezaContencion()
			throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			
			Extent e1 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q1 = accesoBD.getPersistencia().newQuery(e1, "");
			Collection entidades = (Collection) q1.execute();
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
			Calendar calendar =Calendar.getInstance(); //obtiene la fecha de hoy 
			calendar.add(Calendar.DATE, -10); //el -10 indica que se le restaran 3 dias
			
			java.util.Date fechaHoy = calendar.getTime();
			String fecha = format2.format(fechaHoy);
			
		    java.sql.Date hoy = new java.sql.Date(fechaHoy.getTime());

			String filtro = "fecha_recepcion_fabrica == null && pedido.reclamo.fecha_turno == null && entidades.contains(pedido.reclamo.registrante) && hoy>=pedido.reclamo.fecha_reclamo";
						
			Extent clnCliente = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, false);
            Query query = accesoBD.getPersistencia().newQuery(clnCliente,filtro);
            query.setGrouping("pedido.id");
            
            query.declareImports("import java.util.Collection ; import java.sql.Date");
            query.declareParameters("Collection entidades, Date hoy");
			Collection c2 = (Collection) query.execute(entidades,hoy);

			Vector<Pedido_Pieza> pedidos_Pieza = new Vector<Pedido_Pieza> (c2);	
			Pedido_PiezaAssembler pedido_PiezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			for (int i = 0; i < pedidos_Pieza.size(); i++) {	
				Pedido_PiezaDTO pedido_PiezaDTO = pedido_PiezaAssemb.getPedido_PiezaDTO(pedidos_Pieza.elementAt(i));
				pedidos_PiezaDTO.add(pedido_PiezaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}

	@Override
	public Vector<Pedido_PiezaDTO> obtenerPedido_PiezaReclamoAgente() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
				
			Extent e1 = accesoBD.getPersistencia().getExtent(Agente.class, true);
			Query q1 = accesoBD.getPersistencia().newQuery(e1, "");
			Collection agentes = (Collection) q1.execute();
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
			Calendar calendar =Calendar.getInstance(); //obtiene la fecha de hoy 
			calendar.add(Calendar.DATE, -7); //el -7 indica que se le restaran 3 dias
			
			java.util.Date fechaHoy = calendar.getTime();
			String fecha = format2.format(fechaHoy);
			
		    java.sql.Date hoy = new java.sql.Date(fechaHoy.getTime());			
			
			String filtro = "fecha_envio_agente != null && fecha_recepcion_agente == null && agentes.contains(pedido.reclamo.registrante) && hoy>=fecha_envio_agente";       
						
			Extent clnCliente = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, false);
            Query query = accesoBD.getPersistencia().newQuery(clnCliente,filtro);
            query.setGrouping("pedido.id");
            
            query.declareImports("import java.util.Collection ; import java.sql.Date");
            query.declareParameters("Collection agentes, Date hoy");
			Collection c2 = (Collection) query.execute(agentes,hoy);

			Vector<Pedido_Pieza> pedidos_Pieza = new Vector<Pedido_Pieza> (c2);
			Pedido_PiezaAssembler pedido_PiezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			for (int i = 0; i < pedidos_Pieza.size(); i++) {	
				Pedido_PiezaDTO pedido_PiezaDTO = pedido_PiezaAssemb.getPedido_PiezaDTO(pedidos_Pieza.elementAt(i));
				pedidos_PiezaDTO.add(pedido_PiezaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}

	@Override
	public Vector<Pedido_PiezaDTO> obtenerPedido_PiezaReclamoFabrica() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
					
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
			Calendar calendar =Calendar.getInstance(); //obtiene la fecha de hoy 
			calendar.add(Calendar.DATE, -10); //el -10 indica que se le restaran 3 dias
			
			java.util.Date fechaHoy = calendar.getTime();
			String fecha = format2.format(fechaHoy);
			
		    java.sql.Date hoy = new java.sql.Date(fechaHoy.getTime());			
								
			String filtro = "fecha_solicitud_fabrica != null && fecha_recepcion_fabrica == null && hoy>=fecha_solicitud_fabrica";       
						
			Extent clnCliente = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, false);
            Query query = accesoBD.getPersistencia().newQuery(clnCliente,filtro);
            query.setGrouping("pedido.id");
            query.declareImports("import java.sql.Date");
            query.declareParameters("Date hoy");
			Collection c2 = (Collection) query.execute(hoy);

			Vector<Pedido_Pieza> pedidos_Pieza = new Vector<Pedido_Pieza> (c2);	
			Pedido_PiezaAssembler pedido_PiezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			for (int i = 0; i < pedidos_Pieza.size(); i++) {	
				Pedido_PiezaDTO pedido_PiezaDTO = pedido_PiezaAssemb.getPedido_PiezaDTO(pedidos_Pieza.elementAt(i));
				pedidos_PiezaDTO.add(pedido_PiezaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}

	@Override
	public Vector<Pedido_PiezaDTO> obtenerPedido_PiezaSugerencia() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
					
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
			Calendar calendar =Calendar.getInstance(); //obtiene la fecha de hoy 
			calendar.add(Calendar.DATE, -15); //el -15 indica que se le restaran 3 dias
			
			java.util.Date fechaHoy = calendar.getTime();
			String fecha = format2.format(fechaHoy);
			
		    java.sql.Date hoy = new java.sql.Date(fechaHoy.getTime());			
								
			String filtro = "fecha_solicitud_fabrica != null && fecha_recepcion_fabrica == null && hoy>=fecha_solicitud_fabrica";       
						
			Extent clnCliente = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, false);
            Query query = accesoBD.getPersistencia().newQuery(clnCliente,filtro);
            query.setGrouping("pedido.id");
            query.declareImports("import java.sql.Date");
            query.declareParameters("Date hoy");
			Collection c2 = (Collection) query.execute(hoy);

			Vector<Pedido_Pieza> pedidos_Pieza = new Vector<Pedido_Pieza> (c2);	
			Pedido_PiezaAssembler pedido_PiezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			for (int i = 0; i < pedidos_Pieza.size(); i++) {	
				Pedido_PiezaDTO pedido_PiezaDTO = pedido_PiezaAssemb.getPedido_PiezaDTO(pedidos_Pieza.elementAt(i));
				pedidos_PiezaDTO.add(pedido_PiezaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}

	@Override
	public Vector<Pedido_PiezaDTO> obtenerPedido_PiezaAgente() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
				
			Extent e1 = accesoBD.getPersistencia().getExtent(Agente.class, true);
			Query q1 = accesoBD.getPersistencia().newQuery(e1, "");
			Collection agentes = (Collection) q1.execute();	
			
			String filtro = "agentes.contains(pedido.reclamo.registrante)";       
						
			Extent clnCliente = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, false);
            Query query = accesoBD.getPersistencia().newQuery(clnCliente,filtro);
            //query.setGrouping("pedido.id");
            
            query.declareImports("import java.util.Collection");
            query.declareParameters("Collection agentes");
			Collection c2 = (Collection) query.execute(agentes);

			Vector<Pedido_Pieza> pedidos_Pieza = new Vector<Pedido_Pieza> (c2);	
			Pedido_PiezaAssembler pedido_PiezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			for (int i = 0; i < pedidos_Pieza.size(); i++) {	
				Pedido_PiezaDTO pedido_PiezaDTO = pedido_PiezaAssemb.getPedido_PiezaDTO(pedidos_Pieza.elementAt(i));
				pedidos_PiezaDTO.add(pedido_PiezaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}

	@Override
	public Vector<Pedido_PiezaDTO> obtenerPedido_PiezaEntidad() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
				
			Extent e1 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q1 = accesoBD.getPersistencia().newQuery(e1, "");
			Collection entidad = (Collection) q1.execute();	
			
			String filtro = "entidad.contains(pedido.reclamo.registrante)";       
						
			Extent clnCliente = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, false);
            Query query = accesoBD.getPersistencia().newQuery(clnCliente,filtro);
            //query.setGrouping("pedido.id");
            
            query.declareImports("import java.util.Collection");
            query.declareParameters("Collection entidad");
			Collection c2 = (Collection) query.execute(entidad);

			Vector<Pedido_Pieza> pedidos_Pieza = new Vector<Pedido_Pieza> (c2);	
			Pedido_PiezaAssembler pedido_PiezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			for (int i = 0; i < pedidos_Pieza.size(); i++) {	
				Pedido_PiezaDTO pedido_PiezaDTO = pedido_PiezaAssemb.getPedido_PiezaDTO(pedidos_Pieza.elementAt(i));
				pedidos_PiezaDTO.add(pedido_PiezaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}
	
	@Override
	public Vector<Pedido_PiezaDTO> obtenerPiezasLlegadas() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
				
			String filtro = "fecha_solicitud_fabrica !=null && fecha_recepcion_fabrica != null && devolucion_pieza == null && fecha_envio_agente == null";       
			Vector<Pedido_Pieza> pedidos_Pieza = new Vector<Pedido_Pieza> (accesoBD.buscarPorFiltro(Pedido_Pieza.class, filtro));
			Pedido_PiezaAssembler pedido_PiezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			for (int i = 0; i < pedidos_Pieza.size(); i++) {
				Pedido_PiezaDTO pedido_PiezaDTO = pedido_PiezaAssemb.getPedido_PiezaDTO(pedidos_Pieza.elementAt(i));
				pedidos_PiezaDTO.add(pedido_PiezaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}
	
	@Override
	public Vector<Pedido_PiezaDTO> obtenerPiezasDevueltas() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
				
			String filtro = "devolucion_pieza != null && devolucion_pieza.fecha_devolucion != null";       
			Vector<Pedido_Pieza> pedidos_Pieza = new Vector<Pedido_Pieza> (accesoBD.buscarPorFiltro(Pedido_Pieza.class, filtro));
			Pedido_PiezaAssembler pedido_PiezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			for (int i = 0; i < pedidos_Pieza.size(); i++) {
				Pedido_PiezaDTO pedido_PiezaDTO = pedido_PiezaAssemb.getPedido_PiezaDTO(pedidos_Pieza.elementAt(i));
				pedidos_PiezaDTO.add(pedido_PiezaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}

	@Override
	public Vector<Pedido_PiezaDTO> obtenerPiezasSinLlegar() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
				
			String filtro = "fecha_solicitud_fabrica != null && fecha_recepcion_fabrica == null";       
			Vector<Pedido_Pieza> pedidos_Pieza = new Vector<Pedido_Pieza> (accesoBD.buscarPorFiltro(Pedido_Pieza.class, filtro));
			Pedido_PiezaAssembler pedido_PiezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			for (int i = 0; i < pedidos_Pieza.size(); i++) {
				Pedido_PiezaDTO pedido_PiezaDTO = pedido_PiezaAssemb.getPedido_PiezaDTO(pedidos_Pieza.elementAt(i));
				pedidos_PiezaDTO.add(pedido_PiezaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}
	
	@Override
	public Vector<Pedido_PiezaDTO> obtenerPiezasLlegadasSinTurno() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
				
			Extent e1 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q1 = accesoBD.getPersistencia().newQuery(e1, "");
			Collection entidad = (Collection) q1.execute();	
			
			String filtro = "fecha_solicitud_fabrica !=null && fecha_recepcion_fabrica != null && devolucion_pieza == null && entidad.contains(pedido.reclamo.registrante) && pedido.reclamo.fecha_turno == null";       
						
			Extent clnCliente = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, false);
            Query query = accesoBD.getPersistencia().newQuery(clnCliente,filtro);
            //query.setGrouping("pedido.id");
            
            query.declareImports("import java.util.Collection");
            query.declareParameters("Collection entidad");
			Collection c2 = (Collection) query.execute(entidad);

			Vector<Pedido_Pieza> pedidos_Pieza = new Vector<Pedido_Pieza> (c2);	
			Pedido_PiezaAssembler pedido_PiezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			for (int i = 0; i < pedidos_Pieza.size(); i++) {	
				Pedido_PiezaDTO pedido_PiezaDTO = pedido_PiezaAssemb.getPedido_PiezaDTO(pedidos_Pieza.elementAt(i));
				pedidos_PiezaDTO.add(pedido_PiezaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}

	@Override
	public Vector<Pedido_PiezaDTO> obtenerSDPSinNP() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();		
			String filtro = "numero_pedido.equals(\"\") && pedido.fecha_solicitud_pedido!=null";       
			Vector<Pedido_Pieza> pedidos_Pieza = new Vector<Pedido_Pieza> (accesoBD.buscarPorFiltro(Pedido_Pieza.class, filtro));
			Pedido_PiezaAssembler pedido_PiezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			for (int i = 0; i < pedidos_Pieza.size(); i++) {
				Pedido_PiezaDTO pedido_PiezaDTO = pedido_PiezaAssemb.getPedido_PiezaDTO(pedidos_Pieza.elementAt(i));
				pedidos_PiezaDTO.add(pedido_PiezaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}

	@Override
	public Vector<Pedido_PiezaDTO> obtenerPedido_PiezaEntidad(Long id_pedido)
			throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
				
			Extent e1 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q1 = accesoBD.getPersistencia().newQuery(e1, "");
			Collection entidad = (Collection) q1.execute();	
			
			String filtro = "entidad.contains(pedido.reclamo.registrante) && pedido.id=="+id_pedido.toString();       
						
			Extent clnCliente = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, false);
            Query query = accesoBD.getPersistencia().newQuery(clnCliente,filtro);
            //query.setGrouping("pedido.id");
            
            query.declareImports("import java.util.Collection");
            query.declareParameters("Collection entidad");
			Collection c2 = (Collection) query.execute(entidad);

			Vector<Pedido_Pieza> pedidos_Pieza = new Vector<Pedido_Pieza> (c2);	
			Pedido_PiezaAssembler pedido_PiezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			for (int i = 0; i < pedidos_Pieza.size(); i++) {	
				Pedido_PiezaDTO pedido_PiezaDTO = pedido_PiezaAssemb.getPedido_PiezaDTO(pedidos_Pieza.elementAt(i));
				pedidos_PiezaDTO.add(pedido_PiezaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}

	@Override
	public Vector<Pedido_PiezaDTO> obtenerPedido_PiezaAgente(Long id_pedido)
			throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
				
			Extent e1 = accesoBD.getPersistencia().getExtent(Agente.class, true);
			Query q1 = accesoBD.getPersistencia().newQuery(e1, "");
			Collection agentes = (Collection) q1.execute();	
			
			String filtro = "agentes.contains(pedido.reclamo.registrante) && pedido.id=="+id_pedido.toString();     
						
			Extent clnCliente = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, false);
            Query query = accesoBD.getPersistencia().newQuery(clnCliente,filtro);
            //query.setGrouping("pedido.id");
            
            query.declareImports("import java.util.Collection");
            query.declareParameters("Collection agentes");
			Collection c2 = (Collection) query.execute(agentes);

			Vector<Pedido_Pieza> pedidos_Pieza = new Vector<Pedido_Pieza> (c2);	
			Pedido_PiezaAssembler pedido_PiezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			for (int i = 0; i < pedidos_Pieza.size(); i++) {	
				Pedido_PiezaDTO pedido_PiezaDTO = pedido_PiezaAssemb.getPedido_PiezaDTO(pedidos_Pieza.elementAt(i));
				pedidos_PiezaDTO.add(pedido_PiezaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}
	
	// -------------------------------------------------------------------------------------------------- //
	
	@Override
	public Long piezas_pedidas_a_fabrica_entidad(Date desde, Date hasta) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long cantidad = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection entidad = (Collection) q0.execute();
			String filtro = "entidad.contains(pedido.reclamo.registrante) && fecha_solicitud_fabrica!=null";
			if(desde==null){
				if(hasta!=null){ //desde sin fecha hasta con fecha
					filtro += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date hasta");
					q1.setResult("count(this)");
					cantidad = (Long) q1.execute(entidad,hasta);
				}else{ //desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection entidad");
					q1.setResult("count(this)");
					cantidad = (Long) q1.execute(entidad);
				}
			}else{
				if(hasta!=null){ //desde con fecha hasta con fecha
					filtro += " && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde, Date hasta");
					q1.setResult("count(this)");
					cantidad = (Long)q1.execute(entidad,desde,hasta);
				}else{ //desde con fecha hasta sin fecha
					filtro += " && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde");
					q1.setResult("count(this)");
					cantidad = (Long)q1.execute(entidad,desde);
				}
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return cantidad;
	}
	
	@Override
	public Vector<Pedido_PiezaDTO> obtener_piezas_pedidas_a_fabrica_entidad(Date desde, Date hasta) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection entidad = (Collection) q0.execute();
			String filtro = "entidad.contains(pedido.reclamo.registrante) && fecha_solicitud_fabrica!=null";
			Vector<Pedido_Pieza> pedidos_Pieza = new Vector<Pedido_Pieza>();
			if(desde==null){
				if(hasta!=null){ //desde sin fecha hasta con fecha
					filtro += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date hasta");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(entidad,hasta));
				}else{ //desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection entidad");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(entidad));
				}
			}else{
				if(hasta!=null){ //desde con fecha hasta con fecha
					filtro += " && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection; import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date desde, Date hasta");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(entidad,desde,hasta));
				}else{ //desde con fecha hasta sin fecha
					filtro += " && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(entidad,desde));
				}
			}
			Pedido_PiezaAssembler pedido_PiezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			for (int i = 0; i < pedidos_Pieza.size(); i++) {
				Pedido_PiezaDTO pedido_PiezaDTO = pedido_PiezaAssemb.getPedido_PiezaDTO(pedidos_Pieza.elementAt(i));
				pedidos_PiezaDTO.add(pedido_PiezaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}	

	@Override
	public Triple<Double,Double,Double> anticuacion_pedidas_a_fabrica_entidad(Date desde, Date hasta) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Triple<Double,Double,Double> anticuacion = new Triple<Double,Double,Double>(null, null, null);
		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection entidad = (Collection) q0.execute();
			String filtro = "entidad.contains(pedido.reclamo.registrante) && fecha_solicitud_fabrica!=null";
			Collection c = null;
			if(desde==null){//desde sin fecha hasta con fecha
				if(hasta!=null){
					filtro += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date hasta");
					c = (Collection) q1.execute(entidad,hasta);
				}else{//desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection entidad");
					c = (Collection) q1.execute(entidad);
				}
			}else{
				if(hasta!=null){
					filtro += " && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection; import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date desde, Date hasta");
					c = (Collection) q1.execute(entidad,desde,hasta);
				}else{
					filtro += " && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde");
					c = (Collection) q1.execute(entidad,desde);
				}
			}
			if(c!=null){
				Iterator iter = c.iterator();
				Double sum = null;
				Double promedio = new Double(0);
				Double cantidad = new Double(0);
				SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
				java.util.Date fechaHoy = new java.util.Date();
				String fecha = format2.format(fechaHoy);
			    java.sql.Date hoy = new java.sql.Date(fechaHoy.getTime());
				while (iter.hasNext()){
					Pedido_Pieza current = (Pedido_Pieza)iter.next();
					Double diferencia = new Double(0);
					cantidad+=1;
					diferencia = new Double ((hoy.getTime() - current.getFecha_solicitud_fabrica().getTime())/MILLSECS_PER_DAY);
					if(diferencia>=0){
						if(sum!=null)
							sum+=diferencia;
						else
							sum=diferencia;
						if(anticuacion.second()!=null){
							if(anticuacion.second()<diferencia){
								anticuacion.setSecond(diferencia);
							}
						}else{
							anticuacion.setSecond(diferencia);
						}
						if(anticuacion.third()!=null){
							if(anticuacion.third()>diferencia){
								anticuacion.setThird(diferencia);
							}							
						}else{
							anticuacion.setThird(diferencia);
						}
					}
				}
				if(c.size()!=0 && sum!=null && cantidad!=0){
					promedio = sum / cantidad;
					anticuacion.setFirst(promedio);
				}
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return anticuacion;
	}

	@Override
	public Cuadruple<Double,Double,Double,Double> monto_pedidas_a_fabrica_entidad(Date desde, Date hasta) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Cuadruple<Double,Double,Double,Double> monto = new Cuadruple<Double,Double,Double,Double>(null, null, null, null);
		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection entidad = (Collection) q0.execute();
			String filtro = "entidad.contains(pedido.reclamo.registrante) && fecha_solicitud_fabrica!=null";
			Collection c = null;
			if(desde==null){//desde sin fecha hasta con fecha
				if(hasta!=null){
					filtro += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date hasta");
					c = (Collection) q1.execute(entidad,hasta);
				}else{//desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection entidad");
					c = (Collection) q1.execute(entidad);
				}
			}else{
				if(hasta!=null){
					filtro += " && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection; import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date desde, Date hasta");
					c = (Collection) q1.execute(entidad,desde,hasta);
				}else{
					filtro += " && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde");
					c = (Collection) q1.execute(entidad,desde);
				}
			}
			if(c!=null){
				Iterator iter = c.iterator();
				Double sum = null;
				Double promedio = new Double(0);
				Double cantidad = new Double(0);
				SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
				java.util.Date fechaHoy = new java.util.Date();
				String fecha = format2.format(fechaHoy);
			    java.sql.Date hoy = new java.sql.Date(fechaHoy.getTime());
				while (iter.hasNext()){
					Pedido_Pieza current = (Pedido_Pieza)iter.next();
					if(current.getPnc()!=null){
						if(!current.getPnc().isEmpty()){
							cantidad+=1;
							Double pnc = new Double(current.getPnc());
							if(sum!=null)
								sum+=pnc;
							else
								sum=pnc;
							if(monto.third()!=null){
								if(monto.third()<pnc){
									monto.setThird(pnc);
								}				
							}else{
								monto.setThird(pnc);
							}
							if(monto.fourth()!=null){
								if(monto.fourth()>pnc){
									monto.setFourth(pnc);
								}							
							}else{
								monto.setFourth(pnc);
							}
						}
					}
				}
				if(c.size()!=0 && sum!=null && 	cantidad!=0){
					promedio = sum / cantidad;
					monto.setFirst(sum);
					monto.setSecond(promedio);
				}
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return monto;
	}
	
	@Override
	public Long cantidad_piezas_agente(Date desde, Date hasta , AgenteDTO agente, String filtro) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long cantidad = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Agente.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection agentes = (Collection) q0.execute();
			String filtro_ = filtro;
			if(agente!=null)
				filtro_ += " && pedido.reclamo.registrante.id == "+agente.getId();
			else
				filtro_ += " && agentes.contains(pedido.reclamo.registrante)";
			if(desde==null){
				if(hasta!=null){ //desde sin fecha hasta con fecha
					filtro_ += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro_);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection agentes,Date hasta");
					q1.setResult("count(this)");
					cantidad = (Long) q1.execute(agentes,hasta);
				}else{ //desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro_);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection agentes");
					q1.setResult("count(this)");
					cantidad = (Long) q1.execute(agentes);
				}
			}else{
				if(hasta!=null){ //desde con fecha hasta con fecha
					filtro_ += " && fecha_solicitud_fabrica != null && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro_);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection agentes,Date desde, Date hasta");
					q1.setResult("count(this)");
					cantidad = (Long)q1.execute(agentes,desde,hasta);
				}else{ //desde con fecha hasta sin fecha
					filtro_ += " && fecha_solicitud_fabrica != null && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro_);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection agentes,Date desde");
					q1.setResult("count(this)");
					cantidad = (Long)q1.execute(agentes,desde);
				}
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return cantidad;
	}

	@Override
	public Vector<Pedido_PiezaDTO> obtener_piezas_agente(Date desde, Date hasta, AgenteDTO agente, String filtro) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Agente.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection agentes = (Collection) q0.execute();
			String filtro_ = filtro;
			if(agente!=null)
				filtro_ += " && pedido.reclamo.registrante.id == "+agente.getId().toString();
			else
				filtro_ += " && agentes.contains(pedido.reclamo.registrante)";
			Vector<Pedido_Pieza> pedidos_Pieza = new Vector<Pedido_Pieza>();
			if(desde==null){
				if(hasta!=null){ //desde sin fecha hasta con fecha
					filtro_ += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro_);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection agentes, Date hasta");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(agentes,hasta));
				}else{ //desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro_);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection agentes");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(agentes));
				}
			}else{
				if(hasta!=null){ //desde con fecha hasta con fecha
					filtro_ += " && fecha_solicitud_fabrica != null && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro_);
					q1.declareImports("import java.util.Collection; import java.sql.Date;");
					q1.declareParameters("Collection agentes, Date desde, Date hasta");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(agentes,desde,hasta));
				}else{ //desde con fecha hasta sin fecha
					filtro_ += " && fecha_solicitud_fabrica != null && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro_);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection agentes,Date desde");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(agentes,desde));
				}
			}
			Pedido_PiezaAssembler pedido_PiezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			for (int i = 0; i < pedidos_Pieza.size(); i++) {
				Pedido_PiezaDTO pedido_PiezaDTO = pedido_PiezaAssemb.getPedido_PiezaDTO(pedidos_Pieza.elementAt(i));
				pedidos_PiezaDTO.add(pedido_PiezaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}

	@Override
	public Triple<Double, Double, Double> anticuacion_agente(Date desde, Date hasta, AgenteDTO agente, String filtro) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Triple<Double,Double,Double> anticuacion = new Triple<Double,Double,Double>(null, null, null);
		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Agente.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection agentes = (Collection) q0.execute();
			String filtro_ = filtro;
			if(agente!=null)
				filtro_ += " && pedido.reclamo.registrante.id == "+agente.getId().toString();
			else
				filtro_ += " && agentes.contains(pedido.reclamo.registrante)";
			Collection c = null;
			if(desde==null){//desde sin fecha hasta con fecha
				if(hasta!=null){
					filtro_ += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro_);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection agentes, Date hasta");
					c = (Collection) q1.execute(agentes,hasta);
				}else{//desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro_);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection agentes");
					c = (Collection) q1.execute(agentes);
				}
			}else{
				if(hasta!=null){
					filtro_ += " && fecha_solicitud_fabrica != null && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro_);
					q1.declareImports("import java.util.Collection; import java.sql.Date;");
					q1.declareParameters("Collection agentes, Date desde, Date hasta");
					c = (Collection) q1.execute(agentes,desde,hasta);
				}else{
					filtro_ += " && fecha_solicitud_fabrica != null && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro_);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection agentes,Date desde");
					c = (Collection) q1.execute(agentes,desde);
				}
			}
			if(c!=null){
				Iterator iter = c.iterator();
				Double sum = null;
				Double promedio = new Double(0);
				Double cantidad = new Double(0);
				SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
				java.util.Date fechaHoy = new java.util.Date();
				String fecha = format2.format(fechaHoy);
			    java.sql.Date hoy = new java.sql.Date(fechaHoy.getTime());
				while (iter.hasNext()){
					Pedido_Pieza current = (Pedido_Pieza)iter.next();
					Double diferencia = new Double(0);
					cantidad+=1;
					diferencia = new Double ((hoy.getTime() - current.getFecha_solicitud_fabrica().getTime())/MILLSECS_PER_DAY);
					if(diferencia>=0){
						if(sum!=null)
							sum+=diferencia;
						else
							sum=diferencia;
						if(anticuacion.second()!=null){
							if(anticuacion.second()<diferencia){
								anticuacion.setSecond(diferencia);
							}
						}else{
							anticuacion.setSecond(diferencia);
						}
						if(anticuacion.third()!=null){
							if(anticuacion.third()>diferencia){
								anticuacion.setThird(diferencia);
							}							
						}else{
							anticuacion.setThird(diferencia);
						}
					}
				}
				if(c.size()!=0 && sum!=null && cantidad!=0){
					promedio = sum / cantidad;
					anticuacion.setFirst(promedio);
				}
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return anticuacion;
	}

	@Override
	public Cuadruple<Double, Double, Double, Double> monto_agente(Date desde, Date hasta, AgenteDTO agente, String filtro) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Cuadruple<Double,Double,Double,Double> monto = new Cuadruple<Double,Double,Double,Double>(null, null, null, null);
		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Agente.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection agentes = (Collection) q0.execute();
			String filtro_ = filtro;
			if(agente!=null)
				filtro_ += " && pedido.reclamo.registrante.id == "+agente.getId();
			else
				filtro_ += " && agentes.contains(pedido.reclamo.registrante)";
			Collection c = null;
			if(desde==null){//desde sin fecha hasta con fecha
				if(hasta!=null){
					filtro_ += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro_);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection agentes, Date hasta");
					c = (Collection) q1.execute(agentes,hasta);
				}else{//desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro_);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection agentes");
					c = (Collection) q1.execute(agentes);
				}
			}else{
				if(hasta!=null){
					filtro_ += " && fecha_solicitud_fabrica != null && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro_);
					q1.declareImports("import java.util.Collection; import java.sql.Date;");
					q1.declareParameters("Collection agentes, Date desde, Date hasta");
					c = (Collection) q1.execute(agentes,desde,hasta);
				}else{
					filtro_ += " && fecha_solicitud_fabrica != null && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro_);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection agentes,Date desde");
					c = (Collection) q1.execute(agentes,desde);
				}
			}
			if(c!=null){
				Iterator iter = c.iterator();
				Double sum = null;
				Double promedio = new Double(0);
				Double cantidad = new Double(0);
				SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
				java.util.Date fechaHoy = new java.util.Date();
				String fecha = format2.format(fechaHoy);
			    java.sql.Date hoy = new java.sql.Date(fechaHoy.getTime());
				while (iter.hasNext()){
					Pedido_Pieza current = (Pedido_Pieza)iter.next();
					if(current.getPnc()!=null){
						if(!current.getPnc().isEmpty()){
							cantidad+=1;
							Double pnc = new Double(current.getPnc());
							if(sum!=null)
								sum+=pnc;
							else
								sum=pnc;
							if(monto.third()!=null){
								if(monto.third()<pnc){
									monto.setThird(pnc);
								}				
							}else{
								monto.setThird(pnc);
							}
							if(monto.fourth()!=null){
								if(monto.fourth()>pnc){
									monto.setFourth(pnc);
								}							
							}else{
								monto.setFourth(pnc);
							}
						}
					}
				}
				if(c.size()!=0 && sum!=null && 	cantidad!=0){
					promedio = sum / cantidad;
					monto.setFirst(sum);
					monto.setSecond(promedio);
				}
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return monto;
	}
	// -------------------------------------------------------------------------------------------------- //

	@Override
	public Long piezas_en_transito(Date desde, Date hasta) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long cantidad = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection entidad = (Collection) q0.execute();
			String filtro = "entidad.contains(pedido.reclamo.registrante) && fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica==null";
			if(desde==null){
				if(hasta!=null){ //desde sin fecha hasta con fecha
					filtro += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date hasta");
					q1.setResult("count(this)");
					cantidad = (Long) q1.execute(entidad,hasta);
				}else{ //desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection entidad");
					q1.setResult("count(this)");
					cantidad = (Long) q1.execute(entidad);
				}
			}else{
				if(hasta!=null){ //desde con fecha hasta con fecha
					filtro += " && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde, Date hasta");
					q1.setResult("count(this)");
					cantidad = (Long)q1.execute(entidad,desde,hasta);
				}else{ //desde con fecha hasta sin fecha
					filtro += " && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde");
					q1.setResult("count(this)");
					cantidad = (Long)q1.execute(entidad,desde);
				}
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return cantidad;
	}
	
	@Override
	public Vector<Pedido_PiezaDTO> obtener_piezas_en_transito(Date desde, Date hasta) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection entidad = (Collection) q0.execute();
			String filtro = "entidad.contains(pedido.reclamo.registrante) && fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica==null";
			Vector<Pedido_Pieza> pedidos_Pieza = new Vector<Pedido_Pieza>();
			if(desde==null){
				if(hasta!=null){ //desde sin fecha hasta con fecha
					filtro += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date hasta");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(entidad,hasta));
				}else{ //desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection entidad");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(entidad));
				}
			}else{
				if(hasta!=null){ //desde con fecha hasta con fecha
					filtro += " && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection; import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date desde, Date hasta");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(entidad,desde,hasta));
				}else{ //desde con fecha hasta sin fecha
					filtro += " && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(entidad,desde));
				}
			}
			Pedido_PiezaAssembler pedido_PiezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			for (int i = 0; i < pedidos_Pieza.size(); i++) {
				Pedido_PiezaDTO pedido_PiezaDTO = pedido_PiezaAssemb.getPedido_PiezaDTO(pedidos_Pieza.elementAt(i));
				pedidos_PiezaDTO.add(pedido_PiezaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}

	@Override
	public Triple<Double,Double,Double> anticuacion_en_transito(Date desde, Date hasta) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Triple<Double,Double,Double> anticuacion = new Triple<Double,Double,Double>(null, null, null);
		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection entidad = (Collection) q0.execute();
			String filtro = "entidad.contains(pedido.reclamo.registrante) && fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica==null";
			Collection c = null;
			if(desde==null){//desde sin fecha hasta con fecha
				if(hasta!=null){
					filtro += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date hasta");
					c = (Collection) q1.execute(entidad,hasta);
				}else{//desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection entidad");
					c = (Collection) q1.execute(entidad);
				}
			}else{
				if(hasta!=null){
					filtro += " && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection; import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date desde, Date hasta");
					c = (Collection) q1.execute(entidad,desde,hasta);
				}else{
					filtro += " && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde");
					c = (Collection) q1.execute(entidad,desde);
				}
			}
			if(c!=null){
				Iterator iter = c.iterator();
				Double sum = null;
				Double promedio = new Double(0);
				Double cantidad = new Double(0);
				SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
				java.util.Date fechaHoy = new java.util.Date();
				String fecha = format2.format(fechaHoy);
			    java.sql.Date hoy = new java.sql.Date(fechaHoy.getTime());
				while (iter.hasNext()){
					Pedido_Pieza current = (Pedido_Pieza)iter.next();
					Double diferencia = new Double(0);
					cantidad+=1;
					diferencia = new Double ((hoy.getTime() - current.getFecha_solicitud_fabrica().getTime())/MILLSECS_PER_DAY);
					if(diferencia>=0){
						if(sum!=null)
							sum+=diferencia;
						else
							sum=diferencia;
						if(anticuacion.second()!=null){
							if(anticuacion.second()<diferencia){
								anticuacion.setSecond(diferencia);
							}
						}else{
							anticuacion.setSecond(diferencia);
						}
						if(anticuacion.third()!=null){
							if(anticuacion.third()>diferencia){
								anticuacion.setThird(diferencia);
							}							
						}else{
							anticuacion.setThird(diferencia);
						}
					}
				}
				if(c.size()!=0 && sum!=null && cantidad!=0){
					promedio = sum / cantidad;
					anticuacion.setFirst(promedio);
				}
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return anticuacion;
	}

	@Override
	public Cuadruple<Double,Double,Double,Double> monto_en_transito(Date desde, Date hasta) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Cuadruple<Double,Double,Double,Double> monto = new Cuadruple<Double,Double,Double,Double>(null, null, null, null);
		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection entidad = (Collection) q0.execute();
			String filtro = "entidad.contains(pedido.reclamo.registrante) && fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica==null";
			Collection c = null;
			if(desde==null){//desde sin fecha hasta con fecha
				if(hasta!=null){
					filtro += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date hasta");
					c = (Collection) q1.execute(entidad,hasta);
				}else{//desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection entidad");
					c = (Collection) q1.execute(entidad);
				}
			}else{
				if(hasta!=null){
					filtro += " && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection; import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date desde, Date hasta");
					c = (Collection) q1.execute(entidad,desde,hasta);
				}else{
					filtro += " && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde");
					c = (Collection) q1.execute(entidad,desde);
				}
			}
			if(c!=null){
				Iterator iter = c.iterator();
				Double sum = null;
				Double promedio = new Double(0);
				Double cantidad = new Double(0);
				SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
				java.util.Date fechaHoy = new java.util.Date();
				String fecha = format2.format(fechaHoy);
			    java.sql.Date hoy = new java.sql.Date(fechaHoy.getTime());
				while (iter.hasNext()){
					Pedido_Pieza current = (Pedido_Pieza)iter.next();
					if(current.getPnc()!=null){
						if(!current.getPnc().isEmpty()){
							cantidad+=1;
							Double pnc = new Double(current.getPnc());
							if(sum!=null)
								sum+=pnc;
							else
								sum=pnc;
							if(monto.third()!=null){
								if(monto.third()<pnc){
									monto.setThird(pnc);
								}				
							}else{
								monto.setThird(pnc);
							}
							if(monto.fourth()!=null){
								if(monto.fourth()>pnc){
									monto.setFourth(pnc);
								}							
							}else{
								monto.setFourth(pnc);
							}
						}
					}
				}
				if(c.size()!=0 && sum!=null && 	cantidad!=0){
					promedio = sum / cantidad;
					monto.setFirst(sum);
					monto.setSecond(promedio);
				}
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return monto;
	}

	// -------------------------------------------------------------------------------------------------- //	
	
	@Override
	public Long piezas_sin_turno(Date desde, Date hasta) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long cantidad = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection entidad = (Collection) q0.execute();
			String filtro = "entidad.contains(pedido.reclamo.registrante) && fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && pedido.reclamo.fecha_turno==null";
			if(desde==null){
				if(hasta!=null){ //desde sin fecha hasta con fecha
					filtro += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date hasta");
					q1.setResult("count(this)");
					cantidad = (Long) q1.execute(entidad,hasta);
				}else{ //desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection entidad");
					q1.setResult("count(this)");
					cantidad = (Long) q1.execute(entidad);
				}
			}else{
				if(hasta!=null){ //desde con fecha hasta con fecha
					filtro += " && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde, Date hasta");
					q1.setResult("count(this)");
					cantidad = (Long)q1.execute(entidad,desde,hasta);
				}else{ //desde con fecha hasta sin fecha
					filtro += " && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde");
					q1.setResult("count(this)");
					cantidad = (Long)q1.execute(entidad,desde);
				}
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return cantidad;
	}

	@Override
	public Vector<Pedido_PiezaDTO> obtener_piezas_sin_turno(Date desde, Date hasta) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection entidad = (Collection) q0.execute();
			String filtro = "entidad.contains(pedido.reclamo.registrante) && fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && pedido.reclamo.fecha_turno==null";
			Vector<Pedido_Pieza> pedidos_Pieza = new Vector<Pedido_Pieza>();
			if(desde==null){
				if(hasta!=null){ //desde sin fecha hasta con fecha
					filtro += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date hasta");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(entidad,hasta));
				}else{ //desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection entidad");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(entidad));
				}
			}else{
				if(hasta!=null){ //desde con fecha hasta con fecha
					filtro += " && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection; import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date desde, Date hasta");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(entidad,desde,hasta));
				}else{ //desde con fecha hasta sin fecha
					filtro += " && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(entidad,desde));
				}
			}
			Pedido_PiezaAssembler pedido_PiezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			for (int i = 0; i < pedidos_Pieza.size(); i++) {
				Pedido_PiezaDTO pedido_PiezaDTO = pedido_PiezaAssemb.getPedido_PiezaDTO(pedidos_Pieza.elementAt(i));
				pedidos_PiezaDTO.add(pedido_PiezaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;

	}
	
	@Override
	public Triple<Double,Double,Double> anticuacion_sin_turno(Date desde, Date hasta) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Triple<Double,Double,Double> anticuacion = new Triple<Double,Double,Double>(null, null, null);
		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection entidad = (Collection) q0.execute();
			String filtro = "entidad.contains(pedido.reclamo.registrante) && fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && pedido.reclamo.fecha_turno==null";
			Collection c = null;
			if(desde==null){//desde sin fecha hasta con fecha
				if(hasta!=null){
					filtro += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date hasta");
					c = (Collection) q1.execute(entidad,hasta);
				}else{//desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection entidad");
					c = (Collection) q1.execute(entidad);
				}
			}else{
				if(hasta!=null){
					filtro += " && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection; import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date desde, Date hasta");
					c = (Collection) q1.execute(entidad,desde,hasta);
				}else{
					filtro += " && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde");
					c = (Collection) q1.execute(entidad,desde);
				}
			}
			if(c!=null){
				Iterator iter = c.iterator();
				Double sum = null;
				Double promedio = new Double(0);
				Double cantidad = new Double(0);
				SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
				java.util.Date fechaHoy = new java.util.Date();
				String fecha = format2.format(fechaHoy);
			    java.sql.Date hoy = new java.sql.Date(fechaHoy.getTime());
				while (iter.hasNext()){
					Pedido_Pieza current = (Pedido_Pieza)iter.next();
					Double diferencia = new Double(0);
					cantidad+=1;
					diferencia = new Double ((hoy.getTime() - current.getFecha_solicitud_fabrica().getTime())/MILLSECS_PER_DAY);
					if(diferencia>=0){
						if(sum!=null)
							sum+=diferencia;
						else
							sum=diferencia;
						if(anticuacion.second()!=null){
							if(anticuacion.second()<diferencia){
								anticuacion.setSecond(diferencia);
							}
						}else{
							anticuacion.setSecond(diferencia);
						}
						if(anticuacion.third()!=null){
							if(anticuacion.third()>diferencia){
								anticuacion.setThird(diferencia);
							}							
						}else{
							anticuacion.setThird(diferencia);
						}
					}
				}
				if(c.size()!=0 && sum!=null && cantidad!=0){
					promedio = sum / cantidad;
					anticuacion.setFirst(promedio);
				}
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return anticuacion;
	}

	@Override
	public Cuadruple<Double,Double,Double,Double> monto_sin_turno(Date desde, Date hasta) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Cuadruple<Double,Double,Double,Double> monto = new Cuadruple<Double,Double,Double,Double>(null, null, null, null);
		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection entidad = (Collection) q0.execute();
			String filtro = "entidad.contains(pedido.reclamo.registrante) && fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && pedido.reclamo.fecha_turno==null";
			Collection c = null;
			if(desde==null){//desde sin fecha hasta con fecha
				if(hasta!=null){
					filtro += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date hasta");
					c = (Collection) q1.execute(entidad,hasta);
				}else{//desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection entidad");
					c = (Collection) q1.execute(entidad);
				}
			}else{
				if(hasta!=null){
					filtro += " && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection; import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date desde, Date hasta");
					c = (Collection) q1.execute(entidad,desde,hasta);
				}else{
					filtro += " && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde");
					c = (Collection) q1.execute(entidad,desde);
				}
			}
			if(c!=null){
				Iterator iter = c.iterator();
				Double sum = null;
				Double promedio = new Double(0);
				Double cantidad = new Double(0);
				SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
				java.util.Date fechaHoy = new java.util.Date();
				String fecha = format2.format(fechaHoy);
			    java.sql.Date hoy = new java.sql.Date(fechaHoy.getTime());
				while (iter.hasNext()){
					Pedido_Pieza current = (Pedido_Pieza)iter.next();
					if(current.getPnc()!=null){
						if(!current.getPnc().isEmpty()){
							cantidad+=1;
							Double pnc = new Double(current.getPnc());
							if(sum!=null)
								sum+=pnc;
							else
								sum=pnc;
							if(monto.third()!=null){
								if(monto.third()<pnc){
									monto.setThird(pnc);
								}				
							}else{
								monto.setThird(pnc);
							}
							if(monto.fourth()!=null){
								if(monto.fourth()>pnc){
									monto.setFourth(pnc);
								}							
							}else{
								monto.setFourth(pnc);
							}
						}
					}
				}
				if(c.size()!=0 && sum!=null && 	cantidad!=0){
					promedio = sum / cantidad;
					monto.setFirst(sum);
					monto.setSecond(promedio);
				}
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return monto;
	}

	// -------------------------------------------------------------------------------------------------- //
	
	@Override
	public Long piezas_con_turno(Date desde, Date hasta) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long cantidad = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection entidad = (Collection) q0.execute();
			String filtro = "entidad.contains(pedido.reclamo.registrante) && fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && pedido.reclamo.fecha_turno!=null && fecha_cambio==null";
			if(desde==null){
				if(hasta!=null){ //desde sin fecha hasta con fecha
					filtro += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date hasta");
					q1.setResult("count(this)");
					cantidad = (Long) q1.execute(entidad,hasta);
				}else{ //desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection entidad");
					q1.setResult("count(this)");
					cantidad = (Long) q1.execute(entidad);
				}
			}else{
				if(hasta!=null){ //desde con fecha hasta con fecha
					filtro += " && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde, Date hasta");
					q1.setResult("count(this)");
					cantidad = (Long)q1.execute(entidad,desde,hasta);
				}else{ //desde con fecha hasta sin fecha
					filtro += " && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde");
					q1.setResult("count(this)");
					cantidad = (Long)q1.execute(entidad,desde);
				}
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return cantidad;
	}

	@Override
	public Vector<Pedido_PiezaDTO> obtener_piezas_con_turno(Date desde,	Date hasta) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection entidad = (Collection) q0.execute();
			String filtro = "entidad.contains(pedido.reclamo.registrante) && fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && pedido.reclamo.fecha_turno!=null && fecha_cambio==null";
			Vector<Pedido_Pieza> pedidos_Pieza = new Vector<Pedido_Pieza>();
			if(desde==null){
				if(hasta!=null){ //desde sin fecha hasta con fecha
					filtro += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date hasta");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(entidad,hasta));
				}else{ //desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection entidad");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(entidad));
				}
			}else{
				if(hasta!=null){ //desde con fecha hasta con fecha
					filtro += " && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection; import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date desde, Date hasta");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(entidad,desde,hasta));
				}else{ //desde con fecha hasta sin fecha
					filtro += " && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(entidad,desde));
				}
			}
			Pedido_PiezaAssembler pedido_PiezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			for (int i = 0; i < pedidos_Pieza.size(); i++) {
				Pedido_PiezaDTO pedido_PiezaDTO = pedido_PiezaAssemb.getPedido_PiezaDTO(pedidos_Pieza.elementAt(i));
				pedidos_PiezaDTO.add(pedido_PiezaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}
	
	@Override
	public Triple<Double,Double,Double> anticuacion_con_turno(Date desde, Date hasta) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Triple<Double,Double,Double> anticuacion = new Triple<Double,Double,Double>(null, null, null);
		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection entidad = (Collection) q0.execute();			
			String filtro = "entidad.contains(pedido.reclamo.registrante) && fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && pedido.reclamo.fecha_turno!=null && fecha_cambio==null";
			Collection c = null;
			if(desde==null){//desde sin fecha hasta con fecha
				if(hasta!=null){
					filtro += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date hasta");
					c = (Collection) q1.execute(entidad,hasta);
				}else{//desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection entidad");
					c = (Collection) q1.execute(entidad);
				}
			}else{
				if(hasta!=null){
					filtro += " && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection; import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date desde, Date hasta");
					c = (Collection) q1.execute(entidad,desde,hasta);
				}else{
					filtro += " && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde");
					c = (Collection) q1.execute(entidad,desde);
				}
			}
			if(c!=null){
				Iterator iter = c.iterator();
				Double sum = null;
				Double promedio = new Double(0);
				Double cantidad = new Double(0);
				SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
				java.util.Date fechaHoy = new java.util.Date();
				String fecha = format2.format(fechaHoy);
			    java.sql.Date hoy = new java.sql.Date(fechaHoy.getTime());
				while (iter.hasNext()){
					Pedido_Pieza current = (Pedido_Pieza)iter.next();
					Double diferencia = new Double(0);
					cantidad+=1;
					diferencia = new Double ((hoy.getTime() - current.getFecha_solicitud_fabrica().getTime())/MILLSECS_PER_DAY);
					if(diferencia>=0){
						if(sum!=null)
							sum+=diferencia;
						else
							sum=diferencia;
						if(anticuacion.second()!=null){
							if(anticuacion.second()<diferencia){
								anticuacion.setSecond(diferencia);
							}
						}else{
							anticuacion.setSecond(diferencia);
						}
						if(anticuacion.third()!=null){
							if(anticuacion.third()>diferencia){
								anticuacion.setThird(diferencia);
							}							
						}else{
							anticuacion.setThird(diferencia);
						}
					}
				}
				if(c.size()!=0 && sum!=null && cantidad!=0){
					promedio = sum / cantidad;
					anticuacion.setFirst(promedio);
				}
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return anticuacion;
	}

	@Override
	public Cuadruple<Double,Double,Double,Double> monto_con_turno(Date desde, Date hasta) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Cuadruple<Double,Double,Double,Double> monto = new Cuadruple<Double,Double,Double,Double>(null, null, null, null);
		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection entidad = (Collection) q0.execute();
			String filtro = "entidad.contains(pedido.reclamo.registrante) && fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && pedido.reclamo.fecha_turno!=null && fecha_cambio==null";
			Collection c = null;
			if(desde==null){//desde sin fecha hasta con fecha
				if(hasta!=null){
					filtro += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date hasta");
					c = (Collection) q1.execute(entidad,hasta);
				}else{//desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection entidad");
					c = (Collection) q1.execute(entidad);
				}
			}else{
				if(hasta!=null){
					filtro += " && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection; import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date desde, Date hasta");
					c = (Collection) q1.execute(entidad,desde,hasta);
				}else{
					filtro += " && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde");
					c = (Collection) q1.execute(entidad,desde);
				}
			}
			if(c!=null){
				Iterator iter = c.iterator();
				Double sum = null;
				Double promedio = new Double(0);
				Double cantidad = new Double(0);
				SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
				java.util.Date fechaHoy = new java.util.Date();
				String fecha = format2.format(fechaHoy);
			    java.sql.Date hoy = new java.sql.Date(fechaHoy.getTime());
				while (iter.hasNext()){
					Pedido_Pieza current = (Pedido_Pieza)iter.next();
					if(current.getPnc()!=null){
						if(!current.getPnc().isEmpty()){
							cantidad+=1;
							Double pnc = new Double(current.getPnc());
							if(sum!=null)
								sum+=pnc;
							else
								sum=pnc;
							if(monto.third()!=null){
								if(monto.third()<pnc){
									monto.setThird(pnc);
								}				
							}else{
								monto.setThird(pnc);
							}
							if(monto.fourth()!=null){
								if(monto.fourth()>pnc){
									monto.setFourth(pnc);
								}							
							}else{
								monto.setFourth(pnc);
							}
						}
					}
				}
				if(c.size()!=0 && sum!=null && 	cantidad!=0){
					promedio = sum / cantidad;
					monto.setFirst(sum);
					monto.setSecond(promedio);
				}
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return monto;
	}

	// -------------------------------------------------------------------------------------------------- //
	@Override
	public Long piezas_cambiadas(Date desde, Date hasta) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long cantidad = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection entidad = (Collection) q0.execute();
			String filtro = "entidad.contains(pedido.reclamo.registrante) && fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && pedido.reclamo.fecha_turno!=null && fecha_cambio!=null && pedido.reclamo.orden.recurso.fecha_recurso==null";
			if(desde==null){
				if(hasta!=null){ //desde sin fecha hasta con fecha
					filtro += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date hasta");
					q1.setResult("count(this)");
					cantidad = (Long) q1.execute(entidad,hasta);
				}else{ //desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection entidad");
					q1.setResult("count(this)");
					cantidad = (Long) q1.execute(entidad);
				}
			}else{
				if(hasta!=null){ //desde con fecha hasta con fecha
					filtro += " && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde, Date hasta");
					q1.setResult("count(this)");
					cantidad = (Long)q1.execute(entidad,desde,hasta);
				}else{ //desde con fecha hasta sin fecha
					filtro += " && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde");
					q1.setResult("count(this)");
					cantidad = (Long)q1.execute(entidad,desde);
				}
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return cantidad;
	}

	@Override
	public Vector<Pedido_PiezaDTO> obtener_piezas_cambiadas(Date desde,	Date hasta) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection entidad = (Collection) q0.execute();
			String filtro = "entidad.contains(pedido.reclamo.registrante) && fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && pedido.reclamo.fecha_turno!=null && fecha_cambio!=null && pedido.reclamo.orden.recurso.fecha_recurso==null";
			Vector<Pedido_Pieza> pedidos_Pieza = new Vector<Pedido_Pieza>();
			if(desde==null){
				if(hasta!=null){ //desde sin fecha hasta con fecha
					filtro += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date hasta");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(entidad,hasta));
				}else{ //desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection entidad");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(entidad));
				}
			}else{
				if(hasta!=null){ //desde con fecha hasta con fecha
					filtro += " && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection; import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date desde, Date hasta");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(entidad,desde,hasta));
				}else{ //desde con fecha hasta sin fecha
					filtro += " && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(entidad,desde));
				}
			}
			Pedido_PiezaAssembler pedido_PiezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			for (int i = 0; i < pedidos_Pieza.size(); i++) {
				Pedido_PiezaDTO pedido_PiezaDTO = pedido_PiezaAssemb.getPedido_PiezaDTO(pedidos_Pieza.elementAt(i));
				pedidos_PiezaDTO.add(pedido_PiezaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}
	
	@Override
	public Triple<Double,Double,Double> anticuacion_cambiadas(Date desde, Date hasta) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Triple<Double,Double,Double> anticuacion = new Triple<Double,Double,Double>(null, null, null);
		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection entidad = (Collection) q0.execute();
			String filtro = "entidad.contains(pedido.reclamo.registrante) && fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && pedido.reclamo.fecha_turno!=null && fecha_cambio!=null && pedido.reclamo.orden.recurso.fecha_recurso==null";
			Collection c = null;
			if(desde==null){//desde sin fecha hasta con fecha
				if(hasta!=null){
					filtro += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date hasta");
					c = (Collection) q1.execute(entidad,hasta);
				}else{//desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection entidad");
					c = (Collection) q1.execute(entidad);
				}
			}else{
				if(hasta!=null){
					filtro += " && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection; import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date desde, Date hasta");
					c = (Collection) q1.execute(entidad,desde,hasta);
				}else{
					filtro += " && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde");
					c = (Collection) q1.execute(entidad,desde);
				}
			}
			if(c!=null){
				Iterator iter = c.iterator();
				Double sum = null;
				Double promedio = new Double(0);
				Double cantidad = new Double(0);
				SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
				java.util.Date fechaHoy = new java.util.Date();
				String fecha = format2.format(fechaHoy);
			    java.sql.Date hoy = new java.sql.Date(fechaHoy.getTime());
				while (iter.hasNext()){
					Pedido_Pieza current = (Pedido_Pieza)iter.next();
					Double diferencia = new Double(0);
					cantidad+=1;
					diferencia = new Double ((hoy.getTime() - current.getFecha_solicitud_fabrica().getTime())/MILLSECS_PER_DAY);
					if(diferencia>=0){
						if(sum!=null)
							sum+=diferencia;
						else
							sum=diferencia;
						if(anticuacion.second()!=null){
							if(anticuacion.second()<diferencia){
								anticuacion.setSecond(diferencia);
							}
						}else{
							anticuacion.setSecond(diferencia);
						}
						if(anticuacion.third()!=null){
							if(anticuacion.third()>diferencia){
								anticuacion.setThird(diferencia);
							}							
						}else{
							anticuacion.setThird(diferencia);
						}
					}
				}
				if(c.size()!=0 && sum!=null && cantidad!=0){
					promedio = sum / cantidad;
					anticuacion.setFirst(promedio);
				}
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return anticuacion;
	}

	@Override
	public Cuadruple<Double,Double,Double,Double> monto_cambiadas(Date desde, Date hasta)	throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Cuadruple<Double,Double,Double,Double> monto = new Cuadruple<Double,Double,Double,Double>(null, null, null, null);
		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection entidad = (Collection) q0.execute();
			String filtro = "entidad.contains(pedido.reclamo.registrante) && fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && pedido.reclamo.fecha_turno!=null && fecha_cambio!=null && pedido.reclamo.orden.recurso.fecha_recurso==null";
			Collection c = null;
			if(desde==null){//desde sin fecha hasta con fecha
				if(hasta!=null){
					filtro += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date hasta");
					c = (Collection) q1.execute(entidad,hasta);
				}else{//desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection entidad");
					c = (Collection) q1.execute(entidad);
				}
			}else{
				if(hasta!=null){
					filtro += " && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection; import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date desde, Date hasta");
					c = (Collection) q1.execute(entidad,desde,hasta);
				}else{
					filtro += " && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde");
					c = (Collection) q1.execute(entidad,desde);
				}
			}
			if(c!=null){
				Iterator iter = c.iterator();
				Double sum = null;
				Double promedio = new Double(0);
				Double cantidad = new Double(0);
				SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
				java.util.Date fechaHoy = new java.util.Date();
				String fecha = format2.format(fechaHoy);
			    java.sql.Date hoy = new java.sql.Date(fechaHoy.getTime());
				while (iter.hasNext()){
					Pedido_Pieza current = (Pedido_Pieza)iter.next();
					if(current.getPnc()!=null){
						if(!current.getPnc().isEmpty()){
							cantidad+=1;
							Double pnc = new Double(current.getPnc());
							if(sum!=null)
								sum+=pnc;
							else
								sum=pnc;
							if(monto.third()!=null){
								if(monto.third()<pnc){
									monto.setThird(pnc);
								}				
							}else{
								monto.setThird(pnc);
							}
							if(monto.fourth()!=null){
								if(monto.fourth()>pnc){
									monto.setFourth(pnc);
								}							
							}else{
								monto.setFourth(pnc);
							}
						}
					}
				}
				if(c.size()!=0 && sum!=null && 	cantidad!=0){
					promedio = sum / cantidad;
					monto.setFirst(sum);
					monto.setSecond(promedio);
				}
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return monto;
	}

	// -------------------------------------------------------------------------------------------------- //
	
	@Override
	public Long piezas_recursadas(Date desde, Date hasta) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long cantidad = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection entidad = (Collection) q0.execute();
			String filtro = "entidad.contains(pedido.reclamo.registrante) && fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && pedido.reclamo.fecha_turno!=null && fecha_cambio!=null && pedido.reclamo.orden.recurso.fecha_recurso!=null && fecha_solicitud_devolucion==null";
			if(desde==null){
				if(hasta!=null){ //desde sin fecha hasta con fecha
					filtro += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date hasta");
					q1.setResult("count(this)");
					cantidad = (Long) q1.execute(entidad,hasta);
				}else{ //desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection entidad");
					q1.setResult("count(this)");
					cantidad = (Long) q1.execute(entidad);
				}
			}else{
				if(hasta!=null){ //desde con fecha hasta con fecha
					filtro += " && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde, Date hasta");
					q1.setResult("count(this)");
					cantidad = (Long)q1.execute(entidad,desde,hasta);
				}else{ //desde con fecha hasta sin fecha
					filtro += " && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde");
					q1.setResult("count(this)");
					cantidad = (Long)q1.execute(entidad,desde);
				}
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return cantidad;
	}

	@Override
	public Vector<Pedido_PiezaDTO> obtener_piezas_recursadas(Date desde, Date hasta) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection entidad = (Collection) q0.execute();
			String filtro = "entidad.contains(pedido.reclamo.registrante) && fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && pedido.reclamo.fecha_turno!=null && fecha_cambio!=null && pedido.reclamo.orden.recurso!=null && fecha_solicitud_devolucion==null";
			Vector<Pedido_Pieza> pedidos_Pieza = new Vector<Pedido_Pieza>();
			if(desde==null){
				if(hasta!=null){ //desde sin fecha hasta con fecha
					filtro += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date hasta");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(entidad,hasta));
				}else{ //desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection entidad");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(entidad));
				}
			}else{
				if(hasta!=null){ //desde con fecha hasta con fecha
					filtro += " && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection; import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date desde, Date hasta");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(entidad,desde,hasta));
				}else{ //desde con fecha hasta sin fecha
					filtro += " && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(entidad,desde));
				}
			}
			Pedido_PiezaAssembler pedido_PiezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			for (int i = 0; i < pedidos_Pieza.size(); i++) {
				Pedido_PiezaDTO pedido_PiezaDTO = pedido_PiezaAssemb.getPedido_PiezaDTO(pedidos_Pieza.elementAt(i));
				pedidos_PiezaDTO.add(pedido_PiezaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}
	
	@Override
	public Triple<Double,Double,Double> anticuacion_recursadas(Date desde, Date hasta) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Triple<Double,Double,Double> anticuacion = new Triple<Double,Double,Double>(null, null, null);
		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection entidad = (Collection) q0.execute();
			String filtro = "entidad.contains(pedido.reclamo.registrante) && fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && pedido.reclamo.fecha_turno!=null && fecha_cambio!=null && pedido.reclamo.orden.recurso.fecha_recurso!=null && fecha_solicitud_devolucion==null";
			Collection c = null;
			if(desde==null){//desde sin fecha hasta con fecha
				if(hasta!=null){
					filtro += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date hasta");
					c = (Collection) q1.execute(entidad,hasta);
				}else{//desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection entidad");
					c = (Collection) q1.execute(entidad);
				}
			}else{
				if(hasta!=null){
					filtro += " && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection; import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date desde, Date hasta");
					c = (Collection) q1.execute(entidad,desde,hasta);
				}else{
					filtro += " && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde");
					c = (Collection) q1.execute(entidad,desde);
				}
			}
			if(c!=null){
				Iterator iter = c.iterator();
				Double sum = null;
				Double promedio = new Double(0);
				Double cantidad = new Double(0);
				SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
				java.util.Date fechaHoy = new java.util.Date();
				String fecha = format2.format(fechaHoy);
			    java.sql.Date hoy = new java.sql.Date(fechaHoy.getTime());
				while (iter.hasNext()){
					Pedido_Pieza current = (Pedido_Pieza)iter.next();
					Double diferencia = new Double(0);
					cantidad+=1;
					diferencia = new Double ((hoy.getTime() - current.getFecha_solicitud_fabrica().getTime())/MILLSECS_PER_DAY);
					if(diferencia>=0){
						if(sum!=null)
							sum+=diferencia;
						else
							sum=diferencia;
						if(anticuacion.second()!=null){
							if(anticuacion.second()<diferencia){
								anticuacion.setSecond(diferencia);
							}
						}else{
							anticuacion.setSecond(diferencia);
						}
						if(anticuacion.third()!=null){
							if(anticuacion.third()>diferencia){
								anticuacion.setThird(diferencia);
							}							
						}else{
							anticuacion.setThird(diferencia);
						}
					}
				}
				if(c.size()!=0 && sum!=null && cantidad!=0){
					promedio = sum / cantidad;
					anticuacion.setFirst(promedio);
				}
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return anticuacion;
	}

	@Override
	public Cuadruple<Double,Double,Double,Double> monto_recursadas(Date desde, Date hasta) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Cuadruple<Double,Double,Double,Double> monto = new Cuadruple<Double,Double,Double,Double>(null, null, null, null);
		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection entidad = (Collection) q0.execute();
			String filtro = "entidad.contains(pedido.reclamo.registrante) && fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && pedido.reclamo.fecha_turno!=null && fecha_cambio!=null && pedido.reclamo.orden.recurso.fecha_recurso!=null && fecha_solicitud_devolucion==null";
			Collection c = null;
			if(desde==null){//desde sin fecha hasta con fecha
				if(hasta!=null){
					filtro += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date hasta");
					c = (Collection) q1.execute(entidad,hasta);
				}else{//desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection entidad");
					c = (Collection) q1.execute(entidad);
				}
			}else{
				if(hasta!=null){
					filtro += " && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection; import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date desde, Date hasta");
					c = (Collection) q1.execute(entidad,desde,hasta);
				}else{
					filtro += " && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde");
					c = (Collection) q1.execute(entidad,desde);
				}
			}
			if(c!=null){
				Iterator iter = c.iterator();
				Double sum = null;
				Double promedio = new Double(0);
				Double cantidad = new Double(0);
				SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
				java.util.Date fechaHoy = new java.util.Date();
				String fecha = format2.format(fechaHoy);
			    java.sql.Date hoy = new java.sql.Date(fechaHoy.getTime());
				while (iter.hasNext()){
					Pedido_Pieza current = (Pedido_Pieza)iter.next();
					if(current.getPnc()!=null){
						if(!current.getPnc().isEmpty()){
							cantidad+=1;
							Double pnc = new Double(current.getPnc());
							if(sum!=null)
								sum+=pnc;
							else
								sum=pnc;
							if(monto.third()!=null){
								if(monto.third()<pnc){
									monto.setThird(pnc);
								}				
							}else{
								monto.setThird(pnc);
							}
							if(monto.fourth()!=null){
								if(monto.fourth()>pnc){
									monto.setFourth(pnc);
								}							
							}else{
								monto.setFourth(pnc);
							}
						}
					}
				}
				if(c.size()!=0 && sum!=null && 	cantidad!=0){
					promedio = sum / cantidad;
					monto.setFirst(sum);
					monto.setSecond(promedio);
				}
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return monto;
	}

	// -------------------------------------------------------------------------------------------------- //
	
	@Override
	public Long piezas_con_sdevolucion(Date desde, Date hasta) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long cantidad = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection entidad = (Collection) q0.execute();
			String filtro = "entidad.contains(pedido.reclamo.registrante) && fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && pedido.reclamo.fecha_turno!=null && fecha_cambio!=null && pedido.reclamo.orden.recurso.fecha_recurso!=null && fecha_solicitud_devolucion!=null && fecha_aprobacion_solicitud_devolucion==null";
			if(desde==null){
				if(hasta!=null){ //desde sin fecha hasta con fecha
					filtro += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date hasta");
					q1.setResult("count(this)");
					cantidad = (Long) q1.execute(entidad,hasta);
				}else{ //desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection entidad");
					q1.setResult("count(this)");
					cantidad = (Long) q1.execute(entidad);
				}
			}else{
				if(hasta!=null){ //desde con fecha hasta con fecha
					filtro += " && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde, Date hasta");
					q1.setResult("count(this)");
					cantidad = (Long)q1.execute(entidad,desde,hasta);
				}else{ //desde con fecha hasta sin fecha
					filtro += " && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde");
					q1.setResult("count(this)");
					cantidad = (Long)q1.execute(entidad,desde);
				}
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return cantidad;
	}

	@Override
	public Vector<Pedido_PiezaDTO> obtener_piezas_con_sdevolucion(Date desde, Date hasta) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection entidad = (Collection) q0.execute();
			String filtro = "entidad.contains(pedido.reclamo.registrante) && fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && pedido.reclamo.fecha_turno!=null && fecha_cambio!=null && pedido.reclamo.orden.recurso.fecha_recurso!=null && fecha_solicitud_devolucion!=null && fecha_aprobacion_solicitud_devolucion==null";
			Vector<Pedido_Pieza> pedidos_Pieza = new Vector<Pedido_Pieza>();
			if(desde==null){
				if(hasta!=null){ //desde sin fecha hasta con fecha
					filtro += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date hasta");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(entidad,hasta));
				}else{ //desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection entidad");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(entidad));
				}
			}else{
				if(hasta!=null){ //desde con fecha hasta con fecha
					filtro += " && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection; import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date desde, Date hasta");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(entidad,desde,hasta));
				}else{ //desde con fecha hasta sin fecha
					filtro += " && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(entidad,desde));
				}
			}
			Pedido_PiezaAssembler pedido_PiezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			for (int i = 0; i < pedidos_Pieza.size(); i++) {
				Pedido_PiezaDTO pedido_PiezaDTO = pedido_PiezaAssemb.getPedido_PiezaDTO(pedidos_Pieza.elementAt(i));
				pedidos_PiezaDTO.add(pedido_PiezaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}
	
	@Override
	public Triple<Double,Double,Double> anticuacion_con_sdevolucion(Date desde, Date hasta) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Triple<Double,Double,Double> anticuacion = new Triple<Double,Double,Double>(null, null, null);
		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection entidad = (Collection) q0.execute();
			String filtro = "entidad.contains(pedido.reclamo.registrante) && fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && pedido.reclamo.fecha_turno!=null && fecha_cambio!=null && pedido.reclamo.orden.recurso.fecha_recurso!=null && fecha_solicitud_devolucion!=null && fecha_aprobacion_solicitud_devolucion==null";
			Collection c = null;
			if(desde==null){//desde sin fecha hasta con fecha
				if(hasta!=null){
					filtro += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date hasta");
					c = (Collection) q1.execute(entidad,hasta);
				}else{//desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection entidad");
					c = (Collection) q1.execute(entidad);
				}
			}else{
				if(hasta!=null){
					filtro += " && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection; import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date desde, Date hasta");
					c = (Collection) q1.execute(entidad,desde,hasta);
				}else{
					filtro += " && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde");
					c = (Collection) q1.execute(entidad,desde);
				}
			}
			if(c!=null){
				Iterator iter = c.iterator();
				Double sum = null;
				Double promedio = new Double(0);
				Double cantidad = new Double(0);
				SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
				java.util.Date fechaHoy = new java.util.Date();
				String fecha = format2.format(fechaHoy);
			    java.sql.Date hoy = new java.sql.Date(fechaHoy.getTime());
				while (iter.hasNext()){
					Pedido_Pieza current = (Pedido_Pieza)iter.next();
					Double diferencia = new Double(0);
					cantidad+=1;
					diferencia = new Double ((hoy.getTime() - current.getFecha_solicitud_fabrica().getTime())/MILLSECS_PER_DAY);
					if(diferencia>=0){
						if(sum!=null)
							sum+=diferencia;
						else
							sum=diferencia;
						if(anticuacion.second()!=null){
							if(anticuacion.second()<diferencia){
								anticuacion.setSecond(diferencia);
							}
						}else{
							anticuacion.setSecond(diferencia);
						}
						if(anticuacion.third()!=null){
							if(anticuacion.third()>diferencia){
								anticuacion.setThird(diferencia);
							}							
						}else{
							anticuacion.setThird(diferencia);
						}
					}
				}
				if(c.size()!=0 && sum!=null && cantidad!=0){
					promedio = sum / cantidad;
					anticuacion.setFirst(promedio);
				}
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return anticuacion;
	}

	@Override
	public Cuadruple<Double,Double,Double,Double> monto_con_sdevolucion(Date desde, Date hasta) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Cuadruple<Double,Double,Double,Double> monto = new Cuadruple<Double,Double,Double,Double>(null, null, null, null);
		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection entidad = (Collection) q0.execute();
			String filtro = "entidad.contains(pedido.reclamo.registrante) && fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && pedido.reclamo.fecha_turno!=null && fecha_cambio!=null && pedido.reclamo.orden.recurso.fecha_recurso!=null && fecha_solicitud_devolucion!=null && fecha_aprobacion_solicitud_devolucion==null";
			Collection c = null;
			if(desde==null){//desde sin fecha hasta con fecha
				if(hasta!=null){
					filtro += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date hasta");
					c = (Collection) q1.execute(entidad,hasta);
				}else{//desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection entidad");
					c = (Collection) q1.execute(entidad);
				}
			}else{
				if(hasta!=null){
					filtro += " && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection; import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date desde, Date hasta");
					c = (Collection) q1.execute(entidad,desde,hasta);
				}else{
					filtro += " && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde");
					c = (Collection) q1.execute(entidad,desde);
				}
			}
			if(c!=null){
				Iterator iter = c.iterator();
				Double sum = null;
				Double promedio = new Double(0);
				Double cantidad = new Double(0);
				SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
				java.util.Date fechaHoy = new java.util.Date();
				String fecha = format2.format(fechaHoy);
			    java.sql.Date hoy = new java.sql.Date(fechaHoy.getTime());
				while (iter.hasNext()){
					Pedido_Pieza current = (Pedido_Pieza)iter.next();
					if(current.getPnc()!=null){
						if(!current.getPnc().isEmpty()){
							cantidad+=1;
							Double pnc = new Double(current.getPnc());
							if(sum!=null)
								sum+=pnc;
							else
								sum=pnc;
							if(monto.third()!=null){
								if(monto.third()<pnc){
									monto.setThird(pnc);
								}				
							}else{
								monto.setThird(pnc);
							}
							if(monto.fourth()!=null){
								if(monto.fourth()>pnc){
									monto.setFourth(pnc);
								}							
							}else{
								monto.setFourth(pnc);
							}
						}
					}
				}
				if(c.size()!=0 && sum!=null && 	cantidad!=0){
					promedio = sum / cantidad;
					monto.setFirst(sum);
					monto.setSecond(promedio);
				}
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return monto;
	}

	// -------------------------------------------------------------------------------------------------- //
	
	@Override
	public Long piezas_aprobada_devolucion(Date desde, Date hasta) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long cantidad = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection entidad = (Collection) q0.execute();
			String filtro = "entidad.contains(pedido.reclamo.registrante) && fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && pedido.reclamo.fecha_turno!=null && fecha_cambio!=null && pedido.reclamo.orden.recurso.fecha_recurso!=null && fecha_solicitud_devolucion!=null && fecha_aprobacion_solicitud_devolucion!=null && devolucion_pieza==null";
			if(desde==null){
				if(hasta!=null){ //desde sin fecha hasta con fecha
					filtro += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date hasta");
					q1.setResult("count(this)");
					cantidad = (Long) q1.execute(entidad,hasta);
				}else{ //desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection entidad");
					q1.setResult("count(this)");
					cantidad = (Long) q1.execute(entidad);
				}
			}else{
				if(hasta!=null){ //desde con fecha hasta con fecha
					filtro += " && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde, Date hasta");
					q1.setResult("count(this)");
					cantidad = (Long)q1.execute(entidad,desde,hasta);
				}else{ //desde con fecha hasta sin fecha
					filtro += " && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde");
					q1.setResult("count(this)");
					cantidad = (Long)q1.execute(entidad,desde);
				}
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return cantidad;
	}

	@Override
	public Vector<Pedido_PiezaDTO> obtener_piezas_aprobada_devolucion(Date desde, Date hasta) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection entidad = (Collection) q0.execute();
			String filtro = "entidad.contains(pedido.reclamo.registrante) && fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && pedido.reclamo.fecha_turno!=null && fecha_cambio!=null && pedido.reclamo.orden.recurso.fecha_recurso!=null && fecha_solicitud_devolucion!=null && fecha_aprobacion_solicitud_devolucion!=null && devolucion_pieza==null";
			Vector<Pedido_Pieza> pedidos_Pieza = new Vector<Pedido_Pieza>();
			if(desde==null){
				if(hasta!=null){ //desde sin fecha hasta con fecha
					filtro += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date hasta");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(entidad,hasta));
				}else{ //desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection entidad");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(entidad));
				}
			}else{
				if(hasta!=null){ //desde con fecha hasta con fecha
					filtro += " && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection; import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date desde, Date hasta");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(entidad,desde,hasta));
				}else{ //desde con fecha hasta sin fecha
					filtro += " && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(entidad,desde));
				}
			}
			Pedido_PiezaAssembler pedido_PiezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			for (int i = 0; i < pedidos_Pieza.size(); i++) {
				Pedido_PiezaDTO pedido_PiezaDTO = pedido_PiezaAssemb.getPedido_PiezaDTO(pedidos_Pieza.elementAt(i));
				pedidos_PiezaDTO.add(pedido_PiezaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}
	
	@Override
	public Triple<Double,Double,Double> anticuacion_aprobada_devolucion(Date desde,	Date hasta) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Triple<Double,Double,Double> anticuacion = new Triple<Double,Double,Double>(null, null, null);
		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection entidad = (Collection) q0.execute();
			String filtro = "entidad.contains(pedido.reclamo.registrante) && fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && pedido.reclamo.fecha_turno!=null && fecha_cambio!=null && pedido.reclamo.orden.recurso.fecha_recurso!=null && fecha_solicitud_devolucion!=null && fecha_aprobacion_solicitud_devolucion!=null && devolucion_pieza==null";
			Collection c = null;
			if(desde==null){//desde sin fecha hasta con fecha
				if(hasta!=null){
					filtro += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date hasta");
					c = (Collection) q1.execute(entidad,hasta);
				}else{//desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection entidad");
					c = (Collection) q1.execute(entidad);
				}
			}else{
				if(hasta!=null){
					filtro += " && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection; import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date desde, Date hasta");
					c = (Collection) q1.execute(entidad,desde,hasta);
				}else{
					filtro += " && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde");
					c = (Collection) q1.execute(entidad,desde);
				}
			}
			if(c!=null){
				Iterator iter = c.iterator();
				Double sum = null;
				Double promedio = new Double(0);
				Double cantidad = new Double(0);
				SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
				java.util.Date fechaHoy = new java.util.Date();
				String fecha = format2.format(fechaHoy);
			    java.sql.Date hoy = new java.sql.Date(fechaHoy.getTime());
				while (iter.hasNext()){
					Pedido_Pieza current = (Pedido_Pieza)iter.next();
					Double diferencia = new Double(0);
					cantidad+=1;
					diferencia = new Double ((hoy.getTime() - current.getFecha_solicitud_fabrica().getTime())/MILLSECS_PER_DAY);
					if(diferencia>=0){
						if(sum!=null)
							sum+=diferencia;
						else
							sum=diferencia;
						if(anticuacion.second()!=null){
							if(anticuacion.second()<diferencia){
								anticuacion.setSecond(diferencia);
							}
						}else{
							anticuacion.setSecond(diferencia);
						}
						if(anticuacion.third()!=null){
							if(anticuacion.third()>diferencia){
								anticuacion.setThird(diferencia);
							}							
						}else{
							anticuacion.setThird(diferencia);
						}
					}
				}
				if(c.size()!=0 && sum!=null && cantidad!=0){
					promedio = sum / cantidad;
					anticuacion.setFirst(promedio);
				}
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return anticuacion;
	}

	@Override
	public Cuadruple<Double,Double,Double,Double> monto_aprobada_devolucion(Date desde, Date hasta) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Cuadruple<Double,Double,Double,Double> monto = new Cuadruple<Double,Double,Double,Double>(null, null, null, null);
		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection entidad = (Collection) q0.execute();
			String filtro = "entidad.contains(pedido.reclamo.registrante) && fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && pedido.reclamo.fecha_turno!=null && fecha_cambio!=null && pedido.reclamo.orden.recurso.fecha_recurso!=null && fecha_solicitud_devolucion!=null && fecha_aprobacion_solicitud_devolucion!=null && devolucion_pieza==null";
			Collection c = null;
			if(desde==null){//desde sin fecha hasta con fecha
				if(hasta!=null){
					filtro += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date hasta");
					c = (Collection) q1.execute(entidad,hasta);
				}else{//desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection entidad");
					c = (Collection) q1.execute(entidad);
				}
			}else{
				if(hasta!=null){
					filtro += " && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection; import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date desde, Date hasta");
					c = (Collection) q1.execute(entidad,desde,hasta);
				}else{
					filtro += " && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde");
					c = (Collection) q1.execute(entidad,desde);
				}
			}
			if(c!=null){
				Iterator iter = c.iterator();
				Double sum = null;
				Double promedio = new Double(0);
				Double cantidad = new Double(0);
				SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
				java.util.Date fechaHoy = new java.util.Date();
				String fecha = format2.format(fechaHoy);
			    java.sql.Date hoy = new java.sql.Date(fechaHoy.getTime());
				while (iter.hasNext()){
					Pedido_Pieza current = (Pedido_Pieza)iter.next();
					if(current.getPnc()!=null){
						if(!current.getPnc().isEmpty()){
							cantidad+=1;
							Double pnc = new Double(current.getPnc());
							if(sum!=null)
								sum+=pnc;
							else
								sum=pnc;
							if(monto.third()!=null){
								if(monto.third()<pnc){
									monto.setThird(pnc);
								}				
							}else{
								monto.setThird(pnc);
							}
							if(monto.fourth()!=null){
								if(monto.fourth()>pnc){
									monto.setFourth(pnc);
								}							
							}else{
								monto.setFourth(pnc);
							}
						}
					}
				}
				if(c.size()!=0 && sum!=null && 	cantidad!=0){
					promedio = sum / cantidad;
					monto.setFirst(sum);
					monto.setSecond(promedio);
				}
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return monto;
	}

	// -------------------------------------------------------------------------------------------------- //
	
	@Override
	public Long piezas_devueltas(Date desde, Date hasta) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long cantidad = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection entidad = (Collection) q0.execute();
			String filtro = "entidad.contains(pedido.reclamo.registrante) && fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && pedido.reclamo.fecha_turno!=null && fecha_cambio!=null && pedido.reclamo.orden.recurso.fecha_recurso!=null && fecha_solicitud_devolucion!=null && fecha_aprobacion_solicitud_devolucion!=null && devolucion_pieza!=null";
			if(desde==null){
				if(hasta!=null){ //desde sin fecha hasta con fecha
					filtro += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date hasta");
					q1.setResult("count(this)");
					cantidad = (Long) q1.execute(entidad,hasta);
				}else{ //desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection entidad");
					q1.setResult("count(this)");
					cantidad = (Long) q1.execute(entidad);
				}
			}else{
				if(hasta!=null){ //desde con fecha hasta con fecha
					filtro += " && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde, Date hasta");
					q1.setResult("count(this)");
					cantidad = (Long)q1.execute(entidad,desde,hasta);
				}else{ //desde con fecha hasta sin fecha
					filtro += " && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde");
					q1.setResult("count(this)");
					cantidad = (Long)q1.execute(entidad,desde);
				}
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return cantidad;
	}

	@Override
	public Vector<Pedido_PiezaDTO> obtener_piezas_devueltas(Date desde,	Date hasta) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection entidad = (Collection) q0.execute();
			String filtro = "entidad.contains(pedido.reclamo.registrante) && fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && pedido.reclamo.fecha_turno!=null && fecha_cambio!=null && pedido.reclamo.orden.recurso.fecha_recurso!=null && fecha_solicitud_devolucion!=null && fecha_aprobacion_solicitud_devolucion!=null && devolucion_pieza!=null";
			Vector<Pedido_Pieza> pedidos_Pieza = new Vector<Pedido_Pieza>();
			if(desde==null){
				if(hasta!=null){ //desde sin fecha hasta con fecha
					filtro += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date hasta");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(entidad,hasta));
				}else{ //desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection entidad");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(entidad));
				}
			}else{
				if(hasta!=null){ //desde con fecha hasta con fecha
					filtro += " && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection; import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date desde, Date hasta");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(entidad,desde,hasta));
				}else{ //desde con fecha hasta sin fecha
					filtro += " && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde");
					pedidos_Pieza = new Vector<Pedido_Pieza>((Collection) q1.execute(entidad,desde));
				}
			}
			Pedido_PiezaAssembler pedido_PiezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			for (int i = 0; i < pedidos_Pieza.size(); i++) {
				Pedido_PiezaDTO pedido_PiezaDTO = pedido_PiezaAssemb.getPedido_PiezaDTO(pedidos_Pieza.elementAt(i));
				pedidos_PiezaDTO.add(pedido_PiezaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}
	
	@Override
	public Triple<Double,Double,Double> anticuacion_devueltas(Date desde, Date hasta) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Triple<Double,Double,Double> anticuacion = new Triple<Double,Double,Double>(null, null, null);
		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection entidad = (Collection) q0.execute();
			String filtro = "entidad.contains(pedido.reclamo.registrante) && fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && pedido.reclamo.fecha_turno!=null && fecha_cambio!=null && pedido.reclamo.orden.recurso.fecha_recurso!=null && fecha_solicitud_devolucion!=null && fecha_aprobacion_solicitud_devolucion!=null && devolucion_pieza!=null";
			Collection c = null;
			if(desde==null){//desde sin fecha hasta con fecha
				if(hasta!=null){
					filtro += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date hasta");
					c = (Collection) q1.execute(entidad,hasta);
				}else{//desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection entidad");
					c = (Collection) q1.execute(entidad);
				}
			}else{
				if(hasta!=null){
					filtro += " && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection; import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date desde, Date hasta");
					c = (Collection) q1.execute(entidad,desde,hasta);
				}else{
					filtro += " && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde");
					c = (Collection) q1.execute(entidad,desde);
				}
			}
			if(c!=null){
				Iterator iter = c.iterator();
				Double sum = null;
				Double promedio = new Double(0);
				Double cantidad = new Double(0);
				SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
				java.util.Date fechaHoy = new java.util.Date();
				String fecha = format2.format(fechaHoy);
			    java.sql.Date hoy = new java.sql.Date(fechaHoy.getTime());
				while (iter.hasNext()){
					Pedido_Pieza current = (Pedido_Pieza)iter.next();
					Double diferencia = new Double(0);
					cantidad+=1;
					diferencia = new Double ((hoy.getTime() - current.getFecha_solicitud_fabrica().getTime())/MILLSECS_PER_DAY);
					if(diferencia>=0){
						if(sum!=null)
							sum+=diferencia;
						else
							sum=diferencia;
						if(anticuacion.second()!=null){
							if(anticuacion.second()<diferencia){
								anticuacion.setSecond(diferencia);
							}
						}else{
							anticuacion.setSecond(diferencia);
						}
						if(anticuacion.third()!=null){
							if(anticuacion.third()>diferencia){
								anticuacion.setThird(diferencia);
							}							
						}else{
							anticuacion.setThird(diferencia);
						}
					}
				}
				if(c.size()!=0 && sum!=null && cantidad!=0){
					promedio = sum / cantidad;
					anticuacion.setFirst(promedio);
				}
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return anticuacion;
	}

	@Override
	public Cuadruple<Double,Double,Double,Double> monto_devueltas(Date desde, Date hasta)	throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Cuadruple<Double,Double,Double,Double> monto = new Cuadruple<Double,Double,Double,Double>(null, null, null, null);
		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection entidad = (Collection) q0.execute();
			String filtro = "entidad.contains(pedido.reclamo.registrante) && fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && pedido.reclamo.fecha_turno!=null && fecha_cambio!=null && pedido.reclamo.orden.recurso.fecha_recurso!=null && fecha_solicitud_devolucion!=null && fecha_aprobacion_solicitud_devolucion!=null && devolucion_pieza!=null";
			Collection c = null;
			if(desde==null){//desde sin fecha hasta con fecha
				if(hasta!=null){
					filtro += " && (devolucion_pieza==null || devolucion_pieza.fecha_devolucion <= hasta)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date hasta");
					c = (Collection) q1.execute(entidad,hasta);
				}else{//desde sin fecha hasta sin fecha
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;");
					q1.declareParameters("Collection entidad");
					c = (Collection) q1.execute(entidad);
				}
			}else{
				if(hasta!=null){
					filtro += " && fecha_solicitud_fabrica >= desde && (devolucion_pieza.fecha_devolucion <= hasta || devolucion_pieza == null)";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection; import java.sql.Date;");
					q1.declareParameters("Collection entidad, Date desde, Date hasta");
					c = (Collection) q1.execute(entidad,desde,hasta);
				}else{
					filtro += " && fecha_solicitud_fabrica >= desde";
					Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
					Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
					q1.declareImports("import java.util.Collection;import java.sql.Date;");
					q1.declareParameters("Collection entidad,Date desde");
					c = (Collection) q1.execute(entidad,desde);
				}
			}
			if(c!=null){
				Iterator iter = c.iterator();
				Double sum = null;
				Double promedio = new Double(0);
				Double cantidad = new Double(0);
				SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
				java.util.Date fechaHoy = new java.util.Date();
				String fecha = format2.format(fechaHoy);
			    java.sql.Date hoy = new java.sql.Date(fechaHoy.getTime());
				while (iter.hasNext()){
					Pedido_Pieza current = (Pedido_Pieza)iter.next();
					if(current.getPnc()!=null){
						if(!current.getPnc().isEmpty()){
							cantidad+=1;
							Double pnc = new Double(current.getPnc());
							if(sum!=null)
								sum+=pnc;
							else
								sum=pnc;
							if(monto.third()!=null){
								if(monto.third()<pnc){
									monto.setThird(pnc);
								}				
							}else{
								monto.setThird(pnc);
							}
							if(monto.fourth()!=null){
								if(monto.fourth()>pnc){
									monto.setFourth(pnc);
								}							
							}else{
								monto.setFourth(pnc);
							}
						}
					}
				}
				if(c.size()!=0 && sum!=null && 	cantidad!=0){
					promedio = sum / cantidad;
					monto.setFirst(sum);
					monto.setSecond(promedio);
				}
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return monto;
	}

	
	@Override
	public Vector<Pedido_PiezaDTO> pedidos_piezas_reclamos_fabrica() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "fecha_solicitud_fabrica!=null";
			Vector<Pedido_Pieza> pedidos_Pieza = new Vector<Pedido_Pieza>();
			
			Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
			Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro);
			
			Collection c = (Collection) q1.execute();
			Iterator iter = c.iterator();
			
			Pedido_PiezaAssembler pedido_PiezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			while (iter.hasNext()){
				Pedido_PiezaDTO pedido_PiezaDTO = pedido_PiezaAssemb.getPedido_PiezaDTO((Pedido_Pieza)iter.next());
				pedidos_PiezaDTO.add(pedido_PiezaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}

	@Override
	public Vector<Pedido_PiezaDTO> pedidos_piezas_reclamos_agente() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			Extent e0 = accesoBD.getPersistencia().getExtent(Agente.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection agentes = (Collection) q0.execute();
			String filtro_ = "agentes.contains(pedido.reclamo.registrante) && fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && fecha_envio_agente!=null";
			
			Extent e1 = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, true);
			Query q1 = accesoBD.getPersistencia().newQuery(e1, filtro_);
			q1.declareImports("import java.util.Collection;");
			q1.declareParameters("Collection agentes");
			Collection c = (Collection) q1.execute(agentes);

			Iterator iter = c.iterator();
			
			Pedido_PiezaAssembler pedido_PiezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			while (iter.hasNext()){
				Pedido_PiezaDTO pedido_PiezaDTO = pedido_PiezaAssemb.getPedido_PiezaDTO((Pedido_Pieza)iter.next());
				pedidos_PiezaDTO.add(pedido_PiezaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}

	// -------------------------------------------------------------------------------------------------- //
}
