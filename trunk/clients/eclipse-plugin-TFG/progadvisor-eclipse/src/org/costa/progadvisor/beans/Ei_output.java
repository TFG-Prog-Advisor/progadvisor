package org.costa.progadvisor.beans;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.costa.progadvisor.structures.CostabsXMLFrontend;

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlRootElement(name = "ei_output")
	public class Ei_output {
		
		@XmlElement(name = "apps", type = Apps.class)
		private Apps apps;
		
		@XmlElement(name = "eiout", type = Ei_out.class)
		private Ei_out eiout;
		
		@XmlElement(name = "ei_error")
		private String ei_error;

		public String getEi_error() {
			return ei_error;
		}

		public void setEi_error(String ei_error) {
			this.ei_error = ei_error;
		}

		public Apps getApps() {
			return apps;
		}

		public void setApps(Apps apps) {
			this.apps = apps;
		}

		
		public Ei_out getEiout() {
			return eiout;
		}

		public void setEiout(Ei_out eiout) {
			this.eiout = eiout;
		}

		@Override
		public String toString() {
			return CostabsXMLFrontend.toString(this);
		}
}
