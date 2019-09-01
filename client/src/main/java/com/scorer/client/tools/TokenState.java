package com.scorer.client.tools;

/**
 * 枚举，定义token的三种状态
 * @author running@vip.163.com
 *
 */
 public enum TokenState {  
	 /**
	  * 过期
	  */
	EXPIRED("EXPIRED"),
	/**
	 * 无效(token不合法)
	 */
	INVALID("INVALID"), 
	/**
	 * 有效的
	 */
	VALID("VALID");  
	
    private String  state;  
      
    private TokenState(String state) {  
        this.state = state;  
    }
    
    /**
     * 根据状态字符串获取token状态枚举对象
     * @param tokenState
     * @return
     */
    public static com.scorer.client.tools.TokenState getTokenState(String tokenState){
    	com.scorer.client.tools.TokenState[] states= com.scorer.client.tools.TokenState.values();
    	com.scorer.client.tools.TokenState ts=null;
    	for (com.scorer.client.tools.TokenState state : states) {
			if(state.toString().equals(tokenState)){
				ts=state;
				break;
			}
		}
    	return ts;
    }
    @Override
	public String toString() {
    	return this.state;
    }
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
    
}  
