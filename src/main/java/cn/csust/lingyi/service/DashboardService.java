package cn.csust.lingyi.service;

import cn.csust.lingyi.bo.AddressCount;
import cn.csust.lingyi.bo.PoliticalStatus;

import java.util.List;
import java.util.Map;

/**
 * Created by Enzo Cotter on 2020/3/15.
 */
public interface DashboardService {
    List<Integer> showBoard();
    List<String> listGrades();
    Map<String,Integer> getSexNumByTermYear(String year);
    Map<String,Integer> getNationNumByTermYear(String Year);
    Map<String,Integer> gradecount();
    List<PoliticalStatus> pscount();
    String stuMap();
}
