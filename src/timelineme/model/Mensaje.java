package timelineme.model;

import java.util.List;

public class Mensaje {
		private Integer idMensaje;
		private String contenido;
		private String fecha;
		private Integer idAgente;
		private Integer idEmpresa;

		public Integer getId() {
			return this.idMensaje;
		}
		
		public void setId(Integer idMensaje) {
			this.idMensaje = idMensaje;
		}
		
		
		public String getContenido() {
			return this.contenido;
		}
		
		public void setContenido(String contenido) {
			this.contenido = contenido;
		}
		
		
		
		public String getFecha() {
			return this.fecha;
		}
		
		public void setFecha(String string) {
			this.fecha = string;
		}
		
		
		public Integer getIdAgente() {
			return this.idAgente;
		}
		
		public void setIdAgente(Integer id) {
			this.idAgente = id;
		}
		
		
		
		public Integer getIdEmpresa() {
			return this.idEmpresa;
		}
		
		public void setIdEmpresa(Integer idE) {
			this.idEmpresa = idE;
		}

}
