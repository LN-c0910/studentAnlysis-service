

import cn.csust.lingyi.AnalysisApplication;
import cn.csust.lingyi.mapper.StudentMapper;
import cn.csust.lingyi.pojo.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = AnalysisApplication.class)
class testMapper {
//	@Autowired
//	StudentMapper studentMapper;

    @Test
    void tm() {
//		Student student = new Student();
//		student.setStudytype("本科生");
//		Student student1 = this.studentMapper.selectByPrimaryKey("201744070130");
        System.out.println("11111111111111111111");
//		System.out.println(student1.getStudentname());
    }

}
