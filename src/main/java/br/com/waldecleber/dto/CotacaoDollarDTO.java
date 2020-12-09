package br.com.waldecleber.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class CotacaoDollarDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal high;

	public BigDecimal getHigh() {
		return high;
	}

	public void setHigh(BigDecimal high) {
		this.high = high;
	}
}
