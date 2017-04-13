var UserLogin = jsonrpc.UserLoginRPC;
var MenuBean = new Bean("com.cicro.wcm.bean.org.operate.MenuBean",true);	

function showList()
{
	var menu_list =  UserLogin.getMenuListByUserID("1");
	menu_list = List.toJSList(menu_list);

	alert(menu_list.size())
}

