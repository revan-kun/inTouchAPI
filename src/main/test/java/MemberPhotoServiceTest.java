import java.io.IOException;
import java.net.URL;

import com.epam.lab.intouch.api.service.MemberService;


public class MemberPhotoServiceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MemberService service=new MemberService();
		try {
			System.out.println(service.getPhoto("testmanager@gmail.com").available());

		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
