package cn.csust.lingyi.service.impl;

import cn.csust.lingyi.bo.ScoreSignal;
import cn.csust.lingyi.common.VO.ResultVo;
import cn.csust.lingyi.common.utils.Utils;
import cn.csust.lingyi.mapper.ViewScoreAnalysesMapper;
import cn.csust.lingyi.pojo.ViewScoreanalyses;
import cn.csust.lingyi.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Enzo Cotter on 2020/4/25.
 * @author linan
 */

@Service
public class GroupServiceImpl implements GroupService {




    private ViewScoreAnalysesMapper viewScoreAnalysesMapper;

    public GroupServiceImpl() {}

    @Autowired
    public GroupServiceImpl(ViewScoreAnalysesMapper viewScoreAnalysesMapper) {
        this.viewScoreAnalysesMapper = viewScoreAnalysesMapper;
    }

    /**
     * 返回成绩预警信号
     * @param vs  成绩列表
     * @return 返回学号与成绩预警信号对应关系
     */
    @SuppressWarnings("unchecked")
    private Map<String,String> scoreSignalBuilder(List<ViewScoreanalyses> vs) {
        //判断成绩列表是否为空
        if (vs == null || CollectionUtils.isEmpty(vs)){
            return null;
        }
        List<List<Double>> lists = new ArrayList<>();
        //获取学号列表
        List<String> sno = vs.stream().map(ViewScoreanalyses::getStudentno).collect(Collectors.toList());
        //封装成绩统计数据
        vs.forEach(s -> {
            List<Double> scoreList = new ArrayList<>();
            scoreList.add(0,s.getAvgscore());
            scoreList.add(1,s.getStdscore());
            scoreList.add(2, Double.valueOf(s.getMaxscore()));
            scoreList.add(3, Double.valueOf(s.getMinscore()));
            scoreList.add(4, Double.valueOf(s.getLt60()));
            scoreList.add(5, Double.valueOf(s.getGt60lt70()));
            scoreList.add(5, Double.valueOf(s.getGt70lt80()));
            scoreList.add(6, Double.valueOf(s.getGt80lt90()));
            scoreList.add(7, Double.valueOf(s.getGt90()));
            lists.add(scoreList);
        });
        if (CollectionUtils.isEmpty(lists)){
            return null;
        }
        Map<String, List<List<Double>>> map = new HashMap<>(16);
        map.put("data",lists);
        ResultVo resultVo = Utils.sendPost(Utils.DATA_PROCESSING_URL + "/scoreSignal", map);

        List<String> signals = (List<String>) resultVo.getData();
        return  sno.stream().collect(Collectors.toMap(key -> key, key -> signals.get(sno.indexOf(key))));

    }

    @Override
    public List<ScoreSignal> queryScoreSignalByStu(String sno) {

        //查询该学生的最近一次综测成绩统计
        Example example = new Example(ViewScoreanalyses.class);
        //按学年倒序
        example.setOrderByClause("xuenian DESC");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("studentno",sno);
        List<ViewScoreanalyses> viewScoreanalyses = this.viewScoreAnalysesMapper.selectByExample(example);
        //判断成绩列表是否为空
        if (CollectionUtils.isEmpty(viewScoreanalyses) | viewScoreanalyses == null){
            return null;
        }
        //获取最新一次综测成绩统计
        ViewScoreanalyses lastTimeScore = viewScoreanalyses.get(0);

        Map<String, String> scoreSignalBuilder = scoreSignalBuilder(Collections.singletonList(lastTimeScore));
        //判断查询结果是否为空
        if (scoreSignalBuilder == null){
            return null;
        }
        return scoreSignalBuilder.entrySet().stream().map(e -> new ScoreSignal(e.getKey(), e.getValue())).collect(Collectors.toList());

    }

    @Override
    public List<ScoreSignal> queryScoreSignalByClass(String termYear, String major, Integer classNo,String xn) {

        //查询某学年参与综测的学生学号
        List<ViewScoreanalyses> vs = this.viewScoreAnalysesMapper.queryTestedScoreByClass(termYear, major, classNo, xn);
        //判断学生成绩列表是否存在
        if (vs == null || CollectionUtils.isEmpty(vs)){
            return null;
        }
        Map<String, String> scoreSignalBuilder = scoreSignalBuilder(vs);
        //判断查询结果是否为空
        if (scoreSignalBuilder == null){
            return null;
        }
        return scoreSignalBuilder.entrySet().stream().map(e -> new ScoreSignal(e.getKey(), e.getValue())).collect(Collectors.toList());

    }
}
