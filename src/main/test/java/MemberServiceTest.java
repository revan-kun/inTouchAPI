import java.io.IOException;

import com.epam.lab.intouch.api.service.MemberService;

public class MemberServiceTest {

	public static void main(String[] args) throws IOException {
		MemberService memberService = new MemberService();

		// System.out.println(PropertyConfigurator.getProperty("host"));
		try {
//			System.out.println(memberService.getLightweightMember("testmanager@gmail.com", "1111"));
//			System.out.println(memberService.getMiddleweightMember("testmanager@gmail.com", "1111"));
//			System.out.println(memberService.getHeavyMember("testmanager@gmail.com", "1111"));
			
			System.out.println(memberService.getLightweightMember("superDev@gmail.com", "1111"));
			System.out.println(memberService.getLightweightMember("superTester@gmail.com", "1111"));
			
		} catch (IOException e) {
			System.out.println("fail");
		}

	}

}
