package fr.pizzeria.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "performance")
public class Performance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String service;
	private Date date;
	private long tempsExecution;

	public Performance() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param service
	 * @param date
	 * @param tempsExecution
	 */
	public Performance(String service, Date date, long tempsExecution) {
		this.service = service;
		this.date = date;
		this.tempsExecution = tempsExecution;
	}

	public String toString() {
        return "Performance " + id + " du service " + service + " (" + date + ") : temps d'exec => " + tempsExecution + "ms";  
	}

	/**
	 * @return the service
	 */
	public String getService() {
		return service;
	}

	/**
	 * @param service
	 *            the service to set
	 */
	public void setService(String service) {
		this.service = service;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the tempsExecution
	 */
	public long getTempsExecution() {
		return tempsExecution;
	}

	/**
	 * @param tempsExecution
	 *            the tempsExecution to set
	 */
	public void setTempsExecution(long tempsExecution) {
		this.tempsExecution = tempsExecution;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

}
