package cn.csust.lingyi;

import cn.csust.lingyi.bo.*;
import cn.csust.lingyi.common.VO.PageResult;
import cn.csust.lingyi.common.VO.ResultVo;
import cn.csust.lingyi.common.utils.Utils;
import cn.csust.lingyi.mapper.CourseMapper;
import cn.csust.lingyi.mapper.StudentMapper;
import cn.csust.lingyi.mapper.ViewScoreAnalysesMapper;
import cn.csust.lingyi.pojo.*;
import cn.csust.lingyi.service.ClassesService;
import cn.csust.lingyi.service.DashboardService;
import cn.csust.lingyi.service.GroupService;
import cn.csust.lingyi.service.PersonalService;
import cn.csust.lingyi.service.impl.ClassesServiceImpl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.mapper.entity.Example;

import javax.xml.ws.http.HTTPException;
import java.beans.Transient;
import java.net.HttpURLConnection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@SpringBootTest(classes = AnalysisApplication.class)
class AnalysisApplicationTests {
	@Autowired
	StudentMapper studentMapper;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	DashboardService dashboardService;

	@Autowired
	CourseMapper courseMapper;

	@Autowired
	PersonalService personalService;

	@Autowired
	ClassesService classesService;

	@Autowired
	GroupService groupService;

	@Autowired
	ViewScoreAnalysesMapper viewScoreAnalysesMapper;

	@Test
	void contextLoads() {
		Student student = new Student();
//		student.setStutype("本科生");
		int student1 = this.studentMapper.selectCount(student);
		student.setStutype("本科生");
		int student2 = this.studentMapper.selectCount(student);

		student.setSex("男");
		int student3 = this.studentMapper.selectCount(student);

		student.setSex("女");
		int student4 = this.studentMapper.selectCount(student);
		student.setSex("null");
		int student5 = this.studentMapper.selectCount(student);
		System.out.println("=========");
		System.out.println(student1+"=="+student2+"=="+student3+"=="+student4+"=="+student5);
	}
	public static final String NATIONRGEX = "[^族]";
	@Test
	void testNation(){
		List<Nation> nations = this.studentMapper.tgetNationNumByTermYear();
		HashMap<String, Integer> wrongnations= new HashMap<>();
		HashMap<String,Integer> correctnations = new HashMap<>();

		nations.forEach(nation ->{
			if (nation.getMzname().matches(NATIONRGEX)){
				String corr = nation.getMzname()+"族";
				wrongnations.put(corr,nation.getMznumber());
			}else{
				correctnations.put(nation.getMzname(),nation.getMznumber());
			}
//			map.put(nation.getMzname(),nation.getMznumber());
		});
		System.out.println(wrongnations);
		System.out.println(correctnations);
		wrongnations.forEach((key,value) -> {
			correctnations.merge(key,value,Integer::sum);
		});

		System.out.println(JSON.toJSONString(correctnations));
	}

	@Test
	void testRest(){
		String s = this.restTemplate.getForObject("http://127.0.0.1:5000/addressMap", String.class);
		System.out.println(s);
	}
	@Test
	void testService(){
		List<String> address = this.studentMapper.queryAddress();
        Map<String, String> map = new HashMap<>();
		map.put("data", address.toString());
        System.out.println(map);
        ResultVo resultVo = Utils.sendPost("http://47.102.149.169:5000/addressMap", map);
        Object data = resultVo.getData();
        System.out.println(data);


//        System.out.println(resultVo);
//
//		if (resultVo.getCode() != 200){
//			throw new HTTPException(500);
//		}
//		LinkedMultiValueMap data = (LinkedMultiValueMap) resultVo.getData();
//		System.out.println(data);
	}

