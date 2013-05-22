import java.io.IOException;

import com.epam.lab.intouch.api.service.ProjectService;


public class ProjectServiceTest {

	public static void main(String[] args) {
		ProjectService projectService=new ProjectService();
		try {
			System.out.println(projectService.getProgect(2L));
			System.out.println(projectService.getProgectInfo(4L));
		} catch (IOException e) {
			System.out.println("fail");
		}

	}

}
