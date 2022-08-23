package com.flash.app.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RechargeAccountForm {
	
	@NotEmpty(message = "ID can not be empty.")
	private String id;

	@NotEmpty(message = "Amount needed what ever you want to put.")
	private String amount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "RechargeAccountForm [id=" + id + ", amount=" + amount + "]";
	}
}