	public static List<Map<String,String>> addressResolution(String address){
		String regex="((?<province>[^省]+省|.+自治区)|.+上海|.+北京|.+天津|.+重庆|.+内蒙古|.+宁夏|.+广西)(?<city>[^市]+市|.+自治州)";
		Matcher m= Pattern.compile(regex).matcher(address);
		String province=null,city=null,county=null,town=null,village=null;
		List<Map<String,String>> table=new ArrayList<Map<String,String>>();
		Map<String,String> row=null;
		while(m.find()){
			row=new LinkedHashMap<String,String>();
			province=m.group("province");
			row.put("province", province==null?"":province.trim());

			table.add(row);
		}
		return table;
	}
	@Test
	void testAddr(){
//		List<String> addrt = this.studentMapper.queryAddrt();
		String[] addrt = new String[]{"内蒙古乌海市海南区","上海金山区","浙江省台州市玉环县","湖北省潜江市潜江经济开发区","湖北省潜江市江汉石油管理局","湖北省天门市马湾镇"};
		for (String place : addrt) {
			System.out.println(addressResolution(place));
		}
	}

	@Test
	void testcourse(){
//		List<Personknowledge> personknowledges = this.courseMapper.queryCourseScoresBySno("201744070130","2018-2019",100);
//		personknowledges.forEach(System.out::println);
//		System.out.println(personknowledges);
		Double aDouble = this.personalService.queryfailRatesBySnoAndXuenian("201845070124", "2018-2019");
		System.out.println(aDouble);
//		Map<String, Double> map = this.personalService.queryMKSByStu("201744070130", "2018-2019");
//		System.out.println(map);

//		this.personalService.queryDescrBySno("201844070111");

	}

	@Test
	void testClassService(){
		Double R = this.classesService.queryCET4or6PassRate("2018", "信息管理与信息系统", 1, SkillType.CET6);
		System.out.println(R);
	}

	@Test
	void querySizeByClassTest(){
//		Integer sizeByClass = this.classesService.querySexByClass(SexEnum.FEMALE,"2019", "会计",null);
//		List<Nation> map = this.classesService.queryNationByClass("2019", "会计", null);
//		List<PoliticalStatus> politicalStatuses = this.classesService.queryPoliticalStatusByClass("2018", "会计", 1);
//		List<String> strings = this.classesService.queryProvinceByClass("2018", "会计", 1);

//		List<Nation> nations = new ArrayList<>();
//		map.forEach((v,k) -> {
//			nations.add(new Nation(v,k));
//
//		});
//		PageResult<Student> pageResult = this.classesService.queryStudentByClass("2018", "会计", 1, 1, 5);
//		Integer pageResult = this.classesService.avgScoreRankOfClass("2018","2018-2019" ,"金融", 2 );
//		System.out.println(pageResult);
		//添加查询条件
//		String cloud = this.classesService.getClassDescriptionWorldCloud("2018", "信息管理与信息系统", 2);
//		System.out.println(cloud);
//		List<PoliticalStatus> pscount = this.dashboardService.pscount();
//		System.out.println(pscount);


	}

	@Test
	void groupServiceTest(){
//		List<String> sno = this.viewScoreAnalysesMapper.queryTestedSnoByClass("2018", "信息管理与信息系统", 1, "2018-2019");
//
//		List<List<Double>> lists = this.groupService.queryScoreSignalByStu(sno);
//		HashMap<String, List<List<Double>>> map = new HashMap<>();
//		map.put("data",lists);
//		ResultVo resultVo = Utils.sendPost(Utils.DATA_PROCESSING_URL + "/scoreSignal", map);
//
//		System.out.println(resultVo.getData());
//		List<String> signals = (List<String>) resultVo.getData();
//
//		Map<String, String> collect = sno.stream().collect(Collectors.toMap(key -> key, key -> signals.get(sno.indexOf(key))));
//
//		System.out.println(collect);
		List<ScoreSignal> signals = this.groupService.queryScoreSignalByClass("2018", "信息管理与信息系统", 1, "2018-2019");
		System.out.println(signals);

	}
}
