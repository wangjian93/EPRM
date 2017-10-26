package com.ivo.core;

import java.io.Serializable;

/**
 *@author wangjian
 *@time 2017年8月31日 - 上午10:34:16
 *@description:
 */
public class ModelAtom implements Serializable {
	
	private static final long serialVersionUID = -2635049348494928423L;
	private int validFlag = 1;

	public int getValidFlag() {
		return validFlag;
	}
	public void setValidFlag(int i) {
		this.validFlag = i;
	}
	
}
