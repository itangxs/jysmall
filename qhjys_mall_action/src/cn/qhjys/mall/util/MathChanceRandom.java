package cn.qhjys.mall.util;
/** 
 * JAVA 返回随机数，并根据概率、比率 
 * 
 * 
 */  
public class MathChanceRandom   {  


	/**
	 * Math.random()产生一个double型的随机数，判断一下
	 * 
	 *  例如0出现的概率为%50，则介于0到0.50中间的返回0
	 * 
	 * @return int
	 * 
	 */
	public static int percentageRandom(double rate0,double rate1,double rate2,double rate3) {
		double randomNumber;
		randomNumber = Math.random();
		if (randomNumber >= 0 && randomNumber <= rate0) {
			return 0;
		} else if (randomNumber >= rate0 / 100 && randomNumber <= rate0 + rate1) {
			return 1;
		} else if (randomNumber >= rate0 + rate1 && randomNumber <= rate0 + rate1 + rate2) {
			return 2;
		} else if (randomNumber >= rate0 + rate1 + rate2 && randomNumber <= rate0 + rate1 + rate2 + rate3) {
			return 3;
		}
		return 0;
	}

	public static void main(String[] agrs) {
		for (int i = 0; i <= 100; i++)// 打印100个测试概率的准确性
		{
			int iii = MathChanceRandom.percentageRandom(0.2, 0.2, 0.5, 0.1);
			System.out.println(iii);
		}
	}
	
	
}