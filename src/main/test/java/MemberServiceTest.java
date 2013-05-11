import java.io.IOException;

import com.epam.lab.intouch.api.service.MemberService;

public class MemberServiceTest {

	public static void main(String[] args) throws IOException {
		MemberService memberService = new MemberService();

		// System.out.println(PropertyConfigurator.getProperty("host"));
		try {
			System.out.println(memberService.login("testmanager@gmail.com", "1111"));
		} catch (IOException e) {
			System.out.println("fail");
		}

	}

}
