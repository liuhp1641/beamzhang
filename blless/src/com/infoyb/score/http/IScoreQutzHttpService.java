package com.cm.score.http;


public interface IScoreQutzHttpService {
	/**
	 * 公布答案算奖
	 * 
	 * @param qutzId
	 * @return
	 */
	public boolean openAnswer(String qutzId);
	
	/**
	 * 返奖
	 * 
	 * @param qutzId
	 * @return
	 */
	public boolean returnBonus(String qutzId);

}
