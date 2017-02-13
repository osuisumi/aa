/**
 * 
 */
package com.haoyu.aip.debate.entity;



import com.haoyu.sip.core.entity.BaseEntity;

/**
 * 论点信息
 * @author lianghuahuang
 *
 */
public class DebateArgument  extends BaseEntity implements Comparable<DebateArgument>{
	/**
	 * 论点id
	 */
	private String id;
	
	/**
	 * 论点描述
	 */
	private String description;	
	/**
	 * 补充说明
	 */
	private String supplementExplanation;
	
	/**
	 * 论点排列顺序
	 */
	private int orderNo;
	
	/**
	 * 辩论
	 */
	private Debate debate;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getSupplementExplanation() {
		return supplementExplanation;
	}
	
	public void setSupplementExplanation(String supplementExplanation) {
		this.supplementExplanation = supplementExplanation;
	}
	
	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public Debate getDebate() {
		return debate;
	}

	public void setDebate(Debate debate) {
		this.debate = debate;
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
		DebateArgument other = (DebateArgument) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int compareTo(DebateArgument da) {
		if(orderNo>da.orderNo){
			return 1;
		}
		else if(orderNo<da.orderNo){
			return -1;
		}
		return 0;
	}
}
