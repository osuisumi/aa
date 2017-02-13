/**
 * 
 */
package com.haoyu.aip.qti.entity;

/**
 * @author lianghuahuang
 *
 */
public class InteractionOption {
	private String id;
	
	private boolean fixed;
	
	private String text;

	public InteractionOption(){}
	
	public InteractionOption(String id){
		this.id = id;
	}
	
	public InteractionOption(String id, String text) {
		super();
		this.id = id;
		this.text = text;
	}

	public InteractionOption(String id, boolean fixed, String text) {
		super();
		this.id = id;
		this.fixed = fixed;
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isFixed() {
		return fixed;
	}

	public void setFixed(boolean fixed) {
		this.fixed = fixed;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InteractionOption other = (InteractionOption) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
