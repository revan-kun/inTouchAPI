import java.io.IOException;

import com.epam.lab.intouch.api.service.MemberService;
import com.epam.lab.intouch.api.service.util.PropertyConfigurator;


public class MemberServiceTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		MemberService memberService=new  MemberService();
		
		PropertyConfigurator pr=new PropertyConfigurator();
		pr.setProperty("asdasd", "asdasdaaaaaaaaaaaa");
		
		PropertyConfigurator pr2=new PropertyConfigurator();
		System.out.println(pr2.getProperty("asdasd"));
		try {
			System.out.println(memberService.login("carlos@gmail.com", "2222"));
		} catch (IOException e) {
			System.out.println("fail");
		}

	}

}
