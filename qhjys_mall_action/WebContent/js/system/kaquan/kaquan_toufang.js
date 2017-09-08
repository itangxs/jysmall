function updateZyDeliver(type) {
	var cardReceiveMin = $("#cardReceiveMin").val();
	var cardReceiveMax = $("#cardReceiveMax").val();
	var cardShareMin = $("#cardShareMin").val();
	var cardShareMax = $("#cardShareMax").val();
	var cardShareReceiveMin = $("#cardShareReceiveMin").val();
	var cardShareReceiveMax = $("#cardShareReceiveMax").val();
	var firstRankProbability = $("#firstRankProbability").val();
	var secondRankProbability = $("#secondRankProbability").val();
	var thirdRankProbability = $("#thirdRankProbability").val();
	var fourthRankProbability = $("#fourthRankProbability").val();
	var id = $("#queryByTypeId").val();
	var total = cardReceiveMin + cardReceiveMax + cardShareMin + cardShareMax
			+ cardShareReceiveMin + cardShareReceiveMax + firstRankProbability
			+ secondRankProbability + thirdRankProbability
			+ fourthRankProbability;

	var rankTotal = parseInt(firstRankProbability)
			+ parseInt(secondRankProbability) + parseInt(thirdRankProbability)
			+ parseInt(fourthRankProbability);
	if (total == 0) {
		alert("请输入要设定的值");
	} else if (parseInt(cardReceiveMin) > parseInt(cardReceiveMax)) {
		alert("卡券领取数范围设定有误");
	} else if (parseInt(cardShareMin) > parseInt(cardShareMax)) {
		alert("活动分享数范围设定有误");
	} else if (parseInt(cardShareReceiveMin) > parseInt(cardShareReceiveMax)) {
		alert("分享后卡券领取数范围设定有误");
	}else if (rankTotal > 100) {
		alert("四张卡券抽奖概率总和不能超过100");
	} else {
		console.log(type, id);
		$.ajax({
			type : "post",
			url : "/managermall/systemmall/cardcoupon/updateZyDeliver.do",
			data : {
				"id" : id,
				"deliverType" : type,
				"cardReceiveMin" : cardReceiveMin,
				"cardReceiveMax" : cardReceiveMax,
				"cardShareMin" : cardShareMin,
				"cardShareMax" : cardShareMax,
				"cardShareReceiveMin" : cardShareReceiveMin,
				"cardShareReceiveMax" : cardShareReceiveMax,
				"firstRankProbability" : firstRankProbability,
				"secondRankProbability" : secondRankProbability,
				"thirdRankProbability" : thirdRankProbability,
				"fourthRankProbability" : fourthRankProbability,
			},
			success : function(byType) {
				if (byType == "success") {
					window.location.reload();
				}
			},
			error : function(e) {
				console.log(e);
			}
		});
	}
}

function updateZbDeliver(type2) {
	var cardPushMin2 = $("#cardPushMin2").val();
	var cardPushMax2 = $("#cardPushMax2").val();
	var cardReceiveMin2 = $("#cardReceiveMin2").val();
	var cardReceiveMax2 = $("#cardReceiveMax2").val();
	var cardShareMin2 = $("#cardShareMin2").val();
	var cardShareMax2 = $("#cardShareMax2").val();
	var cardShareReceiveMin2 = $("#cardShareReceiveMin2").val();
	var cardShareReceiveMax2 = $("#cardShareReceiveMax2").val();
	var firstRankProbability2 = $("#firstRankProbability2").val();
	var secondRankProbability2 = $("#secondRankProbability2").val();
	var thirdRankProbability2 = $("#thirdRankProbability2").val();
	var fourthRankProbability2 = $("#fourthRankProbability2").val();
	var id2 = $("#queryByTypeId2").val();

	var total = cardPushMin2 + cardPushMax2 + cardReceiveMin2 + cardReceiveMax2
			+ cardShareReceiveMin2 + cardShareReceiveMax2 + cardShareMin2
			+ cardShareMax2 + firstRankProbability2 + secondRankProbability2
			+ thirdRankProbability2 + fourthRankProbability2;

	var rankTotal = parseInt(firstRankProbability2)
			+ parseInt(secondRankProbability2)
			+ parseInt(thirdRankProbability2)
			+ parseInt(fourthRankProbability2);
	if (total == 0) {
		alert("请输入要设定的值");
	} else if (rankTotal > 100) {
		alert("四张卡券抽奖概率总和不能超过100");
	} else if (parseInt(cardPushMin2) > parseInt(cardPushMax2)) {
		alert("卡券推送次数倍率范围设定有误");
	} else if (parseInt(cardReceiveMin2) > parseInt(cardReceiveMax2)) {
		alert("卡券领取数范围设定有误");
	} else if (parseInt(cardShareMin2) > parseInt(cardShareMax2)) {
		alert("活动分享数范围设定有误");
	} else if (parseInt(cardShareReceiveMin2) > parseInt(cardShareReceiveMax2)) {
		alert("分享后卡券领取数分为设定有误");
	} else {

		$.ajax({
			type : "post",
			url : "/managermall/systemmall/cardcoupon/updateZbDeliver.do",
			data : {
				"id" : id2,
				"deliverType" : type2,
				"cardPushMin" : cardPushMin2,
				"cardPushMax" : cardPushMax2,
				"cardReceiveMin" : cardReceiveMin2,
				"cardReceiveMax" : cardReceiveMax2,
				"cardShareMin" : cardShareMin2,
				"cardShareMax" : cardShareMax2,
				"cardShareReceiveMin" : cardShareReceiveMin2,
				"cardShareReceiveMax" : cardShareReceiveMax2,
				"firstRankProbability" : firstRankProbability2,
				"secondRankProbability" : secondRankProbability2,
				"thirdRankProbability" : thirdRankProbability2,
				"fourthRankProbability" : fourthRankProbability2,
			},
			success : function(cardType) {
				if (cardType == "success") {
					window.location.reload();
				}
			},
			error : function(e) {
				console.log(e);
			}
		});
	}
}

function updateGzDeliver(id) {
	var deliveryNum = $("#deliveryNum").val();
	var sellerId = $("#sellerId").val();
	if(deliveryNum == ""){
		alert("请输入要重置的次数");
	}else{
		$.ajax({
			type : "post",
			url : "/managermall/systemmall/cardcoupon/updateGzDeliver.do",
			data : {
				"id" : id,
				"deliveryNum" : deliveryNum,
				"sellerId" : sellerId
			},
			success : function(result) {
				if(result == "success"){
				window.location.reload();
				}else if(result == "isExist"){
					alert("商户ID不存在");
				}else{
					alert("设定失败");
				}
			}
		});
	}
}