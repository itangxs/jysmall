Global={
	balls:[01,02,03,04,05,06,07,08,09,10,
		11,12,13,14,15,16,17,18,19,20,
		21,22,23,24,25,26,27,28,29,30,
		31,32,33,34,35,36,37,38,39,40,
		41,42,43,44,45,46,47,48,49],
		
	colorMap:{'01':'red','02':'red','03':'blue','04':'blue',
				'05':'green','06':'green','07':'red','08':'red',
				'09':'blue','10':'blue','11':'green','12':'red',
				'13':'red','14':'blue','15':'blue','16':'green',
				'17':'green','18':'red','19':'red','20':'blue',
				'21':'green','22':'green','23':'red','24':'red',
				'25':'blue','26':'blue','27':'green','28':'green',
				'29':'red','30':'red','31':'blue','32':'green',
				'33':'green','34':'red','35':'red','36':'blue',
				'37':'blue','38':'green','39':'green','40':'red',
				'41':'blue','42':'blue','43':'green','44':'green',
				'45':'red','46':'red','47':'blue','48':'blue',
				'49':'green',
				
				"56":"red","57": "blue","58":"green",
				"97":"red","98": "red","99": "red","100": "red",
				"101": "blue","102": "blue","103": "blue","104": "blue",
				"105":"green","106": "green","107": "green","108": "green"
			},
			
	getColor : function(ball) {
//		return ( {
//			'01':'red','02':'red','03':'blue','04':'blue',
//			'05':'green','06':'green','07':'red','08':'red',
//			'09':'blue','10':'blue','11':'green','12':'red',
//			'13':'red','14':'blue','15':'blue','16':'green',
//			'17':'green','18':'red','19':'red','20':'blue',
//			'21':'green','22':'green','23':'red','24':'red',
//			'25':'blue','26':'blue','27':'green','28':'green',
//			'29':'red','30':'red','31':'blue','32':'green',
//			'33':'green','34':'red','35':'red','36':'blue',
//			'37':'blue','38':'green','39':'green','40':'red',
//			'41':'blue','42':'blue','43':'green','44':'green',
//			'45':'red','46':'red','47':'blue','48':'blue',
//			'49':'green',
//				
//			"56":"red","57": "blue","58":"green",
//			"97":"red","98": "red","99": "red","100": "red",
//			"101": "blue","102": "blue","103": "blue","104": "blue",
//			"105":"green","106": "green","107": "green","108": "green"
//		})[ball]
				return this.colorMap[ball];
	},
			
	userType:{
		"1":"子公司",
		"2":"分公司",
		"3":"股东",
		"4":"总代",
		"5":"代理",
		"6":"会员",
		"7":"直属会员",
		"8":"子帐号",
		"9":"外调会员",
		"10":"管理员"
		         		
	}
}