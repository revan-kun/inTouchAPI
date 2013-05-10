import java.io.IOException;

import com.epam.lab.intouch.api.service.MemberService;


public class MemberServiceTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) {
		MemberService memberService=new  MemberService();
		try {
			System.out.println(memberService.login("carlos@gmail.com", "2222"));
		} catch (IOException e) {
			System.out.println("fail");
		}

	}

}
